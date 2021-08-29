package com.victorsmolinski.DanboiseControls.se.utils.alarms;

import javax.baja.alarm.BAlarmTransitionBits;
import javax.baja.alarm.ext.offnormal.BBooleanChangeOfStateAlgorithm;
import javax.baja.alarm.ext.offnormal.BOutOfRangeAlgorithm;
import javax.baja.control.BControlPoint;
import javax.baja.control.ext.BNullProxyExt;
import javax.baja.driver.point.BPointDeviceExt;
import javax.baja.driver.point.BPointFolder;
import javax.baja.driver.point.BProxyExt;
import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import javax.baja.util.BFormat;

@NiagaraType

@NiagaraProperty(
        name = "AlarmClass",
        type = "java:String",
        defaultValue = "AlarmLoggingClass",
        flags = Flags.SUMMARY
)
@NiagaraProperty(
        name = "AlarmEnable",
        type = "baja:AlarmTransitionBits",
        defaultValue = "BAlarmTransitionBits.make(12)",
        flags = Flags.SUMMARY
)
@NiagaraProperty(
        name = "TimeDelay",
        type = "baja:RelTime",
        defaultValue = "BRelTime.makeMinutes(15)",
        facets = {
                @Facet(name = "BFacets.MIN", value = "BRelTime.makeSeconds(0)"),
                @Facet(name = "BFacets.MAX", value = "BRelTime.makeMinutes(60)")
        }
)
@NiagaraProperty(
        name = "TimeDelayToNormal",
        type = "baja:RelTime",
        defaultValue = "BRelTime.makeSeconds(1)",
        facets = {
                @Facet(name = "BFacets.MIN", value = "BRelTime.makeSeconds(0)"),
                @Facet(name = "BFacets.MAX", value = "BRelTime.makeSeconds(60)"),
        }
)
public  class BAlarmUtil extends BComponent {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.victorsmolinski.DanboiseControls.se.utils.alarms.BAlarmUtil(2114738834)1.0$ @*/
/* Generated Sat Jul 06 07:51:36 EDT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "AlarmClass"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code AlarmClass} property.
   * @see #getAlarmClass
   * @see #setAlarmClass
   */
  public static final Property AlarmClass = newProperty(Flags.SUMMARY, "AlarmLoggingClass", null);
  
  /**
   * Get the {@code AlarmClass} property.
   * @see #AlarmClass
   */
  public String getAlarmClass() { return getString(AlarmClass); }
  
  /**
   * Set the {@code AlarmClass} property.
   * @see #AlarmClass
   */
  public void setAlarmClass(String v) { setString(AlarmClass, v, null); }

////////////////////////////////////////////////////////////////
// Property "AlarmEnable"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code AlarmEnable} property.
   * @see #getAlarmEnable
   * @see #setAlarmEnable
   */
  public static final Property AlarmEnable = newProperty(Flags.SUMMARY, BAlarmTransitionBits.make(12), null);
  
  /**
   * Get the {@code AlarmEnable} property.
   * @see #AlarmEnable
   */
  public BAlarmTransitionBits getAlarmEnable() { return (BAlarmTransitionBits)get(AlarmEnable); }
  
  /**
   * Set the {@code AlarmEnable} property.
   * @see #AlarmEnable
   */
  public void setAlarmEnable(BAlarmTransitionBits v) { set(AlarmEnable, v, null); }

////////////////////////////////////////////////////////////////
// Property "TimeDelay"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code TimeDelay} property.
   * @see #getTimeDelay
   * @see #setTimeDelay
   */
  public static final Property TimeDelay = newProperty(0, BRelTime.makeMinutes(15), BFacets.make(BFacets.make(BFacets.MIN, BRelTime.makeSeconds(0)), BFacets.make(BFacets.MAX, BRelTime.makeMinutes(60))));
  
  /**
   * Get the {@code TimeDelay} property.
   * @see #TimeDelay
   */
  public BRelTime getTimeDelay() { return (BRelTime)get(TimeDelay); }
  
  /**
   * Set the {@code TimeDelay} property.
   * @see #TimeDelay
   */
  public void setTimeDelay(BRelTime v) { set(TimeDelay, v, null); }

////////////////////////////////////////////////////////////////
// Property "TimeDelayToNormal"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code TimeDelayToNormal} property.
   * @see #getTimeDelayToNormal
   * @see #setTimeDelayToNormal
   */
  public static final Property TimeDelayToNormal = newProperty(0, BRelTime.makeSeconds(1), BFacets.make(BFacets.make(BFacets.MIN, BRelTime.makeSeconds(0)), BFacets.make(BFacets.MAX, BRelTime.makeSeconds(60))));
  
  /**
   * Get the {@code TimeDelayToNormal} property.
   * @see #TimeDelayToNormal
   */
  public BRelTime getTimeDelayToNormal() { return (BRelTime)get(TimeDelayToNormal); }
  
  /**
   * Set the {@code TimeDelayToNormal} property.
   * @see #TimeDelayToNormal
   */
  public void setTimeDelayToNormal(BRelTime v) { set(TimeDelayToNormal, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BAlarmUtil.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

/***
 * @author: Victor Smolinski
 * @version:  1.0.1.0
 * @since: 2019-04-02
 * Fire alarm model class
 */

    

    /**FIELDS**/

    /**METHODS**/

    /**
     *Makes and returns the alarm's metadata
     * @param geoCode: building's geolocation code
     * @param id: alarm id code to designate multiple alarms of the same type
     * @Return BFacets
     * **/

  /**
   *Makes and returns the alarm's metadata
   * @param zone: alarm zone/id code to designate multiple alarms of the same type
   * @param almType: ATT Alarm Type designation
   * @Return BFacets
   * **/
  public BFacets makeMetadata(String zone, String almType) {
    String[] metaTags = {"GLC", "Zone", "ID", "AlarmType"};
    BString[] metaValues = {BString.make(
            Sys.getStation().getStationName()),
            BString.make(zone),
            BString.make("01"),
            BString.make(almType)
    };
    BFacets meta = BFacets.make(metaTags, metaValues);
    return meta;
  }

    /**
     * Receives a boolean input to determine if the message is for an active or normal alarm condition; it creates an
     * Alarm message on the calling object based on that criteria.
     * @param alarmText
     * @param normalText
     * @return BFormat[]
     * */

    public BFormat[] makeAlarmMsg(String alarmText, String normalText){
      BFormat[] text = {
              BFormat.make(alarmText),
              BFormat.make(normalText)
      };
      return text;
    }

    /***
   * Numeric alarm OutOfRange Algorithm
   * @return BOutOfRangeAlgorithm
   * */
    public BOutOfRangeAlgorithm makeAlgorithm(boolean hiLimit, boolean lowLimit){
      BOutOfRangeAlgorithm numOorAlg = new BOutOfRangeAlgorithm();
      numOorAlg.getLimitEnable().setHighLimitEnable(hiLimit);
      numOorAlg.getLimitEnable().setLowLimitEnable(lowLimit);
      return numOorAlg;
    }

    /***
   * Boolean alarm BooleanChangeOfState Algorithm.
   * @return BBooleanChangeOfStateAlgorithm
   * */
    public BBooleanChangeOfStateAlgorithm makeAlgorithm(){
      BBooleanChangeOfStateAlgorithm boolCovAlg = new BBooleanChangeOfStateAlgorithm();
      boolCovAlg.setAlarmValue(true);
      return boolCovAlg;
    }
}
