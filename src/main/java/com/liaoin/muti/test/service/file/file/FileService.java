package com.liaoin.muti.test.service.file.file;


import com.liaoin.muti.test.file.util.FileUtils;
import com.liaoin.muti.test.http.Response;
import com.liaoin.muti.test.service.file.file.normal.bean.Config;
import com.liaoin.muti.test.service.file.file.normal.bean.FileTips;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class FileService {

    @Autowired
    private FileUtils fileUtils;
    @Autowired
    private Config config;

    /**不支持的-文件类型*/
    private static List<String> falseType=new ArrayList<>();



    public Response uploadFile(MultipartFile file) {
      if (checkType(getType(file))) {
          return Response.failed(FileTips.TYPE_FALSE.message);
      }
      String path=fileUtils.saveFile(Paths.get(config.getFilePath()),file);
      if (path==null) {
          return Response.failed("未找到路径");
      }
      String url="http://"+config.getFileHost()+":"+config.getFilePort()+"/"+config.getFileUrl()+"/"+path;
      return Response.success(path,url);
    }
    public Response uploadFile(MultipartFile[] files) {
        List<String> path = new ArrayList<String>();
        List<String> url = new ArrayList<String>();
        for(MultipartFile file:files){
            if (checkType(getType(file))) {
                return Response.failed(FileTips.TYPE_FALSE.message);
            }
            String paths=fileUtils.saveFile(Paths.get(config.getFilePath()),file);
            path.add(paths);
            String urls="http://"+config.getFileHost()+":"+config.getFilePort()+"/"+config.getFileUrl()+"/"+path;
            url.add(urls);
        }
      return Response.success(String.join(",",url),String.join(",",path));
    }
    public Response uploadMP4(MultipartFile file){
        if (checkType(getType(file))) {
            return Response.failed(FileTips.TYPE_FALSE.message);
        }
        String path=fileUtils.saveFile(Paths.get(config.getFilePath()),file);
        if (path==null) {
            return Response.failed("未找到路径");
        }
        String uuid = FileUtils.dateToString()+"/"+fileUtils.getUuid()+".jpg";
        try {
          //  FileUtils.fetchFrame(config.getFilePath()+ "/"+path,config.getFilePath()+ "/"+uuid);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failed("服务异常");
        }
        String url="/"+config.getFileUrl()+"/"+uuid;
        return Response.success(url,"/"+config.getFileUrl()+"/"+path);
    }

    /**判断该类型是否支持上传*/
    private boolean checkType(String type){
        if (falseType.size()==0) {
            initType();
        }
        return falseType.contains(type);
    }

    /**类型初始化*/
    private void initType(){
        String[] strings=config.getFileType().split(",");
        Arrays.stream(strings).forEach(i->falseType.add(i));
    }



    /**获取文件类型*/
    private String getType(MultipartFile file){
        String fileName = file.getOriginalFilename();
        //获取文件类型
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return suffix;
    }


    public Config savefile(String base64File) {
        log.info("保存文件开始");
//        log.info(base64File);
        String suffix = "";
        if (base64File.startsWith("data:image/png")) {
            suffix = "png";
            base64File = base64File.replaceAll("data:image/png;base64,", "");
        } else if (base64File.startsWith("data:image/jpeg")) {
            suffix = "jpg";
            base64File = base64File.replaceAll("data:image/jpeg;base64,", "");
        } else if (base64File.startsWith("data:image/jepg")) {
//            log.info("test11111111111111111111111111");
            suffix = "jpg";
            base64File = base64File.replaceAll("data:image/jepg;base64,", "");
        } else if (base64File.startsWith("data:image/jpg")) {
            suffix = "jpg";
            base64File = base64File.replaceAll("data:image/jpg;base64,", "");
        }
        else if (base64File.startsWith("data:audio/mp3")) {
            suffix = "mp3";
            base64File = base64File.replaceAll("data:audio/mp3;base64,", "");
        }
        long fileSize = 0L;

        byte[] buffer = Base64.decodeBase64(base64File);
        String fileName = createBase64FileName(suffix);
        String savePath = config.getFilePath1() + fileName;
//        try {
//            FileOutputStream out = new FileOutputStream(savePath);
//            out.write(buffer);
//            fileSize = out.getChannel().size();
//            out.close();
//        } catch (IllegalStateException | IOException e1) {
//            log.info("文件保存时发生异常" + e1);
//            // throw new BusinessException("file.saveError", "文件保存出错");
//        }
        String path = fileUtils.base64SaveFile(Paths.get(config.getFilePath()), base64File, "jpg");
        log.info("文件路径:" + savePath);
        log.info("保存文件结束");

        Config fileInfo = new Config();
        fileInfo.setFileName(fileName);
        fileInfo.setFilePath(config.getFilePath() + fileName);
        fileInfo.setFileType(getFileSuffix(fileName));
        //fileInfo.setFileSize(PartyUtil.getFileSizeKb(fileSize));
        String urls="http://"+config.getFileHost()+":"+config.getFilePort()+"/"+config.getFileUrl()+"/"+path;
        fileInfo.setFileUrl(urls);
        return fileInfo;
    }


    private String getFileSuffix(String fileName) {
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!StringUtils.isEmpty(suffix)) {
            suffix = suffix.toLowerCase();
        }
        return suffix;
    }
    public Response path() {
        return Response.success("http://"+config.getFileHost()+":"+config.getFilePort()+"/"+config.getFileUrl()+"/");
    }

    private String createBase64FileName(String suffix) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String date = sdf.format(new Date());

        return date + "." + suffix;
    }
}
