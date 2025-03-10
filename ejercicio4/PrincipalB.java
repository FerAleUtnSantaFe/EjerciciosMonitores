package ejercicio4;

public class PrincipalB {
    
    public static void main(String[] args){
        MonitorB monitor = new MonitorB();

        // Crear threads para invocar esperar
        Thread t1 = new Thread(() -> {
            try {
                monitor.esperar();
                System.out.println("Thread 1 liberado");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                monitor.esperar();
                System.out.println("Thread 2 liberado");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                monitor.liberar(3);
                System.out.println("Thread 3 liberado");
            } catch (Exception e) {
                e.printStackTrace();
            }
        
    });

    t1.start();
    t2.start();
    t3.start();

    Thread t4 = new Thread(() -> {
        try {
            Thread.sleep(2000);
            monitor.liberar(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        });

    t4.start();

    }
}
