package com.karlchu.parser.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Khanh Chu on 1/2/2019.
 */
public class HtmlParser {

    public static void main (String [] args){

        File input = new File("ch04-git-on-the-server.xhtml");
        try {
            Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
            String title = doc.title();
            String code = input.getName().substring(0, input.getName().lastIndexOf('.'));
            String code0 = Normalizer.normalize(title, Normalizer.Form.NFD)
                    .replaceAll("[^\\p{ASCII}]", "")
                    .replaceAll("\\s+", "-")
                    .replaceAll("[^-a-zA-Z0-9]", "");
            String code1 = getFirstLetter(title).toLowerCase();

//            System.out.print(doc.body().html());
            unZip();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFirstLetter(String s)
    {
        StringBuilder stringBuilder = new StringBuilder();
        Pattern p = Pattern.compile("\\b[a-zA-Z]");
        Matcher m = p.matcher(s);

        while (m.find()){
            stringBuilder.append(m.group());
        }
        return stringBuilder.toString();
    }

    public static void unZip() throws IOException {
        String fileZip = "src/main/resources/unzipTest/progit.epub";
        String folderName = fileZip.substring(fileZip.lastIndexOf('/') + 1,fileZip.lastIndexOf('.'));
        File destDir = new File("src/main/resources/unzipTest/" + folderName);
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            if(zipEntry.getName().endsWith(".xhtml")){
                File newFile = newFile(destDir, zipEntry);
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }

    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }
}
