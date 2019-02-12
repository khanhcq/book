package com.karlchu.crawler.sachvui;

import com.karlchu.crawler.truyen_yy.TruyenYYCrawler;
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
public class SVXMLParser {

    public static void main(String[] args) throws IOException {


        SVXMLParser parser = new SVXMLParser();
        parser.readXML();
    }

    public static void readXML() {
        SachVuiCrawler crawler = new SachVuiCrawler();
        try {
            File fXmlFile = new File("src\\main\\java\\com\\karlchu\\crawler\\sachvui\\sachvui_sitemap.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("url");
            for (int temp = nList.getLength() - 1 ; temp > -1; temp--) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String url = eElement.getElementsByTagName("loc").item(0).getTextContent();
                    System.out.println(url);

                    String bookName = url.replace("https://sachvui.com/ebook/", "").replace(".html", "");
                    String indexFile = "D:\\CrawledFilesSV\\" + bookName + ".txt";
                    String statusFile = indexFile.replace(".txt",".done");
                    File fStatusFile = new File(statusFile);

                    if(!fStatusFile.exists()){
                        String statusVipFile = indexFile.replace(".txt",".done.vip");
                        File fStatusVipFile = new File(statusVipFile);
                        if(!fStatusVipFile.exists()){
                            Integer chapter = contChapter(indexFile);
                            Object[] crawlingResult = crawler.crawl(url, chapter, bookName);
                            Integer stopAtChapter = (Integer) crawlingResult[0];
                            Boolean isDone = (Boolean) crawlingResult[1];
                            Boolean haveVipChap = (Boolean) crawlingResult[2];

                            BufferedWriter urlCollection = new BufferedWriter(new FileWriter(indexFile, true));
                            urlCollection.write(stopAtChapter.toString());
                            urlCollection.flush();
                            urlCollection.close();
                            if(isDone) {
                                String doneFile = statusFile;
                                if (haveVipChap) {
                                    doneFile = doneFile + ".vip";
                                }
                                File f = new File(doneFile);
                                if (!f.exists()) {
                                    f.createNewFile();
                                }
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
                Integer chap = Integer.parseInt(reader.readLine());
                return chap > 0 ? chap : null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}
