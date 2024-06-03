package Client;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Server.QuanLyDiem.QuanLyDiem_Server;
import Server.QuanLyHocSinh.QuanLyHocSinh_Server;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPasswordField passwordField;
    private JTextField txtHs;

    public static void main(String[] args) {
        Login frame = new Login();
        frame.setVisible(true);
    }

    private void sendLoginData(String username, String password) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;
            byte[] sendData = ("Login" + "," + username + "," + password).getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            socket.send(sendPacket);
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String result = new String(receivePacket.getData(), 0, receivePacket.getLength());
            if ("success".equals(result)) {
                setVisible(false);
                Client_UDP frame = new Client_UDP(username);
                frame.setVisible(true);
                frame.initializeSocket();
            } else {
                JOptionPane.showMessageDialog(this, "Kiểm tra lại thông tin đăng nhập", "Đăng nhập không thành công", JOptionPane.ERROR_MESSAGE);
            }
            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 0, 53));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Tên đăng nhập:");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(58, 80, 110, 17);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Mật khẩu:");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(58, 126, 110, 17);
        contentPane.add(lblNewLabel_1);

        JButton btnNewButton = new JButton("Đăng nhập");
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtHs.getText();
                String password = new String(passwordField.getPassword());
                sendLoginData(username, password);
            }
        });
        btnNewButton.setBounds(156, 178, 110, 21);
        contentPane.add(btnNewButton);

        passwordField = new JPasswordField();
        passwordField.setBounds(178, 124, 203, 19);
        contentPane.add(passwordField);

        txtHs = new JTextField();
        txtHs.setText("HS001");
        txtHs.setBounds(178, 78, 203, 19);
        contentPane.add(txtHs);

        JLabel lblNewLabel_2 = new JLabel("Đăng Nhập");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_2.setBounds(156, 10, 117, 25);
        contentPane.add(lblNewLabel_2);
        
        JButton btnNewButton_1 = new JButton("Thoát");
        btnNewButton_1.setBackground(new Color(255, 255, 255));
        btnNewButton_1.setForeground(new Color(198, 54, 0));
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnNewButton_1.setBounds(341, 232, 85, 21);
        contentPane.add(btnNewButton_1);
    }
}
