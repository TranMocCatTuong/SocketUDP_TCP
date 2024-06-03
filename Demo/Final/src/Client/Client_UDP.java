package Client;

import java.awt.EventQueue;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Client_UDP extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private static DatagramSocket socket;
    private static String user;
    static JTextArea textArea = new JTextArea();
    private JButton btnNewButton_1;
    private JButton btnNewButton_2;
    private JButton btnNewButton_3;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	Client_UDP frame = new Client_UDP("HS001");
                    frame.setVisible(true);
                    initializeSocket();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Client_UDP(String username) {
        user = username;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 0, 53));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 289, 170);
        contentPane.add(scrollPane);
        scrollPane.setViewportView(textArea);

        textField = new JTextField();
        textField.setBounds(10, 183, 289, 19);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Gửi");
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = textField.getText();
                String fullMessage = "User,message," + user + "," + message;
                try {
                    // Gửi thông điệp tới ServerMain        
                    byte[] sendData = fullMessage.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 9876);
                    socket.send(sendPacket);
                    textArea.append("You: " + message + "\n");
                    textField.setText("");                  
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnNewButton.setBounds(309, 182, 85, 21);
        contentPane.add(btnNewButton);
        
        btnNewButton_1 = new JButton("Đổi mật khẩu");
        btnNewButton_1.setBackground(new Color(255, 255, 255));
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		DoiMatKhau frame = new DoiMatKhau(user);
        		frame.setVisible(true);
        		setVisible(false);
        	}
        });
        btnNewButton_1.setBounds(115, 232, 117, 21);
        contentPane.add(btnNewButton_1);
        
        btnNewButton_2 = new JButton("Xem điểm");
        btnNewButton_2.setBackground(new Color(255, 255, 255));
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			DatagramSocket socket = new DatagramSocket();
                    InetAddress serverAddress = InetAddress.getByName("localhost");
                    int serverPort = 9876;
                    byte[] sendData = ("Point" + ","+ user).getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                    socket.send(sendPacket);
                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    socket.receive(receivePacket);
                    String result = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    String[] mess = result.split(",");
                    if ("point".equals(mess[0])) {
                        //Nếu thành công code ở đây
                    	XemDiem frame= new XemDiem(mess[1], user);
                    	frame.setVisible(true);
                    	setVisible(false);
                    }
        		}catch (Exception ex){
        			JOptionPane.showMessageDialog(null, "Server đang bận xem điểm sau!");
        		}
        	}
        });
        btnNewButton_2.setBounds(309, 13, 117, 21);
        contentPane.add(btnNewButton_2);
        
        btnNewButton_3 = new JButton("Xem thông tin");
        btnNewButton_3.setBackground(new Color(255, 255, 255));
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			DatagramSocket socket = new DatagramSocket();
                    InetAddress serverAddress = InetAddress.getByName("localhost");
                    int serverPort = 9876;
                    byte[] sendData = ("Infomation" + ","+ user).getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                    socket.send(sendPacket);
                    byte[] receiveData = new byte[4096];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    socket.receive(receivePacket);
                    String result = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    String[] mess = result.split("-");
                    if ("info".equals(mess[0])) {
                        //Nếu thành công code ở đây
                    	XemThongTin frame= new XemThongTin(mess[1], user);
                    	frame.setVisible(true);
                    	setVisible(false);
                    }
        		}catch (Exception ex){
        			JOptionPane.showMessageDialog(null, "Server đang bận xem điểm sau!");
        		}
        	}
        });
        btnNewButton_3.setBounds(309, 44, 117, 21);
        contentPane.add(btnNewButton_3);
        
        JButton btnNewButton_4 = new JButton("Thoát");
        btnNewButton_4.setBackground(new Color(255, 255, 255));
        btnNewButton_4.setForeground(new Color(198, 54, 0));
        btnNewButton_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String message = textField.getText();
                String fullMessage = "Disconect," + user ;
                try {
                    // Gửi thông điệp tới ServerMain        
                    byte[] sendData = fullMessage.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 9876);
                    socket.send(sendPacket);  
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
        	}
        });
        btnNewButton_4.setBounds(341, 232, 85, 21);
        contentPane.add(btnNewButton_4);
        
        JButton btnNewButton_5 = new JButton("Đăng xuất");
        btnNewButton_5.setForeground(new Color(198, 54, 0));
        btnNewButton_5.setBackground(new Color(255, 255, 255));
        btnNewButton_5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String message = textField.getText();
                String fullMessage = "Disconect," + user ;
                try {
                    // Gửi thông điệp tới ServerMain        
                    byte[] sendData = fullMessage.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 9876);
                    socket.send(sendPacket);  
                    Login frame = new Login();
                    frame.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
        	}
        });
        btnNewButton_5.setBounds(10, 232, 95, 21);
        contentPane.add(btnNewButton_5);
    }

    static void initializeSocket() {
        try {
            socket = new DatagramSocket();

            // Gửi thông báo kết nối thành công đến ServerMain
            String message = "User,connect," + user + ",x";
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 9876);
            socket.send(sendPacket);

            // Khởi tạo một luồng để nhận phản hồi từ server
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            // Nhận phản hồi từ ServerMain
                            byte[] receiveData = new byte[1024];
                            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                            socket.receive(receivePacket);
                            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                            String[] mess = response.split(",");
                            //mess[0]="connect",mess[1]="success"
                            if (mess[0].equals("connect")) {
                                if (mess[1].equals("success")) {
                                    textArea.append("Kết nối thành công với Server\n");
                                }
                                if (mess[1].equals("success") == false) {
                                    textArea.append("Kết nối thất bại\n");
                                }
                            }
                            if (mess[0].equals("message")) {
                                if (mess[1].equals(user)) {
                                    textArea.append("You: " + mess[2] + "\n");
                                }
                                if (mess[1].equals(user) == false) {
                                    textArea.append(mess[1] + ": " + mess[2] + "\n");
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
