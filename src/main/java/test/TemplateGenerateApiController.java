package com.example.project.DmsServerNew.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.example.project.DmsServerNew.model.TxnDocument;
import com.example.project.DmsServerNew.repo.TxnDocumentRepository;
import com.example.project.DmsServerNew.utility.DownloadFile;
import com.example.project.DmsServerNew.utility.PDFGenerator;
import com.example.project.DmsServerNew.utility.S3Utility;

@RestController
@RequestMapping("/api/v3/")
public class TemplateGenerateApiController {

	@Autowired
	S3Utility s3Utility;
	
	@Autowired
	TxnDocumentRepository txnDocumentRepository;	
	
	public static final String MIME_TYPE = "application/octet-stream";
	public static final String FILE_HEADER = "Content-Disposition";
	public static final String DOWNLOAD_FILE_PROPERTY_NAME = "inline; filename=\"%s\"";


	@PostMapping("download-pdf")
	public StreamingResponseBody downloadThumbnail(HttpServletResponse httpResponse, @RequestBody String json) {
		
		DownloadFile downloadFile = new DownloadFile("abc.pdf",
				PDFGenerator.generatePDFContentFromHTML(readXml(json)).toByteArray());
		httpResponse.setContentType(MIME_TYPE);
		httpResponse.setHeader(FILE_HEADER, String.format(DOWNLOAD_FILE_PROPERTY_NAME, downloadFile.getFileName()));
		return new StreamingResponseBody() {
			@Override
			public void writeTo(OutputStream outputStream) throws IOException {
				outputStream.write(downloadFile.getFile());
				outputStream.flush();
			}
		};
	}


	
	private String readXml(String json) {
		String xmlFileData = "Parsing DOM .....";
		try {
			JSONObject jObj = new JSONObject(json);
			Integer docId = jObj.getInt("docId");
			TxnDocument txnDocument = txnDocumentRepository.getByDocId(docId);
			S3ObjectInputStream inputStream = s3Utility.downloadObject(txnDocument.getDocsavefilename()).getObjectContent();
			// Parsing DOM ...
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputStream);
			doc = (Document) removeRecursively(doc, jObj.getJSONObject("view"));

			NodeList ifdiv_Nodes = doc.getElementsByTagName("if-div");
			for (int i = 0; i < ifdiv_Nodes.getLength(); i++) {
				doc.renameNode(ifdiv_Nodes.item(i), null, "div");
			}

			NodeList ifspan_Nodes = doc.getElementsByTagName("if-span");
			for (int i = 0; i < ifspan_Nodes.getLength(); i++) {
				doc.renameNode(ifspan_Nodes.item(i), null, "span");
			}

			doc.normalize();

			xmlFileData = prettyPrint(doc);

			// Replace Parameters in Document
			JSONObject data = jObj.getJSONObject("data");

			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

			Iterator<String> keys = data.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				if (data.get(key) instanceof String) {
					String value = (String) data.get(key);
					String replacingParam = "$" + key.toLowerCase() + "$";

					System.out.println(replacingParam + ": " + value);
					xmlFileData = xmlFileData.replaceAll("(?i)" + Pattern.quote(replacingParam), value);
				}
			}
			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

		} catch (Exception e) {
			xmlFileData = "Exception while Reading File!";
			e.printStackTrace();
		}
		return xmlFileData;
	}

	public Node removeRecursively(Node node, JSONObject jObj) throws JSONException {
		if (node.getNodeName().toLowerCase().equals("if-div") || node.getNodeName().toLowerCase().equals("if-span")) {
			String if_IdAttribute = node.getAttributes().getNamedItem("id").getTextContent();
			boolean if_IdValueInJSON = jObj.has(if_IdAttribute) && jObj.getBoolean(if_IdAttribute);
			if (!if_IdValueInJSON) {
				node.getParentNode().removeChild(node);
			}
		}

		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			removeRecursively(list.item(i), jObj);
		}
		return node;
	}

	public static final String prettyPrint(Document xml) throws Exception {
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		tf.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		Writer out = new StringWriter();
		tf.transform(new DOMSource(xml), new StreamResult(out));
		return out.toString();
	}
}