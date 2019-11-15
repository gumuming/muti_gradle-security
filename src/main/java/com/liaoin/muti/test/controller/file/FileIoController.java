package com.liaoin.muti.test.controller.file;
import com.liaoin.muti.test.service.file.file.FileService;
import com.liaoin.muti.test.service.file.file.normal.bean.Config;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@Api(description = "文件上传--到服务器本地地址 io")
@RestController
@RequestMapping
public class FileIoController {

    @Autowired
    private FileService service;


    @ApiOperation(value = "图片上传", notes = "base64图片上传接口")
    @PostMapping("file/uploadImg")
    public ResponseEntity saveImg(@RequestParam String file) {
        Config fileInfo = service.savefile(file);
        return ResponseEntity.ok(fileInfo);
    }


    @GetMapping("path")
    @ApiOperation(value = "获取地址")
    public ResponseEntity path() {
        return ResponseEntity.ok(service.path());
    }

    @PostMapping("upload")
    @ApiOperation(value = "上传文件")
    public ResponseEntity uploadFle(@RequestParam MultipartFile file) {
        return ResponseEntity.ok(service.uploadFile(file));
    }

    @PostMapping(value = "arrayUpload")
    @ApiOperation(value = "批量上传文件")
    public ResponseEntity arrayUpload(@RequestParam(name = "file", required = true) MultipartFile[] file) {
        return ResponseEntity.ok(service.uploadFile(file));
    }

    @PostMapping(value = "uploadMP4")
    @ApiOperation(value = "上传视频（返回视频开始图片）")
    public ResponseEntity uploadMP4(@RequestParam(name = "file", required = true) MultipartFile file) {
        return ResponseEntity.ok(service.uploadMP4(file));
    }







}
