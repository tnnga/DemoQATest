package com.nnga.projects.pages.AlertsFrameWindows;

import com.nnga.Resources;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.Assert;

import javax.naming.ldap.LdapReferralException;
import java.util.Iterator;
import java.util.Set;

public class BrowserWindowsPage {
    private WebDriver driver;
    private Resources resources;

    public BrowserWindowsPage(WebDriver driver) {
        this.driver = driver;
        resources = new Resources(driver);
    }

    private By btnbrowserWindows = By.xpath("//div[contains(@class,'element-list collapse show')]//li[@id='item-0']");
    private By btnNewTab = By.xpath("//button[@id='tabButton']");
    private By btnNewWindow = By.xpath("//button[@id='windowButton']");
    private By btnNewWindowMessage = By.xpath("//button[@id='messageWindowButton']");
    private String link = "browser-windows";
    private String linkNewTab = "sample";
    private String linkNewWindow = "link for new window example";
    private By txtSamplePage = By.xpath("//h1[@id='sampleHeading']");

    public void gotoBrowserWindows() {
        resources.clickElement(btnbrowserWindows);
        resources.verifyLink(driver, link);
    }

    public void testNewTab() {
        resources.clickElement(btnNewTab);

        String mainWindow = driver.getWindowHandle();
        System.out.println("Main Window is:" + mainWindow);

        Set<String> s1 = driver.getWindowHandles();
        System.out.println("Child Window handle is " + s1);
        Iterator<String> i1 = s1.iterator();

        while (i1.hasNext()) {
            String child = i1.next();
            if (!mainWindow.equalsIgnoreCase(child)) {
                driver.switchTo().window(child);
                resources.verifyLink(driver, linkNewTab);
                resources.elementIsDisplay(txtSamplePage);
                driver.close();
            }
        }
        driver.switchTo().window(mainWindow);
    }

    public void testNewWindow() {
        resources.clickElement(btnNewWindow);
        String mainWindow = driver.getWindowHandle();
        System.out.println("Main Window is:" + mainWindow);

        Set<String> s1 = driver.getWindowHandles();
        System.out.println("Child Window handle is " + s1);
        Iterator<String> i1 = s1.iterator();

        while (i1.hasNext()) {
            String child = i1.next();
            if (!mainWindow.equalsIgnoreCase(child)) {
                driver.switchTo().window(child);
                resources.verifyLink(driver, linkNewTab);
                resources.elementIsDisplay(txtSamplePage);
                driver.close();
            }
        }
        driver.switchTo().window(mainWindow);
    }

    public void testNewWindowMessage() {
        resources.clickElement(btnNewWindowMessage);
        String mainWindow = driver.getWindowHandle();
        System.out.println("Main Window is:" + mainWindow);

        Set<String> s1 = driver.getWindowHandles();
        System.out.println("Child Window handle is " + s1);
        Iterator<String> i1 = s1.iterator();

        while (i1.hasNext()) {
            String child = i1.next();
            if (!mainWindow.equalsIgnoreCase(child)) {
                driver.switchTo().window(child);
                driver.close();
            }
        }
        driver.switchTo().window(mainWindow);
    }

    public FramesPage testMulti() {
        resources.clickElement(btnNewTab);
        resources.clickElement(btnNewWindow);
        String mainWindow = driver.getWindowHandle();

        resources.switchToTab(driver, linkNewTab);
        driver.switchTo().window(mainWindow);
        return new FramesPage(driver);
    }
}
