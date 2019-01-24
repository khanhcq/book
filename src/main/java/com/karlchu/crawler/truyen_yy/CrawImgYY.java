package com.karlchu.crawler.truyen_yy;

import com.karlchu.crawler.utils.CrawlerUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CrawImgYY {
    private static final String root = "D:\\";
    public static void main (String [] args) {
        String rootDir = "D:\\CrawledFiles";
        File fRootDir = new File(rootDir);
        String bookName;
        String fileName;
        for(File file : fRootDir.listFiles()) {
            fileName = file.getName();
            if(file.isFile() && fileName.endsWith("\\.done")) {
                bookName = fileName.replaceAll("\\.done","");
                try {
                    getImg(bookName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String getImg(String bookName) throws IOException {
        String bookDir = root + bookName;
        String introChapDir = bookDir + "\\" + bookName + ".html";
        StringBuilder stringBuilder = CrawlerUtils.getFileContent(introChapDir);
        org.jsoup.nodes.Document document = Jsoup.parse(stringBuilder.toString());
        Element bookCoverE = document.select(".novel-info a.cover img").first();
        String coverURI = bookCoverE.attr("src").trim();
        coverURI = coverURI.substring(0, coverURI.lastIndexOf("?"));

        URL urlInput = new URL(coverURI);
        String ext = coverURI.substring(coverURI.lastIndexOf(".") + 1, coverURI.length());
        BufferedImage bi = ImageIO.read(urlInput);
        File outputfile = new File(root + "img\\" +bookName + "." + ext);
        ImageIO.write(bi, ext, outputfile);
        return "Done";
    }
}
