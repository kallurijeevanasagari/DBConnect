package com.example.hp.dbconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    EditText name,uname,pwd;
    String sname,suname,spwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=(EditText)findViewById(R.id.name);
        uname=(EditText)findViewById(R.id.uname);
        pwd=(EditText)findViewById(R.id.pwd);
    }

    public void register(View view){
        sname=name.getText().toString();
        suname=uname.getText().toString();
        spwd=pwd.getText().toString();
        String method="register";
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute(method,sname,suname,spwd);
        finish();
    }
}
