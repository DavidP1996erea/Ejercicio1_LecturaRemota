import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        try {
            // 1 - Crear un socket de tipo cliente indicando IP y puerto del servidor
            System.out.println("Estableciendo conexión con el servidor");
            Socket cliente = new Socket("192.168.0.186", 49200);

            // 2 - Abrir flujos de lectura y escritura
            InputStream is = cliente.getInputStream();
            OutputStream os = cliente.getOutputStream();

            // 3 - Intercambiar datos con el servidor
            // Envío de mensaje de texto al servidor
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);

            bw.write(pedirRuta());
            bw.newLine();
            bw.flush();

            // Leo mensajes que me envía el servidor
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println("El servidor me envía el siguiente mensaje: " + br.readLine());

            // 4 - Cerrar flujos de lectura y escritura
            br.close();
            isr.close();
            is.close();
            bw.close();
            osw.close();
            os.close();

            // 5 - Cerrar la conexión
            System.out.println("Se cierra la conexión del cliente");
            cliente.close();

        } catch (UnknownHostException e) {
            System.err.println("No se encuentra el host especificado.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Se ha producido un error en la conexión con el servidor.");
            e.printStackTrace();
        }
    }


    /**
     * Método que pide al usuario la ruta y el nombre del archivo. Devuelve un String.
     * @return
     */
    public static String pedirRuta(){
        Scanner sc = new Scanner(System.in);
        String ruta="";
        System.out.println("Introduce la ruta y el nombre del archivo");
        ruta = sc.nextLine();
        return  ruta;
    }
}