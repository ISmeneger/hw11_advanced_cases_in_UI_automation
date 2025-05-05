package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

public class DownloadFilesPage extends BasePage {
    private static final String DOWNLOAD_FILES_FORM_URL = "download.html";
    public static final String DOWNLOAD_FILES_FORM_TITLE = "Download files";


    @FindBy(xpath = "//a[@download = 'webdrivermanager.png']")
    WebElement webDriverManagerLogo;

    @FindBy(xpath = "//a[@download = 'webdrivermanager.pdf']")
    WebElement webDriverManagerDoc;

    @FindBy(xpath = "//a[@download = 'selenium-jupiter.png']")
    WebElement seleniumJupiterLogo;

    @FindBy(xpath = "//a[@download = 'selenium-jupiter.pdf']")
    WebElement seleniumJupiterDoc;

    public DownloadFilesPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get download files page url")
    public String getUrl() {
        return DOWNLOAD_FILES_FORM_URL;
    }

    public WebElement getWebDriverManagerLogoLink() {
        return webDriverManagerLogo;
    }

    @Step("Download web driver manager logo file")
    public File webDriverManagerLogoFile() {
        return new File(".", "webdrivermanager.png");
    }

    public WebElement getWebDriverManagerDocLink() {
        return webDriverManagerDoc;
    }

    @Step("Download web driver manager doc file")
    public File webDriverManagerDocFile() {
        return new File(".", "webdrivermanager.pdf");
    }

    public WebElement getSeleniumJupiterLogoLink() {
        return seleniumJupiterLogo;
    }

    @Step("Download selenium jupiter logo file")
    public File seleniumJupiterLogoFile() {
        return new File(".", "selenium-jupiter.png");
    }

    public WebElement getSeleniumJupiterDocLink() {
        return seleniumJupiterDoc;
    }

    @Step("Download selenium jupiter doc file")
    public File seleniumJupiterDocFile() {
        return new File(".", "selenium-jupiter.pdf");
    }
}
