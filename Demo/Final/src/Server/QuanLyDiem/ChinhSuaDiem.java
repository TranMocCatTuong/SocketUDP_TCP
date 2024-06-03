package Server.QuanLyDiem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.DiemDAO;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ChinhSuaDiem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField IDHocSinh;
	private JTextField MHAnh;
	private JTextField MHToan;
	private JTextField MHVan;
	private JTextField HoTen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChinhSuaDiem frame = new ChinhSuaDiem();
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
	public static String[][] parseInput(String input){
		String[][] temp = new String[4][2];
		
		
		String[] lines = input.split("\n");

		// Khởi tạo mảng hai chiều để lưu trữ thông tin
		temp = new String[lines.length + 1][2];

		// Tìm vị trí dòng bắt đầu thông tin điểm
		int start = 0;
		for (int i = 0; i < lines.length; i++) {
		    if (lines[i].startsWith("Họ và tên:")) {
		        start = i;
		        break;
		    }
		}
		// Tách họ tên từ dòng bắt đầu thông tin điểm
		String[] hoTen = lines[start].split(":");
		temp[0][0] = hoTen[0].trim(); // "Họ và tên"
		temp[0][1] = hoTen[1].trim(); // "Nguyễn Văn A"

		// Bắt đầu từ dòng thứ start + 1
		for (int i = start + 1; i < lines.length; i++) {
		    // Tách tên môn và điểm từ mỗi dòng
		    String[] parts = lines[i].split(":");
		    // Xóa khoảng trắng ở đầu của điểm (nếu có)
		    String monHoc = parts[0].trim();
		    String diem = parts[1].trim();
		    // Lưu tên môn vào cột 0 và điểm vào cột 1
		    temp[i - start][0] = monHoc;
		    temp[i - start][1] = diem;
		}
		return temp;
	}
	public static String[][] conf(String[][] parseInput) {
	    String[][] temp = new String[3][2];

	    for (int i = 0; i < parseInput.length; i++) {
	        // Kiểm tra parseInput[i] không phải là null
	        if (parseInput[i] != null) {
	            for (int j = 0; j < parseInput[i].length; j++) {
	                // Kiểm tra parseInput[i][0] không phải là null trước khi so sánh
	                if (parseInput[i][0] != null) {
	                    if (parseInput[i][0].equals("Anh")) {
	                        temp[0][0] = "Anh";
	                        temp[0][1] = parseInput[i][1];
	                    } else if (parseInput[i][0].equals("Toán")) {
	                        temp[1][0] = "Toán";
	                        temp[1][1] = parseInput[i][1];
	                    } else if (parseInput[i][0].equals("Văn")) {
	                        temp[2][0] = "Văn";
	                        temp[2][1] = parseInput[i][1];
	                    }
	                }
	            }
	        }
	    }
	    return temp;
	}
	public ChinhSuaDiem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 53));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		IDHocSinh = new JTextField();
		IDHocSinh.setText("HS001");
		IDHocSinh.setBounds(112, 55, 96, 19);
		contentPane.add(IDHocSinh);
		IDHocSinh.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nhập IDHocSinh:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 58, 117, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Chỉnh Sửa Điểm");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(120, 10, 167, 24);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Xem");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = IDHocSinh.getText();
				MHAnh.setText("");
				MHToan.setText("");
				MHVan.setText("");
				String Diem = DiemDAO.TimDiemTheoIDHocSinh(ID);
				String[][] temp =parseInput(Diem);
				String[][] temp1 =conf(temp);
				HoTen.setText(temp[0][1]);
				MHAnh.setText(temp1[0][1]);
				MHToan.setText(temp1[1][1]);
				MHVan.setText(temp1[2][1]);
			}
		});
		btnNewButton.setBounds(223, 54, 64, 21);
		contentPane.add(btnNewButton);
		
		JLabel Anh = new JLabel("Anh:");
		Anh.setForeground(new Color(255, 255, 255));
		Anh.setBounds(86, 117, 45, 13);
		contentPane.add(Anh);
		
		JLabel lblNewLabel_2 = new JLabel("Toán:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(82, 154, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Văn:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(86, 192, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		MHAnh = new JTextField();
		MHAnh.setColumns(10);
		MHAnh.setBounds(127, 114, 45, 19);
		contentPane.add(MHAnh);
		
		MHToan = new JTextField();
		MHToan.setColumns(10);
		MHToan.setBounds(127, 151, 45, 19);
		contentPane.add(MHToan);
		
		MHVan = new JTextField();
		MHVan.setColumns(10);
		MHVan.setBounds(127, 189, 45, 19);
		contentPane.add(MHVan);
		
		JButton btnNewButton_1 = new JButton("Trở về");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuanLyDiem_Server frame = new QuanLyDiem_Server();
                frame.setVisible(true);
                frame.clearDiem();
                frame.updateDiem();
                setVisible(false);
			}
		});
		btnNewButton_1.setBounds(10, 232, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Thoát");
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setForeground(new Color(198, 54, 0));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_2.setBounds(341, 232, 85, 21);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Chỉnh sửa");
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = IDHocSinh.getText();
				String Anh = MHAnh.getText();
				String Toan = MHToan.getText();
				String Van = MHVan.getText();
				String Name = HoTen.getText();

				
				if(Name.equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng xem học sinh để chỉnh sửa!");
				}
				else {
					String Diem = DiemDAO.TimDiemTheoIDHocSinh(ID);
					
					String[][] temp =parseInput(Diem);
					String[][] temp1 =conf(temp);
					
					// So sánh xem cái nào được chỉnh sửa
					if(Anh.equals(temp1[0][1]) && Toan.equals(temp1[1][1]) && Van.equals(temp1[2][1]) ) {
						JOptionPane.showMessageDialog(null, "Bạn không chỉnh sửa.");
					}
					if(Anh.equals(temp1[0][1]) == false && Toan.equals(temp1[1][1]) && Van.equals(temp1[2][1])) {
						boolean check = DiemDAO.updateDiem(ID, "MH003", Double.parseDouble(Anh));
						if(check) {
							JOptionPane.showMessageDialog(null, "Chỉnh sửa môn Anh thành công.");
						}
						else {
							JOptionPane.showMessageDialog(null, "Chỉnh sửa môn Anh thất bại.");
						}
					}
					if(Anh.equals(temp1[0][1]) && Toan.equals(temp1[1][1]) == false && Van.equals(temp1[2][1])) {
						boolean check = DiemDAO.updateDiem(ID, "MH001", Double.parseDouble(Toan));
						if(check) {
							JOptionPane.showMessageDialog(null, "Chỉnh sửa môn Toán thành công.");
						}
						else {
							JOptionPane.showMessageDialog(null, "Chỉnh sửa môn Toán thất bại.");
						}
					}
					if(Anh.equals(temp1[0][1]) && Toan.equals(temp1[1][1]) && Van.equals(temp1[2][1]) == false) {
						boolean check = DiemDAO.updateDiem(ID, "MH002", Double.parseDouble(Van));
						if(check) {
							JOptionPane.showMessageDialog(null, "Chỉnh sửa môn Văn thành công.");
						}
						else {
							JOptionPane.showMessageDialog(null, "Chỉnh sửa môn Văn thất bại.");
						}
					}
					if(Anh.equals(temp1[0][1])== false && Toan.equals(temp1[1][1])== false && Van.equals(temp1[2][1])) {
						boolean check = DiemDAO.updateDiem(ID, "MH003", Double.parseDouble(Anh));
						boolean check1 = DiemDAO.updateDiem(ID, "MH001", Double.parseDouble(Toan));
						if(check && check1) {
							JOptionPane.showMessageDialog(null, "Chỉnh sửa môn Anh và Toán thành công.");
						}
						else {
							JOptionPane.showMessageDialog(null, "Chỉnh sửa môn Anh và Toán thất bại.");
						}
					}
					if(Anh.equals(temp1[0][1]) && Toan.equals(temp1[1][1])== false && Van.equals(temp1[2][1])== false) {
						boolean check1 = DiemDAO.updateDiem(ID, "MH001", Double.parseDouble(Toan));
						boolean check = DiemDAO.updateDiem(ID, "MH002", Double.parseDouble(Van));
						if(check && check1) {
							JOptionPane.showMessageDialog(null, "Chỉnh sửa môn Toán và Văn thành công.");
						}
						else {
							JOptionPane.showMessageDialog(null, "Chỉnh sửa môn Toán và Văn thất bại.");
						}
					}
					if(Anh.equals(temp1[0][1])== false && Toan.equals(temp1[1][1]) && Van.equals(temp1[2][1])== false) {
						boolean check = DiemDAO.updateDiem(ID, "MH003", Double.parseDouble(Anh));
						boolean check1 = DiemDAO.updateDiem(ID, "MH002", Double.parseDouble(Van));
						if(check && check1) {
							JOptionPane.showMessageDialog(null, "Chỉnh sửa môn Anh và Văn thành công.");
						}
						else {
							JOptionPane.showMessageDialog(null, "Chỉnh sửa môn Anh và Văn thất bại.");
						}
					}
					if(Anh.equals(temp1[0][1])== false && Toan.equals(temp1[1][1])== false && Van.equals(temp1[2][1])== false) {
						boolean check = DiemDAO.updateDiem(ID, "MH003", Double.parseDouble(Anh));
						boolean check1 = DiemDAO.updateDiem(ID, "MH002", Double.parseDouble(Van));
						boolean check2 = DiemDAO.updateDiem(ID, "MH001", Double.parseDouble(Toan));
						if(check && check1 && check2) {
							JOptionPane.showMessageDialog(null, "Chỉnh sửa môn 3 môn thành công.");
						}
						else {
							JOptionPane.showMessageDialog(null, "Chỉnh sửa môn 3 môn thất bại.");
						}
					}
					
					String ID1 = IDHocSinh.getText();
					MHAnh.setText("");
					MHToan.setText("");
					MHVan.setText("");
					String Diem1 = DiemDAO.TimDiemTheoIDHocSinh(ID1);
					String[][] temp2 =parseInput(Diem1);
					String[][] temp11 =conf(temp2);
					HoTen.setText(temp2[0][1]);
					MHAnh.setText(temp11[0][1]);
					MHToan.setText(temp11[1][1]);
					MHVan.setText(temp11[2][1]);
				}
				
			}
		});
		btnNewButton_3.setBounds(223, 150, 106, 21);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_4 = new JLabel("Họ và tên:");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(59, 84, 96, 13);
		contentPane.add(lblNewLabel_4);
		
		HoTen = new JTextField();
		HoTen.setBounds(127, 81, 149, 19);
		contentPane.add(HoTen);
		HoTen.setColumns(10);
	}
}
