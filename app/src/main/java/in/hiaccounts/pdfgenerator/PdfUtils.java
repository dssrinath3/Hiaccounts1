package in.hiaccounts.pdfgenerator;

import android.app.Activity;
import android.os.Environment;
import android.os.StrictMode;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import in.hiaccounts.hinext.controlpanel.model.company_setup.compnayinformation.CompanyData;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 6/1/2017.
 */

public class PdfUtils {
    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public static File createFile() {
        File pdfDir = new File(Environment.getExternalStorageDirectory(),Constant.FILEDIRECTORY);
        if (!pdfDir.exists()) {
            pdfDir.mkdir();
        }
        //Now create the name of your PDF file that you will generate
        File pdfFile = new File(pdfDir, Constant.FILENAME);
        return pdfFile;
    }
    public static File createFile(String fileName,String groupDirectory,String childDirectory) {
        File pdfChildDir=null;
        File pdfGroupDir=null;
        File pdfDir = new File(Environment.getExternalStorageDirectory(),Constant.FILEDIRECTORY);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (!pdfDir.exists()){
            pdfDir.mkdir();
            pdfGroupDir = new File(pdfDir,groupDirectory);
            if (!pdfGroupDir.exists()){
                pdfGroupDir.mkdir();
            }
            pdfChildDir = new File(pdfGroupDir,childDirectory);
            if (!pdfChildDir.exists()){
                pdfChildDir.mkdir();
            }
        }else {
            pdfGroupDir = new File(pdfDir,groupDirectory);
            if (!pdfGroupDir.exists()){
                pdfGroupDir.mkdir();
            }
            pdfChildDir = new File(pdfGroupDir,childDirectory);
            if (!pdfChildDir.exists()){
                pdfChildDir.mkdir();
            }
        }

        File pdfFile = new File(pdfChildDir, fileName);
        return pdfFile;


      /*  if (!pdfDir.exists()) {
            pdfDir.mkdir();
            pdfChildDir = new File(pdfDir,fileDirectory);
            if (!pdfChildDir.exists()){
                pdfChildDir.mkdir();
            }
        }else {
            pdfChildDir = new File(pdfDir,fileDirectory);
            if (!pdfChildDir.exists()){
                pdfChildDir.mkdir();
            }
        }*/
        //Now create the name of your PDF file that you will generate

    }

    public static File getFile(){
        File file = null;

        file=new File(Environment.getExternalStorageDirectory() + "/" + Constant.FILEDIRECTORY + "/" + Constant.FILENAME);

        return file;
    }

    public static File getFile(String fileName,String groupDirectory,String childDirectory){
        File file = null;

        file=new File(Environment.getExternalStorageDirectory() + "/" + Constant.FILEDIRECTORY+"/"+groupDirectory+"/"+childDirectory + "/" + fileName);

        return file;
    }

    public static InputStream getLogoInputStream(Activity activity, ProgressView progressBar) {

        SharedPreference sharedPreference = SharedPreference.getInstance(activity);
        InputStream inputStream = null;


        if (sharedPreference != null) {

            String applicationDataJson = sharedPreference.getData(Constant.COMPANYDATA);
            String imageLogoUrl;

            if (applicationDataJson != null) {
                Gson gson = new Gson();
                String serverUrl= UtilView.getUrl(activity);

                CompanyData companyData = gson.fromJson(applicationDataJson, CompanyData.class);
                if (companyData!=null){
                if (companyData.getFileName() != null) {
                    imageLogoUrl =serverUrl + companyData.getFileName();
                    HttpURLConnection urlConnection = null;
                    try {
                        urlConnection = (HttpURLConnection) new URL(imageLogoUrl).openConnection();
                        urlConnection.setConnectTimeout(10000);
                        urlConnection.setRequestMethod("GET");
                        urlConnection.connect();
                        try {
                            int responseCode = urlConnection.getResponseCode();

                            if (responseCode == HttpsURLConnection.HTTP_OK) {
                                // Read the input stream into ic_category_leftview String
                                inputStream = urlConnection.getInputStream();



                                return inputStream;
                            }
                        } catch (NullPointerException e) {
                            return null;
                        }
                    } catch (Exception e) {
                        return null;
                    }
                }else {
                    return null;
                }
                }else {
                   return null;
                }
            }
        }
        return inputStream;

    }
}
