package com.anon.notes811;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by WIN7SP1 on 01/09/2018.
 */

public class MyHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="MyDatabase";
    private static final int DB_VERSION=1;
    private static final String TABLE_NAME="contacts";
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    // private static final String KEY_PHONE="phone";

    public MyHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+KEY_ID+" integer primary key ,"+KEY_NAME+" varchar(100));";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DELETE_TABLE="DROP TABLE "+ TABLE_NAME+"IF EXISTS";
        db.execSQL(DELETE_TABLE);
        onCreate(db);

    }
    //add data to database
    public void addContact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,contact.getName());
        //values.put(KEY_PHONE,contact.getPhone());
        db.insert(TABLE_NAME,null,values);
    }
    public ArrayList<Contact> getAllContacts(){
        ArrayList<Contact> contacts=new ArrayList<>();
        String select_query="SELECT * FROM "+TABLE_NAME+"";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(select_query,null);
        if(cursor.moveToLast()){
            do{
                String name=cursor.getString(cursor.getColumnIndex(KEY_NAME));
                //String phone=cursor.getString(cursor.getColumnIndex(KEY_PHONE));
                int id=cursor.getInt(cursor.getColumnIndex(KEY_ID));
                Contact contact=new Contact(name, id);
                contacts.add(contact);

            }while(cursor.moveToPrevious());
        }

        return contacts;
    }
    public Contact getContactId(int id){
        String select_query="SELECT * FROM "+TABLE_NAME+" WHERE id="+id;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(select_query,null);
        Contact contact=null;
        if(cursor.moveToFirst()) {
            int  contact_id =cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String name=cursor.getString(cursor.getColumnIndex(KEY_NAME));
            // String phone=cursor.getString(cursor.getColumnIndex(KEY_PHONE));
            contact=new Contact(name, id);
        }
        return contact;
    }
    public void updateContact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,contact.getName());
        //values.put(KEY_PHONE,contact.getPhone());

        db.update(TABLE_NAME,values,"id=?",new String[]{String.valueOf(contact.getId())});
    }
    public void deleteContact(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,"id=?",new String[]{String.valueOf(id)});
    }
}
