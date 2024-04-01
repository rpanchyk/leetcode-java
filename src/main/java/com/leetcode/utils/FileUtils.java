package com.leetcode.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    public static String read(String file) {
        try {
            Path path = Path.of(ClassLoader.getSystemResource(file).toURI());
            byte[] bytes = Files.readAllBytes(path);
            return new String(bytes);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
