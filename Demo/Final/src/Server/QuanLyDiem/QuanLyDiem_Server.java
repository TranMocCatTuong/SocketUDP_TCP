package Server.QuanLyDiem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import OOP.Diem;
import DAO.DiemDAO;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class QuanLyDiem_Server extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField IDHocSinh;
    private static JTextArea textArea = new JTextArea();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QuanLyDiem_Server frame = new QuanLyDiem_Server();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        updateDiem();
    }

    /**
     * Create the frame.
     */
    public static void updateDiem() {
        String Diem = DiemDAO.BangDiemAll();
        appendToTextArea(Diem);
    }
    public static void clearDiem() {
    	textArea.setText("");
    }

    public QuanLyDiem_Server() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 0, 53));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Trang Quản Lý Điểm");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setBounds(116, 10, 208, 25);
        contentPane.add(lblNewLabel);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 93, 418, 133);
        contentPane.add(scrollPane);
        
        IDHocSinh = new JTextField();
        IDHocSinh.setText("HS001");
        IDHocSinh.setBounds(195, 64, 96, 19);
        contentPane.add(IDHocSinh);
        IDHocSinh.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Tìm kiếm theo IDHocSinh:");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(37, 67, 148, 13);
        contentPane.add(lblNewLabel_1);
        
        JButton btnNewButton = new JButton("Tìm");
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ID = IDHocSinh.getText();
                textArea.setText("");
                String Diem = DiemDAO.TimDiemTheoIDHocSinh(ID);
                appendToTextArea(Diem);
            }
        });
        btnNewButton.setBounds(297, 62, 57, 21);
        contentPane.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Thoát");
        btnNewButton_1.setBackground(new Color(255, 255, 255));
        btnNewButton_1.setForeground(new Color(198, 54, 0));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
            }
        });
        btnNewButton_1.setBounds(341, 236, 85, 21);
        contentPane.add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("Chỉnh sửa điểm");
        btnNewButton_2.setBackground(new Color(255, 255, 255));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChinhSuaDiem frame = new ChinhSuaDiem();
                frame.setVisible(true);
                setVisible(false);
            }
        });
        btnNewButton_2.setBounds(18, 236, 132, 21);
        contentPane.add(btnNewButton_2);
        
        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setBounds(411, 93, 17, 133);
        contentPane.add(scrollBar);
        
        JButton btnNewButton_3 = new JButton("Danh sách tổng");
        btnNewButton_3.setBackground(new Color(255, 255, 255));
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		clearDiem();
        		updateDiem();
        	}
        });
        btnNewButton_3.setBounds(160, 236, 131, 21);
        contentPane.add(btnNewButton_3);
    }

    private static void appendToTextArea(String text) {
        textArea.append(text + "\n");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
    }
}
