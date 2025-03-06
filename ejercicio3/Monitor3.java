package ejercicio3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor3 {
    private final Lock lock = new ReentrantLock();
    private final Condition letras = lock.newCondition();
    private int threadsEsperando = 0;
    
    public int cantidadHilos;

    public Monitor3(int cantidadHilos){
        this.cantidadHilos = cantidadHilos;
    }

    public void primero(){
        lock.lock();
        try {
            
            System.out.println("A");

            while(threadsEsperando < cantidadHilos -1 ){
                threadsEsperando++;
                letras.await();
            }
            letras.signalAll();
            System.out.println("1");

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
    }

    public void segundo(){
        lock.lock();
        try {
                        
            System.out.println("B");

            while(threadsEsperando < cantidadHilos -1 ){
                threadsEsperando++;
                letras.await();
            }
            letras.signalAll();
            System.out.println("2");
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
                        
            System.out.println("C");

            while(threadsEsperando < cantidadHilos -1 ){
                threadsEsperando++;
                letras.await();
            }
            letras.signalAll();
            System.out.println("3");

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
    }
}
