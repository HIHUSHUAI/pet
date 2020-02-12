/**
 * @Author: LanHao
 * @Website:https://lanhaoo.club/
 * @Created_Date:4:49 PM_2/11/2020
 * @Magic_Power_Of_Code!
 */

package pet.program.XmlSave;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class XmlSave {
    private String filename;
    private Document document;
    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;
    private Transformer tr;


    public XmlSave(String filename){
        this.filename = filename;

        dbf = DocumentBuilderFactory.newInstance();
        try{
            db = dbf.newDocumentBuilder();
            tr = TransformerFactory.newInstance().newTransformer();
        }catch (ParserConfigurationException| TransformerConfigurationException e){
            e.printStackTrace();
        }
        document=db.newDocument();

    }

    public boolean createXml(String filename) {
        Element Pets=document.createElement("Pets");
        document.appendChild(Pets);
        try{
            tr.transform(new DOMSource(document),
                    new StreamResult(new FileOutputStream(filename+".xml")));

            return true;
        }catch (FileNotFoundException | TransformerException e){
            e.printStackTrace();

            return false;
        }
    }

    public void createNode(String name, String value,String id){
        Document doc=getParsedXML();
        Element root=doc.getDocumentElement();
        Element Pet=doc.createElement("Pet");

        Element newElement=doc.createElement(name);
        newElement.setTextContent(value);
        Pet.appendChild(newElement);
        Pet.setAttribute("id",id);
        root.appendChild(Pet);

//        在这里可额外创建新的节点,作为模板
        generateXML(doc);

    }


    public void insertNode(String name, String value,int id) {
        Document doc= getParsedXML();
        Element Pets=doc.getDocumentElement();
        NodeList nodeList=Pets.getElementsByTagName("Pet");
        Element oldE= (Element) nodeList.item(id);
        Element newE=doc.createElement(name);
        newE.setTextContent(value);

        oldE.appendChild(newE);
        generateXML(doc);
    }



    public String readNodeValue(int id,String name) {
        Document doc=getParsedXML();
        Element Pets=doc.getDocumentElement();

        NodeList nodeList=Pets.getElementsByTagName("Pet");
        Element element= (Element) nodeList.item(id);
        return element.getElementsByTagName(name).item(0).getTextContent();

    }

    private Document getParsedXML(){
        try {
            return db.parse(filename+".xml");
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void generateXML(Document document){
        try{
            tr.transform(new DOMSource(document),
                    new StreamResult(new FileOutputStream(filename+".xml")));
        }catch (FileNotFoundException | TransformerException e){
            e.printStackTrace();
        }
    }

}
