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
                @Range("Critical"),
                @Range("Abnormal"),
                @Range("Warning")
        }
)

public final class BAlarmClassEnum extends BFrozenEnum {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.victorsmolinski.DanboiseControls.se.models.enums.BAlarmClassEnum(130234209)1.0$ @*/
/* Generated Wed May 22 17:09:28 EDT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */
  
  /** Ordinal value for Critical. */
  public static final int CRITICAL = 0;
  /** Ordinal value for Abnormal. */
  public static final int ABNORMAL = 1;
  /** Ordinal value for Warning. */
  public static final int WARNING = 2;
  
  /** BAlarmClassEnum constant for Critical. */
  public static final BAlarmClassEnum Critical = new BAlarmClassEnum(CRITICAL);
  /** BAlarmClassEnum constant for Abnormal. */
  public static final BAlarmClassEnum Abnormal = new BAlarmClassEnum(ABNORMAL);
  /** BAlarmClassEnum constant for Warning. */
  public static final BAlarmClassEnum Warning = new BAlarmClassEnum(WARNING);
  
  /** Factory method with ordinal. */
  public static BAlarmClassEnum make(int ordinal)
  {
    return (BAlarmClassEnum)Critical.getRange().get(ordinal, false);
  }
  
  /** Factory method with tag. */
  public static BAlarmClassEnum make(String tag)
  {
    return (BAlarmClassEnum)Critical.getRange().get(tag);
  }
  
  /** Private constructor. */
  private BAlarmClassEnum(int ordinal)
  {
    super(ordinal);
  }
  
  public static final BAlarmClassEnum DEFAULT = Critical;

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BAlarmClassEnum.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

}
