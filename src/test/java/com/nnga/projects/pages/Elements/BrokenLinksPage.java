package com.nnga.projects.pages.Elements;

import com.nnga.Resources;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BrokenLinksPage {
    private WebDriver driver;
    private Resources resources;
    public BrokenLinksPage(WebDriver driver){
        this.driver = driver;
        resources = new Resources(driver);
    }
    private By btnBrokenLinks = By.xpath("//div[@class='element-list collapse show']//li[@id='item-6']");
    private String link = "broken";
    private By linkWeb = By.tagName("a");
    public void gotoBrokenLinksPage(){
        resources.clickElement(btnBrokenLinks);
        resources.verifyLink(driver, link);
    }
    public void testBrokenLinks(){
        List<WebElement> links = driver.findElements(linkWeb);
        for(WebElement link : links){
            String url = link.getAttribute("href");
            resources.verifyURL(url);
        }
    }
}
