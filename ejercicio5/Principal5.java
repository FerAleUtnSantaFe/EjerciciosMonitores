package ejercicio5;

public class Principal5 {
    
    public static void main(String[] args) {
        Pelu pelu = new Pelu();

        Thread[] clientes = new Thread[6];
        Thread[] peluqueros = new Thread[3];


        for(int i = 0; i < peluqueros.length; i++){
            peluqueros[i] = new Thread(new Peluquero(pelu, i));
            peluqueros[i].start();
        }

        
        for (int i = 0; i < clientes.length; i++){
            clientes[i] = new Thread(new Cliente(pelu, i));
            clientes[i].start();
        }
    }

    public static void randomDelay(int min, int max){
        int random = (int)(max * Math.random() + min);
        try 
        {
            Thread.sleep(random*10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
