package Interface;

import Sockets.Socket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartWindow extends JFrame {
	
	Boolean conection = false;
	
	public static void main(String[] args) {

		Thread thread1 = new Thread() {
			public void run() {
				startSocket();
			}
		};
		
		thread1.start();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartWindow frame = new StartWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private Image Image = new ImageIcon("Images/paceman.jpg").getImage();
	private Image play = new ImageIcon("Images/play.png").getImage();
	private JPanel contentpane;


	public StartWindow() {
		setLayout(null);
		setTitle("PaCE-man");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,800,450);
		setResizable(true);
		setVisible(true);



		contentpane = new JPanel();
		contentpane.setBounds(0,0,800,450);
		contentpane.setLayout(null);

		JLabel background = new JLabel();
		background.setBounds(0, 0, 800, 450);
		background.setIcon(new ImageIcon(Image));

		JButton btn = new JButton();
		btn.setBounds(320, 290, 154,89);
		btn.setIcon(new ImageIcon(play));
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				conection = Socket.tryConection();
				if(conection) {
				windowJFrame frame = new windowJFrame();
				 StartWindow.this.dispose();
				}
			}
		});

		contentpane.add(btn);
		contentpane.add(background);
		this.add(contentpane);
	}

	static Runnable startSocket() {
		try {
			new Socket();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
