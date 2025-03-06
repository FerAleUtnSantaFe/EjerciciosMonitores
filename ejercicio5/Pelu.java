package ejercicio5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Pelu {
    private final Lock lock = new ReentrantLock();
    private final Condition esperarClientes = lock.newCondition();
    private final Condition esperarPeluqueros = lock.newCondition();
    private final Condition esperarCorte = lock.newCondition();

    private int cantClientes = 0;

    public void empezarCorte(int id) {
        lock.lock();
        try {
            while (cantClientes == 0) {
                esperarClientes.await();
            }
            System.out.println("Peluquero " + id + " empieza a cortar el pelo");
            cantClientes--;
            esperarPeluqueros.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void cortarseElPelo(int id) {
        lock.lock();
        try {
            cantClientes++;
            System.out.println("Cliente " + id + " espera por un peluquero");
            esperarClientes.signal();

            esperarPeluqueros.await();
            System.out.println("Cliente " + id + " se corta el pelo");

            esperarCorte.await();
            System.out.println("Cliente " + id + " se retira de la peluqueria");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void terminarCorte(int id) {
        lock.lock();
        try {
            System.out.println("Peluquero " + id + " termina de cortar el pelo");
            esperarCorte.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
