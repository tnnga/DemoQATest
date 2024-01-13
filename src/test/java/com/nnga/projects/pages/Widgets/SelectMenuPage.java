package com.nnga.projects.pages.Widgets;

import com.beust.ah.A;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.nnga.Resources;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class SelectMenuPage {
    private WebDriver driver;
    private Resources resources;
    private Actions action;

    public SelectMenuPage(WebDriver driver) {
        this.driver = driver;
        resources = new Resources(driver);
        action = new Actions(driver);
    }

    private By btnSelectMenu = By.xpath("//div[@class='element-list collapse show']//li[@id='item-8']");
    private By selectMenu = By.xpath("//select[@id='oldSelectMenu']");
    private String linkSelectMenu = "select-menu";
    private By selectMulti = By.xpath("//select[@id='cars']");
    private By selectValue = By.xpath("//div[@id='withOptGroup']");
    private String gr1op1 = "Group 1, option 1";
    private String gr1op2 = "Group 1, option 2";
    private String gr2op1 = "Group 2, option 1";
    private String gr2op2 = "Group 2, option 2";
    private String aRootOp = "A root option";
    private String anoRootOp = "Another root option";
    private By selectOne = By.xpath("//div[@id='selectOne']");
    private By multiDropdown = By.xpath("//div[@id='selectMenuContainer']//div[@class='row']//div[contains(@class,'css-yk16xz-control')]");
    private By listChildMultiDropdown = By.xpath("//div[@class='css-1rhbuit-multiValue']");
    private By deleteSelect = By.xpath("//body//div[@id='app']//div[@class='row']//div[@class='row']//div[@class=' css-1wy0on6']//div[1]");

    public void gotoSelectMenu() {
        resources.clickElementWithJS(btnSelectMenu);
        resources.verifyLink(driver, linkSelectMenu);
    }

    public void testSelectValue() {
        resources.elementIsDisplay(selectValue);
        resources.verifyText(selectValue, "Select Option");

        resources.clickElement(selectValue);
        action.sendKeys(gr1op1).sendKeys(Keys.ENTER).build().perform();
        resources.verifyText(selectValue, gr1op1);

        action.sendKeys(gr1op2).sendKeys(Keys.ENTER).build().perform();
        resources.verifyText(selectValue, gr1op2);

        action.sendKeys(gr2op1).sendKeys(Keys.ENTER).build().perform();
        resources.verifyText(selectValue, gr2op1);

        action.sendKeys(gr2op2).sendKeys(Keys.ENTER).build().perform();
        resources.verifyText(selectValue, gr2op2);

        action.sendKeys(aRootOp).sendKeys(Keys.ENTER).build().perform();
        resources.verifyText(selectValue, aRootOp);

        action.sendKeys(anoRootOp).sendKeys(Keys.ENTER).build().perform();
        resources.verifyText(selectValue, anoRootOp);
    }

    public void testSelectOne() {
        resources.elementIsDisplay(selectOne);
        resources.verifyText(selectOne, "Select Title");

        resources.clickElement(selectOne);
        action.sendKeys("Dr.").sendKeys(Keys.ENTER).build().perform();
        resources.verifyText(selectOne, "Dr.");

        action.sendKeys("Mr.").sendKeys(Keys.ENTER).build().perform();
        resources.verifyText(selectOne, "Mr.");

        action.sendKeys("Mrs.").sendKeys(Keys.ENTER).build().perform();
        resources.verifyText(selectOne, "Mrs.");

        action.sendKeys("Ms.").sendKeys(Keys.ENTER).build().perform();
        resources.verifyText(selectOne, "Ms.");

        action.sendKeys("Prof.").sendKeys(Keys.ENTER).build().perform();
        resources.verifyText(selectOne, "Prof.");

        action.sendKeys("Other").sendKeys(Keys.ENTER).build().perform();
        resources.verifyText(selectOne, "Other");
    }

    public void testSelectMenu() {
        resources.verifyFirstSelectOption(selectMenu, "Red");
        List<WebElement> select = resources.getSelectOption(selectMenu);

        for (int i = 0; i < select.size(); i++) {
            resources.selectByIndex(selectMenu, i);
            List<WebElement> selectedOption = resources.getSelectedOption(selectMenu);
            String text = selectedOption.get(0).getText();
            verifyDropdown(i, text);
        }
    }

    public void testMultiDropdown() {
        resources.elementIsDisplay(multiDropdown);
        resources.verifyText(multiDropdown, "Select...");

        resources.clickElement(multiDropdown);
        action.sendKeys("Green").sendKeys(Keys.ENTER).build().perform();
        action.sendKeys("Black").sendKeys(Keys.ENTER).build().perform();

        resources.elementIsDisplay(listChildMultiDropdown);
        List<WebElement> childDropdown;
        childDropdown = driver.findElements(listChildMultiDropdown);
        for (int i = 0; i < childDropdown.size(); i++) {
            String text = childDropdown.get(i).getText();
            switch (i) {
                case 0:
                    Assert.assertEquals(text, "Green");
                    break;
                case 1:
                    Assert.assertEquals(text, "Black");
                    break;
                default:
                    System.out.println("Error");
                    break;
            }
        }
        resources.clickElement(deleteSelect);
        resources.verifyText(multiDropdown, "Select...");

        action.sendKeys("Red").sendKeys(Keys.ENTER).build().perform();
        resources.elementIsDisplay(listChildMultiDropdown);
        childDropdown = driver.findElements(listChildMultiDropdown);
        Assert.assertEquals(childDropdown.get(0).getText(), "Red");
    }

    public DatePickerPage testSelectMulti() {
        resources.elementIsDisplay(selectMulti);

        resources.selectByIndex(selectMulti, 1);
        resources.selectByIndex(selectMulti, 2);

        List<WebElement> selectedOption;

        selectedOption = resources.getSelectedOption(selectMulti);
        for (int i = 0; i < selectedOption.size(); i++) {
            String text = selectedOption.get(i).getText();
            switch (i) {
                case 0:
                    Assert.assertEquals(text, "Saab");
                    break;
                case 1:
                    Assert.assertEquals(text, "Opel");
                    break;
                default:
                    System.out.println("Error");
                    break;
            }
        }

        resources.deselectAll(selectMulti);
        selectedOption = resources.getSelectedOption(selectMulti);
        Assert.assertEquals(selectedOption.size(), 0);

        resources.selectByValue(selectMulti, "volvo");
        selectedOption = resources.getSelectedOption(selectMulti);
        for (WebElement getOption : selectedOption) {
            Assert.assertEquals(getOption.getText(), "Volvo");
        }
        resources.deselectByText(selectMulti, "Volvo");

        resources.selectByText(selectMulti, "Audi");
        selectedOption = resources.getSelectedOption(selectMulti);
        for (WebElement getOption : selectedOption) {
            Assert.assertEquals(getOption.getText(), "Audi");
        }
        return new DatePickerPage(driver);
    }

    private void verifyDropdown(int i, String actualResult) {
        switch (i) {
            case 0:
                Assert.assertEquals(actualResult, "Red");
                break;
            case 1:
                Assert.assertEquals(actualResult, "Blue");
                break;
            case 2:
                Assert.assertEquals(actualResult, "Green");
                break;
            case 3:
                Assert.assertEquals(actualResult, "Yellow");
                break;
            case 4:
                Assert.assertEquals(actualResult, "Purple");
                break;
            case 5:
                Assert.assertEquals(actualResult, "Black");
                break;
            case 6:
                Assert.assertEquals(actualResult, "White");
                break;
            case 7:
                Assert.assertEquals(actualResult, "Voilet");
                break;
            case 8:
                Assert.assertEquals(actualResult, "Indigo");
                break;
            case 9:
                Assert.assertEquals(actualResult, "Magenta");
                break;
            case 10:
                Assert.assertEquals(actualResult, "Aqua");
                break;
            default:
                System.out.println("Error");
        }
    }
}
