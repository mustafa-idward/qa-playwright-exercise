package live.sdet;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicReference;

public class GetConsoleMessages {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeTest
    public void setUp(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setChannel("chrome").setHeadless(false));
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @Test
    public void printConsoleErrors(){
        final String[] texts = {""};
        page.onConsoleMessage(msg -> {
            if ("error".equals(msg.type())) {
                texts[0] = msg.text();
//                texts[1] = msg.text();
                System.out.println("Error text: " + msg.text());
            }
        });
        page.navigate("https://blog-website-for-cmp-test-lwvpr3pb6a-ew.a.run.app/");
        page.click("text=AGREE");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        System.out.println("allErrorTexts are \n" + texts[0] );
        Assert.assertFalse(texts[0].contains("idw"));
        Assert.assertFalse(texts[0].contains("id-w"));
    }
    @Test
    public void printConsoleAllSportando(){
        page.onConsoleMessage(msg -> System.out.println(msg.text()));
        page.navigate("https://sportando.basketball/");
        page.waitForTimeout(10000);
    }
    @Test
    public void printConsoleAll(){
        page.onConsoleMessage(msg -> System.out.println(msg.text()));
        page.navigate("https://blog-website-for-cmp-test-lwvpr3pb6a-ew.a.run.app/");
        page.click("text='AGREE'");
    }
    @Test
    public void printAllTextsWithLocations (){
        page.onConsoleMessage(msg->
            System.out.println("text=>"+msg.text()+ " locs => " + msg.location()));

        page.navigate("https://blog-website-for-cmp-test-lwvpr3pb6a-ew.a.run.app/");
        page.click("text=AGREE");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.click("text=HOME");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }
    @Test
    public void printLocationsOfJustErrors (){
        page.onConsoleMessage(msg-> {
            if ("error".equals(msg.type())) {
                System.out.println("error ==> "+msg.text()+"loc ==>> " + msg.location());
            }
        });

        page.navigate("https://blog-website-for-cmp-test-lwvpr3pb6a-ew.a.run.app/");
        page.click("text=AGREE");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }



}
