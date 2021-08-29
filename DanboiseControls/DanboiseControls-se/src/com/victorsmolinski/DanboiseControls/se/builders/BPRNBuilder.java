package com.victorsmolinski.DanboiseControls.se.builders;

import com.victorsmolinski.DanboiseControls.se.jobs.BSinglePRNJob;
import com.victorsmolinski.DanboiseControls.se.utils.n2parsers.PRNParser;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;

@NiagaraProperty(
        name = "fileOrd",
        type = "baja:Ord",
        defaultValue = "BOrd.make(\"file:^n2ResourceFiles/n2Device.prn\")",
        facets = {
                @Facet(name = "BFacets.TARGET_TYPE", value="\"baja:IFile\"")
        },
        flags = Flags.SUMMARY
)

@NiagaraProperty(
        name = "regex",
        type = "baja:String",
        defaultValue = "BString.make(\"^\\\\s{2,}+[A-Z]{2,3}+\\\\s{3,}+\\\\d{1,3}+\\\\s{3,}+.{1,60}\\\\s{3,}[A-Z]*-*[A-Z]*\\\\s{3,}.++\")",
        flags = Flags.HIDDEN
)

@NiagaraProperty(
        name = "sysWorker",
        type = "DanboiseControls:SysBuilderWorker",
        defaultValue = "new BSysBuilderWorker()",
        flags = Flags.HIDDEN
)

@NiagaraAction(
        name = "buildFromPrn"
)

@NiagaraAction(
        name = "dumpPrn",
        flags = Flags.ASYNC
)

@NiagaraType

public class BPRNBuilder extends BComponent {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.victorsmolinski.DanboiseControls.se.builders.BPRNBuilder(3591642000)1.0$ @*/
/* Generated Fri Jun 28 08:58:51 EDT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "fileOrd"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code fileOrd} property.
   * @see #getFileOrd
   * @see #setFileOrd
   */
  public static final Property fileOrd = newProperty(Flags.SUMMARY, BOrd.make("file:^n2ResourceFiles/n2Device.prn"), BFacets.make(BFacets.TARGET_TYPE, "baja:IFile"));
  
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
// Property "regex"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code regex} property.
   * @see #getRegex
   * @see #setRegex
   */
  public static final Property regex = newProperty(Flags.HIDDEN, BString.make("^\\s{2,}+[A-Z]{2,3}+\\s{3,}+\\d{1,3}+\\s{3,}+.{1,60}\\s{3,}[A-Z]*-*[A-Z]*\\s{3,}.++"), null);
  
  /**
   * Get the {@code regex} property.
   * @see #regex
   */
  public String getRegex() { return getString(regex); }
  
  /**
   * Set the {@code regex} property.
   * @see #regex
   */
  public void setRegex(String v) { setString(regex, v, null); }

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
// Action "buildFromPrn"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code buildFromPrn} action.
   * @see #buildFromPrn()
   */
  public static final Action buildFromPrn = newAction(0, null);
  
  /**
   * Invoke the {@code buildFromPrn} action.
   * @see #buildFromPrn
   */
  public void buildFromPrn() { invoke(buildFromPrn, null, null); }

////////////////////////////////////////////////////////////////
// Action "dumpPrn"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code dumpPrn} action.
   * @see #dumpPrn()
   */
  public static final Action dumpPrn = newAction(Flags.ASYNC, null);
  
  /**
   * Invoke the {@code dumpPrn} action.
   * @see #dumpPrn
   */
  public void dumpPrn() { invoke(dumpPrn, null, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BPRNBuilder.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/


    //SLOT CALLBACKS
    @Override
    public void started(){
            BOrd ord = getFileOrd();
            try{
                    BIFile prnFile = (BIFile)ord.get(this);
                    InputStreamReader in = new InputStreamReader(prnFile.getInputStream());
                    try{
                            BufferedReader bin = new BufferedReader(in);
                    }finally{
                            in.close();
                    }
            }catch(UnresolvedException ue){
                    logger.severe("PRNBuilder File ORD is unresolved...!!\n"+ue.getCause());
            }catch(IOException ioe){
                    logger.severe("PRNBuilder:221 File IO - Unknown file handling exception...!!\n"+ioe.getCause());
            }
    }

    //ACTION CALLBACKS
    public void doBuildFromPrn(Context cx){
            BJobService.getService().submit(new BSinglePRNJob(), cx);
    }

    public void doDumpPrn(){
        PRNParser prnParser = new PRNParser();
        setPRNLines(prnParser.parseToCSV(getRegex(), getFileOrd(), this));
        getPRNLines().stream().forEach(e -> logger.info(e));
    }

    //PARENT METHOD OVERRIDES
    @Override
    public BIcon getIcon(){
            return BIcon.make("module://DanboiseControls/res/icons/gear-black.svg");
    }

    //PRIVATE FIELDS
    private ArrayList<String> prnLines = new ArrayList<>();
    private static Logger logger = Logger.getLogger("DMI_SysBuilder_PRNBuilder");

    //CLASS METHODS

    public ArrayList<String> getPRNLines(){
            if(prnLines != null){ return prnLines;
            }else{
                    logger.warning("PRNBuilder Couldn't do getPRNLines() because prnLines field is null...!!!");
                    return new ArrayList<>();
            }
    }
    public void setPRNLines(ArrayList<String> list) {
            if (list != null) {
                    prnLines = list;
            } else {

                    logger.warning("PRNBuilder Couldn't do setPRNLines() because the list argument is null...!!!");
            }
    }

    public static BPRNBuilder make(){ return new BPRNBuilder(); }
    public IFuture post(Action a, BValue arg, Context c){
        Invocation work = new Invocation(this, a, arg, c);
        this.getSysWorker().postWork(work);
        return null;
    }
}
