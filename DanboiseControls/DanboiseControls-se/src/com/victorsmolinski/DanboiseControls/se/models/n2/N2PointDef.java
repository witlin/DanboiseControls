package com.victorsmolinski.DanboiseControls.se.models.n2;

public class N2PointDef {

    //FIELDS
    private String pointType = "";
    private String subType = "";
    private int pointAddress = 0;
    private String longName = "";
    private String shortName = "";
    private String pointUnits = "";
    private boolean isWritable = false;

    //METHOD
    public String getPointType(){ return pointType; }
    public String getSubType(){ return subType; }
    public int getPointAddress(){ return pointAddress; }
    public String getLongName(){ return longName; }
    public String getShortName(){ return shortName; }
    public String getPointUnits(){ return pointUnits; }
    public boolean getIsWritable(){return isWritable;}

    public static N2PointDef make(String pType, int address,  String lName, String sName, String u){

        N2PointDef pointDefObj = new N2PointDef();
        pointDefObj.pointType = pType;
        pointDefObj.pointAddress = address;
        pointDefObj.longName = lName;
        pointDefObj.shortName = sName;
        try{
            pointDefObj.pointUnits = u;
        }catch(NullPointerException npe){}

        return pointDefObj;
    }
    public static N2PointDef make(String pType, int address,  String lName,
                                  String sName, String u, boolean rw){

        N2PointDef pointDefObj = new N2PointDef();
        pointDefObj.pointType = pType;
        pointDefObj.pointAddress = address;
        pointDefObj.longName = lName;
        pointDefObj.shortName = sName;
        pointDefObj.pointUnits = u;
        pointDefObj.isWritable = rw;
        try{
            pointDefObj.pointUnits = u;
        }catch(NullPointerException npe){}

        return pointDefObj;
    }
    public static N2PointDef make(String pType, int address,  String lName,
                                  String sName, String u, boolean rw, String subtype){

        N2PointDef pointDefObj = new N2PointDef();
        pointDefObj.pointType = pType;
        pointDefObj.pointAddress = address;
        pointDefObj.longName = lName;
        pointDefObj.shortName = sName;
        pointDefObj.pointUnits = u;
        pointDefObj.isWritable = rw;
        pointDefObj.subType = subtype;
        try{
            pointDefObj.pointUnits = u;
        }catch(NullPointerException npe){}

        return pointDefObj;
    }
}
