package ejercicio6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorB6 {
    private final Lock lock = new ReentrantLock();
    private final Condition puedeEntrar = lock.newCondition();
    private final Condition puedeSalir = lock.newCondition();
    private final Condition puedeComenzar = lock.newCondition();

    private static final int CAPACIDAD_MAXIMA = 50;
    private static final int MIN_ASISTENTES = 40;

    private int asistentes = 0;
    private boolean charlaIniciada = false;

    public void orador() throws InterruptedException {
        lock.lock();
        try {
            while (asistentes < MIN_ASISTENTES) {
                System.out.println("Orador esperando la cantidad minima de asistentes");
                puedeComenzar.await();
            }
            
            System.out.println("Comienza la charla con " + asistentes + " asistentes");
            charlaIniciada = true;
            Thread.sleep(5000);
            System.out.println("Termina la charla");
            charlaIniciada = false;
            puedeSalir.signalAll();
            asistentes = 0;
            System.out.println("Descanso de 5 minutos");
            Thread.sleep(5000);
            puedeEntrar.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void asistente(int id) {
        lock.lock();
        try {
            while (asistentes >= CAPACIDAD_MAXIMA || charlaIniciada) {
                System.out.println("Asistente " + id + " no puede entrar aun");
                puedeEntrar.await();
            }
            asistentes++;
            System.out.println("Asistente " + id + " entra a la charla");
            if (asistentes == MIN_ASISTENTES) {
                puedeComenzar.signal();
            }

            while (!charlaIniciada) {
                puedeSalir.await();
            }
            System.out.println("Asistente " + id + " sale de la charla");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
