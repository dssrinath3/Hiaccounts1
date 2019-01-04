package in.hiaccounts.hinext.application.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.YoYo;
import com.github.jorgecastillo.FillableLoader;
import com.github.jorgecastillo.FillableLoaderBuilder;
import com.github.jorgecastillo.State;
import com.github.jorgecastillo.clippingtransforms.PlainClippingTransform;
import com.github.jorgecastillo.listener.OnStateChangeListener;
import com.nineoldandroids.animation.Animator;
import com.vistrav.ask.Constants;

import in.hiaccounts.R;
import in.hiaccounts.hinext.company_setup.activity.Activity_CompanySubcription;
import in.hiaccounts.hinext.restaurant.activity.RestuarantActivity;
import in.hiaccounts.model.ConfigSplash;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.Flags;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UIUtil;
import in.hiaccounts.utility.UtilView;
import in.hiaccounts.utility.ValidationUtil;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by Prateek on 2/9/2017.
 */

 public class Activity_Splash extends AwesomeSplash {

    /**
     * Duration of wait
     **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    SharedPreference sharedPreference;


    @Override
    public void initSplash(ConfigSplash configSplash) {
    }

    @Override
    public void animationsFinished() {
        //wait 5 sec and then go back to MainActivity
        final Activity a = this;
        animationsFinishedData();
    }


    /**
     * Called when the activity is first created.
     */
   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_splash);


    }*/



    private void animationsFinishedData() {

        sharedPreference = SharedPreference.getInstance(Activity_Splash.this);

        if (sharedPreference.isFirstTimeLaunch()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(Activity_Splash.this, AppIntroduction_Activity.class);
                    Activity_Splash.this.startActivity(mainIntent);
                    Activity_Splash.this.finish();

                    sharedPreference.setFirstTimeLaunch(false);
                }
            }, SPLASH_DISPLAY_LENGTH);
        }else {




            //String app_module = sharedPreference.getData(Constant.SERVER_APP_MODULE);
            String accessToken = sharedPreference.getData(Constant.ACCESSTOKEN);

            if (!accessToken.equals("")) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String redirectUrl = sharedPreference.getData(Constant.SERVER_URL);
                        if (redirectUrl!=null) {
                            String pageNumber=sharedPreference.getData(Constant.NAVIGATION_REDIRECTPAGE);
                            if (pageNumber!=null) {
                                if (pageNumber.equals(Constant.PAGE_DAHSBOARD)) {
                                    String isRestraUser=sharedPreference.getData(Constant.ISRESTARUSER);
                                    if (isRestraUser.equals("true")){
                                        Intent intent = new Intent(Activity_Splash.this, RestuarantActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        Intent mainIntent = new Intent(Activity_Splash.this, NavigationDrawer_Activity.class);
                                        mainIntent.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_DASHBOARD);
                                        Activity_Splash.this.startActivity(mainIntent);
                                        Activity_Splash.this.finish();
                                    }
                                } else {
                                    Intent intent = new Intent(Activity_Splash.this, Activity_CompanySubcription.class);
                                    intent.putExtra(Constant.NAVIGATION_REDIRECTPAGE, pageNumber);
                                    startActivity(intent);
                                    finish();
                                }
                            }else {
                                String isRestraUser=sharedPreference.getData(Constant.ISRESTARUSER);
                                if (isRestraUser.equals("true")){
                                    Intent intent = new Intent(Activity_Splash.this, RestuarantActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Intent mainIntent = new Intent(Activity_Splash.this, NavigationDrawer_Activity.class);
                                    mainIntent.putExtra(Constant.NAVIGATION_GROUPHEADER, Constant.NAVIGATION_DASHBOARD);
                                    Activity_Splash.this.startActivity(mainIntent);
                                    Activity_Splash.this.finish();
                                }

                            }

                        }else {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent mainIntent = new Intent(Activity_Splash.this, Activity_Login.class);
                                    Activity_Splash.this.startActivity(mainIntent);
                                    Activity_Splash.this.finish();
                                }
                            }, SPLASH_DISPLAY_LENGTH);
                        }


                    }
                }, SPLASH_DISPLAY_LENGTH);
            }
            if (accessToken.equals("")) {
                // String serverDetail = sharedPreference.getData(Constant.SERVERDETAIL);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent mainIntent = new Intent(Activity_Splash.this, Activity_Login.class);
                        Activity_Splash.this.startActivity(mainIntent);
                        Activity_Splash.this.finish();
                    }
                }, SPLASH_DISPLAY_LENGTH);

            }
            /*if (serverDetail != null && !serverDetail.equals("")) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent mainIntent = new Intent(Activity_Splash.this, Activity_Login.class);
                        Activity_Splash.this.startActivity(mainIntent);
                        Activity_Splash.this.finish();
                    }
                }, SPLASH_DISPLAY_LENGTH);
            }else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent mainIntent = new Intent(Activity_Splash.this, Activity_ServerConfiguration.class);
                        Activity_Splash.this.startActivity(mainIntent);
                        Activity_Splash.this.finish();


                    }
                }, SPLASH_DISPLAY_LENGTH);
*/
        }

    }





    }

