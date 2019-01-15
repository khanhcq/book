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
        int htmlFile = 1;
        try {
            //store links that crawled
            HashSet crawledList = new HashSet();
            //store links that would be crawled in order
            LinkedHashSet crawlList = new LinkedHashSet();

            //write file name corresponding to url to text file
//            BufferedWriter urlCollection = new BufferedWriter(new FileWriter(indexFile, true));


            if (chapter != null) {
                htmlFile = chapter;
            } else {
                File f = new File("C:\\CrawledFiles\\" + bookName);
                if (!f.exists()) {
                    f.mkdirs();
                }
                //read intro page
                URL strURL = toUrl(inputUrl);
                getPageCode(strURL, bookName, bookName);
//            urlCollection.write(bookName+".html = "+ strURL);
//            urlCollection.newLine();
//            urlCollection.flush();
            }
            //start frist chapter
            URL firstChapURL = toUrl(inputUrl + "chuong-" + htmlFile + ".html");
            //add given url to the crawl list
            crawlList.add(firstChapURL);
            //define the name of crawled pages

            //crawling for all URL in the crawl list
            while (crawlList.size() > 0) {
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
                String pageCode = getPageCode(url, bookName, fileName);
                //add link that was crawled to crawled list
                crawledList.add(url);
                //add url to UrlCollection


                //display crawling links
                System.out.println(fileName + ".html = " + url);

                //Retrive all links in the given url's page
                ArrayList linkList = retriveLinks(url, pageCode, crawledList);
                //add all retived links to crawl list
                crawlList.addAll(linkList);
                htmlFile++;
            }
//            urlCollection.close();
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[]{htmlFile, Boolean.FALSE};
        }
        return new Object[]{htmlFile - 1, Boolean.TRUE};
    }
    // retrive urls from a web page
    public ArrayList retriveLinks(URL url, String pageCode, HashSet crawledList ) throws MalformedURLException{
        Document doc = Jsoup.parse(pageCode);
        String chapterTitle = doc.title();
        doc.body().html();
        Element vipBtn = doc.select("#btn_buy").first();
        Element linkEle = doc.select(".weui_btn_primary").first();
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
    public String getPageCode(URL url, String bookName, String pageName) throws IOException{

        File file = new File("C:\\CrawledFiles\\"+ bookName +"\\" +pageName+".html");
        PrintWriter writer =new PrintWriter(file);
        StringBuilder out = new StringBuilder();
        try{
            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
            httpcon.addRequestProperty("User-Agent", "Mozilla/4.0");
            httpcon.setConnectTimeout(10000); //set timeout to 5 seconds
            InputStream inputStream = httpcon.getInputStream();
             out = CrawlerUtils.getTxtFiles(inputStream);
        }catch (SocketTimeoutException e){
            writer.println(out.toString());
            writer.close();
            throw new IOException("timeOut");
        }
        writer.println(out.toString());
        writer.close();
        return out.toString();
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
