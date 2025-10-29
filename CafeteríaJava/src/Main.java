import java.util.ArrayList;
public class Main {
    public static void main(String[] args) throws InterruptedException  {
        //crear lista de usuarios
        ArrayList<cliente> list_clientes=new ArrayList<>();

        //lista de atendidod
        ArrayList<cliente> list_atendidos=new ArrayList<>();

        //crear 6 clietentes
        cliente cli1=new cliente("Pedro",3000);
        cliente cli2=new cliente("Antón",3000);
        cliente cli3=new cliente("Angel",3000);
        cliente cli4=new cliente("Gonzalo",3000);
        cliente cli5=new cliente("Ana",3000);
        cliente cli6=new cliente("Carlos",3000);

        //Añadir user a lista
        list_clientes.add(cli1);
        list_clientes.add(cli2);
        list_clientes.add(cli3);
        list_clientes.add(cli4);
        list_clientes.add(cli5);
        list_clientes.add(cli6);

        //crear a los camareros
        camarero cama1=new camarero("Alex",list_clientes,list_atendidos);
        camarero cama2=new camarero("Perez",list_clientes,list_atendidos);

        //inicio hilos
        cama1.start();
        cama2.start();

        //esperara a los camareros
        cama1.join();
        cama2.join();

        System.out.println("Todos los cliantes han sido antendidos....");

        System.out.println("Clientes atendidos:");
        for (cliente cli:list_atendidos){
            System.out.println("- " + cli.getNombre());
        }
    }
}