package in.hiaccounts.hinext.application.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import in.hiaccounts.R;

/**
 * Created by Admin on 9/27/2017.
 */

public class Activity_SignUp extends AppCompatActivity {
    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("http://bit.ly/2xLGuwN");
        //webView.loadUrl("https://www.hiaccounts.in/register/");

    }
}
