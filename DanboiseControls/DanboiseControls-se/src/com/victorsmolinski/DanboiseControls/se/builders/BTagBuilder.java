package com.victorsmolinski.DanboiseControls.se.builders;

import com.victorsmolinski.DanboiseControls.se.models.builder_rules.BFilter;
import com.victorsmolinski.DanboiseControls.se.models.builder_rules.BTagRule;
import com.victorsmolinski.DanboiseControls.se.utils.general.Resolver;

import com.victorsmolinski.DanboiseControls.se.workers.BTagBuilderWorker;
import com.google.gson.*;

import javax.baja.file.BFileSystem;
import javax.baja.file.BIFile;
import javax.baja.file.FilePath;
import javax.baja.naming.BOrd;
import javax.baja.naming.OrdQuery;
import javax.baja.naming.SlotPath;
import javax.baja.naming.UnresolvedException;
import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import javax.baja.tag.Id;
import javax.baja.tag.Tag;
import javax.baja.util.IFuture;
import javax.baja.util.Invocation;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@NiagaraType

@NiagaraProperty(
        name = "QueryPath",
        type = "baja:Ord",
        defaultValue = "BOrd.make(\"\")"
)
@NiagaraProperty(
        name = "QueryBaseType",
        type = "baja:String",
        defaultValue = "BString.make(\"\")"
)
@NiagaraProperty(
        name = "TagRulesOrd",
        type = "baja:Ord",
        defaultValue = "BOrd.make(\"file:^sysBuilder/TagRules.json\")",
        facets = {
                @Facet(name = "BFacets.TARGET_TYPE", value = "\"baja:IFile\"")
        }
)
@NiagaraProperty(
        name = "TagWorker",
        type = "DanboiseControls:TagBuilderWorker",
        defaultValue = "BTagBuilderWorker.make()",
        flags = Flags.HIDDEN
)
@NiagaraAction(
        name = "AddRule",
        parameterType = "DanboiseControls:TagRule",
        defaultValue = "BTagRule.make()",
        flags = Flags.SUMMARY | Flags.ASYNC
)
@NiagaraAction(
        name = "BuildAll",
        flags = Flags.SUMMARY | Flags.ASYNC
)
@NiagaraAction(
        name = "ClearDoc",
        flags = Flags.SUMMARY | Flags.ASYNC
)
@NiagaraAction(
        name = "ReadFromDoc",
        flags = Flags.SUMMARY | Flags.ASYNC
)
@NiagaraAction(
        name = "SyncToFile",
        flags = Flags.SUMMARY | Flags.ASYNC
)

public class BTagBuilder extends BComponent {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.victorsmolinski.DanboiseControls.se.builders.BTagBuilder(1999939874)1.0$ @*/
/* Generated Wed Jul 24 08:19:36 EDT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "QueryPath"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code QueryPath} property.
   * @see #getQueryPath
   * @see #setQueryPath
   */
  public static final Property QueryPath = newProperty(0, BOrd.make(""), null);
  
  /**
   * Get the {@code QueryPath} property.
   * @see #QueryPath
   */
  public BOrd getQueryPath() { return (BOrd)get(QueryPath); }
  
  /**
   * Set the {@code QueryPath} property.
   * @see #QueryPath
   */
  public void setQueryPath(BOrd v) { set(QueryPath, v, null); }

////////////////////////////////////////////////////////////////
// Property "QueryBaseType"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code QueryBaseType} property.
   * @see #getQueryBaseType
   * @see #setQueryBaseType
   */
  public static final Property QueryBaseType = newProperty(0, BString.make(""), null);
  
  /**
   * Get the {@code QueryBaseType} property.
   * @see #QueryBaseType
   */
  public String getQueryBaseType() { return getString(QueryBaseType); }
  
  /**
   * Set the {@code QueryBaseType} property.
   * @see #QueryBaseType
   */
  public void setQueryBaseType(String v) { setString(QueryBaseType, v, null); }

////////////////////////////////////////////////////////////////
// Property "TagRulesOrd"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code TagRulesOrd} property.
   * @see #getTagRulesOrd
   * @see #setTagRulesOrd
   */
  public static final Property TagRulesOrd = newProperty(0, BOrd.make("file:^sysBuilder/TagRules.json"), BFacets.make(BFacets.TARGET_TYPE, "baja:IFile"));
  
  /**
   * Get the {@code TagRulesOrd} property.
   * @see #TagRulesOrd
   */
  public BOrd getTagRulesOrd() { return (BOrd)get(TagRulesOrd); }
  
  /**
   * Set the {@code TagRulesOrd} property.
   * @see #TagRulesOrd
   */
  public void setTagRulesOrd(BOrd v) { set(TagRulesOrd, v, null); }

////////////////////////////////////////////////////////////////
// Property "TagWorker"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code TagWorker} property.
   * @see #getTagWorker
   * @see #setTagWorker
   */
  public static final Property TagWorker = newProperty(Flags.HIDDEN, BTagBuilderWorker.make(), null);
  
  /**
   * Get the {@code TagWorker} property.
   * @see #TagWorker
   */
  public BTagBuilderWorker getTagWorker() { return (BTagBuilderWorker)get(TagWorker); }
  
  /**
   * Set the {@code TagWorker} property.
   * @see #TagWorker
   */
  public void setTagWorker(BTagBuilderWorker v) { set(TagWorker, v, null); }

////////////////////////////////////////////////////////////////
// Action "AddRule"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code AddRule} action.
   * @see #AddRule(BTagRule parameter)
   */
  public static final Action AddRule = newAction(Flags.SUMMARY | Flags.ASYNC, BTagRule.make(), null);
  
  /**
   * Invoke the {@code AddRule} action.
   * @see #AddRule
   */
  public void AddRule(BTagRule parameter) { invoke(AddRule, parameter, null); }

////////////////////////////////////////////////////////////////
// Action "BuildAll"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code BuildAll} action.
   * @see #BuildAll()
   */
  public static final Action BuildAll = newAction(Flags.SUMMARY | Flags.ASYNC, null);
  
  /**
   * Invoke the {@code BuildAll} action.
   * @see #BuildAll
   */
  public void BuildAll() { invoke(BuildAll, null, null); }

////////////////////////////////////////////////////////////////
// Action "ClearDoc"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code ClearDoc} action.
   * @see #ClearDoc()
   */
  public static final Action ClearDoc = newAction(Flags.SUMMARY | Flags.ASYNC, null);
  
  /**
   * Invoke the {@code ClearDoc} action.
   * @see #ClearDoc
   */
  public void ClearDoc() { invoke(ClearDoc, null, null); }

////////////////////////////////////////////////////////////////
// Action "ReadFromDoc"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code ReadFromDoc} action.
   * @see #ReadFromDoc()
   */
  public static final Action ReadFromDoc = newAction(Flags.SUMMARY | Flags.ASYNC, null);
  
  /**
   * Invoke the {@code ReadFromDoc} action.
   * @see #ReadFromDoc
   */
  public void ReadFromDoc() { invoke(ReadFromDoc, null, null); }

////////////////////////////////////////////////////////////////
// Action "SyncToFile"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code SyncToFile} action.
   * @see #SyncToFile()
   */
  public static final Action SyncToFile = newAction(Flags.SUMMARY | Flags.ASYNC, null);
  
  /**
   * Invoke the {@code SyncToFile} action.
   * @see #SyncToFile
   */
  public void SyncToFile() { invoke(SyncToFile, null, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BTagBuilder.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    //TODO: 07/22/2019 - USE THE changed() BAJA-COMPONENT CALLBACK FOR ALL THE FILE-SYNC-OPS THAT DON'T HAVE THEIR OWN.

    //TODO: 07/22/2019 - Make a set of type filters and add syntax checks for them on all action callbacks querying for tags to be added in the format :
    //TODO: 07/22/2019 - all->
    //TODO: 07/22/2019 - numeric->all
    //TODO: 07/22/2019 - boolean->all
    //TODO: 07/22/2019 - enum->all

    //TODO: 07/22/2019 - Add ignore upper/lower case for name filters, and regex checks as needed.



    //PRIVATE FIELDS
    private static Logger logger = Logger.getLogger("DMI_SysBuilder_TagBuilder");
    private Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private JsonObject RulesDoc = new JsonObject();
    private boolean setToDelete = true;

    //COMPONENT CALLBACKS
    @Override
    public void atSteadyState(){
        AccessController.doPrivileged((PrivilegedAction<Void>)() -> {
            BOrd fileOrd = getTagRulesOrd();
            try{
                this.invoke(ReadFromDoc, null);
            }catch(UnresolvedException ue){
                logger.warning("File ORD Unresolved: \n"+
                        ue.getCause());
                OrdQuery[] qps = fileOrd.parse();
                FilePath fp = (FilePath)qps[qps.length-1];
                try{
                    BIFile file = BFileSystem.INSTANCE.makeFile(fp);

                    RulesDoc.add("rules", new JsonArray());
                    try{
                        RulesDoc.keySet().stream().collect(Collectors.toSet());
                        file.write(gson.toJson(RulesDoc).getBytes());

                    }catch(Exception e){
                        logger.warning("Cant add to Rules document because of possible duplicate...!");
                    }

                    logger.info("Tag Builder in the SysBuilder service started, but could not find a tag rule's file...\n" +
                            gson.toJson(RulesDoc));
                }
                catch(IOException ioe){ logger.severe("File IO Error - Couldn't make the file: \n"+
                        ioe.getCause());
                }
            }
            return null;
        });
    }

    @Override
    public void added(Property prop, Context cx){
        if(this.isRunning()){
            logger.info("TAG  BUILDER --- TAG BUILDER --- TAG BUILDER...ADDED A RULE...!");
            logger.info("Property Added: \t"+prop.getName()+"\t"+this.get(prop.getName()));
            logger.info("Property Type: \t"+prop.getType());
            this.invoke(AddRule, this.get(prop.getName()));
        }
    }

    @Override
    public void removed(Property prop, BValue oldVal, Context cx){
        AccessController.doPrivileged((PrivilegedAction<Void>)()->{
            try{
                if(setToDelete){
                    JsonObject obj2Delete = null;
                    JsonArray rules = RulesDoc.getAsJsonArray("rules");
                    String ruleId = SlotPath.unescape(
                            prop.getName().substring(10,prop.getName().length()-1));

                    for(JsonElement e: rules){
                        try{
                            if(e.getAsJsonObject().get("id").getAsString()
                                    .contains(ruleId)){
                                obj2Delete = e.getAsJsonObject();
                                logger.info(e.getAsJsonObject().get("id").getAsString());
                            }
                        }catch(ConcurrentModificationException cme){
                            logger.severe(cme.getMessage());
                            cme.printStackTrace();
                        }
                    }

                    logger.info(prop.getName());
                    logger.info(ruleId);

                    RulesDoc.getAsJsonArray("rules")
                            .remove(obj2Delete);
                    logger.info(gson.toJson(RulesDoc));
                    FileWriter fw = tagFileWriter();
                    fw.write(gson.toJson(RulesDoc));
                    fw.close();
                }
            }catch(Exception e){
                logger.severe(e.getMessage());
                e.printStackTrace();
            }
            return null;
        });
    }

    //ACTION CALLBACKS

    /**
     *
     * @param
     * @return void
     * */

    public void doClearDoc(){

        AccessController.doPrivileged((PrivilegedAction<Void>)() -> {
            try{
                RulesDoc.add("rules", new JsonArray());
                logger.info(gson.toJson(RulesDoc));
                FileWriter fw = tagFileWriter();
                fw.write(gson.toJson(RulesDoc));
                fw.close();

            }catch(Exception io){
                io.getMessage();
                io.printStackTrace();
            }
            return null;
        });
    }

    /**
     *
     * @param
     * @return void
     * */

    public void doSyncToFile(){

        AccessController.doPrivileged((PrivilegedAction<Void>)() -> {
            try{
                logger.info(gson.toJson(RulesDoc));
                RulesDoc.keySet().stream().collect(Collectors.toSet());
                FileWriter fw = tagFileWriter();
                fw.write(gson.toJson(RulesDoc));
                fw.close();
            }catch(IOException io){
                io.getMessage();
                io.printStackTrace();
            }catch(Exception e){
                logger.warning("Failed op due to possible duplicate rule");
                e.getMessage();
                e.printStackTrace();
            }
            return null;
        });
    }

    /**
     *
     * @param
     * @return void
     * */

    public void doReadFromDoc(){
        AccessController.doPrivileged((PrivilegedAction<Void>)() -> {
            setToDelete = false;

            RulesDoc = this.getRulesDoc(getTagRulesOrd());
            logger.info("Rules Json Document ("+RulesDoc.size()
                    +"):\n"+gson.toJson(RulesDoc));
            for (String s : RulesDoc.keySet()) {
                logger.info(s);
            }
            try{
                Arrays.stream(this.getDynamicPropertiesArray()).forEach( d -> {
                    if(d.getName().contains("TagRule")){ this.remove(d.getName()); }
                });
            }catch(Exception e){
                logger.severe(e.getMessage());
                e.printStackTrace();
            }

            try{
                RulesDoc = getRulesDoc(getTagRulesOrd());
                JsonArray rules = RulesDoc.getAsJsonArray("rules");

                rules.forEach( r ->{
                    JsonObject rule = r.getAsJsonObject();
                    JsonArray types = rule.get("types").getAsJsonArray();
                    JsonArray names = rule.get("names").getAsJsonArray();

                    BTagRule ruleComp = BTagRule.make();
                    ruleComp.setFlags(ruleComp.getSlot("types"), Flags.HIDDEN);
                    ruleComp.setFlags(ruleComp.getSlot("names"), Flags.HIDDEN);

                    ruleComp.setId(rule.get("id").getAsString());
                    ruleComp.setNs(rule.get("ns").getAsString());
                    ruleComp.setTag(rule.get("tag").getAsString());

                    ruleComp.add("TypeFilters", new BComponent());
                    ruleComp.add("NameFilters", new BComponent());
                    BComponent typesComp = ruleComp.get("TypeFilters").asComponent();
                    BComponent namesComp = ruleComp.get("NameFilters").asComponent();

                    for (JsonElement t : types) {
                        BFilter tFilter = new BFilter();
                        JsonObject jsonFilter = t.getAsJsonObject();
                        tFilter.setValue(jsonFilter.get("TypeFilter").getAsString());
                        tFilter.setFilterID(jsonFilter.get("TypeId").getAsString());
                        typesComp.add(SlotPath
                                .escape("TypeFilter "
                                        .concat(tFilter.getFilterID())),
                                tFilter);
                    }

                    for(JsonElement n : names){
                        BFilter nFilter = new BFilter();
                        JsonObject jsonFilter = n.getAsJsonObject();
                        nFilter.setValue(jsonFilter.get("NameFilter").getAsString());
                        nFilter.setFilterID(jsonFilter.get("NameId").getAsString());
                        namesComp.add(SlotPath
                                .escape("NameFilter "
                                        .concat(nFilter.getFilterID())),
                                nFilter);
                    }

                    this.add(SlotPath.escape("TagRule ".
                                    concat(ruleComp.getId())),
                            ruleComp);
                });
            }catch(Exception e){
                setToDelete = true;
                e.getMessage();
                e.printStackTrace();
            }
            setToDelete = true;
            return null;
        });
    }
//
    /**
     *
     * @param parameter
     * @return void
    * */

    public void doAddRule(BTagRule parameter){

        AccessController.doPrivileged((PrivilegedAction<Void>)() -> {
            /*
            * Make the rule object, and the complex types like the two arrays of name and type filters.
            * */
            JsonObject rule = new JsonObject();
            JsonArray types = new JsonArray();
            JsonArray names = new JsonArray();

            /*
             * Set the Id field using a basic UUID.
             * */
            try{
                final UUID uuid = UUID.randomUUID();
                parameter.setId("_id".concat(uuid.toString()));
            }catch(Exception e){
                logger.severe(e.getMessage());
                e.printStackTrace();
            }

            /*
             *Set all the basic properties of the rule object
             * */
            rule.addProperty("id", parameter.getId());
            rule.addProperty("ns", parameter.getNs());
            rule.addProperty("tag", parameter.getTag());


            /*
             *NAME FILTERS...
             *Iterate through the items in the name filter list, and create a Filter object for each,
             * add the filter objects to the names array object.
             * */
            Arrays.stream(parameter.getNames().split(","))
                    .forEach(e -> {
                        JsonObject filter = new JsonObject();
                        try{
                            final UUID uuid = UUID.randomUUID();
                            filter.addProperty("NameId", uuid.toString());
                            filter.addProperty("NameFilter", e);
                            names.add(filter);
                        }catch(Exception ex){
                            logger.severe(ex.getMessage());
                            ex.printStackTrace();
                        }
                    });
            rule.add("names", names);

            /*
            TYPE FILTERS
             * Iterate through the items if they are formatted as csv, else try to interpret all->.. cmds,
             * finally throw an error.
             * */
            if(parameter.getTypes().contains(",")){
                Arrays.stream(parameter.getTypes().split(","))
                        .forEach( e -> {
                            JsonObject filter = new JsonObject();
                            try{
                                final UUID uuid = UUID.randomUUID();
                                filter.addProperty("TypeId", uuid.toString());
                                filter.addProperty("TypeFilter", e);
                                types.add(filter);
                            }catch(Exception ex){
                                logger.severe(ex.getMessage());
                                ex.printStackTrace();
                            }
                        });
                rule.add("types", types);
            }else{
                Exception e = new Exception();
                try {
                    logger.severe(e.getLocalizedMessage());
                    throw e;
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

            /*
            *Add the rule record object to the array of rules int the root Json Document Object.
            * */
            RulesDoc.getAsJsonArray("rules").add(rule);
            logger.info(gson.toJson(RulesDoc));

            /*
             *Finally serialize and write the modified document to file.
             * */
            try{
                FileWriter fw = tagFileWriter();
                fw.write(gson.toJson(RulesDoc));
                fw.close();
            }catch(IOException ioe){ioe.getMessage(); ioe.printStackTrace();}

            return null;
        });
    }

    /**
     *
     * @param
     * @return void
     * */

    public void doBuildAll(){
        try{

            Resolver resolver = new Resolver();

            AccessController.doPrivileged((PrivilegedAction<Void>)() -> {
                RulesDoc = getRulesDoc(getTagRulesOrd());
                JsonArray rules = RulesDoc.getAsJsonArray("rules");

                rules.forEach( rule -> {
                    if(rule.isJsonObject()){

                        try{
                            JsonArray names = null;
                            JsonArray types = null;
                            String ns = null;
                            String tag = null;

                            String path = getQueryPath().encodeToString();
                            String bqlSelect = "|bql:select slotPath ";
                            String bqlFrom = "from "+ getQueryBaseType();
                            String bqlWhere = " where name like \'";
                            String bqlType = "\' and type like \'";
                            String bqlEnd = "\'";

                            boolean validRule = true;

                            for(String e: rule.getAsJsonObject().keySet()){
                                JsonElement prop = rule.getAsJsonObject().get(e);

                                if(e.equals("ns") && prop.isJsonPrimitive()){
                                    ns = prop.getAsString();
                                }else if(e.equals("tag") && prop.isJsonPrimitive()){
                                    tag = prop.getAsString();
                                }else if(e.equals("types") && prop.isJsonArray()){
                                    types = prop.getAsJsonArray();
                                }else if(e.equals("names") && prop.isJsonArray()){
                                    names = prop.getAsJsonArray();
                                }else{
                                    logger.warning("ERROR - BuildAll...rule property type unknown...!");
                                    validRule = false;
                                    break;
                                }
                            }
                            if(validRule){
                                for(JsonElement el: names){
                                    String name = el.getAsString();

                                    for(JsonElement type: types){
                                        String bql = path.concat(bqlSelect.concat(bqlFrom.concat(bqlWhere + name))) +
                                                bqlType + type.getAsString() + bqlEnd;
                                        ArrayList<BComponent> queryObjects = resolver.resolve(BString.make( bql ));

                                        for (BComponent obj : queryObjects) {
                                            try{
                                                Id.verifyTagId(ns, tag);
                                                Id i = Id.newId(ns, tag);
                                                Tag t = Tag.newTag(i.getQName());
                                                obj.tags().set(t);
                                                logger.info("TAG ADDED: "+ t.getId().toString() +
                                                        "\t-\t" + obj.getSlotPath().toString());}
                                            catch(Exception e)
                                            {
                                                logger.warning("ERROR-TAG NOT ADDED: "+ e.getMessage());
                                                e.printStackTrace();
                                            }
                                        }
                                        logger.info(bql);
                                    }
                                }
                            }else{ logger.warning("ERROR - Invalid TagRule...!!!"); }

                        }catch(UnresolvedException ue){logger.severe(ue.getMessage()); ue.printStackTrace();}
                    }else{
                        logger.warning("ERROR - Rule is not a valid JSON object...!");
                    }
                });
                return null;
            });
        }catch(Exception e){
            logger.severe(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Static Factory Method for creating TagBuilders...
     * @param
     * @return BTagBuilder
     * */

    public static BTagBuilder make() { return new BTagBuilder(); }

    /**
     * Method for extracting the rule set stored in a local JSON file
     * @return JsonArray
     * */

    private JsonObject getRulesDoc(BOrd fileOrd){

        JsonObject json = null;
        try{
            BIFile tagFile = (BIFile)fileOrd.get(this);
            String src = new String(tagFile.read());

            json = AccessController.doPrivileged((PrivilegedAction<JsonObject>)() -> {

                JsonObject doc = new JsonObject();
                try{
                    doc = gson.fromJson(src, JsonObject.class);
                }catch(Exception RulesDocException){
                    logger.severe(RulesDocException.getMessage());
                }
                return doc;
            });
        }catch(IOException ioe){
            logger.severe(ioe.getMessage());
        }
        return json;
    }

    /**
     * We want to make sure the rules document is not empty, and it contains a JSON array of rule objects.
     * This method doesn't have privileged code access so it must be run called from one that does...!
     * @param
     * @return boolean
     * */

    private FileWriter tagFileWriter(){
        try{
            BIFile fileObj = (BIFile)getTagRulesOrd().get(this);
            FilePath fp = fileObj.getFilePath();
            File f = BFileSystem.INSTANCE.pathToLocalFile(fp);
            FileWriter fw = new FileWriter(f);
            return fw;
        }catch(UnresolvedException ure){
            logger.severe("FILE IO = ERROR - Unresolved file ord...!!!\n"+
                    ure.getMessage());
        }catch(IOException io){
            logger.severe("FILE IO ERROR - Unknown error...!!\n"+io.getMessage());
        }catch(Exception e){ logger.severe("UNKOWN EXCEPTION...!!\t"+e.getMessage()); }

        return null;
    }

    /**
     * Method for read only exposure of the rules json array object.
     * @return JsonArray
     * */
    public JsonArray getRulesArray(){
         return this.RulesDoc.getAsJsonArray("rules");
    }

    public IFuture post(Action a, BValue arg, Context c){
        Invocation work = new Invocation (this, a , arg, c);
        this.getTagWorker().postWork(work);
        return null;
    }
}