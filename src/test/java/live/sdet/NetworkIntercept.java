package live.sdet;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;

public class NetworkIntercept {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @Test
    public void filterOutImages(){
        // this tests filters the images and just shows the page without images.
        playwright = Playwright.create();
        browser= playwright.chromium().launch(new BrowserType.LaunchOptions().setTimeout(60000)
                .setSlowMo(50).setHeadless(false));
        browserContext = browser.newContext();
        page= browser.newPage();
//        page.navigate("https://www.amazon.com");

        page.route("**/*",route -> {
            if(route.request().resourceType().equalsIgnoreCase("Image"))
                route.abort();
            else
                route.resume();

        });
        page.navigate("https://www.amazon.com");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.waitForTimeout(5000);


    }



}
