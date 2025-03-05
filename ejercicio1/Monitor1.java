package ejercicio1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor1 {
    private final Lock lock = new ReentrantLock();
    private final Condition conditionAntes = lock.newCondition();
    private final Condition conditionImportante = lock.newCondition();
    private boolean antesEjecutado = false;
    private boolean importanteEjecutado = false;

    public void antesydespues() {
        lock.lock();
        try {
            // Parte antes
            System.out.println("Antes");
            antesEjecutado = true;

            // Señalar que la parte importante puede ejecutarse
            conditionAntes.signal();

            // Esperar hasta que la parte importante se haya ejecutado
            while (!importanteEjecutado) {
                conditionImportante.await();
            }

            // Parte después
            System.out.println("Despues");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void parte2() {
        lock.lock();
        try {
           while(!antesEjecutado){
                conditionAntes.await();
           }

           System.out.println("Importante");
           importanteEjecutado = true;

           conditionImportante.signal();
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            lock.unlock();
        }
    }

}