package com.anon.notes811;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class UpdateActivity extends AppCompatActivity {
    com.anon.notes811.MyHelper db;
    EditText edit1;
    TextView TextViewJAVA;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edit1=findViewById(R.id.editname);
        TextViewJAVA =findViewById(R.id.textView2XML);



        id=getIntent().getIntExtra("id",0);
        db=new MyHelper(this);
        Contact contact=db.getContactId(id);
        edit1.setText(contact.getName());


        TextViewJAVA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edit1.getText().toString();
                Contact newContact=new Contact(name, id);
                db.updateContact(newContact);
                Toast.makeText(UpdateActivity.this,"ok",Toast.LENGTH_SHORT).show();
                Intent update = new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(update);

            }
        });
/*
ExitupdateJAVA.setOnClickListener(new View.OnClickListener() {
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        finishAffinity();
    }
});*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                showAlert();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlert() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("تأكيد الحذف")
                .setMessage("هل تريد الحذف ؟")
                .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //delete contact
                        db.deleteContact(id);
                        finish();
                    }
                })
                .setNegativeButton("لا", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();
    }


}


