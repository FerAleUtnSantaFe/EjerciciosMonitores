package ejercicio3;

import java.util.ArrayList;

public class Principal3 {
    
    public ArrayList<Thread> hilos = new ArrayList<>();
    public void main(String[] args){
        Monitor3 monitor = new Monitor3(3);


        Thread hilo1 = new Thread(() -> monitor.primero());
        Thread hilo2 = new Thread(() -> monitor.segundo());
        Thread hilo3 = new Thread(() -> monitor.tercero());

        hilos.add(hilo1);
        hilos.add(hilo2);
        hilos.add(hilo3);

        hilo1.start();
        hilo2.start();
        hilo3.start();

    }
}
