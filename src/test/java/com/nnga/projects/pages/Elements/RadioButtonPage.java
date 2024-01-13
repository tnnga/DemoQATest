package com.nnga.projects.pages.Elements;

import com.nnga.Resources;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RadioButtonPage {
    private WebDriver driver;
    private Resources resources;
    public RadioButtonPage(WebDriver driver){
        this.driver = driver;
        resources = new Resources(driver);
    }
    private By btnRadioButon = By.id("item-2");
    private By radYes = By.xpath("//label[normalize-space()='Yes']");
    private By radImpressive = By.xpath("//label[normalize-space()='Impressive']");
    private By result = By.xpath("//span[@class='text-success']");
    private By radNo = By.xpath("//label[normalize-space()='No']");
    private String linkPage = "radio-button";
    private String txtYes = "Yes";
    private String txtImpressive = "Impressive";
    private String txtNo = "No";
    public void gotoRadioButton(){
        resources.clickElement(btnRadioButon);
        resources.verifyLink(driver, linkPage);
    }
    public ButtonsPage testRadioButton(){
        resources.clickElement(radYes);
        resources.elementIsDisplay(result);
        resources.verifyText(result,txtYes);
        resources.clickElement(radImpressive);
        resources.elementIsDisplay(result);
        resources.verifyText(result, txtImpressive);
        resources.clickElement(radNo);
        resources.elementIsDisplay(result);
        resources.verifyTextNotMatch(result, txtNo);

        return new ButtonsPage(driver);
    }
}
