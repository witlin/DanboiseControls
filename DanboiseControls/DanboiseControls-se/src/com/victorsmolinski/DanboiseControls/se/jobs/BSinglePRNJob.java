package com.victorsmolinski.DanboiseControls.se.jobs;

import com.victorsmolinski.DanboiseControls.se.builders.BPRNBuilder;
import com.victorsmolinski.DanboiseControls.se.models.n2.N2DevDef;
import com.victorsmolinski.DanboiseControls.se.models.n2.N2PointDef;
import com.victorsmolinski.DanboiseControls.se.services.BSysBuilderService;
import com.victorsmolinski.DanboiseControls.se.utils.n2parsers.PRNParser;

import com.jci.jcin2.BJciN2Network;
import com.jci.jcin2.BJciN2ODevice;
import com.jci.jcin2.enums.BJciN2ObjectType;
import com.jci.jcin2.point.BJciN2OBooleanProxyExt;
import com.jci.jcin2.point.BJciN2OEnumProxyExt;
import com.jci.jcin2.point.BJciN2ONumericProxyExt;
import com.jci.jcin2.point.BJciN2OPointDeviceExt;

import javax.baja.control.*;
import javax.baja.job.BSimpleJob;
import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import javax.baja.units.BUnit;
import javax.baja.util.BFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

@NiagaraType

public class BSinglePRNJob extends BSimpleJob {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.victorsmolinski.DanboiseControls.se.jobs.BSinglePRNJob(2979906276)1.0$ @*/
/* Generated Wed May 15 10:14:16 EDT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////

  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BSinglePRNJob.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  //PARENT METHOD OVERRIDES
  @Override
  public void run(Context cx) throws Exception{

    BPRNBuilder builder = ((BSysBuilderService)Sys.getService(BSysBuilderService.TYPE)).getPRNBuilder();//REFERENCE TO THE PRN BUILDER OBJ

    n2Dev = prn.parseN2Dev(builder.getFileOrd(), builder, this);//INSTANCE OF THE N2 DEVICE DEFINITION CLASS
    n2PointList = prn.parseN2Points(builder.getFileOrd(), builder, this);//INSTANCE OF THE LIST OF N2 POINT DEFINITIONS PARSED FROM FILE

    BComponent drivers = BOrd.make("slot:/Drivers").resolve(Sys.getStation()).getComponent();//REFERENCE TO THE DRIVER CONTAINER
    Arrays.stream(drivers.getChildComponents()).forEach(e -> {

        //ITERATE THE DRIVER'S CHILDREN AND FIND THE N2 NETWORK
        if(e.getType().getTypeName().equals("JciN2Network")){
            BJciN2Network n2 = (BJciN2Network)e;
            BJciN2ODevice dev = new BJciN2ODevice();
            String name = "";

            //MAKE SURE THE DEVICE NAME IS LEGAL
            name = n2Dev.getDevName().replaceAll("[^A-Za-z0-9]", "");

            //MAKE AN N2 DEVICE POINT EXTENSION
            BJciN2OPointDeviceExt devExt = new BJciN2OPointDeviceExt();

            //ITERATE THE POINT DEFINITIONS IN THE LIST
            n2PointList.stream().forEach(i -> {

                //PLACEHOLDERS FOR THE POINT DEFINITION PROPERTIES
                String pointType = i.getPointType();
                String shortName = i.getShortName();
                String longName = i.getLongName();
                int pointAddr = i.getPointAddress();

                try{

                    //MAKE AN AI NUMERIC POINT WITH FACETS
                    if(i.getPointType().equals("AI")){
                        BNumericPoint np = new BNumericPoint();
                        BJciN2ONumericProxyExt proxyExt = new BJciN2ONumericProxyExt();
                        String pointName = i.getShortName().replaceAll("-", "_");

                        //SET THE POINT'S PROXY EXTENSION PROPERTIES
                        proxyExt.set("networkPointType", BJciN2ObjectType.analogInput);
                        proxyExt.set("networkPointAddress", BInteger.make(i.getPointAddress()));
                        proxyExt.set("shortName", BString.make(pointName));
                        proxyExt.set("longName", BString.make(i.getLongName()));
                        np.setProxyExt(proxyExt);
                        if(i.getPointUnits().equals("%RH")){
                            np.setFacets(BFacets.makeNumeric(BUnit.getUnit("percent relative humidity"), 0));
                        }else if(i.getPointUnits().equals("deg F") || i.getPointUnits().contains("F delta")){
                            np.setFacets(BFacets.makeNumeric(BUnit.getUnit("fahrenheit"), 1));
                        }else if(i.getPointUnits().equals("in wc")){
                            np.setFacets(BFacets.makeNumeric(BUnit.getUnit("inches of water"), 3));
                        }else if(i.getPointUnits().equals("psi")){
                            np.setFacets(BFacets.makeNumeric(BUnit.getUnit("pounds per square inch"), 1));
                        }else if(i.getPointUnits().equals("ppm")){
                            np.setFacets(BFacets.makeNumeric(BUnit.getUnit("parts per million"),0));
                        }else if(i.getPointUnits().equals("seconds")){
                            np.setFacets(BFacets.makeNumeric(BUnit.getUnit("second"), 0));
                        }else if(i.getPointUnits().equals("min")){
                            np.setFacets(BFacets.makeNumeric(BUnit.getUnit("minute"),0));
                        }else if(i.getPointUnits().equals("hr")){
                            np.setFacets(BFacets.makeNumeric(BUnit.getUnit("hour"), 0));
                        }else if(i.getPointUnits().equals("%")){
                            np.setFacets(BFacets.makeNumeric(BUnit.getUnit("percent"), 0));
                        }
                        else{
                            logger.warning("Making AI point; units parsed from prn file not matched!!...BSinglePRNJob.java");
                        }

                        //ADD POINT TO THE DEVICE POINT EXTENSION
                        devExt.add(pointName, np);
                        //SET DISPLAY NAME FOR POINT GENERATED ON ITS DEVICE POINT EXTENSION
                        devExt.setDisplayName(devExt.getProperty(pointName), BFormat.make(i.getLongName()), null);

                    //MAKE AN AO, ADI, OR ADF A NUMERIC WRITABLE, BASED ON FACET CONTEXT,
                    }else if((i.getPointType().equals("AO") || i.getPointType().equals("ADF"))  ||
                            (i.getPointType().equals("ADI") && i.getPointUnits().equals("min")) ||
                            (i.getPointType().equals("ADI") && i.getPointUnits().equals("ft"))  ||
                            (i.getPointType().equals("ADI") && i.getPointUnits().equals("hr"))  ||
                            (i.getPointType().equals("ADI") && i.getPointUnits().equals("sec"))
                    ){

                        BNumericWritable nw = new BNumericWritable();
                        BJciN2ONumericProxyExt proxyExt = new BJciN2ONumericProxyExt();
                        String pointName = i.getShortName();
                        if(i.getShortName().contains("-") ) pointName = pointName.replaceAll("-", "_");
                        try{
                            int n = Integer.parseInt(pointName.substring(0,1));
                            pointName = pointName.substring(1)+ "_".concat(String.valueOf(n));

                        }catch(Exception ex){}


                        if(i.getPointType().equals("AO")){
                            proxyExt.set("networkPointType", BJciN2ObjectType.analogOutput);
                        }else if( i.getPointType().equals("ADI")){
                            proxyExt.set("networkPointType", BJciN2ObjectType.analogDataInteger);
                        }else if(i.getPointType().equals("ADF")){
                            proxyExt.set("networkPointType", BJciN2ObjectType.analogDataFloat);
                        }
                        proxyExt.set("networkPointAddress", BInteger.make(i.getPointAddress()));
                        proxyExt.set("shortName", BString.make(pointName));
                        proxyExt.set("longName", BString.make(i.getLongName()));
                        nw.setProxyExt(proxyExt);

                        try{
                            if(i.getPointUnits().equals("%")){
                                nw.setFacets(BFacets.makeNumeric(BUnit.getUnit("percent"),0));
                            }else if(i.getPointUnits().equals("%RH")){
                                nw.setFacets(BFacets.makeNumeric(BUnit.getUnit("percent relative humidity"), 0));
                            }else if(i.getPointUnits().equals("deg F") || i.getPointUnits().contains("F delta")){
                                nw.setFacets(BFacets.makeNumeric(BUnit.getUnit("fahrenheit"), 1));
                            }else if(i.getPointUnits().equals("in wc")){
                                nw.setFacets(BFacets.makeNumeric(BUnit.getUnit("inches of water"), 3));
                            }else if(i.getPointUnits().equals("psi")){
                                nw.setFacets(BFacets.makeNumeric(BUnit.getUnit("pounds per square inch"), 1));
                            }else if(i.getPointUnits().equals("ppm")){
                                nw.setFacets(BFacets.makeNumeric(BUnit.getUnit("parts per million"),0));
                            }else if(i.getPointUnits().equals("sec")){
                                nw.setFacets(BFacets.makeNumeric(BUnit.getUnit("second"), 0));
                            }else if(i.getPointUnits().equals("min")){
                                nw.setFacets(BFacets.makeNumeric(BUnit.getUnit("minute"),0));
                            }else if(i.getPointUnits().equals("hr")){
                                nw.setFacets(BFacets.makeNumeric(BUnit.getUnit("hour"), 0));
                            }else if(i.getPointUnits().equals("ft")){
                                nw.setFacets(BFacets.makeNumeric(BUnit.getUnit("foot"), 0));
                            }
                            else{
                                logger.warning("[WARNING] point units parsed from prn file not matched!!...BSinglePRNJob.java");
                            }
                        }catch(NullPointerException npe){}

                        devExt.add(pointName, nw);
                        devExt.setDisplayName(devExt.getProperty(pointName), BFormat.make(i.getLongName()), null);
                        log().message("SinglePRNJob..."+i.getShortName()+" - "+i.getPointType()+":"+i.getPointAddress());

                    }else if(i.getPointType().equals("BI")){

                        BBooleanPoint bp = new BBooleanPoint();
                        BJciN2OBooleanProxyExt proxyExt = new BJciN2OBooleanProxyExt();
                        String pointName = i.getShortName().replaceAll("-", "_");
                        proxyExt.set("networkPointType", BJciN2ObjectType.binaryInput);
                        proxyExt.set("networkPointAddress", BInteger.make(i.getPointAddress()));
                        proxyExt.set("shortName", BString.make(pointName));
                        proxyExt.set("longName", BString.make(i.getLongName()));
                        bp.setProxyExt(proxyExt);
                        if(i.getPointUnits().equals("Off/On")){
                            bp.setFacets(BFacets.make(BFacets.TRUE_TEXT, BString.make("On"),
                                    BFacets.FALSE_TEXT, BString.make("Off")));
                        }else if(i.getPointUnits().equals("Normal/Alarm")){
                            bp.setFacets(BFacets.make(BFacets.TRUE_TEXT, BString.make("Alarm"),
                                    BFacets.FALSE_TEXT, BString.make("Normal")));
                        }else{
                            logger.warning("[WARNING] point units parsed from prn file not matched!!...BSinglePRNJob.java");
                        }

                        devExt.add(pointName, bp);
                        devExt.setDisplayName(devExt.getProperty(pointName), BFormat.make(i.getLongName()), null);
                        log().message("SinglePRNJob..."+i.getShortName()+" - "+i.getPointType()+":"+i.getPointAddress());

                    }else if(i.getPointType().equals("BO")){

                        BBooleanWritable bp = new BBooleanWritable();
                        BJciN2OBooleanProxyExt proxyExt = new BJciN2OBooleanProxyExt();
                        String pointName = i.getShortName().replaceAll("-", "_");
                        proxyExt.set("networkPointType", BJciN2ObjectType.binaryOutput);
                        proxyExt.set("networkPointAddress", BInteger.make(i.getPointAddress()));
                        proxyExt.set("shortName", BString.make(pointName));
                        proxyExt.set("longName", BString.make(i.getLongName()));
                        bp.setProxyExt(proxyExt);
                        if(i.getPointUnits().equals("Off/On")){
                            bp.setFacets(BFacets.make(BFacets.TRUE_TEXT, BString.make("On"),
                                    BFacets.FALSE_TEXT, BString.make("Off")));
                        }else{
                            logger.warning("[WARNING] point units parsed from prn file not matched!!...BSinglePRNJob.java");
                        }

                        devExt.add(pointName, bp);
                        devExt.setDisplayName(devExt.getProperty(pointName), BFormat.make(i.getLongName()), null);
                        log().message("SinglePRNJob..."+i.getShortName()+" - "+i.getPointType()+":"+i.getPointAddress());

                    }else if(i.getPointType().equals("ADI") && (!i.getPointUnits().equals("min"))){

                        BEnumWritable ew = new BEnumWritable();
                        BJciN2OEnumProxyExt proxyExt = new BJciN2OEnumProxyExt();
                        String pointName = i.getShortName().replaceAll("-", "_");
                        proxyExt.set("networkPointType", BJciN2ObjectType.analogDataInteger);
                        proxyExt.set("networkPointAddress", BInteger.make(i.getPointAddress()));
                        proxyExt.set("shortName", BString.make(pointName));
                        proxyExt.set("longName", BString.make(i.getLongName()));
                        try{
                            if(i.getPointUnits().contains("/")){
                                String[] units = i.getPointUnits().split("/");
                                for(int it = 0; it < units.length; it++){
                                    prn.filter("[A-Z]", units[it]);
                                }
                                BEnumRange range = BEnumRange.make(units);
                                BDynamicEnum dyEnum = BDynamicEnum.make(units.length, range);
                                ew.set(dyEnum);
                            }else{
                                logger.warning("[WARNING] Enum point not added due to lack of state defs!!!\t"+
                                        i.getShortName()+"\t"+i.getPointType()+":"+i.getPointAddress());
                                log().message("[WARNING] Enum point not added due to lack of state defs!!!\t"+
                                        i.getShortName()+"\t"+i.getPointType()+":"+i.getPointAddress());
                            }
                        }catch(NullPointerException npe){

                        }catch(IllegalNameException ine){
                            logger.severe("[ERROR] BSinglePRNJob.java-- IllegalNameException ADI - ENUMS...!!!\t"+
                                    i.getShortName()+"\t"+i.getPointType()+":"+i.getPointAddress()+"\n"+ine.getStackTrace().toString());
                            log().message("[ERROR] BSinglePRNJob.java-- IllegalNameException ADI - ENUMS...!!!\t"+
                                    i.getShortName()+"\t"+i.getPointType()+":"+i.getPointAddress()+"\n");
                        }catch(Exception ex){
                            logger.severe(ex.getMessage());
                            ex.printStackTrace();
                        }

                    }else{ logger.warning("[WARNING] point type parsed from prn file not matched!!...BSinglePRNJob.java"
                            .concat("\n".concat(i.getShortName()+"\t"+i.getPointType()+":"+i.getPointAddress())));
                    }
                }catch(Exception catchAll){
                    logger.severe("[ERROR] BSinglePRNJob.java:254 -- CATCH ALL...!!!\n"+ catchAll.getStackTrace().toString());
                    log().message("[ERROR] BSinglePRNJob.java-- CATCH ALL EXCEPTION HANDLER...!!!\n"+
                            i.getShortName()+" - "+i.getPointType()+":"+i.getPointAddress()+
                            catchAll.getStackTrace().toString());
                }
            });

            dev.setPoints(devExt);
            log().message(devExt.getPropertiesArray().toString());

            try{
                n2.add(name, dev);
            }catch(IllegalNameException ine){ n2.add("dmi_n2Device", dev); }

            this.log().message(e.getType().getTypeName() + " FOUND... - BSinglePRNJob");
            this.log().message(n2Dev.getDevName()+" parsed from prn file...");
            this.log().message(name+" parsed name");
        }else{
            this.log().message("JciN2Network NOT FOUND ... while attempting to add n2 device from PRN file...!!! - BSinglePRNJob");
        }
    });
  }

  public void doCancel(Context cx){
      canceled = true;
      super.doCancel(cx);
  }

  //METHODS
  public void setCanceled(boolean b){ canceled = b; }
  public boolean getCanceled(){ return canceled; }

  //FIELDS
  private boolean canceled = false;
  private PRNParser prn = new PRNParser();
  private N2DevDef n2Dev;
  private ArrayList<N2PointDef>n2PointList;
  private Logger logger = Logger.getLogger("DMI_SysBuilder_PRNBuilder");

}
