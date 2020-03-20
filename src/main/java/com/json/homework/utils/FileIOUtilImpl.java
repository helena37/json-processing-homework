package com.json.homework.utils;

import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class FileIOUtilImpl implements FileIOUtil {
    @Override
    public String[] readFileContent(String filepath) throws IOException {
        File file = new File(filepath);
        BufferedReader reader =
                new BufferedReader(
                        new FileReader(file));

        Set<String> result = new LinkedHashSet<>();
        String line;

        while ((line = reader.readLine()) != null) {
            if (!"".equals(line)) {
                result.add(line);
            }
        }
        return result.toArray(String[]::new);
    }

    @Override
    public void write(String content, String filepath) throws IOException {
        Files.write(Paths.get(filepath),
                Collections.singleton(content), StandardCharsets.UTF_8);
    }
}
