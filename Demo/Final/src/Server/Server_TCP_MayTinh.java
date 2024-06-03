package Server;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Server_TCP_MayTinh extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextPane textPane;
    private ServerSocket serverSocket;
    private boolean serverRunning;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Server_TCP_MayTinh frame = new Server_TCP_MayTinh();
                    frame.setVisible(true);
                    frame.startServer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Server_TCP_MayTinh() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        textPane = new JTextPane();
        textPane.setBounds(10, 34, 416, 189);
        contentPane.add(textPane);
        
        JButton btnNewButton = new JButton("Thoát");
        btnNewButton.setForeground(new Color(198, 54, 0));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		stopServer();
        		dispose();
        	}
        });
        btnNewButton.setBounds(341, 233, 85, 21);
        contentPane.add(btnNewButton);
    }

    /**
     * Start the TCP server.
     */
    public void startServer() {
        serverRunning = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(12345);
                    updateTextPane("Server đã khởi động, đang đợi kết nối.");

                    while (serverRunning) {
                        Socket clientSocket = serverSocket.accept();
                        updateTextPane("Client đã kết nối.");

                        // Xử lý client connection
                        new Thread(new ClientHandler(clientSocket)).start();
                    }
                } catch (IOException e) {
                    if (!serverRunning) {
                        updateTextPane("Server đã dừng.");
                    } else {
                        e.printStackTrace();
                    }
                } finally {
                    if (serverSocket != null && !serverSocket.isClosed()) {
                        try {
                            serverSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    /**
     * Stop the TCP server.
     */
    public void stopServer() {
        serverRunning = false;
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Update the JTextPane with a message.
     */
    private void updateTextPane(final String message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                textPane.setText(textPane.getText() + "\n" + message);
            }
        });
    }

    public static String convertPercentage(String expression) {
        // Regular expression to match percentage values (e.g., 10%)
        String regex = "(\\d+\\.?\\d*)%";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);

        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String numericPart = matcher.group(1);
            double decimalValue = Double.parseDouble(numericPart) / 100;
            matcher.appendReplacement(result, String.valueOf(decimalValue));
        }
        matcher.appendTail(result);

        return result.toString();
    }

    public static double evaluate(String expression) throws Exception {
        Stack<Double> values = new Stack<>();
        Stack<Character> ops = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == ' ')
                continue;

            if (expression.charAt(i) >= '0' && expression.charAt(i) <= '9' || expression.charAt(i) == '.') {
                StringBuilder sbuf = new StringBuilder();
                while (i < expression.length() && (expression.charAt(i) >= '0' && expression.charAt(i) <= '9' || expression.charAt(i) == '.'))
                    sbuf.append(expression.charAt(i++));
                values.push(Double.parseDouble(sbuf.toString()));
                i--;
            } else if (expression.charAt(i) == '(') {
                ops.push(expression.charAt(i));
            } else if (expression.charAt(i) == ')') {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            } else if (expression.charAt(i) == '+' || expression.charAt(i) == '-' ||
                       expression.charAt(i) == 'x' || expression.charAt(i) == ':' ||
                       expression.charAt(i) == '%') {
                while (!ops.empty() && hasPrecedence(expression.charAt(i), ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.push(expression.charAt(i));
            }
        }

        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));

        return values.pop();
    }

    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == 'x' || op1 == ':' || op1 == '%') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    public static double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case 'x':
                return a * b;
            case ':':
                if (b == 0)
                    throw new UnsupportedOperationException("Không thể chia cho 0");
                return a / b;
            case '%':
                return a * (b / 100);
        }
        return 0;
    }

    private class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                String clientMessage;
                while ((clientMessage = in.readLine()) != null) {
                    updateTextPane("Nhận từ client: " + clientMessage);
                    String convertedExpression = convertPercentage(clientMessage);
                    double result = evaluate(convertedExpression);
                    out.println(clientMessage + " = " + result);
                }
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (clientSocket != null && !clientSocket.isClosed()) {
                        clientSocket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}