package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.DownloadFilesPage;
import pages.HomePage;
import steps.AllureSteps;
import steps.BaseTest;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pages.DownloadFilesPage.DOWNLOAD_FILES_FORM_TITLE;
import static pages.HomePage.BASE_URL;

@Story("Download")
class DownloadTests extends BaseTest {
    HomePage homePage;
    DownloadFilesPage downloadFilesPage;
    AllureSteps allureSteps = new AllureSteps();

    @BeforeEach
    void setupPage() {
        homePage = new HomePage(getDriver());
        downloadFilesPage = homePage.openDownloadFilesPage();
    }

    @Test
    @DisplayName("Проверка Download files page")
    void openLoginFormTest() {
        String currentUrl = downloadFilesPage.getCurrentUrl();
        String title = downloadFilesPage.getTitle();
        String downloadFilesPageFormUrl = downloadFilesPage.getUrl();

        assertEquals(BASE_URL + downloadFilesPageFormUrl, currentUrl);
        assertEquals(DOWNLOAD_FILES_FORM_TITLE, title);
    }

    @Test
    @DisplayName("Проверка загрузки логотипа WebDriver Manager Logo")
    void downloadWebDriverManagerLogoTest() throws IOException {
        allureSteps.download(downloadFilesPage.getWebDriverManagerLogoLink().getAttribute("href"),
                downloadFilesPage.webDriverManagerLogoFile());

        assertThat(downloadFilesPage.webDriverManagerLogoFile()).exists();
    }

    @Test
    @DisplayName("Проверка загрузки текстового файла WebDriver Manager Doc")
    void downloadWebDriverManagerDocTest() throws IOException {
        allureSteps.download(downloadFilesPage.getWebDriverManagerDocLink().getAttribute("href"),
                downloadFilesPage.webDriverManagerDocFile());

        assertThat(downloadFilesPage.webDriverManagerDocFile()).exists();
    }

    @Test
    @DisplayName("Проверка загрузки логотипа Selenium-Jupiter logo")
    void downloadSeleniumJupiterLogoTest() throws IOException {
        allureSteps.download(downloadFilesPage.getSeleniumJupiterLogoLink().getAttribute("href"),
                downloadFilesPage.seleniumJupiterLogoFile() );

        assertThat(downloadFilesPage.seleniumJupiterLogoFile()).exists();
    }

    @Test
    @DisplayName("Проверка загрузки текстового файла Selenium-Jupiter Doc")
    void downloadSeleniumJupiterDocTest() throws IOException {
        allureSteps.download(downloadFilesPage.getSeleniumJupiterDocLink().getAttribute("href"),
                downloadFilesPage.seleniumJupiterDocFile());

        assertThat(downloadFilesPage.seleniumJupiterDocFile()).isEmpty();
    }

    @Test
    void downloadButtonFolderTest() throws InterruptedException {
        String filePath = "src/main/resources/STE In Banner.jpg";

        open("https://photoaid.com/ru/ru/tools/greyscale");
        SelenideElement fileInput = $("input[type = 'file']");
        fileInput.uploadFile(new File(filePath));
        Thread.sleep(3000);

        // Указываем режим скачивания файлов
        Configuration.fileDownload = FileDownloadMode.FOLDER;
        // Указываем директорию для сохранения скачанных файлов
        Configuration.downloadsFolder = "src/main/resources";
        // Находим элемент кнопки для скачивания
        SelenideElement downloadButton = $(By.xpath("//a[@download]"));
        // Кликаем по кнопке для скачивания и сохраняем файл
        File downloadedFile = downloadButton.download();
        // Проверяем, что файл был скачан успешно
        assertThat(downloadedFile).exists();
    }
}
