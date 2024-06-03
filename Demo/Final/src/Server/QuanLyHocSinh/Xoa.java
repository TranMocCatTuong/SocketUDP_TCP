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
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Xoa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField IDHocSinh;
	static JTextArea textArea = new JTextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Xoa frame = new Xoa();
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
	public static void appendToTextArea(String text) {
        textArea.append(text + "\n");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
    }
	public Xoa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 53));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTrangXaHc = new JLabel("Xóa Học Sinh");
		lblTrangXaHc.setForeground(new Color(255, 255, 255));
		lblTrangXaHc.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTrangXaHc.setBounds(144, 10, 222, 25);
		contentPane.add(lblTrangXaHc);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm theo IDHocSinh:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(10, 50, 148, 13);
		contentPane.add(lblNewLabel_1);
		
		IDHocSinh = new JTextField();
		IDHocSinh.setText("HS001");
		IDHocSinh.setColumns(10);
		IDHocSinh.setBounds(168, 47, 96, 19);
		contentPane.add(IDHocSinh);
		
		JButton btnNewButton_4 = new JButton("Tìm");
		btnNewButton_4.setBackground(new Color(255, 255, 255));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = IDHocSinh.getText();
        		String DanhSach = HocSinhDAO.TimHocSinhTheoIDHocSinh(ID);
        		textArea.setText("");
        		appendToTextArea(DanhSach);
			}
		});
		btnNewButton_4.setBounds(270, 45, 57, 21);
		contentPane.add(btnNewButton_4);
		
		
		textArea.setBounds(11, 74, 415, 83);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("Xóa Học Sinh Này");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = IDHocSinh.getText();
				String text = textArea.getText();
				if(text.equals("")) {
					JOptionPane.showMessageDialog(null, "Mời bạn nhấn nút (Tìm) để xác nhận học sinh cần xóa");
				}else {
					if(HocSinhDAO.xoaHocSinh(ID)) {
						JOptionPane.showMessageDialog(null, "Xóa học sinh có ID ("+ ID +") thành công!");
						textArea.setText("");
					}else {
						JOptionPane.showMessageDialog(null, "Xóa học sinh có ID ("+ ID +") thất bại!");
					}
				}
			}
		});
		btnNewButton.setBounds(10, 167, 148, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Chỉnh Sửa");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChinhSua frame = new ChinhSua();
        		frame.setVisible(true);
        		setVisible(false);
			}
		});
		btnNewButton_1.setBounds(168, 167, 96, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Trở về");
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuanLyHocSinh_Server frame = new QuanLyHocSinh_Server();
                frame.setVisible(true);
                frame.clearDanhSach();
                frame.UpdateDanhSach();
                setVisible(false);
			}
		});
		btnNewButton_2.setBounds(10, 232, 85, 21);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Thoát");
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		btnNewButton_3.setForeground(new Color(198, 54, 0));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_3.setBounds(341, 232, 85, 21);
		contentPane.add(btnNewButton_3);
	}
}
