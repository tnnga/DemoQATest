package com.nnga.projects.pages.AlertsFrameWindows;

import com.nnga.Resources;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AlertsPage {
    private WebDriver driver;
    private Resources resources;
    private Alert alert;
    public AlertsPage(WebDriver driver){
        this.driver = driver;
        resources = new Resources(driver);
    }
    private By btnAlerts = By.xpath("//div[@class='element-list collapse show']//li[@id='item-1']");
    private By btnSeeAlert = By.xpath("//button[@id='alertButton']");
    private By btnSenkeyAlert = By.xpath("//button[@id='promtButton']");
    private By btnConfirmAlert = By.xpath("//button[@id='confirmButton']");
    private By txtResultForSendkey = By.xpath("//span[@id='promptResult']");
    private By txtResultForConfirm = By.xpath("//span[@id='confirmResult']");
    private By btnTimeAlert = By.xpath("//button[@id='timerAlertButton']");
    private String txtAlerts = "alerts";
    private String text = "Nguyen Thi Thuy Nga";
    private String resultText = "You entered Nguyen Thi Thuy Nga";
    private String txtOk = "You selected Ok";
    private String txtCancel = "You selected Cancel";
    private String txtSimpleAlertTitle = "You clicked a button";
    private String txtConfirmAlertTitle = "Do you confirm action?";
    private String txtPromptAlertTitle = "Please enter your name";
    private String txtTimeAlertTitle = "This alert appeared after 5 seconds";
    public void gotoAlerts(){
        resources.clickElement(btnAlerts);
        resources.verifyLink(driver, txtAlerts);
    }
    public void testSimpleAlerts(){
        resources.clickElement(btnSeeAlert);
        alert = resources.switchToAlerts(alert);
        Assert.assertNotNull(alert);
        resources.verifyTitleAlert(alert, txtSimpleAlertTitle);
        alert.accept();

        alert = resources.switchToAlerts(alert);
        Assert.assertNull(alert);
    }
    public void testAlertConfirmBox(){
        resources.clickElement(btnConfirmAlert);
        alert = resources.switchToAlerts(alert);
        Assert.assertNotNull(alert);

        resources.verifyTitleAlert(alert, txtConfirmAlertTitle);

        alert.accept();
        resources.elementIsDisplay(txtResultForConfirm);
        resources.verifyText(txtResultForConfirm, txtOk);

        resources.clickElement(btnConfirmAlert);
        resources.switchToAlerts(alert);
        Assert.assertNotNull(alert);

        alert.dismiss();
        resources.elementIsDisplay(txtResultForConfirm);
        resources.verifyText(txtResultForConfirm, txtCancel);
        alert = resources.switchToAlerts(alert);
        Assert.assertNull(alert);
    }
    public void testTimeAlert(){
        resources.clickElement(btnTimeAlert);
        resources.sleep(5000);
        alert = resources.switchToAlerts(alert);
        Assert.assertNotNull(alert);

        resources.verifyTitleAlert(alert, txtTimeAlertTitle);

        alert.accept();
        alert = resources.switchToAlerts(alert);
        Assert.assertNull(alert);
    }
    public BrowserWindowsPage testAlertPromptBox(){
        resources.clickElement(btnSenkeyAlert);
        alert = resources.switchToAlerts(alert);
        Assert.assertNotNull(alert);
        resources.verifyTitleAlert(alert, txtPromptAlertTitle);

        alert.sendKeys(text);
        alert.accept();
        resources.elementIsDisplay(txtResultForSendkey);
        resources.verifyText(txtResultForSendkey, resultText);
        alert = resources.switchToAlerts(alert);
        Assert.assertNull(alert);

        return new BrowserWindowsPage(driver);
    }
}
