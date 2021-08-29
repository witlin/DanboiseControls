package com.victorsmolinski.DanboiseControls.se.models.builder_rules;

import com.victorsmolinski.DanboiseControls.se.builders.BTagBuilder;

import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import java.util.logging.Logger;

@NiagaraType

@NiagaraProperty(
        name = "value",
        type = "baja:String",
        defaultValue = "BString.make(\"\")"
)
@NiagaraProperty(
        name = "filterID",
        type = "baja:String",
        defaultValue = "BString.make(\"\")"
)

public class BFilter extends BComponent {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.victorsmolinski.DanboiseControls.se.models.builder_rules.BFilter(3824108702)1.0$ @*/
/* Generated Mon Aug 05 15:56:16 EDT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "value"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code value} property.
   * @see #getValue
   * @see #setValue
   */
  public static final Property value = newProperty(0, BString.make(""), null);
  
  /**
   * Get the {@code value} property.
   * @see #value
   */
  public String getValue() { return getString(value); }
  
  /**
   * Set the {@code value} property.
   * @see #value
   */
  public void setValue(String v) { setString(value, v, null); }

////////////////////////////////////////////////////////////////
// Property "filterID"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code filterID} property.
   * @see #getFilterID
   * @see #setFilterID
   */
  public static final Property filterID = newProperty(0, BString.make(""), null);
  
  /**
   * Get the {@code filterID} property.
   * @see #filterID
   */
  public String getFilterID() { return getString(filterID); }
  
  /**
   * Set the {@code filterID} property.
   * @see #filterID
   */
  public void setFilterID(String v) { setString(filterID, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BFilter.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  private static Logger logger = Logger.getLogger("DMI_SysBuilder_TagBuilder");

  @Override
  public void changed(Property prop, Context cx){
    try{
      if(this.isRunning()){
        logger.info("FILTER ---- FILTER ------ FILTER...CHANGED...");
        logger.info("\t"+prop.getName()+"\t"+this.get(prop.getName()));

        BTagBuilder tBuild = (BTagBuilder) getNavParent().getNavParent().getNavParent();

      }
    }catch(Exception e){
      logger.severe(e.getMessage());
      e.printStackTrace();
    }
  }

  public static BFilter make(){ return new BFilter(); }
}
