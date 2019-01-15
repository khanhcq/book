package com.karlchu.crawler.truyen_yy;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * Created by KhanhChu on 1/14/2019.
 */
public class XMLParser {

    public static void main(String[] args) throws IOException {


        XMLParser parser = new XMLParser();
        parser.readXML();
    }

    public static void readXML() {
        TruyenYYCrawler crawler = new TruyenYYCrawler();
        try {
            File fXmlFile = new File("src\\main\\java\\com\\karlchu\\crawler\\truyen_yy\\truyenyy_sitemap.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("url");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String url = eElement.getElementsByTagName("loc").item(0).getTextContent().replace("http://", "https://");
                    System.out.println(url);

                    String bookName = "";
                    if (url.endsWith("/")) {
                        bookName = url.substring(0, url.length() - 1);
                    }
                    bookName = bookName.replace("https://truyenyy.com/truyen/", "");
                    String indexFile = "C:\\CrawledFiles\\" + bookName + ".txt";
                    String statusFile = indexFile.replace(".txt",".done");
                    File fStatusFile = new File(statusFile);

                    if(!fStatusFile.exists()){
                        Integer chapter = contChapter(indexFile);
                        Object[] crawlingResult = crawler.crawl(url, chapter, bookName);
                        Integer stopAtChapter = (Integer) crawlingResult[0];
                        Boolean isDone = (Boolean) crawlingResult[1];
                        BufferedWriter urlCollection = new BufferedWriter(new FileWriter(indexFile, true));
                        urlCollection.write(stopAtChapter.toString());
                        urlCollection.flush();
                        urlCollection.close();
                        if(isDone) {
                            File f = new File(statusFile);
                            if (!f.exists()) {
                                f.createNewFile();
                            }
                        }
                    }

                }
            }

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Integer contChapter(String indexFile) {
        try {
            File f = new File(indexFile);
            if (!f.exists()) {
                f.createNewFile();
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(indexFile));
                String line = reader.readLine();
                return Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
