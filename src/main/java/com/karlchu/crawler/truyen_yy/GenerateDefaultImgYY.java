package com.karlchu.crawler.truyen_yy;

import com.karlchu.crawler.utils.CrawlerUtils;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class GenerateDefaultImgYY {
    private static final String root = "E:\\";
    public static void main (String [] args) {
        String rootDir = root + "CrawledFiles";
        File fRootDir = new File(rootDir);
        String bookName;
        String fileName;
        File imgDir = new File(root + "img\\");
        if (!imgDir.exists()) {
            imgDir.mkdirs();
        }

        File img2Dir = new File(root + "img2\\");
        if (!img2Dir.exists()) {
            img2Dir.mkdirs();
        }

        for(File file : fRootDir.listFiles()) {
            fileName = file.getName();
            if(file.isFile() && fileName.endsWith(".done")) {
                bookName = fileName.replaceAll("\\.done","");
                if(bookName.length() > 0){
                    try {
                        String ext = "jpg";
                        File downloadedFile = new File(root + "img\\" +bookName + "." + ext);
                        File downloadedFile2 = new File(root + "img2\\" +bookName + "." + ext);
                        if(!downloadedFile.exists() && !downloadedFile2.exists()) {
                            File imgFile = new File(root + "img2\\" +bookName + "." + ext);
                            File sampleFile = new File(root + "sampleImg\\sampleImg." + ext);
                            FileUtils.copyFile(sampleFile, imgFile);
                        }                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
