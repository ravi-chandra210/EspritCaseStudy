package com.testproject.pages;

import com.testproject.cucumber.ScenarioContext;
import com.testproject.utilities.DBUtils;
import com.testproject.utils.Driver;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EspritMethods {


    public static String CaseID;
    public static String username;
    public static String password;
    public static String userEmail;

    public static ArrayList<Map<String, String>> excelRows= null;
    public static Map<String, String> data = null;
    public static String tcName=null;
    public static int rowVal;
    public static int cellVal;
    public static String val=null;
    public static int dataIndex = 0;
    File file = null;

    //private final EspritElements test123;
    private final ScenarioContext context;
    public static final Logger LOG = LoggerFactory.getLogger(EspritMethods.class.getName());

    public EspritMethods(ScenarioContext context) {
        this.context = context;
    }

    /******************* Methods to read Excel sheet and store data in Hashmaps ***************/
    public static void getTestData(String testName) throws IOException {

        try {
            tcName = testName;
            excelRows = new ArrayList<Map<String, String>>();
            if (dataIndex == -1) {
                return;
            }
            String dir = System.getProperty("user.dir") + "\\TestData\\TestCasesNew.xls";
            System.out.println("This is the path of the test data file : "+dir);
            Workbook wb = Workbook.getWorkbook((new File(dir)));
            jxl.Sheet dataSheet = wb.getSheet("Test Data");
            data = new HashMap<String, String>();
            System.out.println(testName);
            dataIndex = dataSheet.findCell(testName).getRow();
            for (int i = 0; i < dataSheet.getColumns(); i++) {
                String key = dataSheet.getCell(i, 0).getContents();
                String value = dataSheet.getCell(i, dataIndex).getContents();
                data.put(key, value);
            }
            excelRows.add(data);
            System.out.println(data);
            System.out.println("getTestData method completed...");
        } catch (IOException | BiffException e) {
            e.printStackTrace();
        }
    }

    public static String storeData(String key) {
        return data.get(key);
    }

    /*..... This Method will read test data from provided excel sheet .....*/
    public void getSheetData(String testName){
        try {
            getTestData(testName);
            CaseID= EspritMethods.storeData("Case_ID");
            username = EspritMethods.storeData("Username");
            password = EspritMethods.storeData("Password");
            userEmail = EspritMethods.storeData("User Email");
        } catch (IOException e) {
            LOG.info("Error occured while reading the test data from excel sheet");
        }
    }

    public void assertThat(WebElement elem , String expectedText){
        try{
            String actualText = elem.getText();
            Assert.assertEquals(actualText,expectedText);
            LOG.info("Assert passed");
        }catch(Exception e){
            LOG.error("Assert Failed due to error " );
        }
    }

    public void assertVerifyText(WebElement elem ){
        try{
            String actualText = elem.getText();
            if(actualText.contains("sent to customer"))
                LOG.info("Assert passed");
        }catch(Exception e){
            LOG.error("Test Failed" );
        }
    }

    public void assertPageTitle( String expectedTitle){
        try{
            String pageTitle = Driver.get().getTitle();
            Assert.assertEquals(pageTitle,expectedTitle);
            LOG.info("Assert passed");
        }catch(Exception e){
            LOG.error("Assert Failed " );
        }
    }
public boolean checkXMLfile(){
    boolean fileResults = false;
    String attValue =null;
    try {
        file = new File("Filepath + order.xml");
        fileResults =true;
        LOG.info("XML file is created");
    }catch (Exception e){
        e.printStackTrace();
    }
    return fileResults;
}
    public String readXml(String attribute){
        String attValue = null;
        if(checkXMLfile()==true) {
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(file);
                doc.getDocumentElement().normalize();
                String rootElem = doc.getDocumentElement().getNodeName();
                NodeList tagList = doc.getElementsByTagName("oderId");
                for (int temp = 0; temp < tagList.getLength(); temp++) {

                    Node node = tagList.item(temp);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        Element element = (Element) node;

                        // get attribute
                        attValue = element.getAttribute(attribute);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return attValue;
    }

    public boolean checkTableStatus(){
        boolean status = false;
        try {
            String query = "SELECT * FROM Order Data";
            DBUtils.createConnection();
            DBUtils.getQueryResultList(query);
            status = true ;
            DBUtils.destroyConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return status;
    }

    public void readOrderTable(String tablename){
        try{
            String query = "SELECT * FROM " + tablename;
            DBUtils.createConnection();
            LOG.info("Table is created and following are the details stored in table : " + DBUtils.getQueryResultList(query));
            DBUtils.destroyConnection();
        }catch (Exception e){
e.printStackTrace();
        }
    }


    /********************Methods for EspritCase study*******************/

}
