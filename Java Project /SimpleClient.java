import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

class SimpleClient {

  private static final String SERVER_ADDRESS = "127.0.01"; // or the server's IP address
  private static final int PORT = 59001;

  private static class IncomingMessageHandler extends Thread {

    private BufferedReader reader;

    public IncomingMessageHandler(BufferedReader reader) {
      this.reader = reader;
    }

    @Override
    public void run() {
      String message;
      try {
        while ((message = reader.readLine()) != null) {
          System.out.println(message); // Print incoming messages from the server to the console
        }
      } catch (IOException e) {
        System.out.println("Error reading messages from the server: " + e.getMessage());
      }
    }
  }

  public static void main(String[] args) {

    System.out.printf("Connecting to chat server at %s:%d...\n", SERVER_ADDRESS, PORT);

    Socket socket = null;
    PrintWriter writer = null;
    BufferedReader reader = null;

    try {
      // Connect to the server
      socket = new Socket(SERVER_ADDRESS, PORT);
      writer = new PrintWriter(socket.getOutputStream(), true);
      reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      // separate thread to handle incoming messages from the server
      // handle incoming messages from the server in a separate thread
      Thread incomingMessageHandler = new IncomingMessageHandler(reader);
      incomingMessageHandler.start();

      // main thread will handle user input and sending messages to the server
      System.out.println("Connected to the chat server");
      System.out.println("Type your message:");
      Scanner scanner = new Scanner(System.in);
      while (scanner.hasNextLine()) {
        String message = scanner.nextLine();
        writer.println(message); // Send the user's message to the server
      }

    } catch (IOException e) {
      System.out.println("Error connecting to the server: " + e.getMessage());
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          System.out.println("Error closing the reader: " + e.getMessage());
        }
      }
      if (writer != null) {
        writer.close();
      }
      if (socket != null) {
        try {
          socket.close();
        } catch (IOException e) {
          System.out.println("Error closing the socket: " + e.getMessage());
        }
      }
    }
    
  }
}

public static void main(String[] args) {
    System.out.printf("Connecting to chat server at %s:%d...%n", SERVER_ADDRESS, PORT);

    Socket socket = null;
    PrintWriter writer = null;
    BufferedReader reader = null;

    try {
        socket = new Socket(SERVER_ADDRESS, PORT);
        writer = new PrintWriter(socket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Thread incomingMessageHandler = new IncomingMessageHandler(reader);
        incomingMessageHandler.setDaemon(true);
        incomingMessageHandler.start();

        System.out.println("Connected to the chat server");
        System.out.println("Type your messages below. Type QUIT to exit.");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String message = scanner.nextLine();
            if ("QUIT".equalsIgnoreCase(message.trim())) {
                writer.println(message);
                break;
            }
            writer.println(message);
        }
        scanner.close();
    } catch (IOException e) {
        System.out.println("Error connecting to the server: " + e.getMessage());
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println("Error closing the reader: " + e.getMessage());
            }
        }
        if (writer != null) {
            writer.close();
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error closing the socket: " + e.getMessage());
            }
        }
    }
}

private static Socket tryReconnect(int attempts) {
    for (int i = 1; i <= attempts; i++) {
        try {
            System.out.println("Reconnect attempt " + i + "...");
            return new Socket(SERVER_ADDRESS, PORT);
        } catch (IOException e) {
            System.out.println("Reconnect failed: " + e.getMessage());
            try {
                Thread.sleep(1000L * i);
            } catch (InterruptedException ignored) { }
        }
    }
    return null;
}

}
