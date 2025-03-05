package ejercicio1;

public class Principal1 {

    public void main(){
        Monitor1 monitor = new Monitor1();

        Thread p1 = new Thread(() -> monitor.antesydespues());
        Thread p2 = new Thread(() -> monitor.parte2());
    
        p1.start();
        p2.start();
    }
    
}
