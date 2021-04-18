package com.example.tunes.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FileHelper {
    public static String readFileToString(String filename) {
        try {
            return Files.lines(
                    Paths.get("src", "test", "resources", filename), StandardCharsets.UTF_8)
                    .collect(Collectors.joining());
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }
}
