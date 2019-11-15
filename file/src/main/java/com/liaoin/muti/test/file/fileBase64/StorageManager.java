package com.liaoin.muti.test.file.fileBase64;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Slf4j
public class StorageManager {
	public static final int BUFFER_SIZE = 8192;

	public StorageManager() {
	}

	public static State saveBinaryFile(byte[] data, String path) {
		File file = new File(path);

		State state = valid(file);

		if (!state.isSuccess()) {
			return state;
		}

		try {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(file));
			bos.write(data);
			bos.flush();
			bos.close();
		} catch (IOException ioe) {
			return new BaseState(false, AppInfo.IO_ERROR);
		}

		state = new BaseState(true, file.getAbsolutePath());
		state.putInfo( "size", data.length );
		state.putInfo( "title", file.getName() );
		return state;
	}

	public static State saveFileByInputStream(MultipartFile file, String path,
											  long maxSize) {
		State state = null;

//		File tmpFile = getTmpFile();

//		byte[] dataBuf = new byte[ 2048 ];
//		BufferedInputStream bis = new BufferedInputStream(is, StorageManager.BUFFER_SIZE);

//			BufferedOutputStream bos = new BufferedOutputStream(
//					new FileOutputStream(tmpFile), StorageManager.BUFFER_SIZE);
//
//			int count = 0;
//			while ((count = bis.read(dataBuf)) != -1) {
//				bos.write(dataBuf, 0, count);
//			}
//			bos.flush();
//			bos.close();

			if (file.getSize() > maxSize) {
				return new BaseState(false, AppInfo.MAX_SIZE);
			}

			state = saveTmpFile(file, path);

//			if (!state.isSuccess()) {
//				tmpFile.delete();
//			}

			return state;
	}

	public static State saveFileByInputStream(InputStream is, String path) {
		State state = null;

		File tmpFile = getTmpFile();

		byte[] dataBuf = new byte[ 2048 ];
		BufferedInputStream bis = new BufferedInputStream(is, StorageManager.BUFFER_SIZE);

		try {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(tmpFile), StorageManager.BUFFER_SIZE);

			int count = 0;
			while ((count = bis.read(dataBuf)) != -1) {
				bos.write(dataBuf, 0, count);
			}
			bos.flush();
			bos.close();

			state = saveTmpFile(tmpFile, path);

			if (!state.isSuccess()) {
				tmpFile.delete();
			}

			return state;
		} catch (IOException e) {
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	private static File getTmpFile() {
		File tmpDir = FileUtils.getTempDirectory();
		String tmpFileName = (Math.random() * 10000 + "").replace(".", "");
		return new File(tmpDir, tmpFileName);
	}

	private static State saveTmpFile(MultipartFile file, String path) {
		State state = null;
		File targetFile = new File(path);
		
		if(StringUtils.isNoneBlank(path)) {
			String dir = path.substring(0, path.lastIndexOf("/"));
			File fileDir = new File(dir);
			if(!fileDir.exists()) {
				fileDir.mkdirs();
			}
		}
			
		if (targetFile.canWrite()) {
			return new BaseState(false, AppInfo.PERMISSION_DENIED);
		}
		try {
//			FileUtils.moveFile(tmpFile, targetFile);
			file.transferTo(targetFile);
			targetFile.setReadable(true, false);
		} catch (IllegalStateException | IOException e1) {
			log.info("文件保存时发生异常" + e1);
			throw new RuntimeException("文件保存出错");
		}

		state = new BaseState(true);
		state.putInfo( "size", targetFile.length() );
		state.putInfo( "title", targetFile.getName() );
		
		return state;
	}

	private static State valid(File file) {
		File parentPath = file.getParentFile();

		if ((!parentPath.exists()) && (!parentPath.mkdirs())) {
			return new BaseState(false, AppInfo.FAILED_CREATE_FILE);
		}

		if (!parentPath.canWrite()) {
			return new BaseState(false, AppInfo.PERMISSION_DENIED);
		}

		return new BaseState(true);
	}
	
	private static State saveTmpFile(File tmpFile, String path) {
		State state = null;
		File targetFile = new File(path);

		if (targetFile.canWrite()) {
			return new BaseState(false, AppInfo.PERMISSION_DENIED);
		}
		try {
			FileUtils.moveFile(tmpFile, targetFile);
		} catch (IOException e) {
			return new BaseState(false, AppInfo.IO_ERROR);
		}

		state = new BaseState(true);
		state.putInfo( "size", targetFile.length() );
		state.putInfo( "title", targetFile.getName() );
		
		return state;
	}
}
