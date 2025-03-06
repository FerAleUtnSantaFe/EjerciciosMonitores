package ejercicio6;

public class PrincipalB6 {
    
    public static void main(String[] args){
        MonitorB6 sala = new MonitorB6();

        Thread orador = new Thread(() -> {
            try {
                sala.orador();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        orador.start();

        for(int i = 0; i < 50; i++){
            final int id = i;
            Thread asistente = new Thread(() -> {
                try{
                    sala.asistente(id + 1);
                } catch (Exception e){
                    e.printStackTrace();
                }
            });
            asistente.start();
        }
    }
}
