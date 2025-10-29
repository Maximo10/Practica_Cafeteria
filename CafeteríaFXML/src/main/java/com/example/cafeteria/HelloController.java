package com.example.cafeteria;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class HelloController {

    @FXML
    private Button btnIniciar;

    @FXML
    private TextArea textAreaAlex;

    @FXML
    private TextArea textAreaPerez;

    private ArrayList<cliente> list_clientes;
    private ArrayList<cliente> list_atendidos;

    @FXML
    public void initialize() {
        // Inicializar listas
        list_clientes = new ArrayList<>();
        list_atendidos = new ArrayList<>();

        // Crear clientes
        list_clientes.add(new cliente("Pedro", 3000));
        list_clientes.add(new cliente("Antón", 3000));
        list_clientes.add(new cliente("Angel", 3000));
        list_clientes.add(new cliente("Gonzalo", 3000));
        list_clientes.add(new cliente("Ana", 3000));
        list_clientes.add(new cliente("Carlos", 3000));
    }

    @FXML
    private void iniciarSimulacion() {
        textAreaAlex.clear();
        textAreaPerez.clear();

        // Crear camareros con TextArea para actualizar UI
        camarero cama1 = new camarero("Alex", list_clientes, list_atendidos, textAreaAlex);
        camarero cama2 = new camarero("Perez", list_clientes, list_atendidos, textAreaPerez);

        cama1.start();
        cama2.start();

        // Esperar que terminen los hilos en un Thread separado
        new Thread(() -> {
            try {
                cama1.join();
                cama2.join();

                Platform.runLater(() -> {
                    textAreaAlex.appendText("\nTodos los clientes han sido atendidos.\n");
                    textAreaPerez.appendText("\nTodos los clientes han sido atendidos.\n");
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}