public class cliente extends Thread{
    //campos del cliente
    private String nombre;
    private int tiempo_espera;
    //constructor
    public cliente(String nombre, int tiempo_espera){
        this.nombre=nombre;
        this.tiempo_espera=tiempo_espera;
    }
    public String getNombre() {
        return nombre;
    }
    public int getTiempo_espera() {
        return tiempo_espera;
    }
}