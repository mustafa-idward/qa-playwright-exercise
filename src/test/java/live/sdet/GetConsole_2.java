package live.sdet;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

import java.util.ArrayList;
import java.util.List;

public class GetConsole_2 {

    static Page page;

    public static List<String> getErrors(){

        List<String> errorList = new ArrayList<>();
        //String errors[] = new String[3];
        page.onConsoleMessage(msg->{
            if("error".equals(msg.type()))
            {
                System.out.println(msg.text());
                errorList.add(msg.text());
            }

        });
        return errorList;
    }

    public static List<String> getAllMessages(){
//        System.out.println(page.onConsoleMessage(""););

        List<String> allMessages = new ArrayList<>();
        page.onConsoleMessage(msg->{
            System.out.println(msg.text());
            allMessages.add(msg.text());
            allMessages.add("first");
            }
        );
        allMessages.add("second");
        return allMessages;
    }

    public static String getLocationsOfErrors(){
        return "";
    }



    public static void main(String[] args) {
        Playwright playwright;
        Browser browser;
        BrowserContext browserContext;

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setChannel("chrome").setHeadless(false));
        browserContext = browser.newContext();
        page = browserContext.newPage();

//        getErrors();
        getAllMessages();
        System.out.println(".... Before" + getAllMessages());
        page.navigate("https://blog-website-for-cmp-test-lwvpr3pb6a-ew.a.run.app/");
        page.click("text=AGREE");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.click("text=HOME");
        page.waitForLoadState(LoadState.NETWORKIDLE);

        System.out.println(".... After" + getAllMessages());


    }
}
