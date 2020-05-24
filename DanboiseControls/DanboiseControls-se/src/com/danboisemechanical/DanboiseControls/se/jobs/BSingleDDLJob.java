package com.danboisemechanical.DanboiseControls.se.jobs;

import com.danboisemechanical.DanboiseControls.se.builders.BDDLBuilder;
import com.danboisemechanical.DanboiseControls.se.models.n2.N2DevDef;
import com.danboisemechanical.DanboiseControls.se.models.n2.N2PointDef;
import com.danboisemechanical.DanboiseControls.se.services.BSysBuilderService;
import com.danboisemechanical.DanboiseControls.se.utils.n2parsers.DDLParser;
import com.jci.jcin2.BJciDx9100Device;
import com.jci.jcin2.BJciN2Network;
import com.jci.jcin2.enums.BJciN2ObjectType;
import com.jci.jcin2.point.BJciS91BooleanProxyExt;
import com.jci.jcin2.point.BJciS91NumericProxyExt;
import com.jci.jcin2.point.BJciS91PointDeviceExt;

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
public class BSingleDDLJob extends BSimpleJob {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.danboisemechanical.DanboiseControls.se.jobs.BSingleDDLJob(2979906276)1.0$ @*/
/* Generated Sun May 24 14:20:39 EDT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BSingleDDLJob.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    //FIELDS
    private boolean canceled = false;
    public void setCanceled(boolean c){ canceled = c; }
    public boolean getCanceled(){ return canceled; }
    private DDLParser ddl = new DDLParser();
    private N2DevDef n2Dev;
    private ArrayList<N2PointDef> n2PointList;
    private Logger logger = Logger.getLogger("DMI_SysBuilder_DDLBuilder");

    @Override
    public void run(Context cx) throws Exception {

        BDDLBuilder builder = ((BSysBuilderService)Sys.
                getService(BSysBuilderService.TYPE)).
                getDDLBuilder();
        n2Dev = ddl.parseN2Dev(builder.getFileOrd(), builder, this);
        n2PointList = ddl.parseN2Point(builder.getFileOrd(), builder, this);

        BComponent drivers = BOrd.make("slot:/Drivers").resolve(Sys.getStation()).getComponent();
        Arrays.stream(drivers.getChildComponents()).forEach(e ->{
            if(e.getType().getTypeName().equals("JciN2Network")){
                BJciN2Network n2 = (BJciN2Network)e;
                BJciDx9100Device dev = new BJciDx9100Device();
                String name = n2Dev.getDevName().replaceAll("[^A-Za-z0-9]", "");

                BJciS91PointDeviceExt devExt = new BJciS91PointDeviceExt();

                n2PointList.stream().forEach(i -> {
                    String pointType =  i.getPointType();
                    String shortName = i.getShortName();
                    String longName = i.getLongName();
                    String pointName = i.getShortName().replaceAll("-", "_");
                    int pointAddr = i.getPointAddress();
                    String units = i.getPointUnits();
                    boolean isWritable = i.getIsWritable();
                    String subType = i.getSubType();

                    if(longName.isEmpty()) longName = shortName;

                    try{
                        if(pointType.equals("AI")){
                            BJciS91NumericProxyExt proxyExt = new BJciS91NumericProxyExt();
                            BControlPoint ai = new BNumericPoint();

                            //SET THE POINT'S PROXY EXTENSION PROPERTIES
                            proxyExt.set("networkPointType", BJciN2ObjectType.analogInput);
                            proxyExt.set("networkPointAddress", BInteger.make(pointAddr));
                            proxyExt.set("shortName", BString.make(pointName));
                            proxyExt.set("longName", BString.make(longName));
                            ai.setProxyExt(proxyExt);
                            if(units.equals("%RH")){
                                ai.setFacets(BFacets.makeNumeric(BUnit.getUnit("percent relative humidity"), 0));
                            }else if(units.equalsIgnoreCase("deg f") ||
                                    units.equalsIgnoreCase("degf")  ||
                                    units.contains("F delta")){
                                ai.setFacets(BFacets.makeNumeric(BUnit.getUnit("fahrenheit"), 1));
                            }else if(units.equalsIgnoreCase("in wc")){
                                ai.setFacets(BFacets.makeNumeric(BUnit.getUnit("inches of water"), 3));
                            }else if(units.equalsIgnoreCase("psi")){
                                ai.setFacets(BFacets.makeNumeric(BUnit.getUnit("pounds per square inch"), 1));
                            }else if(units.equalsIgnoreCase("ppm")){
                                ai.setFacets(BFacets.makeNumeric(BUnit.getUnit("parts per million"),0));
                            }else if(units.equalsIgnoreCase("seconds")){
                                ai.setFacets(BFacets.makeNumeric(BUnit.getUnit("second"), 0));
                            }else if(units.equalsIgnoreCase("min")){
                                ai.setFacets(BFacets.makeNumeric(BUnit.getUnit("minute"),0));
                            }else if(units.equalsIgnoreCase("hr")){
                                ai.setFacets(BFacets.makeNumeric(BUnit.getUnit("hour"), 0));
                            }else if(units.equalsIgnoreCase("%")){
                                ai.setFacets(BFacets.makeNumeric(BUnit.getUnit("percent"), 0));
                            }
                            else{
                                logger.warning("Making AI point; units parsed from ddl file not matched!!...BSingleDDLJob.java");
                            }

                            //ADD POINT TO THE DEVICE POINT EXTENSION
                            devExt.add(pointName, ai);
                            //SET DISPLAY NAME FOR POINT GENERATED ON ITS DEVICE POINT EXTENSION
                            devExt.setDisplayName(devExt.getProperty(pointName), BFormat.make(longName), null);

                        }else if((pointType.equals("AO") || pointType.equals("AD"))
                        ){
                            BControlPoint ad = null;
                            BJciS91NumericProxyExt proxyExt = new BJciS91NumericProxyExt();
                            if(i.getShortName().contains("-") ) pointName = pointName.replaceAll("-", "_");
                            try{
                                int n = Integer.parseInt(pointName.substring(0,1));
                                pointName = pointName.substring(1)+ "_".concat(String.valueOf(n));

                            }catch(Exception ex){
                                logger.severe(ex.getMessage());
                                ex.printStackTrace();
                            }
                            if(i.getIsWritable()){
                                ad = new BNumericWritable();
                            }else{
                                ad = new BNumericPoint();
                            }

                            if(pointType.equals("AO")){
                                proxyExt.set("networkPointType", BJciN2ObjectType.analogOutput);
                            }else if( pointType.equals("ADI")){
                                proxyExt.set("networkPointType", BJciN2ObjectType.analogDataInteger);
                            }else if(pointType.equals("ADF")){
                                proxyExt.set("networkPointType", BJciN2ObjectType.analogDataFloat);
                            }else if( pointType.equals("AD")){
                                switch(subType){
                                    case "ACO":
                                        proxyExt.set("networkPointType", BJciN2ObjectType.analogDataFloat);
                                    case "PMK":
                                        proxyExt.set("networkPointType", BJciN2ObjectType.programmableModuleConstant);
                                        break;
                                    case "PMOU":
                                        proxyExt.set("networkPointType", BJciN2ObjectType.programmableModuleOutput);
                                        break;
                                    default:
                                        logger.warning("N2 Point SUB-TYPE Unknown...! "+ subType);
                                }
                            }
                            proxyExt.set("networkPointAddress", BInteger.make(pointAddr));
                            proxyExt.set("shortName", BString.make(pointName));
                            proxyExt.set("longName", BString.make(longName));
                            ad.setProxyExt(proxyExt);

                            try{
                                if(units.equals("%")){
                                    ad.setFacets(BFacets.makeNumeric(BUnit.getUnit("percent"),0));
                                }else if(units.equalsIgnoreCase("%RH")){
                                    ad.setFacets(BFacets.makeNumeric(BUnit.getUnit("percent relative humidity"), 0));
                                }else if(units.equalsIgnoreCase("deg F") || units.contains("F delta")){
                                    ad.setFacets(BFacets.makeNumeric(BUnit.getUnit("fahrenheit"), 1));
                                }else if(units.equalsIgnoreCase("in wc")){
                                    ad.setFacets(BFacets.makeNumeric(BUnit.getUnit("inches of water"), 3));
                                }else if(units.equalsIgnoreCase("psi")){
                                    ad.setFacets(BFacets.makeNumeric(BUnit.getUnit("pounds per square inch"), 1));
                                }else if(units.equalsIgnoreCase("ppm")){
                                    ad.setFacets(BFacets.makeNumeric(BUnit.getUnit("parts per million"),0));
                                }else if(units.equalsIgnoreCase("sec")){
                                    ad.setFacets(BFacets.makeNumeric(BUnit.getUnit("second"), 0));
                                }else if(units.equalsIgnoreCase("min")){
                                    ad.setFacets(BFacets.makeNumeric(BUnit.getUnit("minute"),0));
                                }else if(units.equalsIgnoreCase("hr")){
                                    ad.setFacets(BFacets.makeNumeric(BUnit.getUnit("hour"), 0));
                                }else if(units.equalsIgnoreCase("ft")){
                                    ad.setFacets(BFacets.makeNumeric(BUnit.getUnit("foot"), 0));
                                }
                                else{
                                    logger.warning("[WARNING] point units parsed from ddl file not matched!!...BSingleDDLJob.java");
                                }
                            }catch(NullPointerException npe){}

                            devExt.add(pointName, ad);
                            devExt.setDisplayName(devExt.getProperty(pointName), BFormat.make(longName), null);

                        }else if(pointType.equals("BI")){

                            BControlPoint bi = null;
                            if(isWritable){
                                bi = new BBooleanWritable();
                            }else{
                                bi = new BBooleanPoint();
                            }
                            BJciS91BooleanProxyExt proxyExt = new BJciS91BooleanProxyExt();
                            proxyExt.set("networkPointType", BJciN2ObjectType.binaryInput);
                            proxyExt.set("networkPointAddress", BInteger.make(pointAddr));
                            proxyExt.set("shortName", BString.make(pointName));
                            proxyExt.set("longName", BString.make(longName));
                            bi.setProxyExt(proxyExt);
                            if(units.equals("Off/On")){
                                bi.setFacets(BFacets.make(BFacets.TRUE_TEXT, BString.make("On"),
                                        BFacets.FALSE_TEXT, BString.make("Off")));
                            }else if(units.equals("Normal/Alarm")){
                                bi.setFacets(BFacets.make(BFacets.TRUE_TEXT, BString.make("Alarm"),
                                        BFacets.FALSE_TEXT, BString.make("Normal")));
                            }else{
                                logger.warning("[WARNING] point units parsed from ddl file not matched!!...BSingleDDLJob.java");
                            }

                            devExt.add(pointName, bi);
                            devExt.setDisplayName(devExt.getProperty(pointName), BFormat.make(longName), null);
                            log().message("SingleDDLJob..."+i.getShortName()+" - "+pointType+":"+pointAddr);

                        }else if(pointType.equals("BO") ||
                                pointType.equals("BD")){

                            BControlPoint bo = null;
                            if(isWritable){
                                bo = new BBooleanWritable();
                            }else{
                                bo = new BBooleanPoint();
                            }
                            BJciS91BooleanProxyExt proxyExt = new BJciS91BooleanProxyExt();
                            if(pointType.equals("BD")) proxyExt.set("networkPointType", BJciN2ObjectType.binaryData);
                            if(pointType.equals("BO")) proxyExt.set("networkPointType", BJciN2ObjectType.binaryOutput);
                            proxyExt.set("networkPointAddress", BInteger.make(pointAddr));
                            proxyExt.set("shortName", BString.make(pointName));
                            proxyExt.set("longName", BString.make(longName));
                            bo.setProxyExt(proxyExt);
                            if(units.equals("OFF/ON")){
                                bo.setFacets(BFacets.make(BFacets.TRUE_TEXT, BString.make("On"),
                                        BFacets.FALSE_TEXT, BString.make("Off")));
                            }else{
                                logger.warning("[WARNING] point units parsed from ddl file not matched!!...BSingleDDLJob.java");
                            }

                            devExt.add(pointName, bo);
                            devExt.setDisplayName(devExt.getProperty(pointName), BFormat.make(longName), null);

                        }
                        else{
                            logger.warning("DDL PARSER CAN'T IDENTIFY POINT TYPE: "+pointType);
                            log().message("DDL PARSER CAN'T IDENTIFY POINT TYPE: "+pointType);
                        }
                    }catch(Exception catchAll){
                        logger.severe("[ERROR] BSingleDDLJob-- CATCH ALL...!!!\n"+ catchAll.getStackTrace().toString());
                        log().message("[ERROR] BSingleDDLJob.java-- CATCH ALL EXCEPTION HANDLER...!!!\n"+
                                i.getShortName()+" - "+pointType+":"+pointAddr+
                                catchAll.getStackTrace().toString());
                    }
                });

                dev.setPoints(devExt);
                log().message(devExt.getPropertiesArray().toString());

                try{
                    n2.add(name, dev);
                }catch(IllegalNameException ine){ n2.add("dmi_n2Device", dev); }

                this.log().message(e.getType().getTypeName() + " FOUND... - BSingleDDLJob");
                this.log().message(n2Dev.getDevName()+" parsed from ddl file...");
                this.log().message(name+" parsed name");
            }else{
                this.log().
                        message(
                                "JciN2Network NOT FOUND ... while attempting to add n2 device from DDL file...!!! - BSingleDDLJob");
            }
        });
    }

    @Override
    public void doCancel(Context cx){
        canceled = true;
        super.doCancel(cx);
    }
}