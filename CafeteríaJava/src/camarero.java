import java.util.ArrayList;
import java.util.Random;

public class camarero extends Thread{
    private String nombrecama;
    private ArrayList<cliente> list_clientes;
    private ArrayList<cliente> list_atendidos;

    public camarero(String nombrecama,ArrayList<cliente> list_clientes,ArrayList<cliente> list_atendidos){
        this.nombrecama=nombrecama;
        this.list_clientes=list_clientes;
        this.list_atendidos=list_atendidos;
    }

    @Override
    public void run(){
        System.out.println(nombrecama+" ha comenzado a atender....\n");
        while (true){
            cliente cliente_actual=null;

            if(!list_clientes.isEmpty()){
                cliente_actual=list_clientes.remove(0);
            }else{
                break;
            }
            //sumulacion
            if(cliente_actual !=null){
                try {
                    System.out.println(nombrecama+" atiende a "+ cliente_actual.getNombre());
                    Random aleatorio=new Random();
                    int tiempo_prepa= aleatorio.nextInt(3000)+2000;
                    System.out.println(nombrecama+ " prepara cafe para " +cliente_actual.getNombre()+ "(tardará "+ (tiempo_prepa/1000)+"s)");
                    Thread.sleep(tiempo_prepa);

                    if(tiempo_prepa<=cliente_actual.getTiempo_espera()){
                        System.out.println(cliente_actual.getNombre()+ " recibe su cafe.\n");
                        list_atendidos.add(cliente_actual);
                    }else {
                        System.out.println(cliente_actual.getNombre() + " se fue sin su cafe");
                    }

                } catch (InterruptedException e) {
                    // Si el hilo se interrumpe mientras duerme, cae aquí
                    e.printStackTrace();
                }
            }
        }
        System.out.println(nombrecama + "ha terminado su turno.\n");
    }
}
