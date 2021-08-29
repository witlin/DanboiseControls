package com.victorsmolinski.DanboiseControls.se.builders;

import com.victorsmolinski.DanboiseControls.se.jobs.BSingleDDLJob;
import com.victorsmolinski.DanboiseControls.se.utils.n2parsers.DDLParser;
import com.victorsmolinski.DanboiseControls.se.workers.BSysBuilderWorker;

import javax.baja.file.BIFile;
import javax.baja.job.BJobService;
import javax.baja.naming.BOrd;
import javax.baja.naming.UnresolvedException;
import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import javax.baja.util.IFuture;
import javax.baja.util.Invocation;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;

@NiagaraType

@NiagaraProperty(
        name = "fileOrd",
        type = "baja:Ord",
        defaultValue = "BOrd.make(\"file:^n2ResourceFiles/n2Device.ddl\")",
        facets = {
                @Facet(name = "BFacets.TARGET_TYPE", value="\"baja:IFile\"")
        },
        flags = Flags.SUMMARY
)

@NiagaraProperty(
        name = "sysWorker",
        type = "DanboiseControls:SysBuilderWorker",
        defaultValue = "new BSysBuilderWorker()",
        flags = Flags.HIDDEN
)

@NiagaraAction(
        name = "build"
)

@NiagaraAction(
        name = "dump",
        flags = Flags.ASYNC
)

public class BDDLBuilder extends BComponent {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.victorsmolinski.DanboiseControls.se.builders.BDDLBuilder(289239936)1.0$ @*/
/* Generated Sun May 24 14:20:39 EDT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "fileOrd"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code fileOrd} property.
   * @see #getFileOrd
   * @see #setFileOrd
   */
  public static final Property fileOrd = newProperty(Flags.SUMMARY, BOrd.make("file:^n2ResourceFiles/n2Device.ddl"), BFacets.make(BFacets.TARGET_TYPE, "baja:IFile"));
  
  /**
   * Get the {@code fileOrd} property.
   * @see #fileOrd
   */
  public BOrd getFileOrd() { return (BOrd)get(fileOrd); }
  
  /**
   * Set the {@code fileOrd} property.
   * @see #fileOrd
   */
  public void setFileOrd(BOrd v) { set(fileOrd, v, null); }

////////////////////////////////////////////////////////////////
// Property "sysWorker"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code sysWorker} property.
   * @see #getSysWorker
   * @see #setSysWorker
   */
  public static final Property sysWorker = newProperty(Flags.HIDDEN, new BSysBuilderWorker(), null);
  
  /**
   * Get the {@code sysWorker} property.
   * @see #sysWorker
   */
  public BSysBuilderWorker getSysWorker() { return (BSysBuilderWorker)get(sysWorker); }
  
  /**
   * Set the {@code sysWorker} property.
   * @see #sysWorker
   */
  public void setSysWorker(BSysBuilderWorker v) { set(sysWorker, v, null); }

////////////////////////////////////////////////////////////////
// Action "build"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code build} action.
   * @see #build()
   */
  public static final Action build = newAction(0, null);
  
  /**
   * Invoke the {@code build} action.
   * @see #build
   */
  public void build() { invoke(build, null, null); }

////////////////////////////////////////////////////////////////
// Action "dump"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code dump} action.
   * @see #dump()
   */
  public static final Action dump = newAction(Flags.ASYNC, null);
  
  /**
   * Invoke the {@code dump} action.
   * @see #dump
   */
  public void dump() { invoke(dump, null, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BDDLBuilder.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    @Override
    public void started(){
        BOrd ord = getFileOrd();
        try{
            BIFile ddlFile = (BIFile)ord.get(this);
            InputStreamReader in = new InputStreamReader(ddlFile.getInputStream());
        }catch(UnresolvedException ue){
            logger.severe("DDLBuilder File ORD is unresolved...!!\n"+ue.getCause());
        }catch(IOException ioe){
            logger.severe("DDLBuilder:221 File IO - Unknown file handling exception...!!\n"+ioe.getCause());
        }
    }

    @Override
    public BIcon getIcon(){
        return BIcon.make("module://DanboiseControls/res/icons/gear-black.svg");
    }

    public void doBuild(Context cx){
        BJobService.getService().submit(new BSingleDDLJob(), cx);
    }

    public void doDump(){
        DDLParser ddlParser = new DDLParser();
        getDDLLines().stream().forEach(e -> logger.info(e));
    }

    public static BDDLBuilder make(){ return new BDDLBuilder(); }

    public IFuture post(Action a, BValue arg, Context c){
        Invocation work = new Invocation(this, a, arg, c);
        this.getSysWorker().postWork(work);
        return null;
    }

    public ArrayList<String> getDDLLines(){
        if(ddlLines != null){ return ddlLines;
        }else{
            logger.warning("DDLBuilder Couldn't do getDDLLines() because ddlLines field is null...!!!");
            return new ArrayList<>();
        }
    }

    public void setDDLLines(ArrayList<String> list) {
        if (list != null) {
            ddlLines = list;
        } else {

            logger.warning("DDLBuilder Couldn't do setDDLLines() because the list argument is null...!!!");
        }
    }
    
    private ArrayList<String> ddlLines = new ArrayList<>();
    private static Logger logger = Logger.getLogger("DMI_SysBuilder_DDLBuilder");
}
