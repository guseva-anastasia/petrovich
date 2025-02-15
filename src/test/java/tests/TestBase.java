package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import config.CredentialsConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.ClientConfig;

import java.time.Duration;
import java.util.Map;

import static java.time.Duration.ofMinutes;

public class TestBase {

    public static final String TEXTBOX = "/text-box";
    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);

    @BeforeAll
    static void configurationBrowser() {
       Configuration.browser = System.getProperty("browser");
       Configuration.browserVersion = System.getProperty("browserVersion");
       Configuration.browserSize = System.getProperty("browserSize","1920x1080");
        Configuration.baseUrl = "https://petrovich.ru";
        Configuration.pageLoadStrategy = "eager";
      Configuration.remote = "https://"+ config.login() + ":" + config.password() + "@" + System.getProperty("remoteHost") + "/wd/hub";
       DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability("selenoid:options", Map .<String, Object>of(
              "enableVNC", true,
               "enableVideo", true
      ));
      Configuration.remoteConnectionTimeout = 5000;
      Configuration.remoteReadTimeout = 5000;
      Configuration.browserCapabilities = capabilities;

      SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

   @BeforeEach
   void addListener(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    public void afterEach() {
       Attach.addScreenshot();
       Attach.addVideo();
       if (!System.getProperty("browser").equalsIgnoreCase("firefox")) {
           Attach.pageSource();
           Attach.getBrowserLogs();
       }
        Selenide.closeWebDriver();

    }
}

