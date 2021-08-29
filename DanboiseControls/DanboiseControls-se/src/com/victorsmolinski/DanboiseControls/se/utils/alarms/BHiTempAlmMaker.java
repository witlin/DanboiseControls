package com.victorsmolinski.DanboiseControls.se.utils.alarms;


import javax.baja.alarm.ext.BAlarmSourceExt;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BComponent;
import javax.baja.sys.BEnum;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType

public class BHiTempAlmMaker extends BAlarmMaker {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.victorsmolinski.DanboiseControls.se.utils.alarms.BHiTempAlmMaker(2979906276)1.0$ @*/
/* Generated Sat Jul 06 07:51:36 EDT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BHiTempAlmMaker.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    /**
     * zone: NGE
     * almType: HT
     * **/
    public BHiTempAlmMaker(BComponent p, BEnum t){
        super(p, t);
    }

  public BAlarmSourceExt makeExtension(){
      alarmExt.setAlarmClass("AlarmLoggingClass");
      alarmExt.setMetaData(
              alarmConfig.makeMetadata("NGE"+ alarmCount, "HT")
      );
      alarmExt.setOffnormalAlgorithm(alarmConfig.makeAlgorithm());
        super.makeAlmMessages("HI TEMP ALARM ACTIVE",
              "HI TEMP ALARM HAS CLEARED");
      alarmCount++;
      return alarmExt;
  }
}
