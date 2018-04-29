package com.example.armando.ejemplosockets;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    TextView txtvSalida;
    EditText edtEntrada;
    String mensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtvSalida = findViewById(R.id.txtvSalida);
        edtEntrada = findViewById(R.id.edtEntrada);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());

    }

    public void onClick(View view){
        ejecutaCliente();
    }



    private void ejecutaCliente() {
        mensaje = edtEntrada.getText().toString();
        String ip = "192.168.137.1";
        int puerto = 7;
        log(" socket " + ip + " " + puerto);
        try
        {
            Socket sk = new Socket(ip,puerto);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(sk.getInputStream()));
            PrintWriter salida = new PrintWriter(new OutputStreamWriter(sk.getOutputStream()),true);
            log("Enviando: " + mensaje + "\n");
            salida.println(mensaje);
            log("recibiendo..." + entrada.readLine());
            sk.close();

        }catch (Exception error)
        {
            log("error :" +error.toString());
        }

    }

    private void log(String salida) {
        txtvSalida.append(salida+  "\n");
    }
}
