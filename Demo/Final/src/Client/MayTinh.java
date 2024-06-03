package Client;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class MayTinh extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private Socket socket;
	private PrintWriter out;
    private BufferedReader in;
    private ArrayList<String> historyList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
    public void addToHistory(String data) {
        // Thêm dữ liệu vào ArrayList
        historyList.add(data);
    }
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MayTinh frame = new MayTinh();
					frame.setVisible(true);
					frame.connectToServer();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MayTinh() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(65, 65, 65));
		contentPane.setForeground(new Color(26, 26, 26));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField.setBounds(35, 44, 296, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("(");
		btnNewButton.setBackground(new Color(128, 128, 128));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText()+"(");
			}
		});
		btnNewButton.setBounds(35, 86, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(")");
		btnNewButton_1.setBackground(new Color(128, 128, 128));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText()+")");
			}
		});
		btnNewButton_1.setBounds(130, 86, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("%");
		btnNewButton_2.setBackground(new Color(128, 128, 128));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText()+"%");
			}
		});
		btnNewButton_2.setBounds(225, 86, 85, 21);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("CE");
		btnNewButton_3.setBackground(new Color(128, 128, 128));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
			}
		});
		btnNewButton_3.setBounds(320, 86, 85, 21);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("7");
		btnNewButton_4.setForeground(new Color(255, 255, 255));
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_4.setBackground(new Color(60, 60, 60));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("=")) {
					textField.setText("");
				}
				textField.setText(textField.getText()+"7");
			}
		});
		btnNewButton_4.setBounds(35, 117, 85, 21);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("4");
		btnNewButton_5.setForeground(new Color(255, 255, 255));
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_5.setBackground(new Color(60, 60, 60));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("=")) {
					textField.setText("");
				}
				textField.setText(textField.getText()+"4");
			}
		});
		btnNewButton_5.setBounds(35, 148, 85, 21);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("1");
		btnNewButton_6.setForeground(new Color(255, 255, 255));
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_6.setBackground(new Color(60, 60, 60));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("=")) {
					textField.setText("");
				}
				textField.setText(textField.getText()+"1");
			}
		});
		btnNewButton_6.setBounds(35, 179, 85, 21);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("0");
		btnNewButton_7.setForeground(new Color(255, 255, 255));
		btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_7.setBackground(new Color(60, 60, 60));
		btnNewButton_7.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if(textField.getText().contains("=")) {
		            textField.setText("");
		        }
		        textField.setText(textField.getText() + "0");
		    }
		});
		btnNewButton_7.setBounds(35, 208, 85, 21);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("8");
		btnNewButton_8.setForeground(new Color(255, 255, 255));
		btnNewButton_8.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_8.setBackground(new Color(60, 60, 60));
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("=")) {
					textField.setText("");
				}
				textField.setText(textField.getText()+"8");
			}
		});
		btnNewButton_8.setBounds(130, 117, 85, 21);
		contentPane.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("5");
		btnNewButton_9.setForeground(new Color(255, 255, 255));
		btnNewButton_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_9.setBackground(new Color(60, 60, 60));
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("=")) {
					textField.setText("");
				}
				textField.setText(textField.getText()+"5");
			}
		});
		btnNewButton_9.setBounds(130, 148, 85, 21);
		contentPane.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("2");
		btnNewButton_10.setForeground(new Color(255, 255, 255));
		btnNewButton_10.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_10.setBackground(new Color(60, 60, 60));
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("=")) {
					textField.setText("");
				}
				textField.setText(textField.getText()+"2");
			}
		});
		btnNewButton_10.setBounds(130, 179, 85, 21);
		contentPane.add(btnNewButton_10);
		
		JButton btnNewButton_11 = new JButton(".");
		btnNewButton_11.setBackground(new Color(60, 60, 60));
		btnNewButton_11.setForeground(new Color(255, 255, 255));
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText()+".");
			}
		});
		btnNewButton_11.setBounds(130, 208, 85, 21);
		contentPane.add(btnNewButton_11);
		
		JButton btnNewButton_12 = new JButton("9");
		btnNewButton_12.setForeground(new Color(255, 255, 255));
		btnNewButton_12.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_12.setBackground(new Color(60, 60, 60));
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("=")) {
					textField.setText("");
				}
				textField.setText(textField.getText()+"9");
			}
		});
		btnNewButton_12.setBounds(225, 117, 85, 21);
		contentPane.add(btnNewButton_12);
		
		JButton btnNewButton_13 = new JButton("6");
		btnNewButton_13.setForeground(new Color(255, 255, 255));
		btnNewButton_13.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_13.setBackground(new Color(60, 60, 60));
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("=")) {
					textField.setText("");
				}
				textField.setText(textField.getText()+"6");
			}
		});
		btnNewButton_13.setBounds(225, 148, 85, 21);
		contentPane.add(btnNewButton_13);
		
		JButton btnNewButton_14 = new JButton("3");
		btnNewButton_14.setForeground(new Color(255, 255, 255));
		btnNewButton_14.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_14.setBackground(new Color(60, 60, 60));
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("=")) {
					textField.setText("");
				}
				textField.setText(textField.getText()+"3");
			}
		});
		btnNewButton_14.setBounds(225, 179, 85, 21);
		contentPane.add(btnNewButton_14);
		
		JButton btnNewButton_15 = new JButton("=");
		btnNewButton_15.setBackground(new Color(129, 149, 203));
		btnNewButton_15.setForeground(new Color(0, 0, 0));
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage(textField.getText());
                textField.setText("");
			}
		});
		btnNewButton_15.setBounds(225, 208, 85, 21);
		contentPane.add(btnNewButton_15);
		
		JButton btnNewButton_16 = new JButton(":");
		btnNewButton_16.setBackground(new Color(128, 128, 128));
		btnNewButton_16.setForeground(new Color(255, 255, 255));
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("=")) {
					textField.setText(getValueAfterEqualSign(textField.getText()) + " : ");
				}else {
					textField.setText(textField.getText()+" : ");
				}	
			}
		});
		btnNewButton_16.setBounds(320, 117, 85, 21);
		contentPane.add(btnNewButton_16);
		
		JButton btnNewButton_17 = new JButton("x");
		btnNewButton_17.setBackground(new Color(128, 128, 128));
		btnNewButton_17.setForeground(new Color(255, 255, 255));
		btnNewButton_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("=")) {
					textField.setText(getValueAfterEqualSign(textField.getText()) + " x ");
				}else {
					textField.setText(textField.getText()+" x ");
				}	
			}
		});
		btnNewButton_17.setBounds(320, 148, 85, 21);
		contentPane.add(btnNewButton_17);
		
		JButton btnNewButton_18 = new JButton("-");
		btnNewButton_18.setBackground(new Color(128, 128, 128));
		btnNewButton_18.setForeground(new Color(255, 255, 255));
		btnNewButton_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("=")) {
					textField.setText(getValueAfterEqualSign(textField.getText()) + " - ");
				}else {
					textField.setText(textField.getText()+" - ");
				}	
			}
		});
		btnNewButton_18.setBounds(320, 179, 85, 21);
		contentPane.add(btnNewButton_18);
		
		JButton btnNewButton_19 = new JButton("+");
		btnNewButton_19.setBackground(new Color(128, 128, 128));
		btnNewButton_19.setForeground(new Color(255, 255, 255));
		btnNewButton_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("=")) {
					textField.setText(getValueAfterEqualSign(textField.getText()) + " + ");
				}else {
					textField.setText(textField.getText()+" + ");
				}	
			}
		});
		btnNewButton_19.setBounds(320, 208, 85, 21);
		contentPane.add(btnNewButton_19);
		
		JButton btnNewButton_20 = new JButton("Del");
		btnNewButton_20.setForeground(new Color(255, 255, 255));
		btnNewButton_20.setBackground(new Color(192, 192, 192));
		btnNewButton_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("=")) {
					textField.setText("");
				}else {
					String result = removeOuterCharacter(textField.getText());
					textField.setText(result);
				}
			}
		});
		
		btnNewButton_20.setBounds(341, 51, 64, 21);
		contentPane.add(btnNewButton_20);
		
		JButton btnNewButton_21 = new JButton("Lịch sử");
		btnNewButton_21.setForeground(new Color(255, 255, 255));
		btnNewButton_21.setBackground(new Color(192, 192, 192));
		btnNewButton_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showHistoryWindow();
			}
		});
		btnNewButton_21.setBounds(35, 10, 85, 21);
		contentPane.add(btnNewButton_21);
		
		JButton btnNewButton_22 = new JButton("Thoát");
		btnNewButton_22.setForeground(new Color(198, 54, 0));
		btnNewButton_22.setBackground(new Color(255, 255, 255));
		btnNewButton_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_22.setBounds(341, 239, 85, 21);
		contentPane.add(btnNewButton_22);
	}
	public void connectToServer() {
        try {
            socket = new Socket("localhost", 12345);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String serverMessage;
                    try {
                        while ((serverMessage = in.readLine()) != null) {
                        	updateTextField(serverMessage);
                            addToHistory(serverMessage);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	 private void showHistoryWindow() {
	        JFrame historyFrame = new JFrame("Lịch sử");
	        JPanel panel = new JPanel();
	        JTextArea textArea = new JTextArea(20, 30);
	        JScrollPane scrollPane = new JScrollPane(textArea);

	        textArea.setEditable(false);
	        textArea.setFont(new Font("Arial", Font.PLAIN, 14));

	        // Thêm lịch sử vào textArea
	        for (String item : historyList) {
	            textArea.append(item + "\n");
	        }

	        panel.add(scrollPane);
	        historyFrame.getContentPane().add(panel);
	        historyFrame.pack();
	        historyFrame.setVisible(true);
	    }
	private void updateTextField(final String message) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Thêm tin nhắn mới vào cuối textField mà không ghi đè lên tin nhắn cũ
                textField.setText(textField.getText() + message + "\n");
            }
        });
    }
	public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }
	public static String getValueAfterEqualSign(String expression) {
        // Check if the expression contains the equal sign
        if (expression.contains("=")) {
            // Split the string into parts separated by the equal sign
            String[] parts = expression.split("=");
            // Check if there's a part after the equal sign
            if (parts.length > 1) {
                // Return the part after the equal sign, trimmed of any leading/trailing whitespace
                return parts[1].trim();
            } else {
                throw new IllegalArgumentException("Invalid expression: No value found after the equal sign.");
            }
        } else {
            throw new IllegalArgumentException("Invalid expression: No equal sign found.");
        }
    }
	public static String removeOuterCharacter(String input) {
        // Kiểm tra xem chuỗi có đủ dài để có ký tự ngoài cùng hay không
        if (input.length() >= 2) {
            // Trả về phần của chuỗi từ vị trí 1 đến vị trí trước ký tự cuối cùng
            return input.substring(0, input.length() - 1);
        } else {
            // Nếu chuỗi không đủ dài, không cần xóa ký tự ngoài cùng, trả về chuỗi gốc
            return input;
        }
    }
}
