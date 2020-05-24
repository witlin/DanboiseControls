package com.danboisemechanical.DanboiseControls.se.utils.n2parsers;

import com.danboisemechanical.DanboiseControls.se.builders.BDDLBuilder;
import com.danboisemechanical.DanboiseControls.se.jobs.BSingleDDLJob;
import com.danboisemechanical.DanboiseControls.se.models.n2.N2DevDef;
import com.danboisemechanical.DanboiseControls.se.models.n2.N2PointDef;

import javax.baja.file.BIFile;
import javax.baja.job.JobCancelException;
import javax.baja.naming.BOrd;
import javax.baja.naming.UnresolvedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;

public class DDLParser extends PRNParser {

    public N2DevDef parseN2Dev(BOrd fileOrd, BDDLBuilder caller, BSingleDDLJob job){

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

    public ArrayList<N2PointDef> parseN2Point(BOrd fileOrd, BDDLBuilder caller, BSingleDDLJob job){

        ArrayList<N2PointDef>  points = new ArrayList();

        try{
            BOrd ord = fileOrd;
            BIFile dmoFile = (BIFile)ord.get(caller);
            InputStreamReader in = new InputStreamReader(dmoFile.getInputStream());
            try{
                BufferedReader bin = new BufferedReader(in);
                String line;
                while((line = bin.readLine()) != null){

/*
                    * LONGNAME "SWITCH ROOM ZN1-T"
                    CSAI "AI3",N,N,"OA_TT","Deg F"
                    CSBI "DI1",N,N,"FIRE_ALM","OFF","ON"
                    CSAO "OUT1",N,N,"AO1","%"
                    CSBO "DO3",N,N,"BMS_CMD","OFF","ON"
                    CSBD "PM3DO1",N,N,"ZN1ONDLY","OFF","ON"
                    CSBD "PM3CT1",N,N,"PM3HLD1","OFF","ON"
                    CSBD "LRS1",N,N,"AC1-ENAB","OFF","ON"
                    CSAD "PM2K1",N,N,"PM2K1-1",""
                    CSAD "PM2OU2",N,N,"ZN1-SP2",""
                    CSBD "P M # SUBTYPE #",N,N,...
                    Programmable Module Subtypes:
                        K = Constant
                        OU = Output
                        LRS = Logic Result (Binary Data)
                        CT = Hold Control/Status (Binary Data)
                        ACO = Analog Constant (translates to an ADF in the Tridium jcin2 module)
                        S = Logic (not used)
*/


                    String pType = "";
                    String subType = "";
                    int addr = -1;
                    String longName = "";
                    String shortName = "";
                    String units = "";
                    boolean rw = false;

                    try{
                        if(line.contains("LONGNAME")){
                            String[] longNameArray = line.split("\"");
                            longName = longNameArray[1];

                        }
                        if(line.startsWith("*") || !line.contains(",") ||
                                line.startsWith("CSMODEL")){
                            logger.info("DMO PARSER LINE DISCARDED...!"+" ".concat(line));

                        }else if(line.startsWith("CS")      &&
                                 !line.contains("MODEL")    &&
                                 !line.contains("LONGNAME") &&
                                 !line.contains("N/A") ){

                            String[] pointRoot = line.split(" ");
                            StringBuilder _sb = new StringBuilder();
                            StringBuilder sb = new StringBuilder();
                            int c = 1;
                            while(c < pointRoot.length){
                                sb.append(pointRoot[c]);
                                c++;
                            }

                            /*
                            * CSAI "AI1",N,N,"ZN1-TT","Deg F"                  AI 1      ________
                            * CSBI "DI6",N,N,"EF-STS","OFF","ON"               DI 6      ________
                            * CSAO "OUT1",N,N,"ZNDMP2-C","%"                   AO 1      ________
                            * CSBO "DO5",N,N,"DX1-CMD","OFF","ON"              BO 5      ________
                            *
                            * **CSAD "PM4OU1",N,N,"10M","Second"               PMO 25    ________
                            * CSBD "PM4DO1",N,N,"SFMINON","Off","On"           PML 25    ________
                            * **CSBD "PM4CT1",N,N,"PM4HLD1","Off","On"         N/A       ________
                            * CSAD "PM7OU1",N,N,"VFD-PID","%"                  PMO 49    ________
                            * CSAD "PM7OU2",N,N,"PM7WSP",""                    PMO 50    ________
                            * **CSAD "PM6K1",N,N,"PM6K1-1",""                  PMK 101   ________
                            * CSAD "ACO1",N,N,"CLGLOCK",""                     ADF 1     ________
                            * CSBD "LRS1",N,N,"SF-C","Off","On"                LRS 1     ________
                            *
                            * 0=[**CSBI "DI8"] , 1=[N] , 2=[N] , 3=["DI8"] , 4=["OFF"] , 5=["ON"]     DI 8      ________
                            * 0=[**CSAD "PM4OU3"] , 1=[N] , 2=[N] , 3=["SFANFLDT"] , 4=["Second"]     PMO 27    ________
                            *
                            * */

                            String[] pointProps = (sb.toString()).split(",");
                            sb.delete(0,sb.length());

                            if(pointProps[3].startsWith("\"")){
                                shortName = pointProps[3].replaceAll("\"", "");
                                shortName = shortName.replace('-', '_');
                            }else{ shortName = pointProps[3]; }

                            if(pointProps.length == 6) {
                                String state1, state2;
                                state1 = pointProps[4].replaceAll("\"", "");
                                String[] array = pointProps[5].split("\"");
                                state2 = array[1];
                                char[] tokens = array[2].toCharArray();
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

                                subType = sb.toString();
                                logger.info("N2 POINT SUB-TYPE: "+subType);
                                units = state1+"/"+state2;

                            } else {
                                String[] array1 = pointProps[4].split("\"");
                                units = array1[1];
                                char[] tokens = array1[2].toCharArray();
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

                                subType = sb.toString();
                                logger.info("N2 POINT SUB-TYPE: "+subType);
                            }

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

                                default:
                                    logger.severe("PARSER CAN'T IDENTIFY N2 POINT TYPE...!");
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

    private static Logger logger = Logger.getLogger("DMI_SysBuilder_DDLBuilder");
}
