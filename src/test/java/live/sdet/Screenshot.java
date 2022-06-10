package live.sdet;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class Screenshot {
    @Test
    public void test1 () {
       Playwright playwright = Playwright.create();
//            Browser browser = playwright.chromium().launch();
            // Instead of the above line, We made headless false and slow down the execution by following line.
    Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
    Page page = browser.newPage();
    page.navigate("http://playwright.dev");
    System.out.println(page.title());
    page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("test-data/example2.png")));
    Assert.assertEquals("Fast and reliable end-to-end testing for modern web apps | Playwright", page.title());

    }
}