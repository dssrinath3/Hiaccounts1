package in.hiaccounts.hinext.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;

import in.hiaccounts.R;
import in.hiaccounts.hinext.application.fragment.FirstView_Fragment;
import in.hiaccounts.hinext.application.fragment.SecondView_Fragment;
import in.hiaccounts.hinext.application.fragment.ThirdView_Fragment;

import static android.graphics.Color.parseColor;

/**
 * Created by Prateek on 26/8/16.
 */
@SuppressWarnings("ResourceType")
public class AppIntroduction_Activity extends AppIntro {

    // activity for introduction for app.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirstView_Fragment first_fragment = new FirstView_Fragment();
        SecondView_Fragment second_fragment = new SecondView_Fragment();
        ThirdView_Fragment third_fragment = new ThirdView_Fragment();


        addSlide(first_fragment);
        addSlide(second_fragment);
        addSlide(third_fragment);


        setBarColor(parseColor(getResources().getString(R.color.colorAppIntro)));
        setSeparatorColor(parseColor(getResources().getString(R.color.colorAppIntro)));
        showSkipButton(false);
        showDoneButton(true);
        showStatusBar(false);
        setIndicatorColor(R.color.colorBlack,R.color.colorWhite);

        /*setNavBarColor(parseColor("#3F51B5"));
        setVibrate(true);
        setVibrateIntensity(30);
        setDepthAnimation();*/
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);

        // calling activity after clcik done from app
        Intent intent = new Intent(AppIntroduction_Activity.this, Activity_Login.class);
        startActivity(intent);
        finish();
    }
}
