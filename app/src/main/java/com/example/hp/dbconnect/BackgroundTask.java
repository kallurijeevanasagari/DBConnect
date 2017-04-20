package com.example.hp.dbconnect;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by WIN 10 on 22-08-2016.
 */
public class BackgroundTask extends AsyncTask<String,String,String>{
AlertDialog alertDialog;
    Context ctx;
    BackgroundTask(Context ctx){
        this.ctx=ctx;
    }
    @Override
    protected void onPreExecute() {
     alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information....");
    }

    @Override
    public String doInBackground(String... params) {

        String reg_url = "https://jeevanasagarik.000webhostapp.com/register.php";
        String login_url = "https://jeevanasagarik.000webhostapp.com/login.php";
        String method = params[0];
        if (method.equals("register")) {

            String name = params[1];
            String uname = params[2];
            String pwd = params[3];

            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                OutputStream os = httpsURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("uname", "UTF-8") + "=" + URLEncoder.encode(uname, "UTF-8") + "&" +
                        URLEncoder.encode("pwd", "UTF-8") + "=" + URLEncoder.encode(pwd, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                os.close();
                InputStream is = httpsURLConnection.getInputStream();
                is.close();
                httpsURLConnection.disconnect();
                return "Registration Success...";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(method.equals("login")){
            String login_name=params[1];
            String login_pass=params[2];

                try {
                    URL url = new URL(login_url);
                    HttpURLConnection httpsURLConnection = (HttpURLConnection)url.openConnection();
                    httpsURLConnection.setRequestMethod("POST");
                    httpsURLConnection.setDoOutput(true);
                    httpsURLConnection.setDoInput(true);
                    OutputStream os = httpsURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    String data = URLEncoder.encode("uname", "UTF-8") + "=" + URLEncoder.encode(login_name, "UTF-8") + "&" +
                            URLEncoder.encode("pwd", "UTF-8") + "=" + URLEncoder.encode(login_pass, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    os.close();
                    InputStream is = httpsURLConnection.getInputStream();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"));
                    String response="";
                    String line="";
                    while ((line = bufferedReader.readLine())!=null){
                        response+=line;
                    }
                    bufferedReader.close();
                    is.close();
                    httpsURLConnection.disconnect();
                    return response;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Registration Success...")) {
            Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();
        }else{
            alertDialog.setMessage(result);
            alertDialog.show();
            Intent i=new Intent(ctx,Login.class);
            ctx.startActivity(i);

        }
    }
}
