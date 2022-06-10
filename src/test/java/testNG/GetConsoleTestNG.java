package testNG;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class GetConsoleTestNG {
    Playwright pw;
    Browser br;
    BrowserContext browserContext;
    Page page;
// Note: I can not implement OOF IDW login with playwright.
// The focus is always all opened tabs, windows, browsers

    @BeforeTest
    public void setup(){
        pw = Playwright.create();
        br = pw.webkit().launch(new BrowserType.LaunchOptions()
                .setHeadless(false).setSlowMo(50));
        browserContext= br.newContext();
        page = browserContext.newPage();
    }

    @Test
    public void getAllMessages(){
        page.navigate("https://blog-website-for-cmp-test-lwvpr3pb6a-ew.a.run.app/");
        page.locator("text=AGREE").click();
        Page page2 = browserContext.newPage();
        page2.navigate("http://cevikpansiyon.com/");

        page.bringToFront();

//        page.mainFrame(
        page.onConsoleMessage(msg->{
            System.out.println("message: " + msg.text());
//            Assert.assertTrue(!msg.text().contains("idw"),
//                    "\n Unfortunately there are messages containing idw");
        });
        page.reload();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("test-data/ss/page2_TestNG.png")));
       page.waitForTimeout(5000);

    }


    @AfterTest
    public void tearDown(){
        page.close();
     // page2.close();
        browserContext.close();
        br.close();
        pw.close();

    }



}
