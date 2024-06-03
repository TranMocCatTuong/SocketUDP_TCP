package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class XemThongTin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_ID;
	private JTextField textField_NamHoc;
	private JTextField textField_DiaChi;
	private JTextField textField_HoTen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XemThongTin frame = new XemThongTin("null", "null");
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
	public XemThongTin(String data, String user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 53));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("IDHocSinh:");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(62, 92, 92, 13);
		contentPane.add(lblNewLabel_1_1);
		
		textField_ID = new JTextField();
		textField_ID.setColumns(10);
		textField_ID.setBounds(160, 89, 55, 19);
		contentPane.add(textField_ID);
		
		JLabel lblNewLabel_1_2 = new JLabel("Họ Tên:");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setBounds(62, 115, 116, 13);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_3 = new JLabel("Địa Chỉ:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(62, 141, 92, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Năm nhập học:");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(62, 164, 116, 13);
		contentPane.add(lblNewLabel_4);
		
		textField_NamHoc = new JTextField();
		textField_NamHoc.setColumns(10);
		textField_NamHoc.setBounds(160, 161, 55, 19);
		contentPane.add(textField_NamHoc);
		
		textField_DiaChi = new JTextField();
		textField_DiaChi.setColumns(10);
		textField_DiaChi.setBounds(160, 135, 266, 19);
		contentPane.add(textField_DiaChi);
		
		textField_HoTen = new JTextField();
		textField_HoTen.setColumns(10);
		textField_HoTen.setBounds(160, 112, 104, 19);
		contentPane.add(textField_HoTen);
		
		JLabel lblNewLabel_2_1 = new JLabel("Thông Tin");
		lblNewLabel_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(160, 10, 104, 25);
		contentPane.add(lblNewLabel_2_1);
		
		JButton btnNewButton_1 = new JButton("Trở về");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client_UDP frame = new Client_UDP(user);
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(10, 232, 85, 21);
		contentPane.add(btnNewButton_1);
		
		String[][] temp = convertTo2DArray(data);
		
		textField_ID.setText(temp[0][1]);
		textField_HoTen.setText(temp[1][1]);
		textField_DiaChi.setText(temp[2][1]);
		textField_NamHoc.setText(temp[3][1]);
	}
	public static String[][] convertTo2DArray(String info) {
	    // Tách thông tin thành các dòng
	    String[] lines = info.split("\n");

	    // Khởi tạo mảng hai chiều để lưu trữ thông tin
	    String[][] result = new String[lines.length][2];

	    // Lặp qua từng dòng và chuyển đổi thành cặp key-value
	    for (int i = 0; i < lines.length; i++) {
	        String[] parts = lines[i].split(": ");
	        result[i][0] = parts[0]; // Key
	        result[i][1] = parts[1]; // Value
	    }

	    return result;
	}
}
