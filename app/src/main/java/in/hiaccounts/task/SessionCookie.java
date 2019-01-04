package in.hiaccounts.task;

import android.util.Log;

import java.util.List;

/**
 * Created by Prateek on 7/11/2017.
 */

public class SessionCookie {

    public static List<String> cookies;

    public static List<String> getCookies() {
        Log.e("@Flow","SeesionCookies getCookies call");
        return cookies;
    }

    public static void setCookies(List<String> cooks) {
        Log.e("@Flow","SeesionCookies setCookies call");
        cookies = cooks;
    }
}
