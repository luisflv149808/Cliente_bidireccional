package clientebidireccional;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClienteBidireccional {

    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter escritor = null;
        BufferedReader lector = null;
        Scanner scanner = new Scanner(System.in);
        String datos;
        String datosEntrada = "";

        String mensaje;

        if (args.length == 2) {

            if (Metodos.ValidarIp(args[0])) {
                if (Metodos.Numerico((args[1]))) {
                    try {
                        socket = new Socket(args[0].toString(), Integer.parseInt(args[1]));
                        System.out.println("Conexión establecida con el servidor.");
                    } catch (Exception e) {
                        System.out.println("Error al crear el socket " + e);
                        System.exit(1);
                    }
                    try {
                        escritor = new PrintWriter(socket.getOutputStream(), true);
                    } catch (IOException e) {
                        System.out.println("Error al crear el escritor " + e);
                        System.exit(2);
                    }

                    try {
                        lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    } catch (IOException e) {
                        System.out.println("Error al crear el lector " + e);
                        System.exit(3);
                    }

                    while (true) {
                        datos = scanner.nextLine();
                        escritor.println(datos);
                        try {
                            datosEntrada = lector.readLine();
                            System.out.println(datosEntrada);
                        } catch (IOException e) {
                            System.out.println("Error al leer " + e);
                            System.exit(4);
                        }
                    }
                } else {
                    System.out.println("!!Error!!. Ingresa un puerto válido");
                }
            } else {

                System.out.println("Dirección IP Inválida");
                System.exit(0);

            }

        } else {
            System.out.println("Ingresa todos los argumentos (Dirección IP y Puerto)");
        }

    }

}
