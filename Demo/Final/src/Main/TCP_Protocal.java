package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Client.Client_TCP;
import Client.MayTinh;
import Server.Server_TCP;
import Server.Server_TCP_MayTinh;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TCP_Protocal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TCP_Protocal frame = new TCP_Protocal();
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
	public TCP_Protocal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(62, 87, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTcpProtocal = new JLabel("TCP Protocal");
		lblTcpProtocal.setForeground(new Color(255, 255, 255));
		lblTcpProtocal.setBackground(new Color(255, 255, 255));
		lblTcpProtocal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTcpProtocal.setBounds(151, 26, 133, 33);
		contentPane.add(lblTcpProtocal);
		
		JButton btnNewButton = new JButton("Chat Room");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(244, 204, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TCP_ChatRoom frame = new TCP_ChatRoom();
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(25, 118, 133, 45);
		contentPane.add(btnNewButton);
		
		JButton btnGame = new JButton("Máy tính");
		btnGame.setForeground(new Color(255, 255, 255));
		btnGame.setBackground(new Color(244, 204, 11));
		btnGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Server_TCP_MayTinh frame1 = new Server_TCP_MayTinh();
				frame1.setVisible(true);
				frame1.startServer();
				MayTinh frame = new MayTinh();
				frame.setVisible(true);
				frame.connectToServer();
			}
		});
		btnGame.setBounds(262, 118, 133, 45);
		contentPane.add(btnGame);
		
		JButton btnNewButton_1 = new JButton("Trở về");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu frame = new MainMenu();
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(10, 232, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnThot = new JButton("Thoát");
		btnThot.setBackground(new Color(255, 255, 255));
		btnThot.setForeground(new Color(198, 54, 0));
		btnThot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnThot.setBounds(341, 232, 85, 21);
		contentPane.add(btnThot);
	}

}
