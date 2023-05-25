package com.racan.fileupload;

import com.racan.fileupload.controller.FileUploadController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new File(FileUploadController.uploadDirectory).mkdirs();
        SpringApplication.run(Application.class, args);
    }

}
