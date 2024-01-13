package com.nnga.projects.pages.Elements;

import com.nnga.Resources;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckboxPage {
    private WebDriver driver;
    private Resources resources;
    public CheckboxPage(WebDriver driver){
        this.driver = driver;
        resources = new Resources(driver);
    }
    private By btnCheckBox = By.xpath("//div[@class='element-list collapse show']//li[@id='item-1']");
    private String txtCheckboxLink = "checkbox";
    private By btnHome = By.xpath("//label[@for='tree-node-home']//span[@class='rct-checkbox']//*[name()='svg']");
    private By txtResult = By.xpath("//div[@id='result']");
    private By btnDisplayChild = By.xpath("//button[@title='Expand all']");
    private By btnUnDisplayChild = By.xpath("//button[@title='Collapse all']");
    private By listChildCheckBox = By.xpath("//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/ol[1]/li[1]/span[1]/following::li");
    private String txtUncheck = "uncheck";
    private String txtCheck = "check";
    private String txtSelectAll = "You have selected :\n" +
                    "home\n" +
                    "desktop\n" +
                    "notes\n" +
                    "commands\n" +
                    "documents\n" +
                    "workspace\n" +
                    "react\n" +
                    "angular\n" +
                    "veu\n" +
                    "office\n" +
                    "public\n" +
                    "private\n" +
                    "classified\n" +
                    "general\n" +
                    "downloads\n" +
                    "wordFile\n" +
                    "excelFile";
    public void gotoCheckBox(){
        resources.clickElement(btnCheckBox);
        resources.verifyLink(driver, txtCheckboxLink);
    }
    public void testCheckBox(){
        resources.elementIsDisplay(btnHome);
        resources.verifyClassAttribute(btnHome, txtUncheck);
        resources.clickElement(btnHome);
        resources.elementIsDisplay(txtResult);
        resources.verifyClassAttribute(btnHome, txtCheck);
        resources.verifyText(txtResult, txtSelectAll);
        resources.clickElement(btnHome);
        resources.verifyClassAttribute(btnHome, txtUncheck);
    }
    public RadioButtonPage testChildCheckBox(){
        resources.clickElement(btnDisplayChild);
        resources.elementIsDisplay(listChildCheckBox);
        testChild();
        resources.verifyClassAttribute(btnHome, txtCheck);
        resources.verifyText(txtResult, txtSelectAll);
        testChild();
        resources.verifyClassAttribute(btnHome, txtUncheck);
        resources.clickElement(btnUnDisplayChild);
        return new RadioButtonPage(driver);
    }
    private void testChild(){
        for (int i = 1; i < 4; i++) {
            By listCheck = By.xpath("//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/ol[1]/li[1]/ol[1]/li["+i+"]/span[1]/label[1]/span[1]/*[1]");
            resources.clickElement(listCheck);
        }
    }
}
