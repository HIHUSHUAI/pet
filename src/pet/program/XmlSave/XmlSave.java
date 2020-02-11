/**
 * @Author: LanHao
 * @Website:https://lanhaoo.club/
 * @Created_Date:4:49 PM_2/11/2020
 * @Magic_Power_Of_Code!
 */

package pet.program.XmlSave;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
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


    public XmlSave(String filename) throws TransformerException, ParserConfigurationException {
        this.filename = filename;

        dbf = DocumentBuilderFactory.newInstance();
        db = dbf.newDocumentBuilder();
        document=db.newDocument();
        tr = TransformerFactory.newInstance().newTransformer();
    }

    public void createXml(String filename) throws  TransformerException, FileNotFoundException {
        Element Pets=document.createElement("Pets");
//        Element Pet=document.createElement("Pet");
//
//        Element Name=document.createElement("Name");
//        Element NickName=document.createElement("Nickname");
//        Pet.appendChild(Name);
//        Pet.appendChild(NickName);
//        Pet.setAttribute("id","0");
//        Pets.appendChild(Pet);


        document.appendChild(Pets);

        tr.transform(new DOMSource(document),
                new StreamResult(new FileOutputStream(filename+".xml")));


    }

    public void createNode(String name, String value,String id) throws IOException, SAXException, TransformerException {
        Document doc=db.parse(filename+".xml");
        Element root=doc.getDocumentElement();
        Element Pet=doc.createElement("Pet");

        Element newElement=doc.createElement(name);
        newElement.setTextContent(value);
        Pet.appendChild(newElement);
        Pet.setAttribute("id",id);
        root.appendChild(Pet);

//        在这里创建新的节点
        tr.transform(new DOMSource(doc),
                new StreamResult(new FileOutputStream(filename+".xml")));

    }





    public String readNodeValue(int id) throws IOException, SAXException {
        Document doc=db.parse(filename+".xml");
        Element Pets=doc.getDocumentElement();

        return Pets.getElementsByTagName("Pet").item(id).getTextContent();
    }

}
