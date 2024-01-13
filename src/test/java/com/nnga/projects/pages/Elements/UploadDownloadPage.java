package com.nnga.projects.pages.Elements;

import com.nnga.Resources;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UploadDownloadPage {
    private WebDriver driver;
    private Resources resources;

    public UploadDownloadPage(WebDriver driver) {
        this.driver = driver;
        resources = new Resources(driver);
    }

    private By btnUploadDownload = By.xpath("//div[@class='element-list collapse show']//li[@id='item-7']");
    private String link = "upload-download";
    private By btnDownload = By.xpath("//a[@id='downloadButton']");
    private By btnUpload = By.xpath("//input[@id='uploadFile']");
    private By resultUpload = By.xpath("//p[@id='uploadedFilePath']");
    private String namefile = "logo.png";

    public void gotoUploadDownloadPage() {
        resources.clickElementWithJS(btnUploadDownload);
        resources.verifyLink(driver, link);
    }

    public void testDownload() {
        resources.clickElement(btnDownload);
        resources.checkDownloadFile("C:\\Users\\intern.nttnga1\\Downloads", "sampleFile.jpeg", 20);
    }

    public BrokenLinksPage testUpload() {
        resources.uploadFile("src1\\test\\resources\\logo.png", btnUpload);
        resources.elementIsDisplay(resultUpload);
        resources.verifyText(resultUpload, namefile);
        return new BrokenLinksPage(driver);
    }
}
