package com.coswafe.odyssey.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
public class FileUtils {

    public static String getFileName(final MultipartFile uploadedLogo, final String username) {
        String extension = FilenameUtils.getExtension(uploadedLogo.getOriginalFilename());
        return System.currentTimeMillis() + "_" + username + "." + extension;
    }
}
