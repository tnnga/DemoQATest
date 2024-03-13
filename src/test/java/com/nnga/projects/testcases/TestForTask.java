package com.nnga.projects.testcases;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nnga.Resources;
import com.nnga.browsers.SetupBrowsers;
import com.nnga.utils.helpers.CaptureHelper;
import com.nnga.utils.helpers.DatabaseHelper;
import com.nnga.utils.helpers.ExcelHelper;
import com.nnga.utils.helpers.PropertiesHelper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestForTask {
    private WebDriver driver;
    private Resources resources;
    private SetupBrowsers setupBrowsers;
    private JavascriptExecutor js;
    private String excelPath = "C:\\Users\\intern.nttnga1\\IdeaProjects\\TestDemoQA\\Data.xls";
    private String sheetName = "Sheet0";
    private String jsonPath = "C:\\Users\\intern.nttnga1\\IdeaProjects\\TestDemoQA\\read.json";

    @Test
    public void testScroll() {
        setupBrowsers = new SetupBrowsers(driver, "https://www.toolsqa.com/selenium-webdriver/scroll-element-view-selenium-javascript/");
        driver = setupBrowsers.gotoBrowser("Chrome");
        js = (JavascriptExecutor) driver;
        resources = new Resources(driver);
        ((JavascriptExecutor) driver).executeScript("document.getElementById('tns1-ow').scrollIntoView(true);");
    }

    @Test
    public void testExcel() throws Exception {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile(excelPath, sheetName);
        excelHelper.setCellData("truong", 2, 3);
    }

    @Test
    public void testJson() throws FileNotFoundException {
        JsonParser jsonParser = new JsonParser();
        FileReader fileReader = new FileReader(jsonPath);

        Object obj = jsonParser.parse(fileReader);
        JsonObject jsonObject = (JsonObject) obj;

        System.out.println(jsonObject.get("browser").toString());

        JsonArray arr = (JsonArray) jsonObject.get("address");
        for (int i = 0; i < arr.size(); i++) {
            JsonObject addressObj = (JsonObject) arr.get(i);
            System.out.println("Street: " + addressObj.get("street"));
            System.out.println("City: " + addressObj.get("city"));
            System.out.println("State: " + addressObj.get("state"));
        }
    }

    @Test
    public void testProperties() {
        PropertiesHelper.setPropertiesFile();
        System.out.println(PropertiesHelper.getPropValue("password"));
    }

    @Test
    public void testPopUp() {
        String username = "admin";
        String password = "admin";

        setupBrowsers = new SetupBrowsers(driver, "https://" + username + ":" + password + "@" +"the-internet.herokuapp.com/basic_auth");
        driver = setupBrowsers.gotoBrowser("Chrome");
    }
    @Test
    public void testForWord(){
        try {
            XWPFDocument document = new XWPFDocument();
//            File file = new File("C:\\Users\\PC\\IdeaProjects\\TestDemoQA\\document.docx");

//            XWPFParagraph paragraph = document.createParagraph();
//            XWPFRun run = paragraph.createRun();
//            run.setText("Test for write to word");

            FileOutputStream outputStream = new FileOutputStream("C:\\Users\\PC\\IdeaProjects\\TestDemoQA\\document.docx");
            document.write(outputStream);
//            document.close();
            System.out.println("Write success");

//            fOut.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    @Test
    public void testSeleniumGrid() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("chrome");
        desiredCapabilities.setPlatform(Platform.WIN10);

        WebDriver driver1 = new RemoteWebDriver(new URL("http://192.168.2.217:4444/wd/hub"), desiredCapabilities);
        driver1.manage().window().maximize();
        driver1.get("https://web.skype.com/");
    }

    @Test
    public void testAuthenticationPopup() throws AWTException {
        setupBrowsers = new SetupBrowsers(driver, "https://the-internet.herokuapp.com/basic_auth");
        driver = setupBrowsers.gotoBrowser("Chrome");
        String username = "admin";
        String password = "admin";
//
//        ((HasAuthentication) driver).register(UsernameAndPassword.of(username, password));
//        driver.get("https://the-internet.herokuapp.com/basic_auth");
        Actions actions = new Actions(driver);
        actions.sendKeys(username).sendKeys(Keys.TAB).sendKeys(password).build().perform();

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyPress(KeyEvent.VK_D);
        robot.keyRelease(KeyEvent.VK_D);
        robot.keyPress(KeyEvent.VK_M);
        robot.keyRelease(KeyEvent.VK_M);
        robot.keyPress(KeyEvent.VK_I);
        robot.keyRelease(KeyEvent.VK_I);
        robot.keyPress(KeyEvent.VK_N);
        robot.keyRelease(KeyEvent.VK_N);

        robot.keyPress(KeyEvent.VK_TAB);

        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyPress(KeyEvent.VK_D);
        robot.keyRelease(KeyEvent.VK_D);
        robot.keyPress(KeyEvent.VK_M);
        robot.keyRelease(KeyEvent.VK_M);
        robot.keyPress(KeyEvent.VK_I);
        robot.keyRelease(KeyEvent.VK_I);
        robot.keyPress(KeyEvent.VK_N);
        robot.keyRelease(KeyEvent.VK_N);

        robot.keyPress(KeyEvent.VK_ENTER);
    }

    @Test
    public void testData() throws SQLException, ClassNotFoundException {
        DatabaseHelper.setData();
        ResultSet result = DatabaseHelper.getQuery("Select * from samsung");
        while (result.next()) {
            System.out.println(result.getString(1));
            System.out.println(result.getString(2));
            System.out.println(result.getString(3));
        }
//
//        DatabaseHelper.setQuery(" INSERT INTO `phone`.`samsung` (`idsamsung`, `phoneName`, `price`, `country`) VALUES ('15', 'Galaxy S29', '24000000', 'VietNam');");
//        DatabaseHelper.setQuery("UPDATE `phone`.`samsung` SET `price` = '27000000' WHERE (`idsamsung` = '4');");
//        DatabaseHelper.closeData();
    }
    @Test
    public void testGridSelenium2() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("firefox");
        desiredCapabilities.setPlatform(Platform.WIN11);

        driver = new RemoteWebDriver(new URL("http://192.168.1.17"), desiredCapabilities);
        driver.manage().window().maximize();
        driver.get("https://web.skype.com/");
        System.out.println("Title is: " + driver.getTitle());
    }
    @Test
    public void testGridSelenium3() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("chrome");
        desiredCapabilities.setPlatform(Platform.WIN11);

        driver = new RemoteWebDriver(new URL("http://192.168.1.17"), desiredCapabilities);
        driver.manage().window().maximize();
        driver.get("https://web.skype.com/");
        System.out.println("Title is: " + driver.getTitle());
    }
    @AfterMethod
    public void checkForScreenshot(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                CaptureHelper.captureScreenshot(driver, result.getName());
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }

}
