package live.sdet;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.nio.file.Paths;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Pixel_Devtools {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeTest
    public void setUp(){
     playwright = Playwright.create();
//     browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false).setSlowMo(50));

        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().
                setChannel("chrome").setHeadless(false).setSlowMo(50));
        browserContext = browser.newContext();
        page = browserContext.newPage();

    }
    @Test
    public void testOurBlog (){
        String url = "https://blog-website-for-cmp-test-lwvpr3pb6a-ew.a.run.app/";
        page.navigate(url, new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
        page.click("text=AGREE");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        Page page2 = browserContext.newPage();
        page.waitForTimeout(10000);
        page.screenshot(new Page.ScreenshotOptions().
                setPath(Paths.get("test-data/ss/page.png")));


//        for (int i = 1; i <6 ; i++) {
//            if (cohortIds==null&&oidcUser==null) {
//
//            }
//
//        }

        Object oidcUserKey = "oidc.user:https://aegis.id-ward.com:"+url;
        System.out.println("oidc key= "+oidcUserKey);
        Object oidcUserKey2 = "oidc.user:https://aegis-lwvpr3pb6a-ew.a.run.app:https://blog-website-for-cmp-test-lwvpr3pb6a-ew.a.run.app";

//        Object cohortIds = page.evaluate("()=>window.localStorage.getItem('cohort_ids')");
        Object CMPList = page.evaluate("()=>window.localStorage.getItem('CMPList')");
//    Object oidcUser = page.evaluate("()=>window.localStorage.getItem('oidcUserKey')" );
        Object oidcUser = page.evaluate("()=>window.localStorage.getItem('oidc.user:https://aegis-lwvpr3pb6a-ew.a.run.app:https://blog-website-for-cmp-test-lwvpr3pb6a-ew.a.run.app')" );
        System.out.println("oidc user value = " + oidcUser);
//        System.out.println("cohort ids = "+cohortIds);
        System.out.println("CMP list = " + CMPList);

        int counter = page.locator("#idward-plugin-js").count();
        System.out.println("counter= " + counter);

        assertTrue(counter>=1);
        Assert.assertEquals(counter, 1, "idward script can not be found");
        Assert.assertNotNull(oidcUser);
//        Assert.assertNotNull(cohortIds);
        Assert.assertNotNull(CMPList);

    }

    @Test
    public void findPixelLaromaWebSite (){
       String url = "https://laroma24.it";

//        page.navigate(url);
        // instead we can use page.navigate (url [,options]);
        page.navigate(url, new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
        page.click("text=AGREE");
        page.waitForLoadState(LoadState.NETWORKIDLE);

//        page.locator("#idward-plugin-js").waitFor();
//        waiting for selector "#idward-plugin-js" to be visible
//        this fails because the selector resolved to hidden <script idw_vendor_id="1" id="idward-plugin-js" idw_envi…></script>
//Note page.click(), fill() auto wait the element to be able to click, fill ... !!!

        page.locator("#idward-plugin-js"); // This does not fail because finds the selector in the hidden script without waitFor()
        int counter = page.locator("#idward-plugin-js").count();
        System.out.println(counter);

        Object oidcUserKey = "oidc.user:https://aegis.id-ward.com:"+url;
        System.out.println("oidc key= "+oidcUserKey);

        //oidc.user:https://aegis.id-ward.com:https://laroma24.it
        Object oidcUser = page.evaluate("()=>window.localStorage.getItem('oidcUserKey')" );
        Object cohortIds = page.evaluate("()=>window.localStorage.getItem('cohort_ids')");
        Object CMPList = page.evaluate("()=>window.localStorage.getItem('CMPList')");

        System.out.println("oidc user value = " + oidcUser);
        System.out.println("cohort ids = "+cohortIds);
        System.out.println("CMP list = " + CMPList);

        Assert.assertEquals(counter, 1, "idward script can not be found");
        Assert.assertNotNull(oidcUser);
        Assert.assertNotNull(cohortIds);
        Assert.assertNotNull(CMPList);


//        for (int i = 1; i < 6; i++) {
//            if (cohortIds==null&&oidcUser==null) {
//
//            }
//        }
//    page.close();
    }

    @Test
    public void testFourTimesTestWebsite (){
//        String url = "https://laroma24.it";
        String url = "https://blog-website-for-cmp-test-lwvpr3pb6a-ew.a.run.app";
        page.navigate(url, new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
        page.click("text='AGREE'");
        page.waitForLoadState(LoadState.NETWORKIDLE);
//        page.waitForTimeout(1000);

        Object cohortIds = page.evaluate("()=>window.localStorage.getItem('cohort_ids')");
        Object CMPList = page.evaluate("()=>window.localStorage.getItem('CMPList')");
        Object oidcUser = page.evaluate("()=>window.localStorage.getItem('oidc.user:https://aegis-lwvpr3pb6a-ew.a.run.app:https://blog-website-for-cmp-test-lwvpr3pb6a-ew.a.run.app')" );
        int refreshNumber=0;
        for (int i = 1; i <4 ; i++) {
            if (oidcUser==null) {
                page.reload();
                page.waitForLoadState(LoadState.NETWORKIDLE);
                page.waitForTimeout(20000);
                 cohortIds = page.evaluate("()=>window.localStorage.getItem('cohort_ids')");
                 CMPList = page.evaluate("()=>window.localStorage.getItem('CMPList')");
                 oidcUser = page.evaluate("()=>window.localStorage.getItem('oidc.user:https://aegis-lwvpr3pb6a-ew.a.run.app:https://blog-website-for-cmp-test-lwvpr3pb6a-ew.a.run.app')" );
             refreshNumber = i;
            }
        }

        Object consoleErr = page.evaluate("()=>window.console.error");
        System.out.println("console err = " + consoleErr);

        System.out.println("oidc user value = " + oidcUser);
        System.out.println("cohort ids = "+cohortIds);
        System.out.println("CMP list = " + CMPList);

        int counter = page.locator("#idward-plugin-js").count();
        System.out.println("counter= " + counter);
        System.out.println("refresh number is " + refreshNumber);
        assertTrue(counter>=1);
        Assert.assertEquals(counter, 1, "idward script can not be found");
        Assert.assertNotNull(oidcUser);
//        Assert.assertNotNull(cohortIds);
        Assert.assertNotNull(CMPList);

    }




}
