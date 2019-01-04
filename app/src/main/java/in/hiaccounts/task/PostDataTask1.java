package in.hiaccounts.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import in.hiaccounts.R;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 29/7/16.
 */
public class PostDataTask1 extends AsyncTask<String,String,String> {

    private Context context;
    private AsyncTaskCompleteListener<String> listener;
    boolean dialogE = true;
    Activity activity;
    private ProgressDialog Dialog;
    SharedPreference sharedPreference;

    public PostDataTask1(Activity activity, AsyncTaskCompleteListener<String> listener, boolean dialog)
    {
        this.activity = activity;
        dialogE = dialog;
        this.listener = listener;
        sharedPreference= SharedPreference.getInstance(activity);
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
    protected String doInBackground(String... strings) {
        String JsonResponse = null;
        String JsonDATA = strings[0];
        String uri=strings[1];
        String function_call=strings[2];
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {

            URL url = new URL(uri);
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");

            //set headers and method
            UtilView.showLogCat("@Flow","Url Request "+url+" & Function Call : "+function_call);
         if (!function_call.equals(Constant.FUNCTION_LOGIN)) {

                String accessToken = sharedPreference.getData(Constant.ACCESSTOKEN);
                if (accessToken.equals("")) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put(Constant.ACCESSTOKEN, "invalid");
                        JsonResponse = jsonObject.toString();

                    } catch (Exception e) {

                        UtilView.showLogCat("@Flow Post Doing accesstoken Exception ",""+e.toString());
                    }
                    return JsonResponse;
                }else {
                    urlConnection.setRequestProperty("SaaS-Authorization", "59f8cab520442fc12dc511057929a8e772c00a3e6e696cf5179d82c96bc40f21");
                    UtilView.showLogCat("Cookie","SaaS-Authorization:"+accessToken);
                }

            }
            if (JsonDATA.equals("")){

                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setRequestProperty("Accept", "text/plain");
            }else {
                UtilView.showLogCat("@Flow Post Doing ",""+JsonDATA);
                urlConnection.setRequestProperty("Content-Type", "application/json");

                if (!function_call.equals(Constant.FUNTION_ADVANCEPARTIALPAYMENT )){
                    urlConnection.setRequestProperty("Accept", "application/json");
                }


                Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
                writer.write(JsonDATA);
                writer.close();

            }


            // json data

            try {
                int responseCode=urlConnection.getResponseCode();
                Log.e("@Flow","Response Code "+responseCode);
                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    InputStream inputStream = urlConnection.getInputStream();
                    //input stream
                    StringBuffer buffer = new StringBuffer();
                    if (inputStream == null) {
                        // Nothing to do.
                        return null;
                    }
                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    String inputLine;
                    while ((inputLine = reader.readLine()) != null)
                        buffer.append(inputLine + "\n");
                    if (buffer.length() == 0) {
                        // Stream was empty. No point in parsing.

                        return activity.getString(R.string.error_rsonsecode204);
                    }
                    JsonResponse = buffer.toString();
                    return JsonResponse;
                }else {
                    return activity.getString(R.string.error_rsonsecode204);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
                Log.e("@Flow", "PostDataTask doingBackground NullPointerException " + e.toString());
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("@Flow", "PostDataTask doingBackground Exception " + e.toString());
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("@Flow", "PostDataTask Error closing stream", e);
                }
            }
        }
        return null;
    }
    @Override
    protected void onPostExecute(String result)
    {
        if (Dialog != null && Dialog.isShowing()) {

            Dialog.dismiss();
        }

        super.onPostExecute(result);
        Log.e("@Flow ", "PostDataTask onPostExecute call result from server: "+result);
        if (result!=null && result.equals(Constant.SESSION_LOST)){
            UtilView.gotToLogin(activity);
        }else if (result!=null && result.equals(Constant.SESSION_INVALID)){
            UtilView.gotToLogin(activity);
        }else {
            listener.onTaskComplete(result);
        }
    }

}