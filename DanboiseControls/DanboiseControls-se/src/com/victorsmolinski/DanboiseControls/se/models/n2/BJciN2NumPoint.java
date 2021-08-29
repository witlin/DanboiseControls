package com.victorsmolinski.DanboiseControls.se.models.n2;

import javax.baja.control.BNumericPoint;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.status.BStatus;
import javax.baja.status.BStatusNumeric;
import javax.baja.sys.Context;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType

public class BJciN2NumPoint extends BNumericPoint {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.victorsmolinski.DanboiseControls.se.models.n2.BJciN2NumPoint(2979906276)1.0$ @*/
/* Generated Thu Aug 15 10:20:20 EDT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BJciN2NumPoint.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    @Override
    public void started(){
        if(this.get("in8").isNull() && this.get("in1").isNull()){
            ((BStatusNumeric)this.get("out")).setStatus(BStatus.ok);
        }else{
            ((BStatusNumeric)this.get("out")).setStatus(BStatus.overridden);
        }
    }

    @Override
    public void changed(Property prop, Context cx){
        if(this.get(prop).asComponent().getName().equals("in1") ||
            this.get(prop).asComponent().getName().equals("in8")){
            if(this.get("in8").isNull() && this.get("in1").isNull()){
                ((BStatusNumeric)this.get("out")).setStatus(BStatus.ok);
            }else{
                ((BStatusNumeric)this.get("out")).setStatus(BStatus.overridden);
            }
        }
    }
}
