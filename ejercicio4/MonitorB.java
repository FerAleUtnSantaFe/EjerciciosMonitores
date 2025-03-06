package ejercicio4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorB {
    private final Lock lock = new ReentrantLock();
    private final Condition condicion = lock.newCondition();
    private int cantBloqueados = 0;

    public void esperar() throws InterruptedException {
        lock.lock();
        try {
            cantBloqueados++;
            condicion.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void liberar(int n) {
        lock.lock();
        try {
            if (cantBloqueados >= n) {
                condicion.signalAll();
                cantBloqueados = 0;
            } else {
                cantBloqueados++;
                condicion.await();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
