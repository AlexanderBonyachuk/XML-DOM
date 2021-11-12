package com.company;

//import org.jsoup.*;
//import org.jsoup.nodes.Document;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Parser {
//    private static Document doc;

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
// DOM подход парсинг XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("C:\\Java_\\Zanyatia_\\HTMLParser\\src\\com\\company\\workspace.xml"));

        Element element = document.getDocumentElement();
        System.out.println(element.getTagName());
        printElements(element.getChildNodes(), 0);
    }

    static void printElements(NodeList nodeList, int tabs) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i) instanceof Element) {

                String value = "";
                if (!nodeList.item(i).getTextContent().trim().isEmpty() && ((Text) nodeList.item(i).getFirstChild()).getData().trim().isEmpty()
                        && !((Text) nodeList.item(i).getFirstChild()).getData().trim().equals("\n")) {
                    Text text = (Text) nodeList.item(i).getFirstChild();
                    value += "=" + text.getData().trim();
                }
                System.out.println(getTab(tabs) + nodeList.item(i).getNodeName() + value);

                System.out.println(getTab(tabs) + ((Element) nodeList.item(i)).getTagName());
                if (((Element) nodeList.item(i)).hasAttribute("name")) {
                    System.out.println(((Element) nodeList.item(i)).getAttribute("name"));
                }
                if (nodeList.item(i).hasChildNodes()) {
                    printElements(nodeList.item(i).getChildNodes(), ++tabs);
                }
            }
        }
    }

    static String getTab(int tabs) {
        String str = "";
        for (int i = 0; i < tabs; i++) {
            str += "\t";
        }
        return str;
    }
}
