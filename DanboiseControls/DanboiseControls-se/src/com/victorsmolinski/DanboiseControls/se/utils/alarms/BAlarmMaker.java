package com.victorsmolinski.DanboiseControls.se.utils.alarms;

import javax.baja.alarm.ext.BAlarmSourceExt;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BComponent;
import javax.baja.sys.BEnum;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.BFormat;

@NiagaraType

public class BAlarmMaker extends BComponent {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.victorsmolinski.DanboiseControls.se.utils.alarms.BAlarmMaker(2979906276)1.0$ @*/
/* Generated Sat Jul 06 08:39:46 EDT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BAlarmMaker.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    protected BComponent sourcePoint = null;
    protected BAlarmUtil alarmConfig = new BAlarmUtil();
    protected BAlarmSourceExt alarmExt = new BAlarmSourceExt();
    protected int alarmCount = 1;

    public BAlarmMaker(BComponent point, BEnum almType){
        sourcePoint = point;
        alarmExt.setSourceName(
            BFormat.make(Sys.getStation().getStationName() + "/".
                    concat(almType.getTag()))
        );
        alarmExt.setTimeDelay(alarmConfig.getTimeDelay());
        alarmExt.setTimeDelayToNormal(alarmConfig.getTimeDelayToNormal());
    }

    public void makeAlmMessages(String almMsg, String normalMsg){
        BFormat[] almMsgs = alarmConfig.makeAlarmMsg(almMsg, normalMsg);
        alarmExt.setToOffnormalText(almMsgs[0]);
        alarmExt.setToNormalText(almMsgs[1]);
    }
}
