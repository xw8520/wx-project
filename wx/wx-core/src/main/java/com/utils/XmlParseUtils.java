/*
 * @(#)XmlParseUtils.java 1.0 2014-5-28 下午5:02:49
 *
 *Copyright EWAYTEC. All Rights Reserved.
 */
package com.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * @author Admin
 */
public class XmlParseUtils {

	/**
	 *
	 * @param xml
	 * @return
     */
	public static Document getDocumentByXML(String xml) {
		if (StringUtils.isEmpty(xml)) {
			return null;
		}
		Document document = null;
		try {
			document = DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}

	/**
	 *
	 * @param input
	 * @return
     */
	public static Document getDocument(InputStream input) {
		Document document = null;
		SAXReader reader = new SAXReader();
		try {
			document = reader.read(input);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;

	}

	/**
	 *
	 * @param document
	 * @param charset
     * @return
     */
	public static String formatXML(Document document, String charset) {
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(charset);
		StringWriter sw = new StringWriter();
		XMLWriter xw = new XMLWriter(sw, format);
		try {
			xw.write(document);
			xw.flush();
			xw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}

	/**
	 *
	 * @param doc
	 * @param path
     * @return
     */
	public static String getDocElementTextByPath(Document doc, String path) {
		if (doc == null) {
			return null;
		}
		Element note = (Element) doc.selectSingleNode(path);
		if (note != null) {
			return note.getText();
		} else {
			return "";
		}
	}

}
