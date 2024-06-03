package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class DoiMatKhau extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField pass_kiemTra;
	private JPasswordField pass_moi;
	private JPasswordField pass_cu;
	private String user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoiMatKhau frame = new DoiMatKhau("HS001");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private void sendChangePassData(String password_cu, String password_moi) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;
            byte[] sendData = ("Pass" + ","+ user + "," + password_cu + "," + password_moi).getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            socket.send(sendPacket);
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String result = new String(receivePacket.getData(), 0, receivePacket.getLength());
            if ("success".equals(result)) {
                //Nếu thành công code ở đây
            	JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công, bạn sẽ quay lại trang chính!");
            	Client_UDP frame = new Client_UDP(user);
            	frame.setVisible(true);
            	setVisible(false);
            } else {
            	JOptionPane.showMessageDialog(null, "Nhập sai mật khẩu cũ!");
            }
            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	public DoiMatKhau(String username) {
		user = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 53));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Đổi Mật Khẩu");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(140, 10, 143, 25);
		contentPane.add(lblNewLabel_2);
		
		JButton btniMtKhu = new JButton("Đổi mật khẩu");
		btniMtKhu.setBackground(new Color(255, 255, 255));
		btniMtKhu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cu = new String(pass_cu.getPassword());
				String moi = new String(pass_moi.getPassword());
				String kiemTra = new String(pass_kiemTra.getPassword());
				if(moi.equals(kiemTra)==false) {
					JOptionPane.showMessageDialog(null, "Mật khẩu mới không khớp với mật khẩu nhập lại!");
				}else {
					sendChangePassData(cu,moi);
				}
				
			}
		});
		btniMtKhu.setBounds(171, 181, 143, 21);
		contentPane.add(btniMtKhu);
		
		pass_kiemTra = new JPasswordField();
		pass_kiemTra.setBounds(171, 152, 143, 19);
		contentPane.add(pass_kiemTra);
		
		JLabel lblNewLabel_1 = new JLabel("Nhập lại mật khẩu:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(51, 154, 110, 17);
		contentPane.add(lblNewLabel_1);
		
		pass_moi = new JPasswordField();
		pass_moi.setBounds(171, 122, 143, 19);
		contentPane.add(pass_moi);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu mới:");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(51, 125, 110, 17);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Mật khẩu cũ:");
		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1.setBounds(51, 95, 110, 17);
		contentPane.add(lblNewLabel_1_1_1);
		
		pass_cu = new JPasswordField();
		pass_cu.setBounds(171, 92, 143, 19);
		contentPane.add(pass_cu);
		
		JButton btnTrV = new JButton("Trở về");
		btnTrV.setBackground(new Color(255, 255, 255));
		btnTrV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client_UDP frame = new Client_UDP(user);
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnTrV.setBounds(10, 232, 75, 21);
		contentPane.add(btnTrV);
	}
}
