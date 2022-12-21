package chattogether;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField pwPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 252);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("LogIn");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(134, 0, 103, 46);
		contentPane.add(lblNewLabel);

		JButton btnSignIn = new JButton("LogIn");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					String connectionURL = "jdbc:sqlserver://DESKTOP-F3B0L8R\\SQLEXPRESS:1433;databaseName=master;integratedSecurity=true";
					Connection connection = DriverManager.getConnection(connectionURL, "sa", "123456");

					java.sql.Statement stmt = connection.createStatement();
					PreparedStatement ps = connection.prepareStatement("select * from AccountAdmin where usname=? and pass=?;");
					ps.setString(1, txtUserName.getText());
					ps.setString(2, pwPass.getText());
					PreparedStatement ps1 = connection.prepareStatement("select * from AccountClient where usname=? and pass=?;");
					ps1.setString(1, txtUserName.getText());
					ps1.setString(2, pwPass.getText());
					ResultSet rs = ps.executeQuery();
					ResultSet rs1 = ps1.executeQuery();
					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "Dang nhap thanh cong");

						ManagerGUI mnGui = new ManagerGUI();
						mnGui.show();

						dispose();

					} else if (rs1.next()) {
						JOptionPane.showMessageDialog(null, "Dang nhap thanh cong");

						ClientGUI clGui = new ClientGUI();
						clGui.show();

						dispose();

					} else {
						JOptionPane.showMessageDialog(null, "Dang nhap that bai");
						txtUserName.setText("");
						pwPass.setText("");
					}

				} catch (SQLException ex) {

					JOptionPane.showMessageDialog(btnSignIn, "Loi truy van");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSignIn.setForeground(Color.WHITE);
		btnSignIn.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSignIn.setBackground(Color.BLUE);
		btnSignIn.setBounds(59, 155, 115, 33);
		contentPane.add(btnSignIn);

		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signup su = new signup();
				su.show();
				dispose();
			}
		});
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSignUp.setBackground(Color.BLUE);
		btnSignUp.setBounds(196, 155, 115, 33);
		contentPane.add(btnSignUp);

		JLabel lblUserName = new JLabel("UserName:");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblUserName.setBounds(59, 62, 85, 14);
		contentPane.add(lblUserName);

		JLabel lblPw = new JLabel("PassWord:");
		lblPw.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPw.setBounds(63, 100, 81, 14);
		contentPane.add(lblPw);

		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		txtUserName.setBounds(154, 59, 157, 20);
		contentPane.add(txtUserName);

		pwPass = new JPasswordField();
		pwPass.setBounds(154, 100, 157, 20);
		contentPane.add(pwPass);
	}
}
