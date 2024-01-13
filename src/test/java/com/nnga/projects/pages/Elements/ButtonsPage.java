package com.nnga.projects.pages.Elements;

import com.nnga.Resources;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.image.Kernel;

public class ButtonsPage {
    private WebDriver driver;
    private Resources resources;
    private Actions actions;

    public ButtonsPage(WebDriver driver) {
        this.driver = driver;
        resources = new Resources(driver);
        actions = new Actions(driver);
    }

    private By btnButtons = By.xpath("//div[@class='element-list collapse show']//li[@id='item-4']");
    private By doubleClick = By.xpath("//button[@id='doubleClickBtn']");
    private By rightClick = By.xpath("//button[@id='rightClickBtn']");
    private By clickMe = By.xpath("//button[@class='btn btn-primary' and text()='Click Me']");
    private By doubleClickMessage = By.xpath("//p[@id='doubleClickMessage']");
    private By rightClickMessage = By.xpath("//p[@id='rightClickMessage']");
    private By clickMeMessage = By.xpath("//p[@id='dynamicClickMessage']");
    private String txtDoubleClickMessage = "You have done a double click";
    private String txtRightClickMessage = "You have done a right click";
    private String txtClickMeMessage = "You have done a dynamic click";

    public void gotoButtonsPage() {
        resources.clickElement(btnButtons);
        resources.verifyLink(driver, "buttons");
    }

    public WebTablesPage testButtons() {
        actions.doubleClick(driver.findElement(doubleClick))
               .contextClick(driver.findElement(rightClick))
               .click(driver.findElement(clickMe))
               .build().perform();

        resources.elementIsDisplay(doubleClickMessage);
        resources.verifyText(doubleClickMessage, txtDoubleClickMessage);

        resources.elementIsDisplay(rightClickMessage);
        resources.verifyText(rightClickMessage, txtRightClickMessage);

        resources.elementIsDisplay(clickMeMessage);
        resources.verifyText(clickMeMessage, txtClickMeMessage);
        return new WebTablesPage(driver);
    }
}
