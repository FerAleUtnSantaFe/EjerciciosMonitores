package ejercicio2;

public class Principal2 {
    
    public static void main(String[] args) {
        Monitor2 monitor = new Monitor2();
        System.out.println("asdasd");
        // Crear threads para ejecutar las operaciones en diferentes órdenes
        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    monitor.primero();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    monitor.segundo();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    monitor.tercero();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Iniciar los threads
        t1.start();
        t2.start();
        t3.start();
    }
}