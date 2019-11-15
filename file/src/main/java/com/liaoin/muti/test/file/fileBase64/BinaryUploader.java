package com.liaoin.muti.test.file.fileBase64;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BinaryUploader {

	public static final State save(HttpServletRequest request,
								   Map<String, Object> conf, MultipartFile file) {
		
//		FileItemStream fileStream = null;
//		boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;

//		if (!ServletFileUpload.isMultipartContent(request)) {
//			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
//		}

//		ServletFileUpload upload = new ServletFileUpload(
//				new DiskFileItemFactory());

//        if ( isAjaxUpload ) {
//            upload.setHeaderEncoding( "UTF-8" );
//        }

		try {
//			FileItemIterator iterator = upload.getItemIterator(request);
//
//			while (iterator.hasNext()) {
//				fileStream = iterator.next();
//
//				if (!fileStream.isFormField())
//					break;
//				fileStream = null;
//			}
//
//			if (fileStream == null) {
//				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
//			}
			
			String savePath = (String) conf.get("savePath");
			String originFileName = file.getOriginalFilename();
			String suffix = FileType.getSuffixByFilename(originFileName);

			originFileName = originFileName.substring(0,
					originFileName.length() - suffix.length());
			savePath = savePath + suffix;

			long maxSize = ((Long) conf.get("maxSize")).longValue();

			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}

			savePath = PathFormat.parse(savePath, originFileName);

			String physicalPath = (String) conf.get("rootPath") + savePath;
//			String physicalPath = savePath;

//			InputStream is = fileStream.openStream();
			State storageState = StorageManager.saveFileByInputStream(file,
					physicalPath, maxSize);
//			is.close();

			if (storageState.isSuccess()) {
				//PathFormat.format(savePath)
				storageState.putInfo("url", PathFormat.format(savePath));
//				System.out.println(PathFormat.format(savePath));
//				storageState.putInfo("url", "/ueditor1_4_3-utf8-jsp/upload/video/20170901/1504229942059083074.mp4");
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}

			return storageState;
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
		} 
	}

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);
		return list.contains(type);
	}
}
