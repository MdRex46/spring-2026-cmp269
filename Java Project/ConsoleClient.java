import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleClient {
    public static final int PORT = 59001;
    
    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        Scanner scanner = new Scanner(System.in);
        
        try {
            socket = new Socket("localhost", PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Wait for server prompt, send name
            System.out.println(in.readLine());
            System.out.print("Name: ");
            out.println(scanner.nextLine());
            
            // Background thread for receiving 
            Thread receiver = new Thread(() -> {
                try {
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    System.out.println("Connection lost.");
                }
            });
            receiver.setDaemon(true);
            receiver.start();
            
            // Main thread: user input
            System.out.println("Connected. Type QUIT to exit.");
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();
                if ("QUIT".equalsIgnoreCase(msg)) break;
                out.println(msg);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            closeQuietly(in, out, socket);
            scanner.close();
        }
    }
    
    private static void closeQuietly(Closeable... closeables) {
        for (Closeable c : closeables) {
            if (c != null) {
                try { c.close(); } catch (IOException ignored) {}
            }
        }
    }
}
