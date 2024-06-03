package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(62, 87, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Main Menu");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(161, 25, 116, 33);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("TCP Protocal");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(244, 204, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TCP_Protocal frame = new TCP_Protocal();
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(21, 106, 131, 51);
		contentPane.add(btnNewButton);
		
		JButton btnUdpProtocal = new JButton("UDP Protocal");
		btnUdpProtocal.setForeground(new Color(255, 255, 255));
		btnUdpProtocal.setBackground(new Color(244, 204, 11));
		btnUdpProtocal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UDP_Protocal frame = new UDP_Protocal();
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnUdpProtocal.setBounds(267, 106, 131, 51);
		contentPane.add(btnUdpProtocal);
		
		JButton btnNewButton_1 = new JButton("Thoát");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setForeground(new Color(198, 54, 0));
		btnNewButton_1.setBounds(341, 232, 85, 21);
		contentPane.add(btnNewButton_1);
	}
}
