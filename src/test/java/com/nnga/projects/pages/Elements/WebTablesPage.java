package com.nnga.projects.pages.Elements;

import com.nnga.Resources;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class WebTablesPage {
    private WebDriver driver;
    private Resources resources;

    public WebTablesPage(WebDriver driver) {
        this.driver = driver;
        resources = new Resources(driver);
    }

    private By btnWebTables = By.xpath("//div[@class='element-list collapse show']//li[@id='item-3']");
    private String link = "webtables";
    private By btnAdd = By.xpath("//button[@id='addNewRecordButton']");
    private By formAdd = By.xpath("//div[@class='modal-content']");
    private By inputFirstName = By.xpath("//input[@id='firstName']");
    private By inputLastName = By.xpath("//input[@id='lastName']");
    private By inputEmail = By.xpath("//input[@id='userEmail']");
    private By inputAge = By.xpath("//input[@id='age']");
    private By inputSalary = By.xpath("//input[@id='salary']");
    private By inputDepartment = By.xpath("//input[@id='department']");
    private String txtFirstName = "Nga";
    private String txtLastName = "Nguyen";
    private String txtEmail = "nnga3363@gmail.com";
    private String txtAge = "20";
    private String txtSalary = "2000000";
    private String txtDepartment = "Intern";
    private By btnSubmit = By.xpath("//button[@id='submit']");
    private By inputSearch = By.xpath("//input[@id='searchBox']");
    private By listChild = By.xpath("//div[@class='rt-tbody']/child::div/child::div/child::div");
    private By listHeader = By.xpath("//div[@class='rt-tr']/child::div");
    private By btnEdit = By.xpath("//div[@class='action-buttons']/child::span[1]");
    private By formEdit = By.xpath("//div[@class='modal-content']");
    private String valueEdit = "Email";
    private String textEdit = "alden@example.com";
    private String firstNameEdit = "Nguyen Viet Khoi";
    private String emailEdit = "khoinguyen.312110275962@st.ueh.edu.vn";
    private By btnDelete = By.xpath("//div[@class='action-buttons']/child::span[2]");
    private String emailDelete = "kierra@example.com";

    public void gotoWebTables() {
        resources.clickElement(btnWebTables);
        resources.verifyLink(driver, link);
    }

    public void testAdd() {
        resources.clickElement(btnAdd);
        resources.elementIsDisplay(formAdd);

        resources.setText(inputFirstName, txtFirstName);
        resources.setText(inputLastName, txtLastName);
        resources.setText(inputEmail, txtEmail);
        resources.setText(inputAge, txtAge);
        resources.setText(inputSalary, txtSalary);
        resources.setText(inputDepartment, txtDepartment);

        resources.clickElement(btnSubmit);

        setCheck("Last Name", txtLastName);
    }

    public void setCheck(String value, String text) {
        resources.setText(inputSearch, text);
        List<WebElement> headerList = driver.findElements(listHeader);
        List<WebElement> childList = driver.findElements(listChild);

        for (int i = 0; i < headerList.size(); i++) {
            if (headerList.get(i).getText().equals(value)) {
                Assert.assertEquals(childList.get(i).getText(), text);
            }
        }
    }

    public void testEdit() {
        gotoEdit(valueEdit, textEdit);
        setEdit(inputFirstName, firstNameEdit);
        setEdit(inputEmail, emailEdit);
        resources.clickElement(btnSubmit);
        checkEdit("First Name", firstNameEdit);
        checkEdit("Email", emailEdit);
    }

    public void gotoEdit(String value, String text) {
        resources.setText(inputSearch, text);
        List<WebElement> headerList = driver.findElements(listHeader);
        List<WebElement> childList = driver.findElements(listChild);

        for (int i = 0; i < headerList.size(); i++) {
            if (headerList.get(i).getText().equals(value)) {
                if (childList.get(i).getText().contains(text)) {
                    resources.clickElement(btnEdit);
                }
            }
        }
    }

    public void setEdit(By value, String textEdit) {
        resources.elementIsDisplay(formEdit);
        resources.setText(value, textEdit);
    }

    public void checkEdit(String value, String text) {
        resources.setText(inputSearch, text);
        List<WebElement> headerList = driver.findElements(listHeader);
        List<WebElement> childList = driver.findElements(listChild);

        for (int i = 0; i < headerList.size(); i++) {
            if (headerList.get(i).getText().equals(value)) {
                Assert.assertEquals(childList.get(i).getText(), text);
            }
        }
    }

    public UploadDownloadPage testDelete() {
        setDelete("Email", emailDelete);
        resources.clickElement(btnDelete);
        checkDelete("Email", emailDelete);
        return new UploadDownloadPage(driver);
    }

    public void setDelete(String value, String text) {
        resources.setText(inputSearch, text);
        List<WebElement> headerList = driver.findElements(listHeader);
        List<WebElement> childList = driver.findElements(listChild);

        for (int i = 0; i < headerList.size(); i++) {
            if (headerList.get(i).getText().equals(value)) {
                Assert.assertEquals(childList.get(i).getText(), text);
            }
        }
    }
    public void checkDelete(String value, String text){
        resources.setText(inputSearch, text);
        List<WebElement> headerList = driver.findElements(listHeader);
        List<WebElement> childList = driver.findElements(listChild);

        for (int i = 0; i < headerList.size(); i++) {
            if (headerList.get(i).getText().equals(value)) {
                Assert.assertNotEquals(childList.get(i).getText(), text);
            }
        }
    }
}

