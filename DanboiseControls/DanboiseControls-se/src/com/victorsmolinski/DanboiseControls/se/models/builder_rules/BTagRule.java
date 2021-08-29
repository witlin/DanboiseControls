package com.victorsmolinski.DanboiseControls.se.models.builder_rules;

import com.victorsmolinski.DanboiseControls.se.builders.BTagBuilder;
import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;

import javax.baja.naming.SlotPath;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ConcurrentModificationException;
import java.util.logging.Logger;

@NiagaraType
@NiagaraProperty(
        name = "id",
        type = "baja:String",
        defaultValue = "\"\"",
        flags = Flags.SUMMARY
)
@NiagaraProperty(
        name = "ns",
        type = "baja:String",
        defaultValue = "BString.make(\"\")"
)
@NiagaraProperty(
        name = "tag",
        type = "baja:String",
        defaultValue = "BString.make(\"\")"
)
@NiagaraProperty(
        name = "types",
        type = "baja:String",
        defaultValue = "BString.make(\"\")"
)
@NiagaraProperty(
        name = "names",
        type = "baja:String",
        defaultValue = "BString.make(\"\")"
)

public class BTagRule extends BComponent {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.victorsmolinski.DanboiseControls.se.models.builder_rules.BTagRule(2799472882)1.0$ @*/
/* Generated Sun Aug 11 17:04:39 EDT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "id"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code id} property.
   * @see #getId
   * @see #setId
   */
  public static final Property id = newProperty(Flags.SUMMARY, "\"\"", null);
  
  /**
   * Get the {@code id} property.
   * @see #id
   */
  public String getId() { return getString(id); }
  
  /**
   * Set the {@code id} property.
   * @see #id
   */
  public void setId(String v) { setString(id, v, null); }

////////////////////////////////////////////////////////////////
// Property "ns"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code ns} property.
   * @see #getNs
   * @see #setNs
   */
  public static final Property ns = newProperty(0, BString.make(""), null);
  
  /**
   * Get the {@code ns} property.
   * @see #ns
   */
  public String getNs() { return getString(ns); }
  
  /**
   * Set the {@code ns} property.
   * @see #ns
   */
  public void setNs(String v) { setString(ns, v, null); }

////////////////////////////////////////////////////////////////
// Property "tag"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code tag} property.
   * @see #getTag
   * @see #setTag
   */
  public static final Property tag = newProperty(0, BString.make(""), null);
  
  /**
   * Get the {@code tag} property.
   * @see #tag
   */
  public String getTag() { return getString(tag); }
  
  /**
   * Set the {@code tag} property.
   * @see #tag
   */
  public void setTag(String v) { setString(tag, v, null); }

////////////////////////////////////////////////////////////////
// Property "types"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code types} property.
   * @see #getTypes
   * @see #setTypes
   */
  public static final Property types = newProperty(0, BString.make(""), null);
  
  /**
   * Get the {@code types} property.
   * @see #types
   */
  public String getTypes() { return getString(types); }
  
  /**
   * Set the {@code types} property.
   * @see #types
   */
  public void setTypes(String v) { setString(types, v, null); }

////////////////////////////////////////////////////////////////
// Property "names"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code names} property.
   * @see #getNames
   * @see #setNames
   */
  public static final Property names = newProperty(0, BString.make(""), null);
  
  /**
   * Get the {@code names} property.
   * @see #names
   */
  public String getNames() { return getString(names); }
  
  /**
   * Set the {@code names} property.
   * @see #names
   */
  public void setNames(String v) { setString(names, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BTagRule.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  private static Logger logger = Logger.getLogger("DMI_SysBuilder_TagBuilder");
  private Gson gson = new GsonBuilder()
                          .setPrettyPrinting()
                            .serializeNulls().create();
  public BTagRule(){}

  @Override
  public void changed(Property prop, Context cx){
    try{
      if(this.isRunning()){
        logger.info("TAG RULE ---- TAG RULE ------ TAG RULE...CHANGED");
        logger.info(getNavParent().getType().toString());

        BTagBuilder builder = (BTagBuilder) getNavParent();
        AccessController.doPrivileged((PrivilegedAction<Void>)() -> {
          JsonArray rules = builder.getRulesArray();

          String ruleId = SlotPath.unescape(
                  this.getName().substring(10,this.getName().length()-1)
          );

          String oldNs = "";
          String oldTag = "";
          String oldNames = "";
          String oldTypes = "";

          String ns = "";
          String tag = "";
          String names = "";
          String types = "";


          for(JsonElement el: rules){
            try{
              if(el.getAsJsonObject().get("id").getAsString()
                      .contains(ruleId)){

                JsonObject obj = el.getAsJsonObject();
                oldNs = obj.get("ns").getAsString();
                oldTag = obj.get("tag").getAsString();
//                oldNames = obj.get("names").getAsString();
//                oldTypes = obj.get("types").getAsString();

                switch(prop.getName()){
                  case "id":
                    logger.warning("id field can't be changed...!!!");
                    break;
                  case "ns":
                    ns = ((BString)this.get(prop.getName())).getString();
                    break;
                  case  "tag":
                    tag = ((BString)this.get(prop.getName())).getString();
                    break;
//                  case "names":
//                    names = ((BString)this.get(prop.getName())).getString();
//                    break;
//                  case "types":
//                    types = ((BString)this.get(prop.getName())).getString();
//                    break;
                  default:
                    logger.warning("rule obj property unknown...!!!");
                }

                obj.remove("id");
                obj.addProperty("id", ruleId);
                if(ns.isEmpty()){
                  obj.remove("ns");
                  obj.addProperty("ns", oldNs);
                }else{
                  obj.remove("ns");
                  obj.addProperty("ns", ns);
                }
                if(tag.isEmpty()){
                  obj.remove("tag");
                  obj.addProperty("tag", oldTag);
                }else{
                  obj.remove("tag");
                  obj.addProperty("tag", tag);
                }
//                if(names.isEmpty()){
//                  obj.remove("names");
//                  obj.addProperty("names",oldNames);
//                }else{
//                  obj.remove("names");
//                  obj.addProperty("names", names);
//                }
//                if(types.isEmpty()){
//                  obj.remove("types");
//                  obj.addProperty("types", oldTypes);
//                }else{
//                  obj.remove("types");
//                  obj.addProperty("types", types);
//                }
//                logger.info("Changed BAJA Property: "+prop.getName()+"\t"+this.get(prop));
//                logger.info("Nav Type: "+getNavParent().getType().getDisplayName(cx));
                logger.info("Json Obj Before\n"+gson.toJson(obj));
                logger.info("Json Obj After: \n"+gson.toJson(obj));
              }
            }catch(ConcurrentModificationException cme){
              logger.severe(cme.getMessage());
              cme.printStackTrace();
            }
          }
          return null;
        });
      }
    }catch(Exception e){
      logger.severe(e.getMessage());
      e.printStackTrace();
    }
  }

  @Override
  public void added(Property prop, Context cx){
    if(this.isRunning()){
      logger.info("TAG RULE ---- TAG RULE ----- TAG RULE... ADDED");
      logger.info("\t"+prop.getName()+"\t"+this.get(prop.getName()));

      BTagBuilder builder = (BTagBuilder) getNavParent().getNavParent();
      builder.invoke(builder.getAction("AddRule"), this.get(prop.getName()));
    }
  }

  public static BTagRule make(){
    return new BTagRule();
  }

}
