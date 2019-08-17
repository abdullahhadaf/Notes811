package com.anon.notes811;

/**
 * Created by WIN7SP1 on 01/09/2018.
 */

public class Contact {
    private String mname;
    private  int mid;

    public Contact(String name){
        mname=name;

    }
    public Contact(String name, int id){
        mname=name;
        mid=id;

    }

    public String getName(){
        return mname;
    }


    public int getId() {
        return mid;
    }
}

