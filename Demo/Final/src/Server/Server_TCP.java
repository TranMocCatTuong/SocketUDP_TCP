package Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server_TCP extends JFrame {
    private ServerSocket serverSocket;
    private final ArrayList<ClientHandler> clients;
    private boolean serverRunning;
    private JTextArea messageArea;

    public Server_TCP() {
        setTitle("Server");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        clients = new ArrayList<>();
        serverRunning = true;

        JPanel panel = new JPanel(new BorderLayout());

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        panel.add(new JScrollPane(messageArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JTextField messageField = new JTextField();
        bottomPanel.add(messageField, BorderLayout.CENTER);

        JButton sendButton = new JButton("Send");
        sendButton.setBackground(new Color(255, 255, 255));
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessageToAll("Server: " + messageField.getText());
                messageArea.append("You: " + messageField.getText() + "\n");
                messageField.setText("");
            }
        });
        bottomPanel.add(sendButton, BorderLayout.EAST);

        JButton btnNewButton = new JButton("Thoát");
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                serverRunning = false;
                try {
                    serverSocket.close();
                    for (ClientHandler client : clients) {
                        client.closeConnection();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                dispose();
            }
        });
        btnNewButton.setForeground(new Color(198, 54, 0));
        bottomPanel.add(btnNewButton, BorderLayout.WEST);

        panel.add(bottomPanel, BorderLayout.SOUTH);
        getContentPane().add(panel);

        try {
            serverSocket = new ServerSocket(9999);
            new Thread(new ServerHandler()).start();
            messageArea.append("Đã khởi động Server đang đợi client kết nối...\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessageToAll(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    private class ServerHandler implements Runnable {
        @Override
        public void run() {
            while (serverRunning) {
                try {
                    Socket clientSocket = serverSocket.accept();  
                    if (!serverRunning) {
                        clientSocket.close();
                        break;
                    }
                    ClientHandler clientHandler = new ClientHandler(clientSocket);
                    clients.add(clientHandler);
                    new Thread(clientHandler).start();
                    messageArea.append("Địa chỉ: " + clientSocket.getInetAddress().getHostAddress() + " đã kết nối!\n");
                } catch (IOException e) {
                    if (!serverRunning) {
                    } else {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;
        private String clientName;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println("Hãy nhập tên và bấm nút 'Send' để chat: ");
                clientName = in.readLine();
                sendMessageToAll(clientName + " đã tham gia kênh chat!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }

        public void sendMessageToAll(String message) {
            for (ClientHandler client : clients) {
                if (!client.equals(this)) {
                    client.sendMessage(clientName + ": " + message);
                    messageArea.append(clientName + ": " + message + "\n"); // In tin nhắn của client ra messageArea
                }
            }
        }

        @Override
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    sendMessageToAll(message);        
                }
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void closeConnection() {
            try {
                if (clientSocket != null && !clientSocket.isClosed()) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Server_TCP().setVisible(true);
            }
        });
    }
}