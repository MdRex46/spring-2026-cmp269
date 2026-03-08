import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleClient {
    public static final int PORT = 59001;
    
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", PORT)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);
            
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
            String input;
            while (scanner.hasNextLine()) {
                input = scanner.nextLine();
                if ("QUIT".equalsIgnoreCase(input.trim())) {
                    out.println(input);
                    break;
                }
                out.println(input);
            }
            scanner.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
