package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Client.Login;
import Server.Server_UDP;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class UDP_Protocal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UDP_Protocal frame = new UDP_Protocal();
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
	public UDP_Protocal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(62, 87, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUdpProtocal = new JLabel("UDP Protocal");
		lblUdpProtocal.setForeground(new Color(255, 255, 255));
		lblUdpProtocal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUdpProtocal.setBounds(153, 10, 133, 33);
		contentPane.add(lblUdpProtocal);
		
		JButton btnHThngQun = new JButton("Hệ thống quản lý học sinh");
		btnHThngQun.setForeground(new Color(255, 255, 255));
		btnHThngQun.setBackground(new Color(244, 204, 11));
		btnHThngQun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login frame = new Login();
				Server_UDP frame2 = new Server_UDP();
				frame.setVisible(true);
				frame2.setVisible(true);
				frame2.startUDPServer();
			}
		});
		btnHThngQun.setBounds(139, 103, 157, 51);
		contentPane.add(btnHThngQun);
		
		JButton btnNewButton = new JButton("Trở về");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu frame = new MainMenu();
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 232, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnThot = new JButton("Thoát");
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
