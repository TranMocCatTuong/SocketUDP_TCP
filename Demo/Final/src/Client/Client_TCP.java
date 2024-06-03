package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client_TCP extends JFrame {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean done;
    private JTextField messageField;
    private JTextArea chatArea;

    public Client_TCP() {
        setTitle("Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBounds(0, 0, 386, 242);
        panel.add(scrollPane);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBounds(0, 242, 386, 21);
        messageField = new JTextField();
        bottomPanel.add(messageField, BorderLayout.CENTER);

        JButton sendButton = new JButton("Send");
        sendButton.setBackground(new Color(255, 255, 255));
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        bottomPanel.add(sendButton, BorderLayout.EAST);

        panel.add(bottomPanel);
        
        JButton btnNewButton = new JButton("Thoát");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		sendDisconect();
        		dispose();
        	}
        });
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.setForeground(new Color(198, 54, 0));
        bottomPanel.add(btnNewButton, BorderLayout.WEST);
        getContentPane().add(panel);

        try {
            client = new Socket("127.0.0.1", 9999);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            new Thread(new InputHandler()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        String message = messageField.getText();
        out.println(message);
        chatArea.append("You: "+message+"\n");
        messageField.setText("");
    }
    private void sendDisconect() {
        String message = "Ngắt kết nối!!";
        out.println(message);
    }

    private class InputHandler implements Runnable {
        @Override
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    chatArea.append(message + "\n");
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
                new Client_TCP().setVisible(true);
            }
        });
    }
}