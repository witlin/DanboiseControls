package com.victorsmolinski.DanboiseControls.se.builders;

import com.victorsmolinski.DanboiseControls.se.models.enums.BAlarmTypeEnum;
import com.victorsmolinski.DanboiseControls.se.utils.alarms.BFireAlmMaker;
import com.victorsmolinski.DanboiseControls.se.workers.BAlarmBuilderWorker;

import javax.baja.alarm.ext.BAlarmSourceExt;
import javax.baja.naming.BOrd;
import javax.baja.naming.UnresolvedException;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.query.BQueryResult;
import javax.baja.sys.*;
import javax.baja.tag.Entity;
import javax.baja.util.IFuture;
import javax.baja.util.Invocation;
import java.util.Iterator;
import java.util.logging.Logger;

@NiagaraType

@NiagaraProperty(
        name = "AlarmType",
        type = "baja:Enum",
        defaultValue = "BAlarmTypeEnum.None",
        flags = Flags.SUMMARY
)
@NiagaraProperty(
        name = "neqlQuery",
        type = "baja:String",
        defaultValue = "\"\"",
        flags = Flags.SUMMARY
)
@NiagaraProperty(
        name = "AlarmWorker",
        type = "DanboiseControls:AlarmBuilderWorker",
        defaultValue = "BAlarmBuilderWorker.make()",
        flags = Flags.HIDDEN
)

@NiagaraAction(
        name = "BuildAll",
        flags = Flags.ASYNC
)
@NiagaraAction(
        name = "BuildOne",
        flags = Flags.ASYNC
)

public class BAlarmBuilder extends BComponent {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.victorsmolinski.DanboiseControls.se.builders.BAlarmBuilder(1117032981)1.0$ @*/
/* Generated Wed Jul 10 09:04:32 EDT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "AlarmType"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code AlarmType} property.
   * @see #getAlarmType
   * @see #setAlarmType
   */
  public static final Property AlarmType = newProperty(Flags.SUMMARY, BAlarmTypeEnum.None, null);
  
  /**
   * Get the {@code AlarmType} property.
   * @see #AlarmType
   */
  public BEnum getAlarmType() { return (BEnum)get(AlarmType); }
  
  /**
   * Set the {@code AlarmType} property.
   * @see #AlarmType
   */
  public void setAlarmType(BEnum v) { set(AlarmType, v, null); }

////////////////////////////////////////////////////////////////
// Property "neqlQuery"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code neqlQuery} property.
   * @see #getNeqlQuery
   * @see #setNeqlQuery
   */
  public static final Property neqlQuery = newProperty(Flags.SUMMARY, "\"\"", null);
  
  /**
   * Get the {@code neqlQuery} property.
   * @see #neqlQuery
   */
  public String getNeqlQuery() { return getString(neqlQuery); }
  
  /**
   * Set the {@code neqlQuery} property.
   * @see #neqlQuery
   */
  public void setNeqlQuery(String v) { setString(neqlQuery, v, null); }

////////////////////////////////////////////////////////////////
// Property "AlarmWorker"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code AlarmWorker} property.
   * @see #getAlarmWorker
   * @see #setAlarmWorker
   */
  public static final Property AlarmWorker = newProperty(Flags.HIDDEN, BAlarmBuilderWorker.make(), null);
  
  /**
   * Get the {@code AlarmWorker} property.
   * @see #AlarmWorker
   */
  public BAlarmBuilderWorker getAlarmWorker() { return (BAlarmBuilderWorker)get(AlarmWorker); }
  
  /**
   * Set the {@code AlarmWorker} property.
   * @see #AlarmWorker
   */
  public void setAlarmWorker(BAlarmBuilderWorker v) { set(AlarmWorker, v, null); }

////////////////////////////////////////////////////////////////
// Action "BuildAll"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code BuildAll} action.
   * @see #BuildAll()
   */
  public static final Action BuildAll = newAction(Flags.ASYNC, null);
  
  /**
   * Invoke the {@code BuildAll} action.
   * @see #BuildAll
   */
  public void BuildAll() { invoke(BuildAll, null, null); }

////////////////////////////////////////////////////////////////
// Action "BuildOne"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code BuildOne} action.
   * @see #BuildOne()
   */
  public static final Action BuildOne = newAction(Flags.ASYNC, null);
  
  /**
   * Invoke the {@code BuildOne} action.
   * @see #BuildOne
   */
  public void BuildOne() { invoke(BuildOne, null, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BAlarmBuilder.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  private static Logger logger = Logger.getLogger("DMI_SysBuilder_AlarmBuilder");

  public void doBuildOne(){

    switch(this.getAlarmType().getOrdinal()){
      case 0:
        break;
      case 1:

        break;
      case 4:
        break;
      default:
          logger.warning("Build One - enum ordinal not found...!");
        break;
    }
  }

  public void doBuildAll(){}

  public static BAlarmBuilder make(){
      return new BAlarmBuilder();
  }

  public IFuture post(Action a, BValue arg, Context c){
      Invocation work = new Invocation (this, a , arg, c);
      this.getAlarmWorker().postWork(work);
      return null;
  }

  private void findAlarmPoints(String neql, BEnum type){
    try{
      BOrd query = BOrd.make(getNeqlQuery());
      BQueryResult result = (BQueryResult)query.resolve(Sys.getStation()).get();
      Iterator<Entity> results = result.getResults();
      results.forEachRemaining(entity -> {
        BComponent almPoint = ((BComponent)entity);
        logger.info(almPoint.getName());
        BFireAlmMaker firMkr = new BFireAlmMaker(almPoint, getAlarmType());
        BAlarmSourceExt almExt = firMkr.makeExtension();
        almPoint.add(type.getTag()+"Extension", almExt);
      });
    }catch(UnresolvedException ue){
      logger.severe(ue.getMessage());
      ue.printStackTrace();
    }
  }
}
