package com.liaoin.muti.test.file.fileBase64;

import com.alibaba.fastjson.JSONException;
import fr.opensagres.xdocreport.document.json.JSONArray;
import fr.opensagres.xdocreport.document.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public final class ConfigManager {

	private String originalPath;
	private String contextPath;
	private static final String configFileName = "config.json";
	private String parentPath = null;
	private JSONObject jsonConfig = null;
	// 涂鸦上传filename定义
	private static String SCRAWL_FILE_NAME = "scrawl";
	// 远程图片抓取filename定义
	private static String REMOTE_FILE_NAME = "remote";
	/*
	 * 通过一个给定的路径构建一个配置管理器， 该管理器要求地址路径所在目录下必须存在config.properties文件
	 */
	public ConfigManager(){

//		rootPath = rootPath.replace("\\", "/");
//
//		this.rootPath = rootPath;
//		this.contextPath = contextPath;
//
//		if (contextPath.length() > 0) {
//			this.originalPath = this.rootPath + uri.substring(contextPath.length());
//		} else {
//			this.originalPath = this.rootPath + uri;
//		}

//		this.initEnv();

	}

	/**
	 * 配置管理器构造工厂
	 *            当前访问的uri
	 * @return 配置管理器实例或者null
	 */
//	public static ConfigManager getInstance(String rootPath, String contextPath, String uri) {
//
//		try {
//			return new ConfigManager(rootPath, contextPath, uri);
//		} catch (Exception e) {
//			return null;
//		}
//
//	}

	// 验证配置文件加载是否正确
	public boolean valid() {
		return this.jsonConfig != null;
	}

	public JSONObject getAllConfig() {
		return this.jsonConfig;
	}

	public Map<String, Object> getConfig(int type) throws JSONException {

		Map<String, Object> conf = new HashMap<String, Object>();
		String savePath = null;
		switch (type) {
		case ActionMap.UPLOAD_FILE:
			conf.put("isBase64", "false");
			conf.put("maxSize", this.jsonConfig.getLong("fileMaxSize"));
			conf.put("allowFiles", this.getArray("fileAllowFiles"));
			conf.put("fieldName", this.jsonConfig.getString("fileFieldName"));
			conf.put("rootPath", this.jsonConfig.getString("rootPath"));
			savePath = this.jsonConfig.getString("filePathFormat");
			break;

		case ActionMap.UPLOAD_IMAGE:
			conf.put("isBase64", "false");
			conf.put("maxSize", this.jsonConfig.getLong("imageMaxSize"));
			conf.put("allowFiles", this.getArray("imageAllowFiles"));
			conf.put("fieldName", this.jsonConfig.getString("imageFieldName"));
			conf.put("rootPath", this.jsonConfig.getString("rootPath"));
			savePath = this.jsonConfig.getString("imagePathFormat");
			break;

		case ActionMap.UPLOAD_VIDEO:
			conf.put("maxSize", this.jsonConfig.getLong("videoMaxSize"));
			conf.put("allowFiles", this.getArray("videoAllowFiles"));
			conf.put("fieldName", this.jsonConfig.getString("videoFieldName"));
			conf.put("rootPath", this.jsonConfig.getString("rootPath"));
			savePath = this.jsonConfig.getString("videoPathFormat");
			break;

		case ActionMap.UPLOAD_SCRAWL:
			conf.put("filename", ConfigManager.SCRAWL_FILE_NAME);
			conf.put("maxSize", this.jsonConfig.getLong("scrawlMaxSize"));
			conf.put("fieldName", this.jsonConfig.getString("scrawlFieldName"));
			conf.put("isBase64", "true");
			conf.put("rootPath", this.jsonConfig.getString("rootPath"));
			savePath = this.jsonConfig.getString("scrawlPathFormat");
			break;

		case ActionMap.CATCH_IMAGE:
			conf.put("filename", ConfigManager.REMOTE_FILE_NAME);
			conf.put("filter", this.getArray("catcherLocalDomain"));
			conf.put("maxSize", this.jsonConfig.getLong("catcherMaxSize"));
			conf.put("allowFiles", this.getArray("catcherAllowFiles"));
			conf.put("fieldName", this.jsonConfig.getString("catcherFieldName") + "[]");
			conf.put("rootPath", this.jsonConfig.getString("rootPath"));
			savePath = this.jsonConfig.getString("catcherPathFormat");
			break;

		case ActionMap.LIST_IMAGE:
			conf.put("allowFiles", this.getArray("imageManagerAllowFiles"));
			conf.put("dir", this.jsonConfig.getString("imageManagerListPath"));
			conf.put("count", this.jsonConfig.getInt("imageManagerListSize"));
			conf.put("rootPath", this.jsonConfig.getString("rootPath"));
			break;

		case ActionMap.LIST_FILE:
			conf.put("allowFiles", this.getArray("fileManagerAllowFiles"));
			conf.put("dir", this.jsonConfig.getString("fileManagerListPath"));
			conf.put("count", this.jsonConfig.getInt("fileManagerListSize"));
			conf.put("rootPath", this.jsonConfig.getString("rootPath"));
			break;

		}
		conf.put("savePath", savePath);
		return conf;

	}

	public void initEnv(String config) throws FileNotFoundException, IOException {

		//File file = new File( this.originalPath );
	   //String path =this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile();
	  // System.out.println(path);
	  // URL url = new URL("jar:file" + path + "!config.json");
		URL url = ConfigManager.class.getClassLoader().getResource(config);
		//File file = new File(url.getFile());
		File file = new File(config);
		if (!file.isAbsolute()) {
			file = new File(file.getAbsolutePath());
		}
		this.parentPath = file.getParent();
		String configContent = this.readFile(this.getConfigPath());
		try {
			JSONObject jsonConfig = new JSONObject(configContent);
			this.jsonConfig = jsonConfig;
		} catch (Exception e) {
			log.error("读取配置文件异常>>>影响富文本上传,{}",e.getMessage());
			this.jsonConfig = null;
		}
	}
	private String getConfigPath() {
		return this.parentPath + File.separator + ConfigManager.configFileName;
	}

	private String[] getArray(String key) throws JSONException {
		JSONArray jsonArray = this.jsonConfig.getJSONArray(key);
		String[] result = new String[jsonArray.length()];
		for (int i = 0, len = jsonArray.length(); i < len; i++) {
			result[i] = jsonArray.getString(i);
		}
		return result;
	}

	private String readFile(String path) throws IOException {

		StringBuilder builder = new StringBuilder();
		try {

			InputStreamReader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
			BufferedReader bfReader = new BufferedReader(reader);
			String tmpContent = null;
			while ((tmpContent = bfReader.readLine()) != null) {
				builder.append(tmpContent);
			}
			bfReader.close();
		} catch (UnsupportedEncodingException e) {
			// 忽略
		}
		return this.filter(builder.toString());
	}
	// 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
	private String filter(String input) {

		return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");

	}

}