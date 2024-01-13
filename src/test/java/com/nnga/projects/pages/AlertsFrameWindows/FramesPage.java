package com.nnga.projects.pages.AlertsFrameWindows;

import com.nnga.Resources;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FramesPage {
    private WebDriver driver;
    private Resources resources;

    public FramesPage(WebDriver driver) {
        this.driver = driver;
        resources = new Resources(driver);
    }

    private By btnFrames = By.xpath("//div[@class='element-list collapse show']//li[@id='item-2']");
    private By frame1 = By.xpath("//iframe[@id='frame1']");
    private By frame2 = By.xpath("//iframe[@id='frame2']");
    private String link = "frames";
    private By txtInFrame = By.xpath("//h1[@id='sampleHeading']");

    public void gotoFrames() {
        resources.clickElement(btnFrames);
        resources.verifyLink(driver, link);
    }

    public void testFrame1() {
        resources.switchToFrame(frame1);

        resources.elementIsDisplay(txtInFrame);
        System.out.println(resources.getText(txtInFrame));

        driver.switchTo().defaultContent();
    }

    public void testFrame2() {
        resources.switchToFrame(frame2);
        resources.elementIsDisplay(txtInFrame);
        System.out.println(resources.getText(txtInFrame));

        driver.switchTo().defaultContent();
    }
}
