package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class XemDiem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField HoTen;
	private JTextField MHAnh;
	private JTextField MHToan;
	private JTextField MHVan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XemDiem frame = new XemDiem("null", "null");
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
	public XemDiem(String data_Diem, String user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 53));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		HoTen = new JTextField();
		HoTen.setColumns(10);
		HoTen.setBounds(169, 80, 149, 19);
		contentPane.add(HoTen);
		
		JLabel lblNewLabel_4 = new JLabel("Họ và tên:");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(101, 83, 96, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel Anh = new JLabel("Anh:");
		Anh.setForeground(new Color(255, 255, 255));
		Anh.setBounds(128, 116, 45, 13);
		contentPane.add(Anh);
		
		MHAnh = new JTextField();
		MHAnh.setColumns(10);
		MHAnh.setBounds(169, 113, 45, 19);
		contentPane.add(MHAnh);
		
		JLabel lblNewLabel_2 = new JLabel("Toán:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(124, 153, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		MHToan = new JTextField();
		MHToan.setColumns(10);
		MHToan.setBounds(169, 150, 45, 19);
		contentPane.add(MHToan);
		
		MHVan = new JTextField();
		MHVan.setColumns(10);
		MHVan.setBounds(169, 188, 45, 19);
		contentPane.add(MHVan);
		
		JLabel lblNewLabel_3 = new JLabel("Văn:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(128, 191, 45, 13);
		contentPane.add(lblNewLabel_3);
		
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
		
		JLabel lblNewLabel_2_1 = new JLabel("Bảng Điểm");
		lblNewLabel_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(145, 10, 143, 25);
		contentPane.add(lblNewLabel_2_1);
		
		String Diem = data_Diem;
		
		String[][] temp =parseInput(Diem);
		String[][] temp1 =conf(temp);
		HoTen.setText(temp[0][1]);
		MHAnh.setText(temp1[0][1]);
		MHToan.setText(temp1[1][1]);
		MHVan.setText(temp1[2][1]);
	}
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
}
