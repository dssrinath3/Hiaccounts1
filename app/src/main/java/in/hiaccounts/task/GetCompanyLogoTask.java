package in.hiaccounts.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import in.hiaccounts.R;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.UtilView;


/**
 * Created by Prateek on 1/11/2016.
 */
public class GetCompanyLogoTask extends AsyncTask<String, InputStream, Bitmap> {
    boolean dialogE = true;
    Activity activity;
    private AsyncTaskCompleteListener1<Bitmap> listener;
    private ProgressDialog Dialog;


    public GetCompanyLogoTask(Activity activity, AsyncTaskCompleteListener1<Bitmap> listener, boolean dialog) {
        this.activity = activity;
        dialogE = dialog;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (dialogE) {
            if (Dialog == null) {
                Dialog = new ProgressDialog(activity);
                Dialog.setMessage("Please Wait..");
                Dialog.setCancelable(false);
                Dialog.show();
            }

        }

    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String JsonStr = null;
        Bitmap bmp = null;
        
        try {

            String url = strings[0];
            String function_call = strings[1];

            UtilView.showLogCat("@Flow","Url Request "+url+" & Function Call : "+function_call);
            urlConnection = (HttpURLConnection) new URL(url).openConnection();
            urlConnection.setConnectTimeout(10000);
            urlConnection.setRequestMethod("GET");
            urlConnection.setUseCaches(true);
            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();
            Log.e("@Flow", "Response Code " + responseCode);
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                    // Read the input stream into ic_category_leftview String
                InputStream  inputStream = urlConnection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                bmp = BitmapFactory.decodeStream(bufferedInputStream);

                        return bmp;
                }
            } catch (Exception e) {

        }
        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        if (Dialog != null && Dialog.isShowing()) {
            Dialog.dismiss();
        }
        listener.onTaskComplete(result);
    }
}
