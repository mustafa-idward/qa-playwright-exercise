package live.sdet;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.util.List;

public class Console {
    Playwright playwright;
    Browser browser;
    Page page;


    @BeforeTest
    public void setUp(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setChannel("msedge").setHeadless(false).setSlowMo(50));
        page = browser.newPage();

        }
@Test
public void printConsoleFromDiscussions(){
        BrowserContext browserContext = browser.newContext();
        Page page2 = browserContext.newPage();
    page2.navigate("https://sportando.basketball/",
            new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
    System.out.println(page2.context().storageState());

       page2.waitForTimeout(5000);
        page2.onConsoleMessage(msg -> {
        if ("error".equals(msg.type()))
            System.out.println("Error text: " + msg.text());
    });


    }

    @Test
    public void printConsole(){
//        page.navigate("https://10elotto5minuti.com/");
        String url = "https://blog-website-for-cmp-test-lwvpr3pb6a-ew.a.run.app/";
        page.navigate(url, new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
        page.click("text='AGREE'");


        // Listen for all System.out.printlns
        page.onConsoleMessage(msg -> System.out.println(msg.text()));

// Listen for all console events and handle errors
        page.onConsoleMessage(msg -> {
            if ("error".equals(msg.type()))
                System.out.println("Error text: " + msg.text());
        });

// Get the next System.out.println
        ConsoleMessage msg = page.waitForConsoleMessage(() -> {
            // Issue console.log inside the page
            page.evaluate("console.log('hello', 42, { foo: 'bar' });");
        });

// Deconstruct console.log arguments
        msg.args().get(0).jsonValue(); // hello
        msg.args().get(1).jsonValue(); // 42

    ConsoleMessage consoleMessage = new ConsoleMessage() {
        @Override
        public List<JSHandle> args() {
            return null;
        }

        @Override
        public String location() {
            return null;
        }

        @Override
        public String text() {
            return null;
        }

        @Override
        public String type() {
            return null;
        }
    };

        System.out.println("assumed console message (but wrong) is \n" + consoleMessage.toString());
    }
    }

