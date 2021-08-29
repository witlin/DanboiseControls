package com.victorsmolinski.DanboiseControls.se.utils.n2parsers;

import com.victorsmolinski.DanboiseControls.se.builders.BDMOBuilder;
import com.victorsmolinski.DanboiseControls.se.jobs.BSingleDMOJob;
import com.victorsmolinski.DanboiseControls.se.models.n2.N2DevDef;
import com.victorsmolinski.DanboiseControls.se.models.n2.N2PointDef;

import javax.baja.file.BIFile;
import javax.baja.job.JobCancelException;
import javax.baja.naming.BOrd;
import javax.baja.naming.UnresolvedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;

public class DMOParser extends PRNParser {

    public N2DevDef parseN2Dev(BOrd fileOrd, BDMOBuilder caller, BSingleDMOJob job){

        String devName = "";
        try{

            BIFile dmoFile = (BIFile) fileOrd.get(caller);
            InputStreamReader in = new InputStreamReader(dmoFile.getInputStream());
            try{
                BufferedReader bin = new BufferedReader(in);
                String line;
                while((line = bin.readLine()) != null){
                    if(job.getCanceled())throw new JobCancelException();
                    if(line.contains("CSMODEL")){
                        devName = (line.split(","))[1];
                    }
                }
            }finally{in.close();}
        }catch(UnresolvedException ue){
            logger.severe(ue.getMessage());
            ue.printStackTrace();
        }catch(IOException ioe){
            logger.severe(ioe.getMessage());
            ioe.printStackTrace();
        }catch(Exception e){
            logger.severe(e.getMessage());
            e.printStackTrace();
        }
        return N2DevDef.make("", devName);
    }

    public ArrayList<N2PointDef> parseN2Point(BOrd fileOrd, BDMOBuilder caller, BSingleDMOJob job){

        ArrayList<N2PointDef>  points = new ArrayList();

        try{
            BOrd ord = fileOrd;
            BIFile dmoFile = (BIFile)ord.get(caller);
            InputStreamReader in = new InputStreamReader(dmoFile.getInputStream());
            try{
                BufferedReader bin = new BufferedReader(in);
                String line;
                while((line = bin.readLine()) != null){

//                    * LONGNAME "SWITCH ROOM ZN1-T"
//                    CSAI "AI3",N,N,"OA_TT","Deg F"
//                    CSBI "DI1",N,N,"FIRE_ALM","OFF","ON"
//                    CSAO "OUT1",N,N,"AO1","%"
//                    CSBO "DO3",N,N,"BMS_CMD","OFF","ON"

                    String pType = "";
                    String subType = "";
                    int addr = -1;
                    String longName = "";
                    String shortName = "";
                    String units = "";
                    boolean rw = true;

                    try{
                        if(line.contains("LONGNAME")){
                            String[] longNameArray = line.split("\"");
                            longName = longNameArray[1];

                        }
                        if(line.startsWith("*") || !line.contains(",") ||
                                line.startsWith("CSMODEL")){
                            logger.info("DMO PARSER LINE DISCARDED...!"+" ".concat(line));

                        }else if(line.startsWith("CS") && !line.contains("MODEL") && !line.contains("LONGNAME")){

                            String[] pointRoot = line.split(" ");
                            StringBuilder _sb = new StringBuilder();
                            StringBuilder sb = new StringBuilder();
                            int c = 1;
                            while(c < pointRoot.length){
                                sb.append(pointRoot[c]);
                                c++;
                            }
                            String[] pointProps = (sb.toString()).split(",");
                            char[] tokens = pointProps[0].toCharArray();
                            sb.delete(0,sb.length());

                            if(pointProps[3].startsWith("\"")){
                                shortName = pointProps[3].replaceAll("\"", "");
                                shortName = shortName.replace('-', '_');
                            }else{ shortName = pointProps[3]; }

                            if(pointProps.length == 6){
                                String state1, state2;

                                if(pointProps[4].startsWith("\"")){
                                    state1 = pointProps[4].replaceAll("\"", "");
                                }else{ state1 = pointProps[4]; }

                                if(pointProps[5].startsWith("\"")){
                                    state2 = pointProps[5].replaceAll("\"", "");
                                }else{ state2 = pointProps[5]; }
                                units = state1+"/"+state2;

                            }else{
                                if(pointProps[4].startsWith("\"")){
                                    units = pointProps[4].replaceAll("\"", "");
                                }else{ units = pointProps[4]; }
                            }

//                            if((pointProps[1]).equals("N") && (pointProps[2]).equals("N")) rw = false;

                            if((pointProps[0]).contains("PM")){
                                for(char t : tokens){
                                    if(Character.isLetter(t)){
                                        sb.append(t);
                                    }else if(Character.isDigit(t)){
                                        _sb.append(t);
                                    }else{
                                        logger.warning("DMOParser, type-addr field unknkown token...!"+t);
                                    }
                                }
                                addr = Integer.parseInt(_sb.toString().substring(1));
                                logger.info("DMOParser...N2 Prog. Module address: "+addr+"\t"+pointProps[0]);

                            }else{
                                for(char t : tokens){
                                    if(Character.isLetter(t)){
                                        sb.append(t);
                                    }else if(Character.isDigit(t)){
                                        _sb.append(t);
                                    }else{
                                        logger.warning("DMOParser, type-addr field unknkown token...!");
                                    }
                                }
                                addr = Integer.parseInt(_sb.toString());
                                logger.info("DMOParser...N2 Point address: "+addr);
                            }
                            subType = sb.toString();
                            logger.info("N2 POINT SUB-TYPE: "+subType);
                            _sb.delete(0, _sb.length());
                            sb.delete(0,sb.length());

                            switch(pointRoot[0]){
                                case "CSAI":
                                    for(String prop : pointProps){
                                        logger.info(prop);
                                    }
                                    pType = "AI";
                                    rw = false;
                                    logger.info("\nPoint Type: ".concat(pType)+
                                            "\nPoint Addr: ".concat(String.valueOf(addr))+
                                            "\nPoint LName: ".concat(longName)+
                                            "\nPoint SName: ".concat(shortName)+
                                            "\nPoint Units: ".concat(units)+
                                            "\nPoint R/W: ".concat(String.valueOf(rw)));

                                    points.add(N2PointDef.make(
                                            pType,
                                            addr,
                                            longName,
                                            shortName,
                                            units,
                                            rw ));
                                    break;

                                case "CSBI":
                                    pType = "BI";
                                    rw = false;
                                    for(String prop : pointProps){
                                        logger.info(prop);
                                    }
                                    logger.info("\nPoint Type: ".concat(pType)+
                                            "\nPoint Addr: ".concat(String.valueOf(addr))+
                                            "\nPoint LName: ".concat(longName)+
                                            "\nPoint SName: ".concat(shortName)+
                                            "\nPoint Units: ".concat(units)+
                                            "\nPoint R/W: ".concat(String.valueOf(rw)));
                                    points.add(N2PointDef.make(
                                            pType,
                                            addr,
                                            longName,
                                            shortName,
                                            units,
                                            rw ));
                                    break;

                                case "CSAO":
                                    pType = "AO";
                                    rw = true;
                                    for(String prop : pointProps){
                                        logger.info(prop);
                                    }
                                    logger.info("\nPoint Type: ".concat(pType)+
                                            "\nPoint Addr: ".concat(String.valueOf(addr))+
                                            "\nPoint LName: ".concat(longName)+
                                            "\nPoint SName: ".concat(shortName)+
                                            "\nPoint Units: ".concat(units)+
                                            "\nPoint R/W: ".concat(String.valueOf(rw)));
                                    points.add(N2PointDef.make(
                                            pType,
                                            addr,
                                            longName,
                                            shortName,
                                            units,
                                            rw ));
                                    break;

                                case "CSBO":
                                    pType = "BO";
                                    rw = true;
                                    for(String prop : pointProps){
                                        logger.info(prop);
                                    }
                                    logger.info("\nPoint Type: ".concat(pType)+
                                            "\nPoint Addr: ".concat(String.valueOf(addr))+
                                            "\nPoint LName: ".concat(longName)+
                                            "\nPoint SName: ".concat(shortName)+
                                            "\nPoint Units: ".concat(units)+
                                            "\nPoint R/W: ".concat(String.valueOf(rw)));

                                    points.add(N2PointDef.make(
                                            pType,
                                            addr,
                                            longName,
                                            shortName,
                                            units,
                                            rw ));
                                    break;
/*

                                case "CSBD":
                                    rw = true;
                                    pType = "BD";
                                    for(String prop : pointProps){
                                        logger.info(prop);
                                    }
                                    logger.info("\nPoint Type: ".concat(pType)+
                                            "\nPoint Addr: ".concat(String.valueOf(addr))+
                                            "\nPoint LName: ".concat(longName)+
                                            "\nPoint SName: ".concat(shortName)+
                                            "\nPoint Units: ".concat(units)+
                                            "\nPoint R/W: ".concat(String.valueOf(rw)));

                                    points.add(N2PointDef.make(
                                            pType,
                                            addr,
                                            longName,
                                            shortName,
                                            units,
                                            rw ));
                                    break;

                                case "CSAD":
                                    pType = "AD";
                                    rw = true;
                                    for(String prop : pointProps){
                                        logger.info(prop);
                                    }
                                    logger.info("\nPoint Type: ".concat(pType)+
                                            "\nPoint Addr: ".concat(String.valueOf(addr))+
                                            "\nPoint LName: ".concat(longName)+
                                            "\nPoint SName: ".concat(shortName)+
                                            "\nPoint Units: ".concat(units)+
                                            "\nPoint R/W: ".concat(String.valueOf(rw))+
                                            "\nPoint Sub-Type: ".concat(subType));

                                    points.add(N2PointDef.make(
                                            pType,
                                            addr,
                                            longName,
                                            shortName,
                                            units,
                                            rw,
                                            subType));
                                    break;
*/

                                default:
                                    logger.warning("DMO PARSER CAN'T IDENTIFY N2 POINT TYPE...!");
                                    break;
                            }
                        }else{
                            logger.warning("DMO PARSER -UNKNOWN SCHEMA, LINE DISCARDED...!"+" ".concat(line));
                        }}catch(Exception e){
                        logger.severe(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }finally{in.close();}
        }catch(IOException ioe){
            logger.severe(ioe.getMessage());
            ioe.printStackTrace();
        }catch(Exception e){
            logger.severe(e.getMessage());
            e.printStackTrace();
        }

        return points;
    }

    private static Logger logger = Logger.getLogger("DMI_SysBuilder_DMOBuilder");
}
