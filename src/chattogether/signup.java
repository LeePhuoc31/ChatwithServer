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
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class signup extends JFrame {

    private JPanel contentPane;
    private JPasswordField pwPass;
    private JPasswordField pwConfirm;
    private JTextField txtUserName;

    /**
     * Launch the application.
     */
    private static final String DB_URL = "jdbc:sqlserver:DESKTOP-F3B0L8R\\SQLEXPRESS:1433;databaseName=master;integratedSecurity=true";
    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "123456";

//    public static Connection getConnection(String dbURL, String userName, String password) {
//        Connection conn;
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            conn = DriverManager.getConnection(dbURL, userName, password);
////            System.out.println("connect successfully!");
//        } catch (ClassNotFoundException | SQLException ex) {
//            System.out.println("connect failure!");
//        }
//        return conn;
//    }

    public static void main(String[] args) throws SQLException {
    	Connection connection = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionURL = "jdbc:sqlserver://DESKTOP-F3B0L8R\\SQLEXPRESS:1433;databaseName=master;integratedSecurity=true";
			connection = DriverManager.getConnection(connectionURL, "sa", "123456");

			System.out.println("Kết nối CSDL thành côngi!!!");
			
			java.sql.Statement stmt = connection.createStatement();
            // get data from table 'student'
            ResultSet rs = stmt.executeQuery("select * from AccountClient");
            // show data
//            while (rs.next()) {
//                System.out.println("Username " + rs.getString("usname") + " PassWord " + rs.getString("pass"));
//            }
		} catch (ClassNotFoundException e) {
			System.out.println("Kết nối CSDL thất bại!!!");
			System.err.println(e.getMessage() + "/n" + e.getCause());

		}

        java.awt.EventQueue.invokeLater(() -> {
            signup frame = new signup();
            frame.setVisible(true);
        });

    }

    /**
     * Create the frame.
     */
    public signup() {
        setTitle("SignUp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 392, 317);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("SignUp");
        lblNewLabel.setForeground(new Color(0, 0, 255));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblNewLabel.setBounds(112, 11, 137, 39);
        contentPane.add(lblNewLabel);

        JLabel lblUserName = new JLabel("UserName:");
        lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblUserName.setBounds(92, 64, 85, 14);
        contentPane.add(lblUserName);

        JLabel lblPw = new JLabel("PassWord:");
        lblPw.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblPw.setBounds(96, 102, 81, 14);
        contentPane.add(lblPw);

        JLabel lblConfirm = new JLabel("Confirm PassWord:");
        lblConfirm.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblConfirm.setBounds(31, 140, 146, 14);
        contentPane.add(lblConfirm);

        JButton btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            public void actionPerformed(ActionEvent e) {
                try {
                	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        			String connectionURL = "jdbc:sqlserver://DESKTOP-F3B0L8R\\SQLEXPRESS:1433;databaseName=master;integratedSecurity=true";
        			Connection connection = DriverManager.getConnection(connectionURL, "sa", "123456");

                    java.sql.Statement stmt = connection.createStatement();
                    PreparedStatement ps = connection.prepareStatement("insert into AccountClient values (?,?,?);");
                    ps.setString(1, txtUserName.getText());
                    ps.setString(2, pwPass.getText());
                    ps.setString(3, pwConfirm.getText());
//                    if (han > 0) {
//                        JOptionPane.showMessageDialog(btnOk, "Thanh cong");
//                        login lg = new login();
//                        lg.show();
//                        dispose();
//                    }
                	if(txtUserName.getText().equals("")) {
                		JOptionPane.showMessageDialog(btnOk, "Vui long nhap username");
                		txtUserName.requestFocus();
                	}
                	else if(pwPass.getText().equals("")) {
                		JOptionPane.showMessageDialog(btnOk, "Vui long nhap mat khau");
                		pwPass.requestFocus();
                	}
                	else if(pwConfirm.getText().equals("")) {
                		JOptionPane.showMessageDialog(btnOk, "Vui long xac nhan lai mat khau");
                		pwConfirm.requestFocus();
                	}
                	else if(!pwPass.getText().equalsIgnoreCase(pwConfirm.getText())){
                		JOptionPane.showMessageDialog(btnOk, "Mat khau khong trung nhau");
                		pwConfirm.requestFocus();
                	}
                	else {
                        int han = ps.executeUpdate();
                        JOptionPane.showMessageDialog(btnOk, "Thanh cong");
                        login lg = new login();
                        lg.show();
                        dispose();
					}

                } catch (SQLException ex) {
                } catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        btnOk.setForeground(new Color(255, 255, 255));
        btnOk.setBackground(new Color(0, 0, 255));
        btnOk.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnOk.setBounds(62, 181, 115, 33);
        contentPane.add(btnOk);

        pwPass = new JPasswordField();
        pwPass.setBounds(187, 102, 157, 20);
        contentPane.add(pwPass);

        pwConfirm = new JPasswordField();
        pwConfirm.setBounds(187, 140, 157, 20);
        contentPane.add(pwConfirm);

        txtUserName = new JTextField();
        txtUserName.setBounds(187, 61, 157, 20);
        contentPane.add(txtUserName);
        txtUserName.setColumns(10);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		txtUserName.setText("");
        		pwPass.setText("");
        		pwConfirm.setText("");
        	}
        });
        btnCancel.setForeground(new Color(255, 255, 255));
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnCancel.setBackground(Color.RED);
        btnCancel.setBounds(197, 181, 115, 33);
        contentPane.add(btnCancel);
        
        JButton btnLogin = new JButton("LogIn");
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		login lg = new login();
        		lg.show();
        		dispose();
        	}
        });
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnLogin.setBackground(Color.BLUE);
        btnLogin.setBounds(62, 236, 250, 33);
        contentPane.add(btnLogin);
    }
}
