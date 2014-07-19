package cn.com.arap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

public class ReceiveDataRead {
	
	public static void paraseXml(InputStream source){
		 SAXBuilder sax = new SAXBuilder();   
	        try {   
	            Document doc = sax.build(source); 
	            Element rootEle = doc.getRootElement();   
	            Element baseInfo = (Element)XPath.selectSingleNode(rootEle, "//data/basedata");   
	            Element billtype = baseInfo.getChild("billtype");
	               
	            Element billData = (Element)XPath.selectSingleNode(rootEle, "//data/aggVo");
	            Element head = billData.getChild("head");
	            Element bodys = billData.getChild("bodys");
	         
	        } catch (JDOMException e) {   
	            e.printStackTrace();   
	        } catch (IOException e) {   
	            e.printStackTrace();   
	        }   
	}
	
	public static void main(String args[]){
		InputStream in = null;
		try {
			in = new FileInputStream("src/recbillData.xml");
			paraseXml(in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					System.out.println("文件关闭出错！");
				}
			}
		}
	}
}
