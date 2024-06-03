package Server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import DAO.DiemDAO;
import DAO.HocSinhDAO;
import Server.QuanLyDiem.QuanLyDiem_Server;
import Server.QuanLyHocSinh.QuanLyHocSinh_Server;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;

public class Server_UDP extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea activityHistory;
    private DatagramSocket socket;
    private JTextField textField;
    private InetAddress clientAddress1;
    private int clientPort1;
    private boolean running = true;

    public static void main(String[] args) {
    	Server_UDP frame = new Server_UDP();
        frame.setVisible(true);
        frame.startUDPServer();
    }

    public Server_UDP() {
    	setBackground(new Color(0, 0, 53));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 0, 53));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        activityHistory = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(activityHistory);
        scrollPane.setBounds(10, 63, 276, 141);
        contentPane.add(scrollPane);

        JButton qlDiem_bt = new JButton("Quản lý điểm");
        qlDiem_bt.setBackground(new Color(255, 255, 255));
        qlDiem_bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                QuanLyDiem_Server frame = new QuanLyDiem_Server();
                frame.setVisible(true);
                frame.clearDiem();
                frame.updateDiem();
            }
        });
        qlDiem_bt.setBounds(296, 66, 130, 21);
        contentPane.add(qlDiem_bt);

        JButton qlHs_bt = new JButton("Quản lý học sinh");
        qlHs_bt.setBackground(new Color(255, 255, 255));
        qlHs_bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                QuanLyHocSinh_Server frame = new QuanLyHocSinh_Server();
                frame.setVisible(true);
                frame.clearDanhSach();
                frame.UpdateDanhSach();
            }
        });
        qlHs_bt.setBounds(296, 97, 130, 21);
        contentPane.add(qlHs_bt);

        JLabel lblNewLabel = new JLabel("Server Main");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblNewLabel.setBounds(152, 10, 134, 27);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(10, 214, 207, 19);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Gửi");
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mess = textField.getText();
                String message = "message,Manager," + mess;
                byte[] sendData = message.getBytes();
                if (clientAddress1 != null && clientPort1 != 0) { // Kiểm tra clientAddress1 và clientPort1 trước khi gửi
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress1, clientPort1);
                    try {
                        socket.send(sendPacket);
                        textField.setText("");
                        appendToActivityHistory("You: " + mess);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    appendToActivityHistory("Không có máy client nào được kết nối.");
                }
            }
        });
        btnNewButton.setBounds(227, 214, 59, 21);
        contentPane.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Thoát");
        btnNewButton_1.setBackground(new Color(255, 255, 255));
        btnNewButton_1.setForeground(new Color(198, 54, 0));
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		running = false; // Dừng vòng lặp trong startUDPServer
                socket.close();  // Đóng socket
                dispose();       // Đóng cửa sổ
        	}
        });
        btnNewButton_1.setBounds(341, 232, 85, 21);
        contentPane.add(btnNewButton_1);
    }

    public void startUDPServer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new DatagramSocket(9876);
                    appendToActivityHistory("Server đã khởi chạy, đang đợi các máy Client..");
                    

                    while (running) {
                    	byte[] receiveData = new byte[1024];
                        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        socket.receive(receivePacket);
                        String receivedMessage = new String(receivePacket.getData()).trim();
                        String[] info = receivedMessage.split(",");
                        clientAddress1 = receivePacket.getAddress();
                        clientPort1 = receivePacket.getPort();
                        if (info[0].endsWith("Login")) {
                            processLoginRequest(receivedMessage, receivePacket.getAddress(), receivePacket.getPort());
                        } else if (info[0].endsWith("User")) {
                            userConectingRequest(receivedMessage, receivePacket.getAddress(), receivePacket.getPort());
                        } else if (info[0].endsWith("Pass")) {
                        	changePassRequest(receivedMessage, receivePacket.getAddress(), receivePacket.getPort());                          
                        } else if (info[0].endsWith("Disconect")) {
                        	disconectRequest(receivedMessage, receivePacket.getAddress(), receivePacket.getPort());                          
                        } else if (info[0].endsWith("Point")) {
                        	pointRequest(receivedMessage, receivePacket.getAddress(), receivePacket.getPort());                          
                        } else if (info[0].endsWith("Infomation")) {
                        	infoRequest(receivedMessage, receivePacket.getAddress(), receivePacket.getPort());                          
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (socket != null && !socket.isClosed()) {
                        socket.close();
                    }
                }
            }
        }).start();
    }

    private void appendToActivityHistory(String message) {
        activityHistory.append(message + "\n");
    }
    private void infoRequest(String receivedMessage, InetAddress clientAddress, int clientPort) {
    	String[] Info = receivedMessage.split(",");
        String ID = Info[1];
        String DanhSach = HocSinhDAO.TimHocSinhTheoIDHocSinh(ID);
        
        String message = "info-" + DanhSach;

        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
        try {
            socket.send(sendPacket);
            appendToActivityHistory("Đã gửi thông tin cho!" + ID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void pointRequest(String receivedMessage, InetAddress clientAddress, int clientPort) {
    	String[] Info = receivedMessage.split(",");
        String ID = Info[1];
        String Diem = DiemDAO.TimDiemTheoIDHocSinh(ID);
        String message = "point," + Diem;
        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
        try {
            socket.send(sendPacket);
            appendToActivityHistory("Đã gửi bảng điểm cho!" + ID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void disconectRequest(String receivedMessage, InetAddress clientAddress, int clientPort) {
    	String[] Info = receivedMessage.split(",");
        String user = Info[1];
        appendToActivityHistory(user + " đã ngắt kết nối với Server!");
    }
    private void userConectingRequest(String receivedMessage, InetAddress clientAddress, int clientPort) {
        String[] Info = receivedMessage.split(",");
        String con = Info[1];
        String user = Info[2];
        String mess = Info[3];
        if (con.equals("connect")) {
            String message = "connect,success," + user;
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            try {
                socket.send(sendPacket);
                appendToActivityHistory(user + " đã kết nối đến Server!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (con.equals("message")) {
            appendToActivityHistory(user + ": " + mess);
        }
    }
    
    private void changePassRequest(String receivedMessage, InetAddress clientAddress, int clientPort) {
    	String[] Info = receivedMessage.split(",");
    	String user = Info[1];
    	String cu = Info[2];
        String moi = Info[3];
        boolean temp = HocSinhDAO.changePass(user, cu, moi);
        
        if(temp) {
        	byte[] sendData = "success".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            try {
                socket.send(sendPacket);     
                appendToActivityHistory(user+" đã đổi mật khẩu!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
        	byte[] sendData = "failure".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            try {
                socket.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processLoginRequest(String receivedMessage, InetAddress clientAddress, int clientPort) {
        String[] loginInfo = receivedMessage.split(",");
        if (loginInfo.length >= 3) { // Kiểm tra độ dài của mảng
            String username = loginInfo[1];
            String message = loginInfo[2];

            // Example validation (replace with your own authentication logic)
            if (HocSinhDAO.checkLogin(username, message)) {
                byte[] sendData = "success".getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                try {
                    socket.send(sendPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                byte[] sendData = "failure".getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                try {
                    socket.send(sendPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Xử lý lỗi hoặc báo cáo một cách phù hợp nếu thông điệp không đủ dài
        }
    }
}
