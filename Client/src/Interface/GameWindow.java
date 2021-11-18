package Interface;

import Characters.Candy;
import Characters.Fruit;
import Characters.Ghost;
import Characters.PacMan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;


public class GameWindow extends JPanel implements ActionListener {

	Integer[][] matrix = {
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1 },
			{ 1, 3, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 3, 1 },
			{ 1, 3, 1, 0, 0, 1, 3, 1, 0, 0, 0, 1, 3, 1, 1, 3, 1, 0, 0, 0, 1, 3, 1, 0, 0, 1, 3, 1 },
			{ 1, 3, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 3, 1 },
			{ 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1 },
			{ 1, 3, 1, 1, 1, 1, 3, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 3, 1, 1, 1, 1, 3, 1 },
			{ 1, 3, 1, 1, 1, 1, 3, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 3, 1, 1, 1, 1, 3, 1 },
			{ 1, 3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 1 },
			{ 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 3, 1, 3, 1, 1, 1, 1 },
			{ 1, 1, 1, 3, 1, 1, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 1, 3, 3, 1, 0, 1 },
			{ 1, 3, 3, 3, 1, 1, 3, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 3, 1, 3, 1, 1, 3, 1, 1, 1 },
			{ 1, 3, 1, 1, 1, 1, 3, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 3, 1, 3, 1, 1, 3, 3, 3, 1 },
			{ 1, 3, 3, 3, 3, 3, 3, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 3, 1, 3, 1, 1, 1, 1, 3, 1 },
			{ 1, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 3, 3, 3, 3, 3, 3, 1 },
			{ 1, 3, 3, 3, 3, 3, 3, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 3, 1, 3, 1, 3, 1, 1, 1, 1 },
			{ 1, 3, 1, 1, 1, 1, 3, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 3, 1, 3, 1, 3, 3, 1, 1, 1 },
			{ 1, 3, 1, 0, 0, 1, 3, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 3, 1, 3, 1, 1, 3, 3, 1, 1 },
			{ 1, 3, 1, 0, 0, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 0, 1, 3, 1, 1, 1},
			{ 1, 3, 1, 1, 1, 1, 3, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 3, 1, 1, 1, 3, 1, 1 },
			{ 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1 },
			{ 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 3, 1 },
			{ 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 3, 1 },
			{ 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 1 },
			{ 1, 3, 1, 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 3, 1, 1, 3, 1, 1, 1 },
			{ 1, 3, 1, 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 3, 1, 1, 3, 1, 1, 1 },
			{ 1, 3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 1 },
			{ 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1 },
			{ 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1 },
			{ 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
	};

	PacMan pacman = new PacMan();
	Boolean playing;
	Rectangle colisionZone;
	private Timer timer;

	ArrayList<Rectangle> rectList = new ArrayList<Rectangle>();
	ArrayList<Rectangle> dotList = new ArrayList<Rectangle>();
	ArrayList<Point> dotConsumedListX = new ArrayList<Point>();
	ArrayList<Fruit> FruitsList = new ArrayList<Fruit>();
	ArrayList<Ghost> GhostsList = new ArrayList<Ghost>();
	//ArrayList<Integer> dotConsumedListY = new ArrayList<Integer>();
	ArrayList<Candy> candyList = new ArrayList<Candy>();

	Image dotImg = new ImageIcon("Images/dot.png").getImage().getScaledInstance(15,15,1);
	Integer size = 25;
	Point pointVer;

	public GameWindow() {
		setBounds(0, 0, 1366, 740);
		setBackground(Color.BLACK);
		setLayout(null);
		setVisible(true);
		playing = true;
		addKeyListener(new TAdapter());
		setFocusable(true);
		colisionZone = new Rectangle(pacman.getPosX(), pacman.getPosY(), 17, 17);
		pacman.setColisionZone(colisionZone);
		pacman.setDir(0);
		timer = new Timer(40, this);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		try {
			doDrawing(g);
		} catch (IOException e) {
			e.printStackTrace();
		}
		isCollidingDots();


	}

	private void doDrawing(Graphics g) throws IOException {

		checkChanges(g);

		for (int f = 0; f < 31; f++) {
			for (int c = 0; c < 28; c++) {
				
				if (matrix[f][c] == 1) {
					Shape rect = new Rectangle((f * size) + 200, (c * size), size, size);
					rectList.add((Rectangle) rect);
					g.setColor(Color.darkGray);
					g.fillRect((f * size) + 200, (c * size), size, size);
					g.setColor(Color.BLUE);
					g.drawRect((f * size) + 200, (c * size), size, size);
				}else if(matrix[f][c] == 3) {
					pointVer= new Point(f,c);
					if(!dotConsumedListX.contains(pointVer)) {
					Shape dotRect = new Rectangle((f * size) + 205, (c * size)+5, 15, 15);
					dotList.add((Rectangle) dotRect);
					g.setColor(Color.BLACK);
					g.drawRect((f * size) + 205, (c * size)+5, 15, 15);
					g.drawImage(dotImg,(f * size) + 205, (c * size)+5, this);
						
					}
				}


			}
		}

		Graphics2D g2d = (Graphics2D) g;
		g.setFont(new Font("TimesRoman", Font.BOLD, 16));
		g.setColor(Color.YELLOW);
		g.drawString("Level: "+ pacman.getCurrentLevel().toString(), 1000, 100);
		g.drawString("Score: "+ pacman.getScore().toString(), 1000, 140);
		g.drawString("Lives: "+ pacman.getLives().toString(), 1000, 180);
		for(int i=0;i<pacman.getLives();i++){
			int sum=50*i;
			g2d.drawImage(pacman.getPacmanLives(), 1100+sum, 160, this);

		}
		pacman.setPosX(pacman.getPosX() + pacman.getVelX());
		pacman.setPosY(pacman.getPosY() + pacman.getVelY());
		colisionZone = new Rectangle(pacman.getPosX(), pacman.getPosY(), 17, 17);
		pacman.setColisionZone(colisionZone);
		if(isCollidingWalls() == false) {
			g2d.drawImage(pacman.getpacmanImg(), pacman.getPosX(), pacman.getPosY(), this);
			g.setColor(Color.BLACK);
			g.drawRect(pacman.getPosX(), pacman.getPosY(), 17, 17);

		}else {
			pacman.setPosX(pacman.getPosX() - pacman.getVelX());
			pacman.setPosY(pacman.getPosY() - pacman.getVelY());
			colisionZone = new Rectangle(pacman.getPosX(), pacman.getPosY(), 17, 17);
			pacman.setColisionZone(colisionZone);
			g2d.drawImage(pacman.getpacmanImg(), pacman.getPosX(), pacman.getPosY(), this);
			g.setColor(Color.BLACK);
			g.drawRect(pacman.getPosX(), pacman.getPosY(), 17, 17);
		}

		for(int i = 0; i < FruitsList.size(); i++){
			Fruit temp_fruit = FruitsList.get(i);
			temp_fruit.paint(g2d);
			if(temp_fruit.collision(pacman.getColisionZone())){
				if(temp_fruit.get_is_on()){
					pacman.setScore(pacman.getScore()+temp_fruit.getPuntaje());
					FruitsList.remove(temp_fruit);
				}
			}
		}

		for(int i = 0; i < candyList.size(); i++){
			Candy temp_candy = candyList.get(i);
			temp_candy.paint(g2d);
			if(temp_candy.collision(pacman.getColisionZone())){
				if(temp_candy.get_is_on()){
					pacman.setScore(pacman.getScore()+temp_candy.getScore());
					candyList.remove(temp_candy);
					candyChange();
				}
			}
		}

		for(int i = 0; i < GhostsList.size(); i++){
			Ghost temp_ghost = GhostsList.get(i);
			if(temp_ghost.collision(pacman.getColisionZone())){
				if(temp_ghost.get_is_on()){
					if(pacman.getLives() == 0){
						playing = false;
					} else{
						pacman.setLives(pacman.getLives() - 1);
						pacman.resetPacman();
					}
				} else{
					temp_ghost.set_is_on(true);
					temp_ghost.load_image();
					temp_ghost.restart_position();
				}
			} else{
				//Movimiento del fantasma
				if(pacman.getPosX() > GhostsList.get(i).getPosX()) {
					temp_ghost.paint(g2d, "L");
				}
				else if (pacman.getPosX() < GhostsList.get(i).getPosX()) {
					temp_ghost.paint(g2d, "R");
				}
				else if (pacman.getPosY() > GhostsList.get(i).getPosY()) {
					temp_ghost.paint(g2d, "D");
				}
				else if (pacman.getPosY() < GhostsList.get(i).getPosY()) {
					temp_ghost.paint(g2d, "U");
				}
			}
		}

		if(timer.isRunning()){
			if(timer.getDelay() == 240){
				for(int i = 0; i < GhostsList.size(); i++){
					Ghost temp_ghost = GhostsList.get(i);
					temp_ghost.set_is_on(true);
					temp_ghost.load_image();
				}
				timer.restart();
				timer.setDelay(40);
				timer.stop();
			} else{
				timer.setDelay(timer.getDelay() + 1);
			}
		}


	}

	public void candyChange() throws IOException {

		for(int i = 0; i < GhostsList.size(); i++){
			Ghost temp_ghost = GhostsList.get(i);
			temp_ghost.set_is_on(false);
			temp_ghost.load_image();
		}

		timer.start();
	}

	public Boolean isCollidingWalls() {
		for(int i=0; i<rectList.size();i++) {
			if(pacman.getColisionZone().intersects(rectList.get(i))) {
				return true;
			}
		}
		return false;
	}

	public void isCollidingDots() {
		for(int i=0; i<dotList.size();i++) {
			if(pacman.getColisionZone().intersects(dotList.get(i))) {
				Point pointConsumed = new Point((dotList.get(i).x-205)/size,(dotList.get(i).y - 5)/size);
				if(!dotConsumedListX.contains(pointConsumed)) {
					dotConsumedListX.add(pointConsumed);
					pacman.setScore(pacman.getScore()+5);
				}
			}
		}


	}

	class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {

			int key = e.getKeyCode();
		
			if (playing) {
				if (key == KeyEvent.VK_LEFT) {
					if(pacman.getOption() != 1) {
						pacman.setDir(1);
					}
					pacman.setVelX(-5);
					pacman.setVelY(0);

				} else if (key == KeyEvent.VK_RIGHT) {
					if(pacman.getOption() != 0) {
						pacman.setDir(0);
					}
					pacman.setVelX(5);
					pacman.setVelY(0);

				} else if (key == KeyEvent.VK_UP) {
					if(pacman.getOption() != 3) {
						pacman.setDir(3);
					}
					pacman.setVelX(0);
					pacman.setVelY(-5);
				} else if (key == KeyEvent.VK_DOWN) {
					if(pacman.getOption() != 2) {
						pacman.setDir(2);
					}
					pacman.setVelX(0);
					pacman.setVelY(5);
				} else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
					playing = false;
				}
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {

			int keyCode = e.getKeyCode();
			if (keyCode == e.VK_LEFT) {
				pacman.setVelX(0);
			}
			if (keyCode == e.VK_RIGHT) {
				pacman.setVelX(0);
			}
			if (keyCode == e.VK_UP) {
				pacman.setVelY(0);
				;
			}
			if (keyCode == e.VK_DOWN) {
				pacman.setVelY(0);
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}
	
	void checkChanges(Graphics g){
		
		if (mens != "") {

			Graphics2D g2d = (Graphics2D) g;

			System.out.print("Mensaje obtenido desde GameWindow: ");
			System.out.println(mens);
			String[] datos = mens.split(",");
			mens="";

			if(datos[0].contentEquals("fruta")) {
				while(true){
					int fila = Integer.parseInt(datos[3]);
					int columna = Integer.parseInt(datos[4]);
					if(matrix[fila][columna] == 0 || matrix[fila][columna] == 3){
						Fruit fruit = new Fruit(Integer.parseInt(datos[1]),Integer.parseInt(datos[2]), columna, fila);
						FruitsList.add(fruit);
						break;
					} else{
						columna += 1;
					}
				}
			}

			if(datos[0].contentEquals("fantasma")) {
				Ghost ghost = new Ghost(Integer.parseInt(datos[1]), Integer.parseInt(datos[2]));
				GhostsList.add(ghost);
			}

			if(datos[0].contentEquals("pastilla")) {
				while(true){
					int fila = Integer.parseInt(datos[2]);
					int columna = Integer.parseInt(datos[3]);
					if(matrix[fila][columna] == 0 || matrix[fila][columna] == 3) {
						Candy candy = new Candy(Integer.parseInt(datos[1]), columna, fila);
						candyList.add(candy);
						break;
					} else{
						columna += 1;
					}
				}
			}
			if(datos[0].contentEquals("vidas")) {
				pacman.addLives(Integer.parseInt(datos[1]));
				System.out.println(pacman.getLives());
			}
			if(datos[0].contentEquals("velocidad")) {
				//Cambiando velocidad
				System.out.println("Cambiando velocidad ");
			}
			
		}
	}

	static String mens = "";
	public static void putText(String texto) {
		mens = texto;
	}

}
