package com.victorsmolinski.DanboiseControls.se.services;

import com.victorsmolinski.DanboiseControls.se.builders.*;

import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import javax.baja.util.IFuture;
import javax.baja.util.Invocation;
import java.util.logging.Logger;

@NiagaraProperty(
        name = "PRNBuilder",
        type = "DanboiseControls:PRNBuilder",
        defaultValue = "BPRNBuilder.make()",
        flags = Flags.SUMMARY
)
@NiagaraProperty(
        name = "DMOBuilder",
        type = "DanboiseControls:DMOBuilder",
        defaultValue = "BDMOBuilder.make()",
        flags = Flags.SUMMARY
)
@NiagaraProperty(
        name = "DDLBuilder",
        type = "DanboiseControls:DDLBuilder",
        defaultValue = "BDDLBuilder.make()",
        flags = Flags.SUMMARY
)
@NiagaraProperty(
        name = "TagBuilder",
        type = "DanboiseControls:TagBuilder",
        defaultValue = "BTagBuilder.make()",
        flags = Flags.SUMMARY
)

@NiagaraProperty(
        name = "AlarmBuilder",
        type = "DanboiseControls:AlarmBuilder",
        defaultValue = "BAlarmBuilder.make()",
        flags = Flags.SUMMARY
)

@NiagaraType

public class BSysBuilderService extends BAbstractService {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.victorsmolinski.DanboiseControls.se.services.BSysBuilderService(12854749)1.0$ @*/
/* Generated Sun May 24 14:28:50 EDT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "PRNBuilder"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code PRNBuilder} property.
   * @see #getPRNBuilder
   * @see #setPRNBuilder
   */
  public static final Property PRNBuilder = newProperty(Flags.SUMMARY, BPRNBuilder.make(), null);
  
  /**
   * Get the {@code PRNBuilder} property.
   * @see #PRNBuilder
   */
  public BPRNBuilder getPRNBuilder() { return (BPRNBuilder)get(PRNBuilder); }
  
  /**
   * Set the {@code PRNBuilder} property.
   * @see #PRNBuilder
   */
  public void setPRNBuilder(BPRNBuilder v) { set(PRNBuilder, v, null); }

////////////////////////////////////////////////////////////////
// Property "DMOBuilder"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code DMOBuilder} property.
   * @see #getDMOBuilder
   * @see #setDMOBuilder
   */
  public static final Property DMOBuilder = newProperty(Flags.SUMMARY, BDMOBuilder.make(), null);
  
  /**
   * Get the {@code DMOBuilder} property.
   * @see #DMOBuilder
   */
  public BDMOBuilder getDMOBuilder() { return (BDMOBuilder)get(DMOBuilder); }
  
  /**
   * Set the {@code DMOBuilder} property.
   * @see #DMOBuilder
   */
  public void setDMOBuilder(BDMOBuilder v) { set(DMOBuilder, v, null); }

////////////////////////////////////////////////////////////////
// Property "DDLBuilder"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code DDLBuilder} property.
   * @see #getDDLBuilder
   * @see #setDDLBuilder
   */
  public static final Property DDLBuilder = newProperty(Flags.SUMMARY, BDDLBuilder.make(), null);
  
  /**
   * Get the {@code DDLBuilder} property.
   * @see #DDLBuilder
   */
  public BDDLBuilder getDDLBuilder() { return (BDDLBuilder)get(DDLBuilder); }
  
  /**
   * Set the {@code DDLBuilder} property.
   * @see #DDLBuilder
   */
  public void setDDLBuilder(BDDLBuilder v) { set(DDLBuilder, v, null); }

////////////////////////////////////////////////////////////////
// Property "TagBuilder"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code TagBuilder} property.
   * @see #getTagBuilder
   * @see #setTagBuilder
   */
  public static final Property TagBuilder = newProperty(Flags.SUMMARY, BTagBuilder.make(), null);
  
  /**
   * Get the {@code TagBuilder} property.
   * @see #TagBuilder
   */
  public BTagBuilder getTagBuilder() { return (BTagBuilder)get(TagBuilder); }
  
  /**
   * Set the {@code TagBuilder} property.
   * @see #TagBuilder
   */
  public void setTagBuilder(BTagBuilder v) { set(TagBuilder, v, null); }

////////////////////////////////////////////////////////////////
// Property "AlarmBuilder"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code AlarmBuilder} property.
   * @see #getAlarmBuilder
   * @see #setAlarmBuilder
   */
  public static final Property AlarmBuilder = newProperty(Flags.SUMMARY, BAlarmBuilder.make(), null);
  
  /**
   * Get the {@code AlarmBuilder} property.
   * @see #AlarmBuilder
   */
  public BAlarmBuilder getAlarmBuilder() { return (BAlarmBuilder)get(AlarmBuilder); }
  
  /**
   * Set the {@code AlarmBuilder} property.
   * @see #AlarmBuilder
   */
  public void setAlarmBuilder(BAlarmBuilder v) { set(AlarmBuilder, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BSysBuilderService.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    //PARENT METHOD OVERRIDES
    @Override
    public Type[] getServiceTypes() {
        return serviceTypes;
    }

    @Override
    public BIcon getIcon(){
        return BIcon.make("module://DanboiseControls/res/icons/dmicon.png");
    }

    //PRIVATE FIELDS
    private static Logger logger = Logger.getLogger("DMI_SysBuilder_Service");
    private static Type[] serviceTypes = new Type[]{TYPE};

    //CLASS METHODS
    /*public IFuture post(Action a, BValue arg, Context c){
        Invocation work = new Invocation(this, a, arg, c);
        getSysBuilderWorker().postWork(work);
        return null;
    }*/
}
