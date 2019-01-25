package com.karlchu.crawler.utils;

import java.io.*;

/**
 * Created by KhanhChu on 1/15/2019.
 */
public class CrawlerUtils {
    public static StringBuilder getTxtFiles(InputStream in) {
        StringBuilder out = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    public static StringBuilder getFileContent(String path) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(path));
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder;
    }
}
