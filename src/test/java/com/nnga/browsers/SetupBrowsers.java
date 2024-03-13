package com.nnga.browsers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

public class SetupBrowsers {

    private String linkBrowser;
    private WebDriver driver;
    public SetupBrowsers(WebDriver driver, String linkBrowser){
        this.driver = driver;
        this.linkBrowser = linkBrowser;
    }
    public WebDriver gotoBrowser(String browserType){
        browserType.toLowerCase();
        switch (browserType){
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "eged":
                driver = new FirefoxDriver();
                break;
            default:
                driver = new ChromeDriver();
                break;
        }
        driver = formatBrowser();
        return driver;
    }
    private WebDriver formatBrowser(){
        driver.manage().window().maximize();
        driver.get(linkBrowser);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }
}
