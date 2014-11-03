import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Cat {	
	private URL url;
	private DocumentBuilderFactory dbf;
	private DocumentBuilder db;
	private org.w3c.dom.Document doc;
	private ArrayList<String> arrayGatos = new ArrayList<String>();
	public Cat(int cantidad) throws IOException {
		// TODO Auto-generated constructor stub
		this.url = new URL("http://thecatapi.com/api/images/get?format=xml&results_per_page="+cantidad);
		url.openConnection();	
		this.dbf = DocumentBuilderFactory.newInstance();
		try {
			this.db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			this.doc = this.db.parse(url.openStream());
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getCat(){
		doc.getDocumentElement().normalize();
		NodeList nodeList = doc.getElementsByTagName("image");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {				
				Element eElement = (Element) node;		
				
				this.arrayGatos.add(eElement.getElementsByTagName("url").item(0).getTextContent());
				
			}
		}		
		return this.arrayGatos;		
	}
	
	
	
}
