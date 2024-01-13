package com.nnga.projects.pages;

import com.nnga.Resources;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private Resources resources;
    public HomePage(WebDriver driver){
        this.driver = driver;
        resources = new Resources(driver);
    }
    private By btnElement = By.xpath("//body/div[@id='app']/div[1]/div[1]/div[2]/div[1]/div[1]");
    private By btnWidgets = By.xpath("//body[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[4]/div[1]");
    private By btnAlertsFrameWindows = By.xpath("//body/div[@id='app']/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]");
    private String txtElement = "elements";
    private String txtWidgets = "widgets";
    private String txtAlertsFrameWindows = "alertsWindows";
    public WebDriver gotoElementPage() {
        resources.clickElement(btnElement);
        resources.verifyLink(driver, txtElement);
        return driver;
    }
    public WebDriver gotoWidgetsPage(){
        resources.clickElement(btnWidgets);
        resources.verifyLink(driver, txtWidgets);
        return driver;
    }
    public WebDriver gotoALertsFrameWindows(){
        resources.clickElement(btnAlertsFrameWindows);
        resources.verifyLink(driver, txtAlertsFrameWindows);
        return driver;
    }
}
