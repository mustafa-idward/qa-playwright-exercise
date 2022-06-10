package live.sdet;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class day3 {

    Playwright playwright;
    Browser browser;
    Page page;
    @BeforeTest
    public void setup (){
     playwright = Playwright.create();
     browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
     page = browser.newPage();
    }
// Asagidaki AfterTest acik olsa da olmasa da page kapaniyor.
//    @AfterTest
//    public void tearDown(){
//    page.close();
//    }
    @Test
    public void verifyLogin (){

        page.navigate("https://app.vwo.com/#/login");

        page.locator("#login-username").fill("test");
        page.locator("#login-password").fill("test123455");
        //page.locator("#js-login-btn").click(); // instead the following is more user-friendly in playwright
        page.click("#js-login-btn");

    }


    @Test
    public void testScroll (){
    Playwright playwright = Playwright.create();
    Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
    Page page = browser.newPage();
    page.navigate("http://www.uitestingplayground.com/");
    page.click("text='Scrollbars'");
    String hidButAtt = page.locator("#hidingButton ").getAttribute("fff"); // attribute olmadigi icin null verdi
    String hidButText = page.locator("#hidingButton ").innerText();//Hiding Button
    String hidButContent = page.locator("#hidingButton ").textContent();//Hiding Button
        System.out.println("att " + hidButAtt); // attribute olmadigi icin null verdi
        System.out.println("inner text " + hidButText);//Hiding Button
        System.out.println("text content " + hidButContent);//Hiding Button
    page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("test-data/before click.png")));//buton gorunmuyor
    page.click("#hidingButton");
    page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("test-data/after click.png")));//Buton gorunuyor

    }
}
