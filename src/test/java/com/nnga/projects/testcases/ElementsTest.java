package com.nnga.projects.testcases;

import com.nnga.browsers.SetupBrowsers;
import com.nnga.projects.pages.*;
import com.nnga.projects.pages.Elements.*;
import com.nnga.utils.helpers.CaptureHelper;
import com.nnga.utils.logs.Log;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ElementsTest {
    private WebDriver driver;
    private SetupBrowsers setupBrowsers;
    private HomePage homePage;
    private TextboxPage textboxPage;
    private CheckboxPage checkboxPage;
    private RadioButtonPage radioButtonPage;
    private ButtonsPage buttonsPage;
    private WebTablesPage webTablesPage;
    private UploadDownloadPage uploadDownloadPage;
    private BrokenLinksPage brokenLinksPage;

    @BeforeTest
    public void setUp() {
        setupBrowsers = new SetupBrowsers(driver, "https://demoqa.com/");
        driver = setupBrowsers.gotoBrowser("Chrome");
    }

    @Test(priority = 0)
    public void gotoElementPage() throws Exception {
        homePage = new HomePage(driver);
        driver = homePage.gotoElementPage();
    }

    @Test(priority = 1)
    public void testTextBox() {
        Log.debug("Set up");

        textboxPage = new TextboxPage(driver);
        textboxPage.gotoTextBox();
        textboxPage.testTextBox();
        checkboxPage = textboxPage.verifySubmit();

    }

    @Test(priority = 2)
    public void testCheckBox() throws Exception {
        checkboxPage.gotoCheckBox();
        checkboxPage.testCheckBox();
        radioButtonPage = checkboxPage.testChildCheckBox();
    }

    @Test(priority = 3)
    public void testRadioButton() {
        radioButtonPage.gotoRadioButton();
        buttonsPage = radioButtonPage.testRadioButton();
    }

    @Test(priority = 4)
    public void testButtons() {
        buttonsPage.gotoButtonsPage();
        webTablesPage = buttonsPage.testButtons();
    }

    @Test(priority = 5)
    public void testWebTables() {
        webTablesPage.gotoWebTables();
        webTablesPage.testAdd();
        webTablesPage.testEdit();
        uploadDownloadPage = webTablesPage.testDelete();
    }

    @Test(priority = 6)
    public void testUploadDownload() {
        uploadDownloadPage = new UploadDownloadPage(driver);
        uploadDownloadPage.gotoUploadDownloadPage();
        uploadDownloadPage.testDownload();
        brokenLinksPage = uploadDownloadPage.testUpload();
    }

    @Test(priority = 7)
    public void testBrokenLinks() {
        brokenLinksPage = new BrokenLinksPage(driver);
        brokenLinksPage.gotoBrokenLinksPage();
        brokenLinksPage.testBrokenLinks();
    }

    @AfterMethod
    public void checkForScreenshot(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                CaptureHelper.captureScreenshot(driver, result.getName());
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }
}
