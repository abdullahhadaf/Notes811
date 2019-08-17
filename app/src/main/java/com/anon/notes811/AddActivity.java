package com.anon.notes811;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.TextView;
import android.widget.Toast;


public class AddActivity extends AppCompatActivity {

   EditText noteJAVA;
   TextView sendJAVA,aboutJAVA;
    MyHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

   sendJAVA=(TextView)findViewById(R.id.sendXML);
   aboutJAVA=(TextView)findViewById(R.id.aboutXML);
   noteJAVA=(EditText)findViewById(R.id.noteXML);
           db = new MyHelper(this);
        sendJAVA.setOnClickListener(new View.OnClickListener() {
            private int id;

            @Override
            public void onClick(View v) {
                String name=noteJAVA.getText().toString();
                Contact contact=new Contact(name, id);
                db.addContact(contact);
                Toast.makeText(AddActivity.this,"حفظ",Toast.LENGTH_SHORT).show();
                Intent add =new Intent(AddActivity.this,MainActivity.class);
                startActivity(add);
            }
        });
        /*
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about= new Intent(AddActivity.this,Houl.class);
                startActivity(about);
            }
        });

        ExitaddJAVA.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });*/

        String Text="تطوير عبدالله النهدي -  اصدار : 1.1 - 2019";
        String[] array=Text.split("-");
        String line;
        StringBuilder ST=new StringBuilder();
        for (int i=0 ; i<array.length; i++){
            line=array[i];
            ST.append(line.trim()+"\n");
            aboutJAVA.setText(ST);
        }
    }

}
