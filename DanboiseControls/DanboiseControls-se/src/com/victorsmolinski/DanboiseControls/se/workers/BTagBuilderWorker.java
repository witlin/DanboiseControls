package com.victorsmolinski.DanboiseControls.se.workers;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.NotRunningException;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.BWorker;
import javax.baja.util.CoalesceQueue;
import javax.baja.util.Worker;

@NiagaraType

public class BTagBuilderWorker extends BWorker {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.victorsmolinski.DanboiseControls.se.workers.BTagBuilderWorker(2979906276)1.0$ @*/
/* Generated Mon Jul 08 15:09:03 EDT 2019 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BTagBuilderWorker.class);

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

    public static BTagBuilderWorker make(){
        return new BTagBuilderWorker();
    }
}
