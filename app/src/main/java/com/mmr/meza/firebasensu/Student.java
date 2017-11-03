package com.mmr.meza.firebasensu;

/**
 * Created by Majedur Rahman on 11/3/2017.
 */

public class Student {

    private  String name;
    private  String Id;


    public  Student(){

    }

    public Student(String name, String id) {
        this.name = name;
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
