package com.nnga.projects.testcases;

import com.nnga.browsers.SetupBrowsers;
import com.nnga.projects.pages.HomePage;
import com.nnga.projects.pages.Widgets.DatePickerPage;
import com.nnga.projects.pages.Widgets.SelectMenuPage;
import com.nnga.utils.helpers.CaptureHelper;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WidgetsTest {
    private WebDriver driver;
    private SetupBrowsers setupBrowsers;
    private HomePage homePage;
    private SelectMenuPage selectMenuPage;
    private DatePickerPage datePickerPage;

    @BeforeTest
    public void setUp() {
        setupBrowsers = new SetupBrowsers(driver, "https://demoqa.com/");
        driver = setupBrowsers.gotoBrowser("Chrome");
    }

    @Test(priority = 0)
    public void gotoWidgetsPage() {
        homePage = new HomePage(driver);
        driver = homePage.gotoWidgetsPage();
    }

    @Test
    public void testSelectMenu() {
        selectMenuPage = new SelectMenuPage(driver);
        selectMenuPage.gotoSelectMenu();
        selectMenuPage.testSelectValue();
        selectMenuPage.testSelectOne();
        selectMenuPage.testSelectMenu();
        selectMenuPage.testMultiDropdown();
        selectMenuPage.testSelectMulti();
    }

    @Test
    public void testDatePicker() {
        datePickerPage = new DatePickerPage(driver);
        datePickerPage.gotoDatePicker();
        datePickerPage.testSelectDate();
        datePickerPage.testDateTime();
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
