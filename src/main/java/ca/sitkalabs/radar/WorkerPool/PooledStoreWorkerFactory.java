package ca.sitkalabs.radar.WorkerPool;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;

public class PooledStoreWorkerFactory  implements PooledObjectFactory<StoreWorkerPool> {
    @Override
    public PooledObject<StoreWorkerPool> makeObject() throws Exception {
        return null;
    }

    @Override
    public void destroyObject(PooledObject<StoreWorkerPool> p) throws Exception {

    }

    @Override
    public boolean validateObject(PooledObject<StoreWorkerPool> p) {
        return false;
    }

    @Override
    public void activateObject(PooledObject<StoreWorkerPool> p) throws Exception {

    }

    @Override
    public void passivateObject(PooledObject<StoreWorkerPool> p) throws Exception {

    }
}
