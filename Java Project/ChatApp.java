import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class ChatApp extends Application {
    private TextArea chatArea = new TextArea();
    private TextField input = new TextField(), nameField = new TextField(), hostField = new TextField("localhost");
    private Button sendBtn = new Button("Send"), connectBtn = new Button("Connect");
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    
    @Override
    public void start(Stage stage) {
        chatArea.setEditable(false);
        input.setPromptText("Message...");
        input.setDisable(true); sendBtn.setDisable(true);
        
        connectBtn.setOnAction(e -> connect());
        sendBtn.setOnAction(e -> send());
        input.setOnKeyPressed(e -> { if (e.getCode() == KeyCode.ENTER) send(); });
        stage.setOnCloseRequest(e -> close());
        
        HBox connectBox = new HBox(5, new Label("Name:"), nameField, 
                new Label("Host:"), hostField, connectBtn);
        connectBox.setPadding(new Insets(10));
        
        HBox inputBox = new HBox(5, input, sendBtn);
        inputBox.setPadding(new Insets(10));
        
        BorderPane root = new BorderPane(chatArea, null, null, inputBox, connectBox);
        stage.setScene(new Scene(root, 500, 400));
        stage.setTitle("Lehman Chat Client");
        stage.show();
    }
    
    private void connect() {
        String name = nameField.getText().trim(), host = hostField.getText().trim();
        if (name.isEmpty() || host.isEmpty()) return;
        
        try {
            socket = new Socket(host, 59001);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Server name prompt
            append("SERVER: " + in.readLine());
            out.println(name);
            
            // Background listener thread
            new Thread(this::listen).setDaemon(true).start();
            
            input.setDisable(false); sendBtn.setDisable(false); connectBtn.setDisable(true);
            append("Connected as " + name);
        } catch (IOException e) {
            append("Connect failed: " + e.getMessage());
        }
    }
    
    private void listen() {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                Platform.runLater(() -> append(line));
            }
        } catch (IOException e) {
            Platform.runLater(() -> append("Disconnected."));
        } finally {
            Platform.runLater(this::close);
        }
    }
    
    private void send() {
        String text = input.getText().trim();
        if (!text.isEmpty()) {
            out.println(text);
            input.clear();
        }
    }
    
    private void append(String msg) {
        chatArea.appendText(msg + "\n");
    }
    
    private void close() {
        input.setDisable(true); sendBtn.setDisable(true); connectBtn.setDisable(false);
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (IOException ignored) {}
        in = out = null; socket = null;
    }
    
    public static void main(String[] args) { launch(args); }
}
