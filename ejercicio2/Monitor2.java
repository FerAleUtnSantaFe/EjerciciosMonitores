package ejercicio2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor2 {
    private final Lock lock = new ReentrantLock();
    private final Condition conditionPrimero = lock.newCondition();
    private final Condition conditionSegundo = lock.newCondition();
    private final Condition conditionTercero = lock.newCondition();

    private int turno = 1; // 1 para primero, 2 para segundo, 3 para tercero

    public void primero(){
        lock.lock();
        try{
            while(turno != 1){
                conditionPrimero.await();
            }

            System.out.println("Uno");
            turno = 2;
            conditionSegundo.signal();
        } catch(Exception e){
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
    }

    public void segundo(){
        lock.lock();
        try {
            while(turno != 2){
                conditionSegundo.await();
            }

            System.out.println("Dos");
            turno = 3;

            conditionTercero.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        finally{
            lock.unlock();
        }
    }

    public void tercero(){
        lock.lock();
        try {
            while(turno != 3){
                conditionTercero.await();
            }

            System.out.println("Tres");
            turno = 1;

            conditionPrimero.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        finally{
            lock.unlock();
        }
    }
}
