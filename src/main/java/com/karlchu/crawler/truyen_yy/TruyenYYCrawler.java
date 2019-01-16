package com.karlchu.crawler.truyen_yy;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.karlchu.crawler.utils.CrawlerUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 *
 * @author Chu Quoc Khanh
 */
public class TruyenYYCrawler {

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

            File f = new File("E:\\CrawledFiles\\" + bookName);
            if (!f.exists()) {
                f.mkdirs();
            }

            if (chapter != null) {
                htmlFile = chapter;
            } else {
                //read intro page
                URL strURL = toUrl(inputUrl);
                getPageCode(strURL, bookName, bookName);
                htmlFile = 1;
//            urlCollection.write(bookName+".html = "+ strURL);
//            urlCollection.newLine();
//            urlCollection.flush();
            }
            //start first chapter
            URL firstChapURL = toUrl(inputUrl + "chuong-" + htmlFile + ".html");
            //add given url to the crawl list
            crawlList.add(firstChapURL);
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
                if(!haveVipChap){
//                    vipBtn = document.select("#btn_buy").first();
                    if (vipBtn != null) {
                        haveVipChap = true;
                    }
                }

                //add link that was crawled to crawled list
                crawledList.add(url);

                //display crawling links
                if (vipBtn != null) {
                    noVipChap++;
                    System.out.println("VIP - " + fileName + ".html = " + url);
                } else {
                    System.out.println(fileName + ".html = " + url);
                }

                //Retrive all links in the given url's page
                ArrayList linkList = retrieveLinks(url, document, crawledList);
                //add all retrieved links to crawl list
                crawlList.addAll(linkList);
                htmlFile++;
            }
//            urlCollection.close();
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[]{htmlFile, Boolean.FALSE, haveVipChap};
        }
        return new Object[]{htmlFile - 1, Boolean.TRUE, haveVipChap};
    }
    // retrive urls from a web page
    public ArrayList retrieveLinks(URL url, Document document, HashSet crawledList ) throws MalformedURLException{
        Element linkEle = document.select(".btn-primary").first();
        String link = linkEle != null ? linkEle.attr("href") : "";
        if (link.isEmpty()){
            return new ArrayList();
        }

        // list of matched links
        ArrayList linkList = new ArrayList();

        // fix link to satisfy format
        link = url.getProtocol() + "://" + url.getHost() + link;


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
        String filePath = "E:\\CrawledFiles\\"+ bookName +"\\" +pageName+".html";
        if(vipBtn != null) {
            filePath += ".vip";
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
