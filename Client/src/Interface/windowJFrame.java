package Interface;



import javax.swing.*;


public class windowJFrame extends JFrame {
	
	
	public windowJFrame() {
		setTitle("Pac Man");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 740);
		
		
		GameWindow game = new GameWindow();
		
		this.add(game);
	}


}