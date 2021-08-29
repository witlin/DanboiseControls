package com.victorsmolinski.DanboiseControls.se.models.builder_rules;

import com.victorsmolinski.DanboiseControls.se.builders.BTagBuilder;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import java.util.logging.Logger;

@NiagaraType

public class BFilterGroup extends BComponent {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.victorsmolinski.DanboiseControls.se.models.builder_rules.BFilterGroup(2979906276)1.0$ @*/
/* Generated Sun Aug 11 18:13:35 EDT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BFilterGroup.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    private static Logger logger = Logger.getLogger("DMI_SysBuilder_TagBuilder");

    @Override
    public void added(Property prop, Context cx){
        if(this.isRunning()){
            logger.info("FILTER-GROUP ---- FILTER-GROUP ------ FILTER-GROUP...ADDED...");
            logger.info("\t"+prop.getName()+"\t"+this.get(prop.getName()));
            logger.info("Nav Parent Type: "+getNavParent().getNavParent().getType());

        }
    }

    @Override
    public void changed(Property prop, Context cx){
        try{
            if(this.isRunning()){
                logger.info("FILTER-GROUP ---- FILTER-GROUP ------ FILTER-GROUP...CHANGED...");
                logger.info("\t"+prop.getName()+"\t"+this.get(prop.getName()));

                BTagBuilder tBuild = (BTagBuilder) getNavParent().getNavParent();
            }
        }catch(Exception e){
            logger.severe(e.getMessage());
            e.printStackTrace();
        }
    }

    public static BFilter make(){ return new BFilter(); }
}
