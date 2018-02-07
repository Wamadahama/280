import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.*;

public class MainWindow {

	private JFrame frame;
	private JTextField inputField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 369, 408);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JRadioButton circleRadioBtn = new JRadioButton("Circle");
		circleRadioBtn.setBounds(27, 22, 149, 23);
		frame.getContentPane().add(circleRadioBtn);
		
		JRadioButton squareRadioBtn = new JRadioButton("Square");
		squareRadioBtn.setBounds(27, 59, 149, 23);
		frame.getContentPane().add(squareRadioBtn);
		
		inputField = new JTextField();
		inputField.setBounds(213, 55, 114, 19);
		frame.getContentPane().add(inputField);
		inputField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Radius/Edge Length");
		lblNewLabel.setBounds(203, 24, 152, 19);
		frame.getContentPane().add(lblNewLabel);
		
		JTextPane serverTextPane = new JTextPane();
		serverTextPane.setBounds(27, 165, 309, 194);
		frame.getContentPane().add(serverTextPane);

		JButton submitBtn = new JButton("Submit Request");
		submitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// determine parameters 
				String option = circleRadioBtn.isSelected() ? "circle" : "square";
				double input = Double.parseDouble(inputField.getText());

				// Send request to the server 
				Client client = new Client();
				DataInputStream stream = client.Request(option, input);
				
				try {
					serverTextPane.setText(stream.readUTF());
				} catch (IOException p) {
					p.printStackTrace();
				}
			}
		});
		submitBtn.setBounds(113, 128, 149, 25);
		frame.getContentPane().add(submitBtn);		
	}
}
