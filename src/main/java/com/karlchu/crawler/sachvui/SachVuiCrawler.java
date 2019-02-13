package com.karlchu.crawler.sachvui;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 *
 * @author Chu Quoc Khanh
 */
public class SachVuiCrawler {

    public Object[] crawl(String inputUrl, Integer chapter, String bookName){
        int htmlFile = 0;
        int noVipChap = 0;
        int vipChapThreshold = 5;
        boolean haveVipChap = false;
        try {
            //store links that crawled
            HashSet crawledList = new HashSet();
            //store links that would be crawled in order
            LinkedHashSet crawlList = new LinkedHashSet();

            //write file name corresponding to url to text file
//            BufferedWriter urlCollection = new BufferedWriter(new FileWriter(indexFile, true));

            File f = new File("D:\\CrawledFilesSV\\" + bookName);
            if (!f.exists()) {
                f.mkdirs();
            }
            boolean isEpub = false;
            String firstChap = "";
            if (chapter != null) {
                htmlFile = chapter;
            } else {
                //read intro page
                URL strURL = toUrl(inputUrl);
                Object[] objects = getIntroPageCode(strURL, bookName, bookName);
                Document document = (Document) objects[0];
                firstChap = (String) objects[1];
                isEpub = (Boolean) objects[2];
                htmlFile = 1;
                haveVipChap = isEpub;
//            urlCollection.write(bookName+".html = "+ strURL);
//            urlCollection.newLine();
//            urlCollection.flush();
            }
            if(!firstChap.isEmpty() && !isEpub){
                //start first chapter
                URL firstChapURL = toUrl(firstChap);
                //add given url to the crawl list
                crawlList.add(firstChapURL);
            }

            //define the name of crawled pages
            //crawling for all URL in the crawl list
            while (crawlList.size() > 0 && noVipChap < vipChapThreshold) {
                String fileName = Integer.toString(htmlFile);
                if (htmlFile < 10) {
                    fileName = "0" + fileName;
                }
                // get link from crawl list
                URL url = (URL) crawlList.iterator().next();
                // remove that crawling link from crawl list
                crawlList.remove(url);

                //update latest crawling file
//                urlCollection.write(fileName + ".html = " + url);
//                urlCollection.newLine();
//                urlCollection.flush();

                //get page code
                Document document = getPageCode(url, bookName, fileName);
                Element vipBtn = document.select("#btn_buy").first();
                boolean needLogin = false;
                Element mustLogin = document.select("#id_chap_content .inner.my-3").first();
                if(mustLogin != null && mustLogin.text().trim().equals("Truyện này yêu cầu đăng nhập mới được xem chương. Đến trang Đăng Nhập hoặc Đăng Ký Tài Khoản.")){
                    needLogin = true;
                }
                if(!haveVipChap){
//                    vipBtn = document.select("#btn_buy").first();
                    if (vipBtn != null || needLogin) {
                        haveVipChap = true;
                    }
                }

                //add link that was crawled to crawled list
                crawledList.add(url);

                //display crawling links
                if (vipBtn != null) {
                    noVipChap++;
                    System.out.println("VIP - " + fileName + ".html = " + url);
                } else if (needLogin){
                    noVipChap++;
                    System.out.println("LOGIN - " + fileName + ".html = " + url);
                } else if (isEpub){
                    System.out.println("EPUB - " + fileName + ".html = " + url);
                } else {
                    System.out.println(fileName + ".html = " + url);
                }

                //Retrive all links in the given url's page
                ArrayList linkList = retrieveLinks(url, document, crawledList);
                //add all retrieved links to crawl list
                crawlList.addAll(linkList);
                htmlFile++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[]{htmlFile, Boolean.FALSE, haveVipChap};
        }
        return new Object[]{htmlFile - 1, Boolean.TRUE, haveVipChap};
    }
    // retrive urls from a web page
    public ArrayList retrieveLinks(URL url, Document document, HashSet crawledList ) throws MalformedURLException{
        Element linkEle = document.select(".btn-default").last();
        String link = linkEle != null ? linkEle.parent().attr("href").trim() : "";
        if (link.isEmpty()){
            return new ArrayList();
        }

        // list of matched links
        ArrayList linkList = new ArrayList();

        // fix link to satisfy format
//        link = url.getProtocol() + "://" + url.getHost() + link;


        // Remove anchors from link.
        int index = link.indexOf('#');
        if (index != -1) {
            link = link.substring(0, index);
        }
        // Replace space in link by %20 - unicode format
        if (link.contains(" ")){
            link = link.replaceAll(" ", "%20");
        }


        // Verify link and skip if invalid.
        URL verifiedLink = toUrl(link);
        if (verifiedLink == null) {
            return linkList;
        }

        // limit links to those having the same host as the start URL
        if (!url.getHost().toLowerCase().equals(
                verifiedLink.getHost().toLowerCase())) {
            return linkList;
        }
        // Skip link if it has already been crawled.
        if (crawledList.contains(verifiedLink)) {
            return linkList;
        }
        // Add link to list.
        linkList.add(verifiedLink);
        return linkList;
    }



    //open link and get its html code
    public Object[] getIntroPageCode(URL url, String bookName, String pageName) throws IOException{
        Document document = Jsoup.connect(url.toString()).get();
        Element readOnlineBtn = document.select(".thong_tin_ebook div.col-md-8 a.btn.btn-warning.btn-md").last();
        Element getEPubBtn = document.select(".thong_tin_ebook div.col-md-8 a.btn.btn-primary").first();
        boolean isEpub = false;
        String firstChap = "";
        String filePath = "D:\\CrawledFilesSV\\"+ bookName +"\\" +pageName+".html";
        if(readOnlineBtn != null && "Đọc Online".equals(readOnlineBtn.text().trim()) && readOnlineBtn.attr("href").trim().endsWith(".html") ){
            firstChap = readOnlineBtn.attr("href").trim();
        }
        if(getEPubBtn != null && "EPUB".equals(getEPubBtn.text().trim())) {
//            filePath += ".epub";
            if(firstChap.isEmpty()){
                isEpub = true;
            }
            //TODO down EPUB file
        }
        File file = new File(filePath);
        PrintWriter writer =new PrintWriter(file);
        String out = document.html();
        writer.println(out);
        writer.close();
        return new Object[]{document, firstChap, isEpub};
    }


    //open link and get its html code
    public Document getPageCode(URL url, String bookName, String pageName) throws IOException{
//        StringBuilder out = new StringBuilder();
//            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
//            httpcon.addRequestProperty("User-Agent", "Mozilla/5.0");
//            httpcon.setConnectTimeout(10000); //set timeout to 5 seconds
//            InputStream inputStream = httpcon.getInputStream();
//            out = CrawlerUtils.getTxtFiles(inputStream);
//            out = CrawlerUtils.getTxtFiles(url.openStream());
        Document document = Jsoup.connect(url.toString()).get();
        Element vipBtn = document.select("#btn_buy").first();
        String filePath = "D:\\CrawledFilesSV\\"+ bookName +"\\" +pageName+".html";
        if(vipBtn != null) {
            filePath += ".vip";
        }
        Element mustLogin = document.select("#id_chap_content .inner.my-3").first();
        if(mustLogin != null && mustLogin.text().trim().equals("Truyện này yêu cầu đăng nhập mới được xem chương. Đến trang Đăng Nhập hoặc Đăng Ký Tài Khoản.")){
            filePath += ".login";
        }

        File file = new File(filePath);
        PrintWriter writer =new PrintWriter(file);
        String out = document.html();
        writer.println(out);
        writer.close();
        return document;
    }


    // convert string to URL
    public URL toUrl(String strUrl) throws MalformedURLException{
        URL url = null;
        if(!strUrl.toLowerCase().startsWith("https://")){
            return url;
        }
        url = new URL(strUrl);
        return  url;
    }



}
