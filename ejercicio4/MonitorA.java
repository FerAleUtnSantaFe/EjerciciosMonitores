package ejercicio4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorA {
    private final Lock lock = new ReentrantLock();
    private final Condition condicion = lock.newCondition();
    private int threadsEsperando = 0;

    public void esperar() throws InterruptedException{
        lock.lock();
        try {
            threadsEsperando++;
            condicion.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
    }

    public void liberar(int n){
        lock.lock();
        try {
            if(threadsEsperando >= n){
                for(int i = 0; i < n; i++){
                    condicion.signal();
                    threadsEsperando--;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
    }
}
