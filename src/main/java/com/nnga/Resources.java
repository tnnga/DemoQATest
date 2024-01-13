package com.nnga;

import com.beust.ah.A;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Resources {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private Select select;
    private File file;

    public Resources(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }

    public void clickElement(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
    }

    public void setText(By element, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        WebElement webElement = driver.findElement(element);
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void elementIsDisplay(By element) {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed());
    }

    public void verifyText(By element, String text) {
        Assert.assertTrue(getText(element).contains(text), "Text is not match");
    }

    public void verifyTextNotMatch(By element, String text) {
        Assert.assertFalse(getText(element).contains(text), "Text is match");
    }

    public void verifyLink(WebDriver driver, String link) {
        Assert.assertTrue(driver.getCurrentUrl().contains(link), "Link is not match");
    }

    public void verifyClassAttribute(By element, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        Assert.assertTrue(getAttribute(element, "class").contains(text));
    }

    public String getText(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getText();
    }

    public String getAttribute(By element, String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getAttribute(value);
    }

    public void clickElementWithJS(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        js.executeScript("arguments[0].click();", driver.findElement(element));
    }

    public void selectByIndex(By element, int index) {
        select = new Select(driver.findElement(element));
        select.selectByIndex(index);
    }

    public void selectByValue(By element, String value) {
        select = new Select(driver.findElement(element));
        select.selectByValue(value);
    }

    public void selectByText(By element, String text) {
        select = new Select(driver.findElement(element));
        select.selectByVisibleText(text);
    }

    public List<WebElement> getSelectOption(By element) {
        select = new Select(driver.findElement(element));
        return select.getOptions();
    }

    public void waitForDisplay(By element) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public void verifyFirstSelectOption(By element, String text) {
        select = new Select(driver.findElement(element));
        WebElement first = select.getFirstSelectedOption();
        Assert.assertTrue(first.getText().contains(text));
    }

    public List<WebElement> getSelectedOption(By element) {
        select = new Select(driver.findElement(element));
        return select.getAllSelectedOptions();
    }

    public void deselectAll(By element) {
        select = new Select(driver.findElement(element));
        select.deselectAll();
    }

    public void deselectByText(By element, String text) {
        select = new Select(driver.findElement(element));
        select.deselectByVisibleText(text);
    }

    public void verifyTitleAlert(Alert alert, String text) {
        Assert.assertTrue(alert.getText().contains(text), "Title is not match");
    }

    public void setAttribute(String idElement, String attributeName, String value){
        js.executeScript("document.getElementById('"+idElement+"').setAttribute('"+attributeName+"', '"+value+"')");
    }

    public Alert switchToAlerts(Alert alert) {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            alert = driver.switchTo().alert();
            return alert;
        } catch (Exception e) {
            return null;
        }
    }

    public WebDriver switchToFrame(By element) {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
            return driver;
        } catch (Exception e) {
            return null;
        }
    }

    public WebDriver switchToTab(WebDriver driver, String linkTab) {
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();

        while (i1.hasNext()) {
            String tabChild = i1.next();
            driver.switchTo().window(tabChild);
            if (driver.getCurrentUrl().contains(linkTab)) {
                return driver;
            } else {
                tabChild = i1.next();
            }
        }
        return null;
    }

    public void checkDownloadFile(String pathName, String fileName, int timeoutSeconds) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        wait.until((x) -> {
            File folder = new File(pathName);
            File[] listOfFiles = folder.listFiles();
            boolean found = false;
            File f = null;

            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    String fName = listOfFile.getName();
                    if (fileName.matches(fileName)) {

                        f = new File(fileName);
                        found = true;
                    }
                }
            }
            if (found) {
                f.deleteOnExit();
                return true;
            } else {
                return false;
            }
        });
    }

    public void uploadFile(String localSite, By element) {
        file = new File(localSite);
        setText(element, file.getAbsolutePath());
    }

    public void verifyURL(String url) {
        try {
            URL link = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() != 500 || httpURLConnection.getResponseCode() != 404) {
                System.out.println(url + " - " + "is a valid link");
            } else {
                System.out.println(url + " - " + "is a broken link");
            }
        } catch (Exception e) {
            System.out.println(url + " - " + "is a broken link");
        }
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
