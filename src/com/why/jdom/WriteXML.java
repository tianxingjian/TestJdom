package com.why.jdom;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class WriteXML {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Element rootEle = new Element("sys-config");
		Element provincesEle = new Element("provinces-info");
		
		Element provinceEle = new Element("province");
		provinceEle.setAttribute("id","hlj");
		provinceEle.setAttribute("name","����ʡ");
		
		Element cityEle1 = new Element("city");
		cityEle1.setAttribute("id","harb");
		cityEle1.addContent("�����");
		
		Element cityEle2 = new Element("city");
		cityEle2.setAttribute("id","nj");
		cityEle2.addContent("�۽�");
		
		
		provinceEle.addContent(cityEle1);
		provinceEle.addContent(cityEle2);
		provincesEle.addContent(provinceEle);
		rootEle.addContent(provincesEle);
		
		Document doc = new Document(rootEle);
		
		Format format = Format.getCompactFormat();
        format.setEncoding("UTF-8");
        format.setIndent("    "); //����4���ո����

		XMLOutputter out = new XMLOutputter(format);
		
//		out.setFormat(Format.getCompactFormat().setEncoding("GBK"));//�����ļ����룬Ĭ��ΪUTF-8
		String xmlStr = out.outputString(doc);
		System.out.println(xmlStr);
		
		try {
			out.output(doc, new FileOutputStream("c:/test.xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
