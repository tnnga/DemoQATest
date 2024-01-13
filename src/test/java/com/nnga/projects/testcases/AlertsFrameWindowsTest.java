package com.nnga.projects.testcases;

import com.nnga.browsers.SetupBrowsers;
import com.nnga.projects.pages.AlertsFrameWindows.AlertsPage;
import com.nnga.projects.pages.AlertsFrameWindows.BrowserWindowsPage;
import com.nnga.projects.pages.AlertsFrameWindows.FramesPage;
import com.nnga.projects.pages.HomePage;
import com.nnga.utils.helpers.CaptureHelper;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AlertsFrameWindowsTest {
    private WebDriver driver;
    private SetupBrowsers setupBrowsers;
    private HomePage homePage;
    private AlertsPage alertsPage;
    private BrowserWindowsPage browserWindowsPage;
    private FramesPage framesPage;

    @BeforeTest(groups = "Before")
    public void setUp() {
        setupBrowsers = new SetupBrowsers(driver, "https://demoqa.com/");
        driver = setupBrowsers.gotoBrowser("Chrome");
    }

    @Test(groups = {"Alerts", "BrowserWindows", "Frames"})
    public void gotoAlertsFrameWindowsPage() {
        homePage = new HomePage(driver);
        driver = homePage.gotoALertsFrameWindows();
    }

    @Test(groups = "Alerts", dependsOnMethods = "gotoAlertsFrameWindowsPage", priority = 1)
    public void testSimpleAlerts() {
        alertsPage = new AlertsPage(driver);
        alertsPage.gotoAlerts();
        alertsPage.testSimpleAlerts();
        alertsPage.testTimeAlert();
    }

    @Test(groups = "Alerts", priority = 2)
    public void testConfirmationAlert() {
        alertsPage.testAlertConfirmBox();
    }

    @Test(groups = "Alerts", priority = 3)
    public void testPromptAlert() {
        browserWindowsPage = alertsPage.testAlertPromptBox();
    }

    @Test(groups = "BrowserWindows", dependsOnMethods = "gotoAlertsFrameWindowsPage", priority = 4)
    public void testBrowserWindowsTab() {
        browserWindowsPage.gotoBrowserWindows();
        browserWindowsPage.testNewTab();
    }

    @Test(groups = "BrowserWindows", priority = 5)
    public void testBrowserWindowsWindow() {
        browserWindowsPage.testNewWindow();
    }

    @Test(groups = "BrowserWindows", priority = 6)
    public void testBrowserWindowsWindowMessage() {
        browserWindowsPage.testNewWindowMessage();
    }

    @Test(groups = "BrowserWindows", priority = 7)
    public void testMultiWindows() {
        framesPage = browserWindowsPage.testMulti();
    }

    @Test(groups = "Frames", dependsOnMethods = "gotoAlertsFrameWindowsPage", priority = 8)
    public void testFrame1() {
        framesPage.gotoFrames();
        framesPage.testFrame1();
    }

//    @Test(groups = "Frames", priority = 9)
//    public void testFrame2() {
//        framesPage.testFrame2();
//    }
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
