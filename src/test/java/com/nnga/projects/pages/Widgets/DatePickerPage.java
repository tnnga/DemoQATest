package com.nnga.projects.pages.Widgets;

import com.nnga.Resources;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class DatePickerPage {
    private WebDriver driver;
    private Resources resources;
    private JavascriptExecutor js;

    public DatePickerPage(WebDriver driver) {
        this.driver = driver;
        resources = new Resources(driver);
        js = (JavascriptExecutor) driver;
    }

    private By btnDatePicker = By.xpath("//div[@class='element-list collapse show']//li[@id='item-2']");
    private String txtLink = "date-picker";
    private By btnSelectDate = By.xpath("//input[@id='datePickerMonthYearInput']");
    private By calender = By.xpath("//div[@class='react-datepicker']");
    private By listMonth = By.xpath("//div[@class='react-datepicker__month-dropdown']/child::div");
    private By listYear = By.xpath("//div[@class='react-datepicker__year-dropdown']/child::div");
    private By btnMonth = By.xpath("//div[@class='react-datepicker__month-read-view']");
    private By btnYear = By.xpath("//div[@class='react-datepicker__year-read-view']");
    private By selectMonth = By.xpath("//select[@class='react-datepicker__month-select']");
    private By selectYear = By.xpath("//select[@class='react-datepicker__year-select']");
    private By btnDateTime = By.xpath("//input[@id='dateAndTimePickerInput']");
    private By listday = By.xpath("//div[@class='react-datepicker__week']/child::div");
    private By listTime = By.xpath("//ul[@class='react-datepicker__time-list']/child::li");
    private String txtmonth = "February";
    private String txtyear = "2003";
    private String txtday = "27";
    private String txttime = "12:45";
    private String txtInputDateTime = "02/27/2003";

    public void gotoDatePicker() {
        resources.clickElement(btnDatePicker);
        resources.verifyLink(driver, txtLink);
    }

    public void testSelectDate() {
        resources.clickElement(btnSelectDate);
        resources.waitForDisplay(calender);
        resources.elementIsDisplay(calender);

        resources.selectByText(selectMonth, txtmonth);
        resources.selectByValue(selectYear, txtyear);

        WebElement day = getDay(txtday);
        try {
            day.click();
        } catch (Exception e) {
            System.out.println("Day does not exist");
        }

        Assert.assertEquals(resources.getAttribute(btnSelectDate, "value"), txtInputDateTime);
    }

    public void testDateTime() {

        resources.clickElement(btnDateTime);
        resources.waitForDisplay(calender);
        resources.elementIsDisplay(calender);

        resources.clickElement(btnMonth);
        WebElement month = getMonth(txtmonth);
        month.click();

        resources.clickElement(btnYear);
        WebElement year = getYear(txtyear);
        year.click();

        WebElement day = getDay(txtday);
        day.click();

        WebElement time = getTime(txttime);
        time.click();
    }

    public WebElement getDay(String textDay) {
        List<WebElement> dayList = driver.findElements(listday);
        for (int i = 0; i < dayList.size(); i++) {
            if (dayList.get(i).getText().equals("1")) {
                for (int j = i; j < dayList.size(); j++) {
                    if (dayList.get(j).getText().equals(textDay)) {
                        return dayList.get(j);
                    }
                }
            }
        }
        return null;
    }

    public WebElement getMonth(String textMonth) {
        List<WebElement> monthList = driver.findElements(listMonth);
        for (WebElement month : monthList) {
            if (month.getText().equals(textMonth)) {
                return month;
            }
        }
        return null;
    }

    public WebElement getYear(String textYear) {
        String txtCurrentSelect = "2024";
        int currentSelect = Integer.parseInt(txtCurrentSelect);
        int yearToSet = Integer.parseInt(textYear);

        while (true) {
            List<WebElement> yearList = driver.findElements(listYear);
            for(WebElement year : yearList){
                if (year.getText().equals(textYear)) {
                    return year;
                }
            }
            if (yearToSet < currentSelect) {
                yearList.getLast().click();
            } else {
                yearList.getFirst().click();
            }
        }
    }

    public WebElement getTime(String textTime) {
        List<WebElement> timeList = driver.findElements(listTime);
        for (WebElement time : timeList) {
            if (time.getText().equals(textTime)) {
                return time;
            }
        }
        return null;
    }

}
