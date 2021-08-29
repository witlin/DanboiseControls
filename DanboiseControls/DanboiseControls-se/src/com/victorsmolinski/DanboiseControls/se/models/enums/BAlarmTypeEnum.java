package com.victorsmolinski.DanboiseControls.se.models.enums;

import javax.baja.nre.annotations.NiagaraEnum;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.nre.annotations.Range;
import javax.baja.sys.BFrozenEnum;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
@NiagaraEnum(
        range = {
                @Range("None"),
                @Range("FireAlm"),
                @Range("FireTrbl"),
                @Range("DdcFail"),
                @Range("HiTemp"),
                @Range("LoTemp"),
                @Range("HiHumidity"),
                @Range("Airloss"),      //Fan Fail
                @Range("AcPowerloss"),
                @Range("MechClgFail"),  //Hi discharge air temp
                @Range("AcFail"),
                @Range("HiDuctPress"),
                @Range("HiBlgPress"),
                @Range("LowBlgPress"),
                @Range("LowMixedAirTemp"),
                @Range("LowDischargeAirTemp"),
                @Range("Freezestat"),      //Both a low mixed air and low discharge air temp
                @Range("PumpFault"),
                @Range("BoilerFail"),
                @Range("ChillerFail"),
                @Range("SumpFail")
        },
        defaultValue = "None"
)
public final class BAlarmTypeEnum extends BFrozenEnum{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.victorsmolinski.DanboiseControls.se.models.enums.BAlarmTypeEnum(2154105225)1.0$ @*/
/* Generated Wed May 22 17:04:00 EDT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */
  
  /** Ordinal value for None. */
  public static final int NONE = 0;
  /** Ordinal value for FireAlm. */
  public static final int FIRE_ALM = 1;
  /** Ordinal value for FireTrbl. */
  public static final int FIRE_TRBL = 2;
  /** Ordinal value for DdcFail. */
  public static final int DDC_FAIL = 3;
  /** Ordinal value for HiTemp. */
  public static final int HI_TEMP = 4;
  /** Ordinal value for LoTemp. */
  public static final int LO_TEMP = 5;
  /** Ordinal value for HiHumidity. */
  public static final int HI_HUMIDITY = 6;
  /** Ordinal value for Airloss. */
  public static final int AIRLOSS = 7;
  /** Ordinal value for AcPowerloss. */
  public static final int AC_POWERLOSS = 8;
  /** Ordinal value for MechClgFail. */
  public static final int MECH_CLG_FAIL = 9;
  /** Ordinal value for AcFail. */
  public static final int AC_FAIL = 10;
  /** Ordinal value for HiDuctPress. */
  public static final int HI_DUCT_PRESS = 11;
  /** Ordinal value for HiBlgPress. */
  public static final int HI_BLG_PRESS = 12;
  /** Ordinal value for LowBlgPress. */
  public static final int LOW_BLG_PRESS = 13;
  /** Ordinal value for LowMixedAirTemp. */
  public static final int LOW_MIXED_AIR_TEMP = 14;
  /** Ordinal value for LowDischargeAirTemp. */
  public static final int LOW_DISCHARGE_AIR_TEMP = 15;
  /** Ordinal value for Freezestat. */
  public static final int FREEZESTAT = 16;
  /** Ordinal value for PumpFault. */
  public static final int PUMP_FAULT = 17;
  /** Ordinal value for BoilerFail. */
  public static final int BOILER_FAIL = 18;
  /** Ordinal value for ChillerFail. */
  public static final int CHILLER_FAIL = 19;
  /** Ordinal value for SumpFail. */
  public static final int SUMP_FAIL = 20;
  
  /** BAlarmTypeEnum constant for None. */
  public static final BAlarmTypeEnum None = new BAlarmTypeEnum(NONE);
  /** BAlarmTypeEnum constant for FireAlm. */
  public static final BAlarmTypeEnum FireAlm = new BAlarmTypeEnum(FIRE_ALM);
  /** BAlarmTypeEnum constant for FireTrbl. */
  public static final BAlarmTypeEnum FireTrbl = new BAlarmTypeEnum(FIRE_TRBL);
  /** BAlarmTypeEnum constant for DdcFail. */
  public static final BAlarmTypeEnum DdcFail = new BAlarmTypeEnum(DDC_FAIL);
  /** BAlarmTypeEnum constant for HiTemp. */
  public static final BAlarmTypeEnum HiTemp = new BAlarmTypeEnum(HI_TEMP);
  /** BAlarmTypeEnum constant for LoTemp. */
  public static final BAlarmTypeEnum LoTemp = new BAlarmTypeEnum(LO_TEMP);
  /** BAlarmTypeEnum constant for HiHumidity. */
  public static final BAlarmTypeEnum HiHumidity = new BAlarmTypeEnum(HI_HUMIDITY);
  /** BAlarmTypeEnum constant for Airloss. */
  public static final BAlarmTypeEnum Airloss = new BAlarmTypeEnum(AIRLOSS);
  /** BAlarmTypeEnum constant for AcPowerloss. */
  public static final BAlarmTypeEnum AcPowerloss = new BAlarmTypeEnum(AC_POWERLOSS);
  /** BAlarmTypeEnum constant for MechClgFail. */
  public static final BAlarmTypeEnum MechClgFail = new BAlarmTypeEnum(MECH_CLG_FAIL);
  /** BAlarmTypeEnum constant for AcFail. */
  public static final BAlarmTypeEnum AcFail = new BAlarmTypeEnum(AC_FAIL);
  /** BAlarmTypeEnum constant for HiDuctPress. */
  public static final BAlarmTypeEnum HiDuctPress = new BAlarmTypeEnum(HI_DUCT_PRESS);
  /** BAlarmTypeEnum constant for HiBlgPress. */
  public static final BAlarmTypeEnum HiBlgPress = new BAlarmTypeEnum(HI_BLG_PRESS);
  /** BAlarmTypeEnum constant for LowBlgPress. */
  public static final BAlarmTypeEnum LowBlgPress = new BAlarmTypeEnum(LOW_BLG_PRESS);
  /** BAlarmTypeEnum constant for LowMixedAirTemp. */
  public static final BAlarmTypeEnum LowMixedAirTemp = new BAlarmTypeEnum(LOW_MIXED_AIR_TEMP);
  /** BAlarmTypeEnum constant for LowDischargeAirTemp. */
  public static final BAlarmTypeEnum LowDischargeAirTemp = new BAlarmTypeEnum(LOW_DISCHARGE_AIR_TEMP);
  /** BAlarmTypeEnum constant for Freezestat. */
  public static final BAlarmTypeEnum Freezestat = new BAlarmTypeEnum(FREEZESTAT);
  /** BAlarmTypeEnum constant for PumpFault. */
  public static final BAlarmTypeEnum PumpFault = new BAlarmTypeEnum(PUMP_FAULT);
  /** BAlarmTypeEnum constant for BoilerFail. */
  public static final BAlarmTypeEnum BoilerFail = new BAlarmTypeEnum(BOILER_FAIL);
  /** BAlarmTypeEnum constant for ChillerFail. */
  public static final BAlarmTypeEnum ChillerFail = new BAlarmTypeEnum(CHILLER_FAIL);
  /** BAlarmTypeEnum constant for SumpFail. */
  public static final BAlarmTypeEnum SumpFail = new BAlarmTypeEnum(SUMP_FAIL);
  
  /** Factory method with ordinal. */
  public static BAlarmTypeEnum make(int ordinal)
  {
    return (BAlarmTypeEnum)None.getRange().get(ordinal, false);
  }
  
  /** Factory method with tag. */
  public static BAlarmTypeEnum make(String tag)
  {
    return (BAlarmTypeEnum)None.getRange().get(tag);
  }
  
  /** Private constructor. */
  private BAlarmTypeEnum(int ordinal)
  {
    super(ordinal);
  }
  
  public static final BAlarmTypeEnum DEFAULT = None;

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BAlarmTypeEnum.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

}
