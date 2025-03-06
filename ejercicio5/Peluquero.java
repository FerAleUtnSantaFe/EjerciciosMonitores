package ejercicio5;

public class Peluquero implements Runnable {
    private Pelu pelu;
    private int id;

    public Peluquero(Pelu pelu, int id) {
        this.pelu = pelu;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            pelu.empezarCorte(id);
            Principal5.randomDelay(10, 50);
            pelu.terminarCorte(id);
            Principal5.randomDelay(10, 50);
        }
    }

}
