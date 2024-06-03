package Server.QuanLyHocSinh;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.HocSinhDAO;
import Server.QuanLyDiem.QuanLyDiem_Server;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Them extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField IDHocSinh;
	private JTextField HoTen;
	private JTextField DiaChi;
	private JTextField Nam;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Them frame = new Them();
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
	public Them() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 53));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTrangThmHc = new JLabel("Thêm Học Sinh");
		lblTrangThmHc.setForeground(new Color(255, 255, 255));
		lblTrangThmHc.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTrangThmHc.setBounds(139, 10, 222, 25);
		contentPane.add(lblTrangThmHc);
		
		JLabel lblNewLabel_1 = new JLabel("Họ Tên:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(54, 92, 64, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("IDHocSinh:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(54, 69, 64, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Địa Chỉ:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(54, 115, 64, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Năm nhập học:");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(54, 141, 85, 13);
		contentPane.add(lblNewLabel_4);
		
		IDHocSinh = new JTextField();
		IDHocSinh.setBounds(152, 66, 55, 19);
		contentPane.add(IDHocSinh);
		IDHocSinh.setColumns(10);
		
		HoTen = new JTextField();
		HoTen.setBounds(152, 89, 154, 19);
		contentPane.add(HoTen);
		HoTen.setColumns(10);
		
		DiaChi = new JTextField();
		DiaChi.setBounds(152, 112, 154, 19);
		contentPane.add(DiaChi);
		DiaChi.setColumns(10);
		
		Nam = new JTextField();
		Nam.setBounds(152, 138, 55, 19);
		contentPane.add(Nam);
		Nam.setColumns(10);
		
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
		
		JButton btnNewButton_2 = new JButton("Thêm");
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = IDHocSinh.getText();
				String Name = HoTen.getText();
				String Address = DiaChi.getText();
				String Year = Nam.getText();
//				System.out.println();
				if(ID.equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn để trống ID học sinh");
				}else if(Name.equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn để trống họ tên học sinh");
				}else if(Address.equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn để trống địa chỉ học sinh");
				}else if(Year.equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn để trống năm nhập học của học sinh");
				}else {
					if(HocSinhDAO.themHocSinh(ID, Name, Address, Year, "123")) {
						JOptionPane.showMessageDialog(null, "Thêm học sinh (" + Name+ ") thành công!");
					}
					else {
						JOptionPane.showMessageDialog(null, "Thêm học sinh (" + Name+ ") thất bại!");
					}
				}
			}
		});
		btnNewButton_2.setBounds(163, 178, 85, 21);
		contentPane.add(btnNewButton_2);
	}
}
