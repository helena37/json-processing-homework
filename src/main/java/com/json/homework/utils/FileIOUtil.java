package com.json.homework.utils;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileIOUtil {
    String[] readFileContent(String filepath) throws IOException;
    void write(String content, String filepath) throws IOException;
}
