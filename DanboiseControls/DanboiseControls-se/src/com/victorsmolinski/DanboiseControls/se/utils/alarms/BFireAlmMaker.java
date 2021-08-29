package com.victorsmolinski.DanboiseControls.se.utils.alarms;

import javax.baja.alarm.ext.BAlarmSourceExt;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;

@NiagaraType

public class BFireAlmMaker extends BAlarmMaker{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.victorsmolinski.DanboiseControls.se.utils.alarms.BFireAlmMaker(2979906276)1.0$ @*/
/* Generated Fri Apr 12 14:41:05 EDT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BFireAlmMaker.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  /**
   * zone: NGF
   * almType: EF
   * **/
  public BFireAlmMaker(BComponent p, BEnum almType){
    super(p, almType);
  }

  public BAlarmSourceExt makeExtension(){
    alarmExt.setMetaData(
            alarmConfig.makeMetadata("NGF"+ alarmCount, "EF")
    );
    alarmExt.setOffnormalAlgorithm(alarmConfig.makeAlgorithm());
    super.makeAlmMessages("FIRE ALARM ACTIVE",
            "FIRE ALARM HAS CLEARED");
    alarmCount++;
    return alarmExt;
  }
}
