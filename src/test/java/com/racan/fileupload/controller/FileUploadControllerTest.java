package com.racan.fileupload.controller;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FileUploadControllerTest {

    @InjectMocks
    private FileUploadController fileUploadController;
    @Mock
    Model model;
    String userDir;
    File fileUploadDirectory;

    @Before
    public void setUp() {
        userDir = System.getProperty("user.dir");
        fileUploadDirectory = new File(userDir + "/upload-directory/");
    }

    @Test
    public void testUploading() {
        String expectedViewName = "upload";
        File[] files = new File[2];
        files[0] = new File(fileUploadDirectory + "/file1.txt");
        files[1] = new File(fileUploadDirectory + "/file2.txt");
        String viewName = fileUploadController.uploading(model);
        assertEquals(expectedViewName, viewName);
    }

    @Test
    public void testUploadingPost() throws IOException {
        MultipartFile[] uploadingFiles = new MultipartFile[2];
        MockMultipartFile file1 = new MockMultipartFile("file", "file1.txt", "text/plain", "File 1 contents".getBytes());
        MockMultipartFile file2 = new MockMultipartFile("file", "file2.txt", "text/plain", "File 2 contents".getBytes());
        uploadingFiles[0] = file1;
        uploadingFiles[1] = file2;
        File expectedFile1 = new File(fileUploadDirectory + "/file1.txt");
        File expectedFile2 = new File(fileUploadDirectory + "/file2.txt");
        fileUploadController.uploadingPost(uploadingFiles);
        assertEquals(true, expectedFile1.exists());
        assertEquals(true, expectedFile2.exists());
        FileUtils.cleanDirectory(fileUploadDirectory);
    }

}
