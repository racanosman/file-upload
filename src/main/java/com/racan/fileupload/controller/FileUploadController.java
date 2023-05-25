package com.racan.fileupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController {

    public static final String uploadDirectory = System.getProperty("user.dir") + "/upload-directory/";

    @RequestMapping("/")
    public String uploading(Model model) {
        File file = new File(uploadDirectory);
        model.addAttribute("files", file.listFiles());
        return "upload";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String uploadingPost(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles) throws IOException {
        for(MultipartFile uploadedFile : uploadingFiles) {
            File file = new File(uploadDirectory + uploadedFile.getOriginalFilename());
            uploadedFile.transferTo(file);
        }
        return "redirect:/";
    }

}
