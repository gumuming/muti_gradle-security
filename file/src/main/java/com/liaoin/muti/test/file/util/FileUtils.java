package com.liaoin.muti.test.file.util;

//import org.bytedeco.javacpp.opencv_core;
//import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtils {



    /**
     * base64上传文件
     *
     *
     */
    public String base64SaveFile(Path path,String data,String type){
        try {
            String root=PathUtils.pathToPath(String.valueOf(path))+dateToString();
            String key=getUuid()+"."+type;
            if(!Files.exists(FileSystems.getDefault().getPath(root))) {
                Files.createDirectories(FileSystems.getDefault().getPath(root));
            }
            byte[] bytes=Base64.getDecoder().decode(data);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            Files.copy(new ByteArrayInputStream(bytes), Paths.get(root).resolve(key));
           // FileOutputStream fos=new FileOutputStream(new File( Paths.get(root).resolve(key).toUri()));
            return dateToString()+"/"+key;
        }catch (Exception e){
          e.printStackTrace();
        }
        return null;
    }



    public String saveFile(String path, MultipartFile file) throws IOException {
        if (path==null||file.isEmpty()) {
            return null;
        }
        String root=PathUtils.pathToPath(String.valueOf(path))+dateToString();
        String fileName = file.getOriginalFilename();
        //获取文件类型
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        //文件名
        String key=getUuid()+"."+suffix;
        if(!Files.exists(FileSystems.getDefault().getPath(root))) {
            Files.createDirectories(FileSystems.getDefault().getPath(root));
        }
        if (suffix==null) {
            return null;
        }
        FileOutputStream fos=new FileOutputStream(new File( Paths.get(root).resolve(key).toUri()));
        FileChannel out=fos.getChannel();
        InputStream is=file.getInputStream();
        int capacity = 1024;// 字节
        ByteBuffer bf = ByteBuffer.allocate(capacity);
       for (int i=0;i<is.available();i++){
       }

        return null;
    }






    /***
     * 保存文件到磁盘
     * @param path
     * @param file
     * @return
     * @throws IOException
     */


    public  String saveFile(Path path, MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return null;
            }
            String root=PathUtils.pathToPath(String.valueOf(path))+dateToString();
            //获取文件类型
            String fileType=getFileType(file);
            //文件名
            String key=getUuid()+"."+fileType;
            if(!Files.exists(FileSystems.getDefault().getPath(root))) {
                Files.createDirectories(FileSystems.getDefault().getPath(root));
            }
            if (fileType==null) {
                return null;
            }
            Files.copy(file.getInputStream(), Paths.get(root).resolve(key));
            return dateToString()+"/"+key;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取上传文件类型
     */
    public  String getFileType(MultipartFile file){
        String[] s = file.getOriginalFilename().split("\\.");
        List list = new ArrayList();
        for (String s1 : s) {
            list.add(s1);
        }
        if(list.size()>1){
            return list.get(list.size()-1).toString();
        }
        return null;
    }


    /**
     * 获取UUID作为文件名
     */
    public String getUuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }


    /**
     * date>>string
     * yyyyMMdd
     */
    public static String dateToString(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));

    }
    /**
     * 获取指定视频的帧并保存为图片至指定目录
     * @param videofile  源视频文件路径
     * @param framefile  截取帧的图片存放路径
     * @throws Exception
     */
//    public static void fetchFrame(String videofile, String framefile)
//            throws Exception {
//        long start = System.currentTimeMillis();
//        File targetFile = new File(framefile);
//        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videofile);
//        ff.start();
//        int lenght = ff.getLengthInFrames();
//        int i = 0;
//        Frame f = null;
//        while (i < lenght) {
//            // 过滤前5帧，避免出现全黑的图片，依自己情况而定
//            f = ff.grabFrame();
//            if ((i > 5) && (f.image != null)) {
//                break;
//            }
//            i++;
//        }
//        opencv_core.IplImage img = f.image;
//        int owidth = img.width();
//        int oheight = img.height();
//        // 对截取的帧进行等比例缩放
//        int width = 800;
//        int height = (int) (((double) width / owidth) * oheight);
//        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//        bi.getGraphics().drawImage(f.image.getBufferedImage().getScaledInstance(width, height, Image.SCALE_SMOOTH),
//                0, 0, null);
//        ImageIO.write(bi, "jpg", targetFile);
//        //ff.flush();
//        ff.stop();
//        System.out.println(System.currentTimeMillis() - start);
//    }




}
