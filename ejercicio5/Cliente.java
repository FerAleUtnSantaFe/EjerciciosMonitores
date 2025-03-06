package ejercicio5;

public class Cliente implements Runnable {
    
    private Pelu pelu;
    private int id;

    public Cliente(Pelu pelu, int id){
        this.pelu = pelu;
        this.id = id;
    }

    @Override
    public void run(){
        pelu.cortarseElPelo(id);
    }
}
