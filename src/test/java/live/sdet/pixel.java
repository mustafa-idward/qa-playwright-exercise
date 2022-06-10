package live.sdet;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

import com.microsoft.playwright.options.WaitUntilState;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.lang.model.element.Element;

public class pixel {

    @Test
    public void findPixel (){

    Playwright playwright = Playwright.create();
    Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
    Page page = browser.newPage();
    page.navigate("https://www.informazionefiscale.it/");
            page.click("text=ACCETTO");
            page.waitForLoadState(LoadState.NETWORKIDLE);
            page.waitForTimeout(60000);

        int counter = page.locator("#idward-plugin-js").count();
        System.out.println(counter);

    }

    @Test
    public void findPixelFidelity (){

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setDevtools(true).setSlowMo(50).setTimeout(60));
        // setDevtools open the dev tools bypass headless:true
        // Timeout is 30000 milisec by default. We can change it with setTimeout(60000)
        Page page = browser.newPage();
        page.navigate("https://news.fidelityhouse.eu/");
        page.click("text='Accetta'");
        page.waitForLoadState(LoadState.NETWORKIDLE);

        int counter = page.locator("#idward-plugin-js").count();
        System.out.println(counter);

        Object cohortsId = page.evaluate("()=>window.localStorage.getItem('cohortts_id')");
        System.out.println(cohortsId);
        Assert.assertNotNull(cohortsId);

    }
    @Test
    public void findPixelOurTestSite (){

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
        Page page = browser.newPage();
//        page.navigate("https://blog-website-for-cmp-test-lwvpr3pb6a-ew.a.run.app/");
        // instead we can use page.navigate (url [,options]);
        page.navigate("https://blog-website-for-cmp-test-lwvpr3pb6a-ew.a.run.app/", new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
        page.click("text=AGREE");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.waitForTimeout(30000);

//        page.locator("#idward-plugin-js").waitFor();
//        waiting for selector "#idward-plugin-js" to be visible
//        this fails because the selector resolved to hidden <script idw_vendor_id="1" id="idward-plugin-js" idw_envi…></script>
//Note page.click(), fill() auto wait the element to be able to click, fill ... !!!

        /*
       Asynchronous navigation
Clicking an element could trigger asynchronous processing before initiating the navigation.
In these cases, it is recommended to explicitly call Page.waitForNavigation([options], callback). For example:

Navigation is triggered from a setTimeout
Page waits for network requests before navigation
// Using waitForNavigation with a callback prevents a race condition
// between clicking and waiting for a navigation.
page.waitForNavigation(() -> { // Waits for the next navigation
  page.click("div.delayed-navigation"); // Triggers a navigation after a timeout
});

Loading a popup
When popup is opened, explicitly calling Page.waitForLoadState([state, options]) ensures that popup is loaded to the desired state.

Page popup = page.waitForPopup(() -> {
  page.click("a[target='_blank']"); // Opens popup
});
popup.waitForLoadState(LoadState.LOAD);
        */


        page.locator("#idward-plugin-js"); // This does not fail because finds the selector in the hidden script without waitFor()
        int counter = page.locator("#idward-plugin-js").count();
        System.out.println(counter);

        Object cohortsId = page.evaluate("()=>window.localStorage.getItem('cohorts_id')");
        Object CMPList = page.evaluate("()=>window.localStorage.getItem('CMPList')");
        System.out.println(cohortsId);
//        Assert.assertNotNull(cohortsId);
        System.out.println(CMPList);
        Assert.assertNotNull("cmp list: " + CMPList);
//oidc.user:https://aegis.id-ward.com:https://laroma24.it
    }



}
