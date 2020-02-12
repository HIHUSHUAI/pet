/**
 * @Author: LanHao
 * @Website:https://lanhaoo.club/
 * @Created_Date:5:22 PM_2/11/2020
 * @Magic_Power_Of_Code!
 */

package pet.program.XmlSave;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Example {
    public static void main(String[] args)  {

        XmlSave xmlSave=new XmlSave("Pets");
        xmlSave.createXml("Pets");
        //创建xml

        xmlSave.createNode("Name","王看山","0");
        xmlSave.createNode("Name","王看","1");
        //创建节点
        xmlSave.insertNode("NickName","山山",1);
        //插入节点

        System.out.println(xmlSave.readNodeValue(0,"Name"));
        //获取节点内容
        System.out.println(xmlSave.readNodeValue(1,"Name"));
        System.out.println(xmlSave.readNodeValue(1,"NickName"));



    }
}
