package com.victorsmolinski.DanboiseControls.se.models.n2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class N2DevDef {

    //FIELDS
    private String devGroup = "";
    private String devName = "";

    //METHODS
    public String getDevGroup(){ return devGroup; }
    public String getDevName(){ return devName; }

    public static N2DevDef make(String group, String name){

        N2DevDef dev = new N2DevDef();

        dev.devGroup = group;
        dev.devName = name;
        return dev;
    }
}
