import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) {

        try {
            // 1 - Crear socket de tipo servidor y le indicamos el puerto
            ServerSocket servidor = new ServerSocket(49200);

            // 2 - Queda a la espera de peticiones y las acepta cuando las recibe
            System.out.println("Ejercicio1.Servidor se encuentra a la escucha...");
            Socket peticion = servidor.accept();

            // 3 - Abrir flujos de lectura y escritura de datos
            InputStream is = peticion.getInputStream();
            InputStreamReader isR = new InputStreamReader(is);
            OutputStream os = peticion.getOutputStream();


            // Enviarle mensaje al cliente
            System.out.println("Ejercicio1.Servidor envía al cliente un mensaje");
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            BufferedReader br = new BufferedReader(isR);
            bw.write(leerFichero(String.valueOf((br.readLine()))));
            bw.newLine();
            bw.flush();

            // 5 - Cerrar flujos de lectura y escritura
            is.close();
            bw.close();
            osw.close();
            os.close();

            // 6 - Cerra la conexión
            System.out.println("Cierre de conexión del servidor");
            peticion.close();
            servidor.close();

        } catch (IOException e) {
            System.err.println("Ha habido algún error en la creación del Socket Ejercicio1.Servidor");
            e.printStackTrace();
        }
    }

    public static String leerFichero(String ruta) {

        BufferedReader br = null;
        String contenido = "";


        try {

            br = new BufferedReader(new FileReader(ruta));
            Scanner sc = new Scanner(br);

            while (sc.hasNext()) {

                contenido = sc.nextLine();

            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);


        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return contenido;
    }
}