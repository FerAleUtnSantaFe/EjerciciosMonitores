package ejercicio1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor1 {
    private final Lock lock = new ReentrantLock();
    private final Condition antes = lock.newCondition();
    private final Condition despues =  lock.newCondition();

    private boolean antesEjecutado = false;
    private boolean importanteEjecutado = false;

    public void antesydespues(){
        lock.lock();
        try{
            System.out.println("Antes");
            antesEjecutado = true;

            antes.signal();

            while(!importanteEjecutado){
                despues.await();
            }

            System.out.println("Despues");

        } catch(Exception e){
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }

    public void parte2(){
        lock.lock();
        try{
            while(!antesEjecutado){
                antes.await();
            }

            System.out.println("Importante");
            importanteEjecutado = true;
            despues.signal();
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }

}