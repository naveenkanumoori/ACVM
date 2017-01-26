package com.example.naveenkumar.ssdiproject;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by naveenkumar on 11/24/16.
 */
public class Login extends AsyncTask<String,Void,String>{
    LoginActivity activity;
    public Login(LoginActivity activity){
        this.activity = activity;
    }
    @Override
    protected String doInBackground(String... strings) {
        String urlString = strings[0];
        String userid = strings[1];
        String password = strings[2];

        try {

            OkHttpClient client = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("userid",userid)
                    .add("password",password)
                    .build();
            Request request = new Request.Builder()
                    .url(urlString)
                    .post(formBody)
                    .build();

            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String responseString = response.body().string();
            return responseString;
        }catch (Exception e){

        }

        return "";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        activity.onLoginResponse(s);
    }
    interface LoginInterface{
        void onLoginResponse(String response);
    }
}
