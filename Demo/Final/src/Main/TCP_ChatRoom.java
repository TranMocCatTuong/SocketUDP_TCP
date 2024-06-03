package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Client.Client_TCP;
import Server.Server_TCP;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TCP_ChatRoom extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TCP_ChatRoom frame = new TCP_ChatRoom();
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
	public TCP_ChatRoom() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(62, 87, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Client");
		btnNewButton.setBackground(new Color(244, 204, 11));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client_TCP frame = new Client_TCP();
				frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 99, 148, 51);
		contentPane.add(btnNewButton);
		
		JButton btnServer = new JButton("Server");
		btnServer.setBackground(new Color(244, 204, 11));
		btnServer.setForeground(new Color(255, 255, 255));
		btnServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Server_TCP frame1 = new Server_TCP();
				frame1.setVisible(true);
			}
		});
		btnServer.setBounds(278, 99, 148, 51);
		contentPane.add(btnServer);
		
		JLabel lblNewLabel = new JLabel("Có thể nhiều Client");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setLabelFor(this);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(20, 164, 148, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Chỉ 1 Server được chạy");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(278, 166, 156, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Chat Room");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(167, 10, 110, 34);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Trở về");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TCP_Protocal frame = new TCP_Protocal();
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(10, 232, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Thoát");
		btnNewButton_2.setForeground(new Color(198, 54, 0));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_2.setBounds(341, 232, 85, 21);
		contentPane.add(btnNewButton_2);
	}
}
