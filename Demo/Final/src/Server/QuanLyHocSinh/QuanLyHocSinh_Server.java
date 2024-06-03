package Server.QuanLyHocSinh;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.HocSinhDAO;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JTree;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import java.awt.Scrollbar;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class QuanLyHocSinh_Server extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField IDHocSinh;
    private static JTextArea textArea = new JTextArea();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QuanLyHocSinh_Server frame = new QuanLyHocSinh_Server();
                    frame.setVisible(true);
                    UpdateDanhSach();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void appendToTextArea(String text) {
        textArea.append(text + "\n");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
    }
    
    public static void clearDanhSach() {
    	textArea.setText("");
    }

    public static void UpdateDanhSach() {
        String DanhSach = HocSinhDAO.printAll();
        appendToTextArea(DanhSach);
    }

    public QuanLyHocSinh_Server() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 0, 53));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTrangQunL = new JLabel("Trang Quản Lý Học Sinh");
        lblTrangQunL.setForeground(new Color(255, 255, 255));
        lblTrangQunL.setBackground(new Color(255, 255, 255));
        lblTrangQunL.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTrangQunL.setBounds(95, 10, 251, 25);
        contentPane.add(lblTrangQunL);

        JButton btnNewButton = new JButton("Thêm");
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Them frame = new Them();
        		frame.setVisible(true);
        		setVisible(false);
        	}
        });
        btnNewButton.setBounds(10, 232, 70, 21);
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

        JButton btnNewButton_2 = new JButton("Xóa");
        btnNewButton_2.setBackground(new Color(255, 255, 255));
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Xoa frame = new Xoa();
        		frame.setVisible(true);
        		setVisible(false);
        	}
        });
        btnNewButton_2.setBounds(95, 232, 70, 21);
        contentPane.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("Sửa");
        btnNewButton_3.setBackground(new Color(255, 255, 255));
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ChinhSua frame = new ChinhSua();
        		frame.setVisible(true);
        		setVisible(false);
        	}
        });
        btnNewButton_3.setBounds(184, 232, 70, 21);
        contentPane.add(btnNewButton_3);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 73, 416, 149);
        contentPane.add(scrollPane);

        JLabel lblNewLabel_1 = new JLabel("Tìm kiếm theo IDHocSinh:");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBackground(new Color(255, 255, 255));
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
    }
}