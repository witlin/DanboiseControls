package com.victorsmolinski.DanboiseControls.se.workers;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.NotRunningException;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.BWorker;
import javax.baja.util.CoalesceQueue;
import javax.baja.util.Worker;

@NiagaraType

public class BSysBuilderWorker extends BWorker {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.victorsmolinski.DanboiseControls.se.workers.BSysBuilderWorker(2979906276)1.0$ @*/
/* Generated Mon May 13 14:59:43 EDT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BSysBuilderWorker.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    private CoalesceQueue q;
    private Worker w;

    @Override
    public Worker getWorker(){
        if(null == w){
            q = new CoalesceQueue(100);
            w = new Worker(q);
        }
        return w;
    }

    public void postWork(Runnable r){
        if(null == q || !isRunning()) throw new NotRunningException();

        q.enqueue(r);
    }

    public static BSysBuilderWorker make(){
        return new BSysBuilderWorker();
    }
}
