package com.myproject.demo.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileUtil {

    public static File convertMultipartToFile(MultipartFile file) throws FileNotFoundException, IOException {
        File convertedFile = new File(file.getOriginalFilename());
        try(FileOutputStream fos = new FileOutputStream(convertedFile))
        {
                fos.write(file.getBytes());
        }
        return  convertedFile;
    }
}
