package com.eat.school_lunch.location;

import com.eat.school_lunch.model.Region;
import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.base.BaseHelper;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

/**
 *
 * @author DoverD
 */
public class ProcessCityRegion {
    
    public static List<String> getValue(String url, String elementName) {
        List<String> answers = new ArrayList();
        try
        {
            DocumentBuilderFactory f = 
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();
            Document doc = b.parse(url);
 
            doc.getDocumentElement().normalize();
//            System.out.println ("Root element: " + 
//                        doc.getDocumentElement().getNodeName());
       
            // loop through each item
            NodeList items = doc.getElementsByTagName("site");
            for (int i = 0; i < items.getLength(); i++)
            {
                Node n = items.item(i);
                if (n.getNodeType() != Node.ELEMENT_NODE)
                    continue;
                Element e = (Element) n;
 
                // get the "title elem" in this item (only one)
                NodeList titleList = 
                                e.getElementsByTagName(elementName);
                Element titleElem = (Element) titleList.item(0);
 
                // get the "text node" in the title (only one)
                Node titleNode = titleElem.getChildNodes().item(0);
                answers.add(titleNode.getNodeValue());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return answers;
    }
    
    public static List<Region> getAllDRegions() {
        
        List<Region> regions;
//        List<String> results = new ArrayList();
        try (BaseDBSessionBean dbUser = new BaseDBSessionBean(BaseHelper.getProp("dbForName"), BaseHelper.getProp("dbURL"),
                BaseHelper.getProp("dbUser"), BaseHelper.getProp("dbPassword"))) {

            regions = new ArrayList(LocationHelper.getAllRegions(dbUser));
        }

//        for (Region region : regions) {
//            System.out.println(region.getAbbreviation());
//            results.add(region.getAbbreviation());
//        }
        
        return regions;
    }

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";
    }
    
    private static Document loadTestDocument(String url) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        return factory.newDocumentBuilder().parse(new URL(url).openStream());
    }
}
