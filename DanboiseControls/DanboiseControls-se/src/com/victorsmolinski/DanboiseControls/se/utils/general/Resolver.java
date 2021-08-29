package com.victorsmolinski.DanboiseControls.se.utils.general;

import javax.baja.collection.BITable;
import javax.baja.collection.TableCursor;
import javax.baja.naming.BOrd;
import javax.baja.sys.BComponent;
import javax.baja.sys.BObject;
import javax.baja.sys.BString;
import javax.baja.sys.Sys;
import java.util.ArrayList;

public class Resolver {

@SuppressWarnings("unchecked")
    public ArrayList<BObject> resolveType(String whType, String stationPath, String fromType){
        ArrayList<BObject> objRefs = new ArrayList<>();
        BOrd ord =  BOrd.make(stationPath.concat("|bql:select name from "+fromType+" where type = '"+whType+"'"));

        BITable<BObject> t = (BITable)ord.resolve(Sys.getStation()).get();
        TableCursor<BObject> c = t.cursor();
        while(c.next()){
            try{
                objRefs.add(c.get());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return objRefs;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<BObject> resolveName(String name, String stationPath, String fromType){
        ArrayList<BObject> objRefs = new ArrayList<>();
        BOrd ord =  BOrd.make(stationPath.concat("|bql:select name from "+fromType+" where name like "+"'*"+name+"*'"));
        BITable<BObject> t = (BITable)ord.resolve(Sys.getStation()).get();
        TableCursor<BObject> c = t.cursor();
        while(c.next()){
            try{
                objRefs.add(c.get());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return objRefs;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<BObject> resolveSlotPath(String slotPath, String stationPath, String fromType){
        ArrayList<BObject> objRefs = new ArrayList<>();
        BOrd ord =  BOrd.make(stationPath.concat("|bql:select name from "+fromType+" where name like "+"'*"+slotPath+"*'"));
        BITable<BObject> t = (BITable)ord.resolve(Sys.getStation()).get();
        TableCursor<BObject> c = t.cursor();
        while(c.next()){
            try{
                objRefs.add(c.get());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return objRefs;
    }

    /***
     * resolve a bql query, and return the list of BObject references.
     * @param query: bql query.
     * @return ArrayList<BObject>
     */

    @SuppressWarnings("unchecked")
    public  ArrayList<BComponent> resolve(BString query){
        ArrayList<BComponent> objRefs = new ArrayList<>();
        BOrd ord = BOrd.make(query.getString());
        BITable<BComponent> t = (BITable)ord.resolve(Sys.getStation()).get();
        TableCursor<BComponent> c = t.cursor();
        while(c.next()){
            try{
                objRefs.add(c.get());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return objRefs;
    }
}
