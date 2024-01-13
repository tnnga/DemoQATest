package com.nnga.projects.pages.Elements;

import com.nnga.Resources;
import com.nnga.projects.pages.Elements.CheckboxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class TextboxPage {
    private WebDriver driver;
    private Resources resources;
    public TextboxPage(WebDriver driver){
        this.driver = driver;
        resources = new Resources(driver);
    }
    private By btnTextBox = By.xpath("//span[normalize-space()='Text Box']");
    private String txtTextBoxLink = "text-box";
    private By txtFullName = By.xpath("//input[@id='userName']");
    private By txtEmail = By.xpath("//input[@id='userEmail']");
    private By txtCurrentAddress = By.xpath("//textarea[@id='currentAddress']");
    private By txtPermanentAddress = By.xpath("//textarea[@id='permanentAddress']");
    private By btnSubmit = By.xpath("//button[@id='submit']");
    private By txtResult = By.xpath("//div[@id='output']");
    private String name = "Nguyen Thi Thuy Nga";
    private String email = "nnga3363@gmail.com";
    private String currentAddress = "Ho Chi Minh";
    private String permanentAddress = "Gia Lai";
    private By txtNameOfResult = By.id("name");
    private By txtEmailOfResult = By.id("email");
    private By txtCurrentAddressOfResult = By.xpath("//p[@id='currentAddress']");
    private By txtPermanentAddressOfResult = By.xpath("//p[@id='permanentAddress']");
    public void gotoTextBox(){
        resources.clickElement(btnTextBox);
        resources.verifyLink(driver, txtTextBoxLink);
    }
    public void testTextBox(){
        resources.setText(txtFullName, name);
        resources.setText(txtEmail, email);
        resources.setText(txtCurrentAddress, currentAddress);
        resources.setText(txtPermanentAddress, permanentAddress);

        resources.clickElement(btnSubmit);
    }
    public CheckboxPage verifySubmit(){
        resources.elementIsDisplay(txtResult);
        resources.verifyText(txtNameOfResult, name);
        resources.verifyText(txtEmailOfResult, email);
        resources.verifyText(txtCurrentAddressOfResult, currentAddress);
        resources.verifyText(txtPermanentAddressOfResult, permanentAddress);
        return new CheckboxPage(driver);
    }
}
