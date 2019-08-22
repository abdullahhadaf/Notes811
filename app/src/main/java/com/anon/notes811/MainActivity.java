package com.anon.notes811;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button button;
    MyHelper db;
    long time;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        if (time +2000 > System.currentTimeMillis()){
            super.finishAffinity();
        }else{
            Toast.makeText(MainActivity.this, "إضغط مرة أخرى للخروج " ,Toast.LENGTH_SHORT).show();
        }
        time = System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         listView = findViewById(R.id.list1);



        db = new MyHelper(this);

        button = (Button) findViewById(R.id.btn);
button.setTypeface(Typeface.createFromAsset(getAssets(),"NotoNaskhArabic-Bold.ttf"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivity(i);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selected_contact = (Contact) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("id", selected_contact.getId());
                startActivity(intent);


            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Contact> contacts=db.getAllContacts();

        ContactAdapter adapter=new ContactAdapter(this,contacts);
        listView.setAdapter(adapter);
    }

}
