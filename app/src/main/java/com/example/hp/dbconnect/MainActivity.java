package com.example.hp.dbconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText uname,pwd;
    String login_name,login_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname=(EditText)findViewById(R.id.uname);
        pwd=(EditText)findViewById(R.id.pwd);

    }
    public void login(View view){
        login_name=uname.getText().toString();
        login_pass=pwd.getText().toString();
        String method="login";
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute(method,login_name,login_pass);


    }
    public void register(View view){
        startActivity(new Intent(this,Register.class));
    }
}
