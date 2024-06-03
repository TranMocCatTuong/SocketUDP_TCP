package Server.QuanLyHocSinh;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.HocSinhDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ChinhSua extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField IDHocSinh;
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
					ChinhSua frame = new ChinhSua();
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
	public ChinhSua() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 53));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Chỉnh Sửa Học Sinh");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(115, 10, 199, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tìm kiếm theo IDHocSinh:");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(10, 49, 148, 13);
		contentPane.add(lblNewLabel_1_1);
		
		IDHocSinh = new JTextField();
		IDHocSinh.setText("HS001");
		IDHocSinh.setColumns(10);
		IDHocSinh.setBounds(168, 46, 96, 19);
		contentPane.add(IDHocSinh);
		
		JButton btnNewButton_4 = new JButton("Xem");
		btnNewButton_4.setBackground(new Color(255, 255, 255));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = IDHocSinh.getText();
        		String DanhSach = HocSinhDAO.TimHocSinhTheoIDHocSinh(ID);

        		String[][] temp = convertTo2DArray(DanhSach);
        		
        		textField_HoTen.setText(temp[1][1]);
        		textField_DiaChi.setText(temp[2][1]);
        		textField_NamHoc.setText(temp[3][1]);
			}
		});
		btnNewButton_4.setBounds(270, 44, 71, 21);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel_4 = new JLabel("Năm nhập học:");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(62, 134, 85, 13);
		contentPane.add(lblNewLabel_4);
		
		textField_NamHoc = new JTextField();
		textField_NamHoc.setColumns(10);
		textField_NamHoc.setBounds(160, 131, 55, 19);
		contentPane.add(textField_NamHoc);
		
		textField_DiaChi = new JTextField();
		textField_DiaChi.setColumns(10);
		textField_DiaChi.setBounds(160, 105, 266, 19);
		contentPane.add(textField_DiaChi);
		
		JLabel lblNewLabel_3 = new JLabel("Địa Chỉ:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(62, 111, 64, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_2 = new JLabel("Họ Tên:");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setBounds(62, 85, 64, 13);
		contentPane.add(lblNewLabel_1_2);
		
		textField_HoTen = new JTextField();
		textField_HoTen.setColumns(10);
		textField_HoTen.setBounds(160, 82, 104, 19);
		contentPane.add(textField_HoTen);
		
		JButton btnNewButton = new JButton("Trở về");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuanLyHocSinh_Server frame = new QuanLyHocSinh_Server();
                frame.setVisible(true);
                frame.clearDanhSach();
                frame.UpdateDanhSach();
                setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 232, 85, 21);
		contentPane.add(btnNewButton);
		
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
		
		JButton btnNewButton_2 = new JButton("Chỉnh sửa");
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String HoTen = textField_HoTen.getText();
				String DiaChi = textField_DiaChi.getText();
				String NamHoc = textField_NamHoc.getText();
				
				if(HoTen.equals("") && DiaChi.equals("") && NamHoc.equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng xem học sinh để bắt đầu chỉnh sửa!");
				}else {
					String ID = IDHocSinh.getText();
					String DanhSach = HocSinhDAO.TimHocSinhTheoIDHocSinh(ID);
	        		String[][] temp = convertTo2DArray(DanhSach);
	        		if(HoTen.equals(temp[1][1]) && DiaChi.equals(temp[2][1]) && NamHoc.equals(temp[3][1])) {
	        			JOptionPane.showMessageDialog(null, "Bạn chưa chỉnh sửa!");
	        		}else if(HoTen.equals(temp[1][1]) == false && DiaChi.equals(temp[2][1]) && NamHoc.equals(temp[3][1])) {
	        			HocSinhDAO.editHoTenHocSinh(ID, HoTen);
	        			JOptionPane.showMessageDialog(null, "Bạn đã chỉnh sửa Họ Tên!");
	        		}else if(HoTen.equals(temp[1][1]) && DiaChi.equals(temp[2][1]) == false && NamHoc.equals(temp[3][1])) {
	        			HocSinhDAO.editDiaChiHocSinh(ID, DiaChi);
	        			JOptionPane.showMessageDialog(null, "Bạn đã chỉnh sửa Địa Chỉ!");
	        		}else if(HoTen.equals(temp[1][1]) && DiaChi.equals(temp[2][1]) && NamHoc.equals(temp[3][1]) == false) {
	        			HocSinhDAO.editNamHocHocSinh(ID, Integer.parseInt(NamHoc));
	        			JOptionPane.showMessageDialog(null, "Bạn đã chỉnh sửa Năm Học!");
	        		}else if(HoTen.equals(temp[1][1]) == false && DiaChi.equals(temp[2][1]) == false && NamHoc.equals(temp[3][1])) {
	        			HocSinhDAO.editHoTenHocSinh(ID, HoTen);
	        			HocSinhDAO.editDiaChiHocSinh(ID, DiaChi);
	        			JOptionPane.showMessageDialog(null, "Bạn đã chỉnh sửa Họ Tên và Địa Chỉ!");
	        		}else if(HoTen.equals(temp[1][1]) == false && DiaChi.equals(temp[2][1]) && NamHoc.equals(temp[3][1]) == false) {
	        			HocSinhDAO.editHoTenHocSinh(ID, HoTen);
	        			HocSinhDAO.editNamHocHocSinh(ID, Integer.parseInt(NamHoc));
	        			JOptionPane.showMessageDialog(null, "Bạn đã chỉnh sửa Họ Tên và Năm Học!");
	        		}else if(HoTen.equals(temp[1][1]) && DiaChi.equals(temp[2][1]) == false && NamHoc.equals(temp[3][1]) == false) {
	        			HocSinhDAO.editDiaChiHocSinh(ID, DiaChi);
	        			HocSinhDAO.editNamHocHocSinh(ID, Integer.parseInt(NamHoc));
	        			JOptionPane.showMessageDialog(null, "Bạn đã chỉnh sửa Địa Chỉ và Năm Học!");
	        		}else if(HoTen.equals(temp[1][1])== false && DiaChi.equals(temp[2][1]) == false && NamHoc.equals(temp[3][1]) == false) {
	        			HocSinhDAO.editHoTenHocSinh(ID, HoTen);
	        			HocSinhDAO.editDiaChiHocSinh(ID, DiaChi);
	        			HocSinhDAO.editNamHocHocSinh(ID, Integer.parseInt(NamHoc));
	        			JOptionPane.showMessageDialog(null, "Bạn đã chỉnh sửa cả 3 thông tin!");
	        		}
	        		String ID1 = IDHocSinh.getText();
	        		String DanhSach1 = HocSinhDAO.TimHocSinhTheoIDHocSinh(ID1);

	        		String[][] temp1 = convertTo2DArray(DanhSach1);
	        		
	        		textField_HoTen.setText(temp1[1][1]);
	        		textField_DiaChi.setText(temp1[2][1]);
	        		textField_NamHoc.setText(temp1[3][1]);
				}	
			}
		});
		btnNewButton_2.setBounds(115, 173, 96, 21);
		contentPane.add(btnNewButton_2);
	}
}
