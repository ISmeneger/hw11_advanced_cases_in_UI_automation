package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebFormPage extends BasePage {
    private static final String WEB_FORM_URL = "web-form.html";

    String selectFile = System.getProperty("user.dir") + "/src/main/resources/STE In Banner.jpg";

    @FindBy(name = "my-file")
    WebElement fileInputButton;

    @FindBy(xpath = "//button[@type = 'submit']")
    WebElement submitButton;

    public WebFormPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get subpage url")
    public String getUrl() {
        return WEB_FORM_URL;
    }

    @Step("File input")
    public void downloadFile() {
        fileInputButton.sendKeys(selectFile);
    }

    @Step("Submit form")
    public void submitForm() {
        submitButton.click();
    }
}
