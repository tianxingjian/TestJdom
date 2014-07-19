package com.why.jdom;

import java.io.IOException;   
import java.util.Iterator;   
import java.util.List;   
  
import org.jdom.input.SAXBuilder;   
import org.jdom.xpath.XPath;   
import org.jdom.Document;   
import org.jdom.Element;   
import org.jdom.JDOMException;   


public class ReadXML {   

    /**  
     * @param args  
     */  
    public static void main(String[] args) {   
        SAXBuilder sax = new SAXBuilder();   
        try {   
            Document doc = sax.build("src/config.xml");   
            Element rootEle = doc.getRootElement();   
            Element driverClassNameElement = (Element)XPath.selectSingleNode(rootEle, "//sys-config/jdbc-info/driver-class-name");   
            String driverClassName = driverClassNameElement.getText();   
            System.out.println("driverClassName = " + driverClassName);   
               
            List provinceList = XPath.selectNodes(rootEle, "//sys-config/provinces-info/province");   
            for(Iterator it = provinceList.iterator();it.hasNext();){   
                Element provinceEle = (Element)it.next();   
                String proId = provinceEle.getAttributeValue("id");   
                String proName = provinceEle.getAttributeValue("name");   
  
                System.out.println("provinceId = " + proId + "   provinceName = " + proName);   
                   
                List cityEleList = (List)provinceEle.getChildren("city");   
                   
                for(Iterator cityIt = cityEleList.iterator();cityIt.hasNext();){   
                    Element cityEle = (Element)cityIt.next();   
                    String cityId = cityEle.getAttributeValue("id");   
                    String cityName = cityEle.getText();   
  
                    System.out.println("    cityId = " + cityId + "   cityName = " + cityName);   
                }   
            }   
        } catch (JDOMException e) {   
            e.printStackTrace();   
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
  
    }   
  
}  

