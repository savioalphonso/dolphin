package ca.sitkalabs.radar.WorkerPool;

import ca.sitkalabs.radar.Models.store.StoreWorker;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;

import java.util.Collections;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class StoreWorkerPool implements ObjectPool<StoreWorker> {

    private PooledStoreWorkerFactory factory;

    @Override
    public StoreWorker borrowObject(){
        return null;
    }

    @Override
    public void returnObject(StoreWorker obj) {

    }

    @Override
    public void invalidateObject(StoreWorker obj) {

    }

    @Override
    public void addObject() {

    }

    @Override
    public int getNumIdle() {
        return 0;
    }

    @Override
    public int getNumActive() {
        return 0;
    }

    @Override
    public void clear(){

    }

    @Override
    public void close() {

    }
}
