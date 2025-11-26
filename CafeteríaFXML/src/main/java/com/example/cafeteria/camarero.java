package com.example.cafeteria;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.Random;

public class camarero extends Thread {
    private String nombrecama;
    private ArrayList<cliente> list_clientes;
    private ArrayList<cliente> list_atendidos;
    private TextArea textArea; // <-- agregar el TextArea

    // Constructor
    public camarero(String nombrecama, ArrayList<cliente> list_clientes, ArrayList<cliente> list_atendidos, TextArea textArea) {
        this.nombrecama = nombrecama;
        this.list_clientes = list_clientes;
        this.list_atendidos = list_atendidos;
        this.textArea = textArea;
    }

    @Override
    public void run() {
        appendText(nombrecama + " ha comenzado a atender...\n");

        while (!list_clientes.isEmpty()){
            cliente cliente_actual =obtenerclie();

            if (cliente_actual != null) {
                try {
                    Random aleatorio = new Random();
                    int tiempo_prepa = aleatorio.nextInt(3000) + 2000;

                    appendText(nombrecama + " atiende a " + cliente_actual.getNombre());
                    appendText(nombrecama + " prepara café para " + cliente_actual.getNombre() + " (tardará " + (tiempo_prepa / 1000) + "s)");

                    Thread.sleep(tiempo_prepa);

                    if (tiempo_prepa <= cliente_actual.getTiempo_espera()) {
                        appendText(cliente_actual.getNombre() + " recibe su café.");
                        list_atendidos.add(cliente_actual);
                    } else {
                        appendText(cliente_actual.getNombre() + " se fue sin su café.");
                    }

                } catch (InterruptedException e) {
                    appendText(nombrecama+ "fue interumpido mientras atendia");
                }
            }else {
                break;
            }
        }
        appendText(nombrecama + " ha terminado su turno.\n");
    }
    private cliente obtenerclie(){
        //si la lista esta vacia
        if (list_clientes.isEmpty()){
            //retona null
            return  null;
        }
        //toma el primer cliente de la lista y eliminalo del ella
        return list_clientes.remove(0);
    }
    // Metodo para actualizar TextArea en JavaFX
    private void appendText(String text) {
        Platform.runLater(() -> textArea.appendText(text + "\n"));
    }
}