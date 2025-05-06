package ui;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import pages.WebFormPage;
import steps.BaseTest;

import java.io.File;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.DragAndDropUtils.dragAndDropFile;

@Story("Upload")
class UploadTests extends BaseTest {
    HomePage homePage;
    WebFormPage webFormPage;

    @Test
    void uploadFileTest() throws InterruptedException {
        String selectFile = System.getProperty("user.dir") + "/src/main/resources/STE In Banner.jpg";

        open("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        SelenideElement fileInput = $("input[name='my-file']");

        fileInput.uploadFile(new File(selectFile));

        Thread.sleep(3000);
        WebElement submit = $(By.xpath("//button[text()='Submit']"));
        submit.click();
        Thread.sleep(3000);

        assertThat(url()).contains("STE+In+Banner.jpg");
    }

    @Test
    void fileInputDragAndDropTest() throws InterruptedException {
        open("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        // Путь к файлу
        File file = new File("src/main/resources/STE In Banner.jpg");
        // Находим элемент куда нужно перетащить файл
        SelenideElement dragTarget = $(By.xpath("//input[@name = 'my-file']"));
        Thread.sleep(3000);
        // Внутри метода JavaScript код для drag & drop
        dragAndDropFile(file, dragTarget);
    }

    @Test
    void fileInputTest() throws InterruptedException {
        homePage = new HomePage(getDriver());
        webFormPage = homePage.openWebFormPage();
        webFormPage.downloadFile();
        webFormPage.submitForm();
        Thread.sleep(3000);

        assertThat(getDriver().getCurrentUrl()).contains("STE+In+Banner.jpg");
    }

    @Test
    void downloadFileRealSiteTest() {
        String filePath = "src/main/resources/STE In Banner.jpg";

        open("https://photoaid.com/ru/ru/tools/greyscale");
        SelenideElement fileInput = $("input[type='file']");
        fileInput.uploadFile(new File(filePath));

        SelenideElement uploadButton = $("a[download]");
        uploadButton.shouldBe(visible);
    }

    @Test
    void simpleDragAndDropTest() {
        open("https://photoaid.com/ru/ru/tools/greyscale");
        // Путь к файлу
        File file = new File("src/main/resources/STE In Banner.jpg");
        // Находим элемент куда нужно перетащить файл
        SelenideElement dragTarget = $(By.xpath("//section"));
        // Внутри метода JavaScript код для drag & drop
        dragAndDropFile(file, dragTarget);
    }
}
