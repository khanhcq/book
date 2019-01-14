package com.karlchu.crawler.truyen_yy;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by KhanhChu on 1/14/2019.
 */
public class XMLParser {

    public static void main(String[] args) throws IOException {


        XMLParser parser = new XMLParser();
        parser.readXML();
    }

    public static void readXML(){
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
                    try {
                        crawler.crawl(url);
                    } catch (IOException e) {
                        e.printStackTrace();
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
}
