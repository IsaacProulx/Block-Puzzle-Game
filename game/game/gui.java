package game;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class gui extends JFrame implements KeyListener, ActionListener{
private ArrayList<Integer> keysPressed;	
    public int count=0;
    private graphics graphics;
    private enemy enemy;
    private heartContainer heartContainer;
    private heart heart;
    private Platform Platform;
    public static Rectangle plr;
    public static Rectangle sword;
    public static ArrayList<Platform> platforms = new ArrayList<Platform>();
    public static ArrayList<button> buttons = new ArrayList<button>();
    public static ArrayList<PushBlocks> blocks = new ArrayList<PushBlocks>();
    public static ArrayList<Path> paths = new ArrayList<Path>();
    public static ArrayList<graphics> bullets = new ArrayList<graphics>();
    public static ArrayList<heartContainer> heartC = new ArrayList<heartContainer>();
    public static ArrayList<heart> hearts = new ArrayList<heart>();
    public static ArrayList<enemy> enemys = new ArrayList<enemy>();
    public static ArrayList<Integer> garbagePlatforms = new ArrayList<Integer>();
    public static ArrayList<Integer> garbageBullets = new ArrayList<Integer>();
    public static ArrayList<Integer> garbageHeartC = new ArrayList<Integer>();
    public static ArrayList<Integer> garbageEnemys = new ArrayList<Integer>();
    static boolean firstTime = true;
    public static int plrFacing = 3;
    public static boolean spacePressed=false;
    public static boolean enemyTookDamage=false;
    public static boolean bRemove = false;
    boolean hRemove=false;
    static boolean eRemove=false;
    public static int plrHealth=3;
    public static int plrX = 250;
    public static int plrY = 250;
    public static int heartNum=3;
    int invulTime=10;
    int buttonDelay=10;
    int oneTime=1;
    int shootAgain = 0;
    int spawnTime=60;
    String name1="New Game";
    String name2="New Game";
    String name3="New Game";
    boolean atRight=false;
    boolean atLeft=false;
    boolean atTop=false;
    boolean atBottom=false;
    boolean moveRight=true;
    boolean moveLeft=true;
    boolean moveUp=true;
    boolean moveDown=true;
    public static boolean newLevel=false;
    static int upHold=0;
    public int level=0;
    int pLength=0;
    public boolean touchingPlatform=false;
    File save;
    File files = new File("saves.txt");
    public boolean allPressed =false;
    
    JPanel pauseMenu, gameScreen, mainMenu;
    JButton back, helpButton, resumeButton, saveButton, quitButton, yesButton, noButton, newGame, file1, file2, file3, start, erase;
    JLabel quitAlert, savedGame, help; 
    public static boolean paused=false;
    
    
    public void init(){
    	quitAlert = new JLabel("<html>Do You Want to Quit<br> (The Game Autosaves at The Start of Each Level, So no Progress Will be Lost)<html>");
    	savedGame = new JLabel("Saved Game");
    	help = new JLabel("<html>The Goal is to Push The Grey Blocks Onto The Yellow Buttons.<br>"
    			+ "Use Arrow Keys or WASD to Move.<br>"
    			+ "Press Space to Shoot.<br>"
    			+ "Avoid Enemies (The Blue Squares).<br>"
    			+ "Green Walls Cannot be Moved or Walked Through.<br>"
    			+ "Press Escape to Open The Pause Menu.<br>"
    			+ "Press Enter to reset the level.<html>");
    	enemys.clear();
        plr= new Rectangle(150,200,50,50);
        keysPressed = new ArrayList<>();
        move();
    }
    
    public void reset() {
        platforms.clear();
        blocks.clear();
        buttons.clear();
        enemys.clear();
        paths.clear();
    	buttonDelay=10;
    	oneTime=2;
    	invulTime=10;
        plrHealth=3;
        heartNum=3;
        bRemove = true;
        hRemove=true;
        shootAgain = 0;
        plrFacing = 3;
        spacePressed=false;
        plrX=150;
        plrY=400;
        enemyTookDamage=false;
        graphics.reset();
        enemy.reset();
        hearts.clear();
        heart.reset();
        enemys.clear();
        hRemove=false;
        levelInit();
        setContentPane(gameScreen);
        
    }
    
    public void levelInit(){
    	try {
 			BufferedReader filein = new BufferedReader(
 				                	new FileReader("Platforms/levelPlatforms"+level+".txt"));
 		
 			String line="";
 			int x;
 			int y;
 			int w;
 			int h;
 			line = filein.readLine();
 			while(!line.equals("NaN")) {
 				x=Integer.parseInt(line.substring(0, line.indexOf(",")));
 				y=Integer.parseInt(line.substring(line.indexOf(",")+1, line.indexOf(" ")));
 				w=Integer.parseInt(line.substring(line.indexOf(" ")+1,line.indexOf("|")));
 				h=Integer.parseInt(line.substring(line.indexOf("|")+1));
 				
 				platforms.add(new Platform(x,y,w,h));
 				line = filein.readLine();
 			}
 			
 		
 			filein.close();
 		}
 		catch (IOException e)
 		{
 			System.out.println("Sorry there was an error reading.\nlevelPlatforms"+level+".txt is either missing or improper");
 		}
    	
    	try {
 			BufferedReader filein = new BufferedReader(
 				                	new FileReader("Blocks/levelBlocks"+level+".txt"));
 		
 			String line="";
 			int x;
 			int y;
 			line = filein.readLine();
 			while(!line.equals("NaN")) {
 				x=Integer.parseInt(line.substring(0, line.indexOf(",")));
 				y=Integer.parseInt(line.substring(line.indexOf(",")+1));
 				blocks.add(new PushBlocks(x,y));
 				line = filein.readLine();
 			}
 			
 		
 			filein.close();
 		}
 		catch (IOException e)
 		{
 			System.out.println("Sorry there was an error reading.\nlevelBlocks"+level+".txt is either missing or improper");
 		}
    	
    	try {
 			BufferedReader filein = new BufferedReader(
 				                	new FileReader("Buttons/levelButtons"+level+".txt"));
 		
 			String line="";
 			int x;
 			int y;
 			line = filein.readLine();
 			while(!line.equals("NaN")) {
 				x=Integer.parseInt(line.substring(0, line.indexOf(",")));
 				y=Integer.parseInt(line.substring(line.indexOf(",")+1));
 				buttons.add(new button(x,y));
 				line = filein.readLine();
 			}
            
 			
 		
 			filein.close();
 		}
 		catch (IOException e)
 		{
 			System.out.println("Sorry there was an error reading.\nlevelButtons"+level+".txt is either missing or improper");
 		}
    	
    	try {
 			BufferedReader filein = new BufferedReader(
 				                	new FileReader("Enemys/levelEnemys"+level+".txt"));
 		
 			String line="";
 			int x;
 			int y;
 			line = filein.readLine();
 			while(!line.equals("NaN")) {
 				x=Integer.parseInt(line.substring(0, line.indexOf(",")));
 				y=Integer.parseInt(line.substring(line.indexOf(",")+1));
 				enemys.add(new enemy(x,y));
 				line = filein.readLine();
 			}
            
 			
 		
 			filein.close();
 		}
 		catch (IOException e)
 		{
 			System.out.println("Sorry there was an error reading.\nlevelEnemys"+level+".txt is either missing or improper");
 		}
    	
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        if(!keysPressed.contains(e.getKeyCode())){
        	if(firstTime==true) {
        		bRemove=false;
        	}
        	firstTime=false;
        		
        	keysPressed.add(new Integer(e.getKeyCode()));
        }
        
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        keysPressed.remove(new Integer(e.getKeyCode()));
    }
        
    	public void scroll(){
    		if(!paused) {
            int i4=0;
            ArrayList<Component> comps = new ArrayList<>();
            while(i4<this.getContentPane().getComponentCount()){
                comps.add(this.getContentPane().getComponent(i4));
                i4++;
            }
            comps.stream().forEach(C -> {
                int cX = C.getX();
                int cY = C.getY();
                if(atLeft&&moveLeft){
                    cX+=10;
                }
                if(atRight&&moveRight){
                    cX-=10;
                }
                if(atTop&&moveUp){
                    cY+=10;
                }
                if(atBottom&&moveDown){
                    cY-=10;
                }
                
                C.setLocation(cX,cY);
            });
    		}
        }
    
        public void move(){
        	allPressed=true;
	        buttons.stream().forEach(button->{
	        	try{
                    if(!button.getPress()) {
                    	allPressed=false;
                    }
        	    }catch(Exception e){
         			e.printStackTrace();
         		}
         		
         	});  
	        
	        if(allPressed&&buttons.size()>0) {
	        	level++;
	        	newLevel=true;
	        	allPressed=false;
	        	System.out.println("Done");
	        	buttons.clear();
	        	plrHealth=3;
	        	save();
	        }
        shootAgain--;
        while(plrHealth>10) {
        	plrHealth--;
        }
        while(heartNum>10) {
        	heartNum--;
        }
        int h =hearts.size()-1;
        while(hearts.size()>10) {
        	hearts.remove(h);
        	h--;
        }
        
        if(newLevel) {
    		for(int i=0; i<blocks.size(); i++) {
    			this.getContentPane().remove(blocks.get(i));
    			blocks.clear();
    		}
    		for(int i=0; i<buttons.size(); i++) {
    			this.getContentPane().remove(buttons.get(i));
    			buttons.clear();
    		}
    		for(int i=0; i<platforms.size(); i++) {
    			this.getContentPane().remove(platforms.get(i));
    			platforms.clear();
    		}
    		for(int i=0; i<enemys.size(); i++) {
    			this.getContentPane().remove(enemys.get(i));
    			enemys.clear();
    		}
    		newLevel=false;
    		plr.x=400;
    		plr.y=400;
    		levelInit();
    	}
       
        scroll();
        bulletUpdate();
        heartUpdate();
        removal();
        collides();
        bulletHitPlatform();
        plrX = plr.x;
        plrY = plr.y;
        if(!paused) {
        if(plrHealth>0) {
        	if(moveRight) {
                    if(keysPressed.contains(KeyEvent.VK_RIGHT)||keysPressed.contains(KeyEvent.VK_D)){
                        if(plrX<500){
                            plrX+=10;
                            atRight=false;
                        }else {
                            atRight=true;
                        }
                            plrFacing = 3;
                            firstTime=false;
                    }else {
                        atRight=false;
                    }
                }
                if(moveLeft) {
        	if(keysPressed.contains(KeyEvent.VK_LEFT)||keysPressed.contains(KeyEvent.VK_A)){
        		if(plrX>50){
                            plrX-=10;
                            atLeft=false;
                        }else {
                            atLeft=true;
                        }
                    plrFacing = 7;
                    firstTime=false;
                
        	}else {
                    atLeft=false;
                }
                }
                if(moveUp) {
        	if(keysPressed.contains(KeyEvent.VK_UP)||keysPressed.contains(KeyEvent.VK_W)){
                        if(plrY>50){
                            plrY-=10;
                            atTop=false;
                        }else {
                            atTop=true;
                        }
                        plrFacing = 1;
                        firstTime=false;
        	}else {
                    atTop=false;
                }
                }
        	if(keysPressed.contains(KeyEvent.VK_UP)&&keysPressed.contains(KeyEvent.VK_RIGHT)){
        		plrFacing = 2;
        	}
        
        	if(keysPressed.contains(KeyEvent.VK_UP)&&keysPressed.contains(KeyEvent.VK_LEFT)){
        		plrFacing = 8;
        	}
                if(moveDown) {
        	if(keysPressed.contains(KeyEvent.VK_DOWN)||keysPressed.contains(KeyEvent.VK_S)){
                    if(plrY<500){
                        plrY+=10;
                        atBottom=false;
                    }else {
                        atBottom=true;
                    }
                    
                        plrFacing = 5;
                        firstTime=false;
                }else{
                    atBottom=false;
                }
                }
        	if(keysPressed.contains(KeyEvent.VK_DOWN)&&keysPressed.contains(KeyEvent.VK_RIGHT)){
        		plrFacing = 4;
        	}
        
        	if(keysPressed.contains(KeyEvent.VK_DOWN)&&keysPressed.contains(KeyEvent.VK_LEFT)){
        		plrFacing = 6;
        	}
        	
        
        	if(keysPressed.contains(KeyEvent.VK_SPACE)&& shootAgain<=0){
        		bullets.add(new graphics());
        		shootAgain=10;
        		firstTime=false;
        	}
        	
        	if(keysPressed.contains(KeyEvent.VK_H)&&buttonDelay<=0){
                    heartC.add(new heartContainer());
                    buttonDelay=10;
        	}
        	
        	if(keysPressed.contains(KeyEvent.VK_E)&&buttonDelay<=0) {
                    buttonDelay=10;
                    enemys.add(new enemy(250,250));
        	}
                
        }
        }
        if(keysPressed.contains(KeyEvent.VK_ENTER)) {
            plrHealth=3;
            getContentPane().removeAll();
            reset();
        }
        
        
        if(keysPressed.contains(KeyEvent.VK_ESCAPE)){
        	paused=true;
        	pause();
        }
        
        buttonDelay--;
        
        try {
            TimeUnit.MILLISECONDS.sleep(60);
        } catch (InterruptedException ex) {
            Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
        }
            plr.setLocation(plrX,plrY);
            if(!paused) {
            repaint();
            }
        }
        
        public void bulletUpdate(){
            count=0;
            bullets.stream().forEach(graphics -> {
        	try{
                    graphics.rectangle();
                    Container jFrameContanier = this.getContentPane();
                    jFrameContanier.add(graphics);
                        if(bRemove==true||graphics.bulX<-150 || graphics.bulX>650 || graphics.bulY<-150 || graphics.bulY>650){
                            bRemove=false;
                            garbageBullets.add(count);
                        }
                }catch(Exception e){
                        e.printStackTrace();
                }
                count++;
            });
        }
        
        public void bulletHitPlatform(){
            count=0;
            bullets.stream().forEach(graphics -> {
        	try{
                    graphics.rectangle();
                    Container jFrameContanier = this.getContentPane();
                    jFrameContanier.add(graphics);
                platforms.stream().forEach(platform -> {
          	try{
                    Container jFrameContanier2 = this.getContentPane();
                    jFrameContanier.add(platform);
                    platform.move();
                    if(graphics.bul.intersects(platform.platform)){
                        bRemove=true;
                    }
         	    }catch(Exception e){
                        e.printStackTrace();
                    }
          	});
                }catch(Exception e){
                        e.printStackTrace();
                }
                if(bRemove){
                    bRemove=false;
                    garbageBullets.add(count);
                }
                count++;
            });
            removal();
        }
        
        public void heartUpdate(){
            count=0;
            heartC.stream().forEach(heartContainer -> {
                try{
                    Container jFrameContanier = this.getContentPane();
                    jFrameContanier.add(heartContainer);
                    if(hRemove==false&&heartContainer.heartC.intersects(plr)){
                        //heartNum++;
                        //plrHealth=heartNum;
                        //hearts.add(new heart());
                    	plrHealth++;
                        if(plr.intersects(heartContainer.heartC)) {
                        	//garbageHeartC.add(count);
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                count++;
            });
        }
        
        public void removal(){
            int i=0;
            int k=0;
            while(i<(garbageEnemys.size())){
                k=garbageEnemys.get(i);
                getContentPane().remove(enemys.get(i));
                k=enemys.size()-1;
                if(k<0) {
                	k=0;
                }
                if(enemys.size()>0) {
                	enemys.remove(k);
                }
                garbageEnemys.remove(i);
                i++;
            }
            i=0;
            k=0;
            /*while(i<(garbageHeartC.size()-1)){
                System.out.println(garbageHeartC.size());
                k=garbageHeartC.get(i);
                getContentPane().remove(heartC.get(i));
                k=heartC .size()-1;
                if(k<0) {
                	k=0;
                }
                if(heartC.size()>0) {
                	heartC.remove(k);
                }
                garbageHeartC.remove(i);
                i++;
            }*/
            heartC.clear();
            i=0;
            k=0;
            while(i<(garbageBullets.size())){
                k=garbageBullets.get(i);
                getContentPane().remove(bullets.get(i));
                if(k<0) {
                	k=0;
                }
                if(bullets.size()>0) {
                	bullets.remove(k);
                }
                garbageBullets.remove(i);
                i++;
            }
        }
        
        public gui(){
        readNames();
        scroll();
        this.graphics=new graphics();
        this.enemy=new enemy(250,250);
        this.heartContainer=new heartContainer();
        this.heart=new heart();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        init();
        repaint();
        oneTime++;
        gameScreen = new JPanel();
        gameScreen.setMinimumSize(new Dimension(600,600));
        gameScreen.setLayout(null);
        gameScreen.setBackground(Color.WHITE);
        setContentPane(gameScreen);
        }
        
        public void gameScreen(){
        	gameScreen = new JPanel();
        	gameScreen.setMinimumSize(new Dimension(600,600));
        	gameScreen.setLayout(null);
        	gameScreen.setBackground(Color.WHITE);
        	gameScreen.setDoubleBuffered(true);
        	setContentPane(gameScreen);
        	move();
        }
        
        public void mainMenu(){
        	try {
        		if(name1.equals(null)) {
        		}
        	}catch(Exception ex) {
        		name1="New Game";
        	}
        	
        	try {
        		if(name2.equals(null)) {
        		}
        	}catch(Exception ex) {
        		name2="New Game";
        	}
        	
        	try {
        		if(name3.equals(null)) {
        		}
        	}catch(Exception ex) {
        		name3="New Game";
        	}
        	paused=true;
        	mainMenu = new JPanel();
        	mainMenu.setSize(new Dimension(600,600));
        	mainMenu.setLayout(null);
        	mainMenu.setBackground(Color.WHITE);
        	file1 = new JButton(name1);
        	file1.setBounds(200,200,200,50);
        	file1.addActionListener(this);
        	file2 = new JButton(name2);
        	file2.setBounds(200,300,200,50);
        	file2.addActionListener(this);
        	file3 = new JButton(name3);
        	file3.setBounds(200,400,200,50);
        	file3.addActionListener(this);
        	start = new JButton("Continue");
        	start.setBounds(200,300,100,50);
        	start.addActionListener(this);
        	erase = new JButton("Erase File");
        	erase.setBounds(300,300,100,50);
        	erase.addActionListener(this);
        	helpButton = new JButton("Help");
        	helpButton.setBounds(485,512,100,50);
        	helpButton.addActionListener(this);
        	back = new JButton("Back");
        	back.setBounds(485,512,100,50);
        	back.addActionListener(this);
        	mainMenu.add(file1);
        	mainMenu.add(file2);
        	mainMenu.add(file3);
        	mainMenu.add(helpButton);
        	mainMenu.setDoubleBuffered(true);
        	quitAlert.setBounds(200,150,200,100);
        	savedGame.setBounds(500,500,200,100);
        	setContentPane(mainMenu);
        }
        
        public void pause(){
        	pauseMenu = new JPanel();
        	pauseMenu.setSize(new Dimension(600,600));
        	pauseMenu.setLayout(null);
        	pauseMenu.setBackground(Color.WHITE);
        	resumeButton = new JButton("Resume");
        	resumeButton.setBounds(240,200,100,50);
        	resumeButton.addActionListener(this);
        	quitButton = new JButton("Quit");
        	quitButton.setBounds(240,300,100,50);
        	quitButton.addActionListener(this);
        	yesButton = new JButton("Yes");
        	yesButton.setBounds(200,250,100,50);
        	yesButton.addActionListener(this);
        	noButton = new JButton("No");
        	noButton.setBounds(300,250,100,50);
        	noButton.addActionListener(this);
        	pauseMenu.add(resumeButton);
        	pauseMenu.add(quitButton);
        	quitAlert.setBounds(200,150,200,100);
        	savedGame.setBounds(500,500,200,100);
        	setContentPane(pauseMenu);
        	
        }
        
        @Override
		public void actionPerformed(ActionEvent e) {
        	try{
    			if(name1.equals(null)) {
    				//can't run this if null so there's nothing inside
    			}
    		}catch(NullPointerException ex) {
    			name1="New Game";
    		}
        	
        	try{
    			if(name2.equals(null)) {
    				//can't run this if null so there's nothing inside
    			}
    		}catch(NullPointerException ex) {
    			name2="New Game";
    		}
        	
        	try{
    			if(name3.equals(null)) {
    				//can't run this if null so there's nothing inside
    			}
    		}catch(NullPointerException ex) {
    			name3="New Game";
    		}
        	
        	if(e.getSource()==file1) {
				if(file1.getText().equals("New Game")) {
					name1=JOptionPane.showInputDialog("Enter a Name");
                    	while(name1.length()<1||name1.contains("\\")||name1.contains("/")){
                    		name1=JOptionPane.showInputDialog("Enter a Name");
                        }
					file1.setText(name1);
					save = new File(name1+".sav1");
					level=0;
					updateNames();
					save();
				}else {
					save = new File(name1+".sav1");
					mainMenu.remove(file1);
					mainMenu.remove(file2);
					mainMenu.remove(file3);
					mainMenu.remove(helpButton);
					mainMenu.add(start);
					mainMenu.add(erase);
					mainMenu.repaint();
				}
			}
			if(e.getSource()==file2) {
				if(file2.getText().equals("New Game")) {
					name2=JOptionPane.showInputDialog("Enter a Name");
                                        while(name2.length()<1||name1.contains("\\")||name1.contains("/")){
                                            name2=JOptionPane.showInputDialog("Enter a Name");
                                        }
					file2.setText(name2);
					save = new File(name2+".sav2");
					updateNames();
					save();
				}else {
					save = new File(name2+".sav2");
					mainMenu.remove(file1);
					mainMenu.remove(file2);
					mainMenu.remove(file3);
					mainMenu.remove(helpButton);
					mainMenu.add(start);
					mainMenu.add(erase);
					mainMenu.repaint();
				}
			}
			if(e.getSource()==file3) {
				if(file3.getText().equals("New Game")) {
					name3=JOptionPane.showInputDialog("Enter a Name");
                                        while(name3.length()<1||name1.contains("\\")||name1.contains("/")){
                                            name3=JOptionPane.showInputDialog("Enter a Name");
                                        }
					file3.setText(name3);
					save = new File(name3+".sav3");
					updateNames();
					save();
				}else {
					save = new File(name3+".sav3");
					mainMenu.remove(file1);
					mainMenu.remove(file2);
					mainMenu.remove(file3);
					mainMenu.remove(helpButton);
					mainMenu.add(start);
					mainMenu.add(erase);
					mainMenu.repaint();
				}
			}
			
			if(e.getSource()==start) {
				readSave();
                getContentPane().removeAll();
				setContentPane(gameScreen);
				paused=false;
				firstTime=false;
				move();
			}
			
			if(e.getSource()==helpButton) {
				mainMenu.remove(file1);
				mainMenu.remove(file2);
				mainMenu.remove(file3);
				mainMenu.remove(helpButton);
				help.setFont(new Font("Georgia", Font.BOLD, 30));
				help.setBounds(0,0,600,600);
				help.setVisible(true);
				mainMenu.repaint();
				mainMenu.add(help);
				mainMenu.add(back);
			}
			
			if(e.getSource()==back) {
				mainMenu.remove(back);
				mainMenu.remove(help);
				mainMenu.repaint();
				mainMenu.add(file1);
				mainMenu.add(file2);
				mainMenu.add(file3);
				mainMenu.add(helpButton);
			}
			
			if(e.getSource()==erase) {
				if(save.getName().equals(name1+".sav1")) {
					name1="New Game";
				} else if(save.getName().equals(name2+".sav2")) {
					name2="New Game";
				} else if(save.getName().equals(name3+".sav3")) {
					name3="New Game";
				}
				eraseFile();
				updateNames();
				file1.setText(name1);
				file2.setText(name2);
				file3.setText(name3);
				mainMenu.remove(start);
				mainMenu.remove(erase);
				mainMenu.add(file1);
				mainMenu.add(file2);
				mainMenu.add(file3);
				mainMenu.add(helpButton);
				mainMenu.repaint();
			}
			
        	if(getContentPane()==pauseMenu) {
        	pauseMenu.remove(savedGame);
        	//save button is not used at the moment
			/*if(e.getSource()==saveButton) {
				pauseMenu.add(savedGame);
				save();
			}
			*/
			if(e.getSource()==resumeButton) {
				paused=false;
				this.getContentPane().removeAll();
				setContentPane(gameScreen);
				repaint();
			}
			if(e.getSource()==quitButton) {
				pauseMenu.remove(resumeButton);
				//pauseMenu.remove(saveButton);
				pauseMenu.remove(quitButton);
				pauseMenu.add(yesButton);
				pauseMenu.add(noButton);
				pauseMenu.add(quitAlert);
			}
			if(e.getSource()==yesButton) {
				System.exit(0);
			}
			if(e.getSource()==noButton) {
				pauseMenu.remove(yesButton);
				pauseMenu.remove(noButton);
				pauseMenu.remove(quitAlert);
				//pauseMenu.add(saveButton);
	        	pauseMenu.add(resumeButton);
	        	pauseMenu.add(quitButton);
			}
			pauseMenu.repaint();
        	}
		}

	public static void main(String[] args) {
                gui frame = new gui();
                frame.setTitle("Summative");
                frame.setResizable(true);
                frame.setSize(600, 600);
                frame.setMinimumSize(new Dimension(600, 600));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
    }

         public void paint(Graphics g){
        	 invulTime--;
             if(!paused){
            	 gameScreen.paint(g);
             }
             Graphics2D g2 = (Graphics2D)g;
             g.setFont(new Font("Georgia", Font.BOLD, 30));
             g2.setColor(Color.WHITE);
             g2.fillRect(0, 0, 600, 600);
             g2.setColor(Color.black);
             if(firstTime==true) {
            	 mainMenu();
            	 g.drawString("Press Any Key", 200, 300);
             }
             
             paths.stream().forEach(path -> {
           		try{
                      Container jFrameContanier = this.getContentPane();
                      jFrameContanier.add(path);
                      path.move();
                      path.paintComponent(g);
          	        }catch(Exception e){
           			e.printStackTrace();
           		}
           		
           	});
             buttons.stream().forEach(button -> {
         		try{
                    Container jFrameContanier = this.getContentPane();
                    jFrameContanier.add(button);
                    button.move();
                    button.paintComponent(g);
        	        }catch(Exception e){
         			e.printStackTrace();
         		}
         		
         	});
             blocks.stream().forEach(block -> {
            		try{
                       Container jFrameContanier = this.getContentPane();
                       jFrameContanier.add(block);
                       block.move();
                       block.paintComponent(g);
           	        }catch(Exception e){
            			e.printStackTrace();
            		}
            		
            	});
             
             bullets.stream().forEach(graphics -> {
         		try{
                            Container jFrameContanier = this.getContentPane();
                            jFrameContanier.add(graphics);
                            graphics.paintComponent(g);
         		}catch(Exception e){
         			e.printStackTrace();
         		}
         	});
             
             heartC.stream().forEach(heartContainer -> {
         		try{
                            heartContainer.paintComponent(g);
         		}catch(Exception e){
                            e.printStackTrace();
         		}
         	});
             
             
             hearts.stream().forEach(hearts -> {
         		try{
                            Container jFrameContanier = this.getContentPane();
                            jFrameContanier.add(hearts);
                            hearts.paintComponent(g);
         		}catch(Exception e){
         			e.printStackTrace();
         		}
         	});
             
            count=0;
            enemys.stream().forEach(enemy -> {
            	int i2=0;
                try{
                    Container jFrameContanier = this.getContentPane();
                    jFrameContanier.add(enemy);
                    enemy.paintComponent(g);
                    if(enemy.eHealth<=0) {
                        garbageEnemys.add(count);
                        heartC.add(new heartContainer());
                    }
                    if(plr.intersects(enemy.enemy)&&invulTime<=0&&enemy.eHealth>0) {
                        plrHealth--;
                        invulTime=7;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }	
            });
             touchingPlatform=false;
             platforms.stream().forEach(platform -> {
         		try{
                    Container jFrameContanier = this.getContentPane();
                    jFrameContanier.add(platform);
                    platform.move();
                    platform.paintComponent(g);
        	        }catch(Exception e){
         			e.printStackTrace();
         		}
         		
         	});
             
             
             
             g2.setColor(Color.BLACK);
             if(plrHealth>0&&firstTime==false){
            	 g2.fill(plr);
             }
             
             if(plrHealth<=0) {
            	 g.drawString("Game Over", 200, 300);
            	 g.drawString("Enter to Return to Main Menu", 50, 400);
             }
             
             if(enemy.eHealth==0) {
            	 g.drawString("You Win", 200, 300);
             }

             for(int i=hearts.size(); i<heartNum; i++){
             	hearts.add(new heart());
             }
             while(plrHealth>heartNum){
                 plrHealth--;
             }
             
             move();
             
         }
         
         public void readSave(){
             try {
            	 BufferedReader filein = new BufferedReader(
            			 new FileReader(save));
            	 
            	 String line;
            	 line = filein.readLine();
            	 level = Integer.parseInt(line);
            	 reset();
            	 System.out.println(line);
            	 line = filein.readLine();
            	 plrHealth=Integer.parseInt(line);
            	 plr.setLocation(plrX,plrY);
            	 filein.close();
             }catch(FileNotFoundException ex) {
            	 System.out.println("Sorry there was an error reading (It's either restricted or nonexistant)");
                 ex.printStackTrace();
        	 }
             catch (IOException e)
             {
            	 
            	 System.out.println("Sorry there was an error reading");
                        e.printStackTrace();
             }
        }
         
         public void save(){
            try  {
                //Creates file - Note: Directory for path must already exist
                PrintWriter fileout = new PrintWriter(
                        new BufferedWriter(
                        new FileWriter(save)));
                                //to simply open file so that more can be added
				//you would use -> new FileWriter(filePath, true)));
                fileout.println(level);
                fileout.println(plrHealth);
                System.out.println("Saved Game");
                fileout.close();
            }catch (IOException e){
                System.out.println("Sorry there was an error writing");
                e.printStackTrace();
            }
         }
         
         public void readNames() {
        	 try {
     			BufferedReader filein = new BufferedReader(
     				                	new FileReader(files));
     		
     			String line;
     			line = filein.readLine();
                             name1 = line;
     			line = filein.readLine();
                             name2 = line;
                line = filein.readLine();
                             name3 = line;
     		
     			filein.close();
     		}
     		catch (IOException e)
     		{
     			System.out.println("Sorry there was an error reading");
     		}
         }
         
         public void updateNames() {
        	 try  {
                 //Creates file - Note: Directory for path must already exist
                 PrintWriter fileout = new PrintWriter(
                         new BufferedWriter(
                         new FileWriter(files)));
                                 //to simply open file so that more can be added
 				//you would use -> new FileWriter(filePath, true)));
                 fileout.println(name1);
                 fileout.println(name2);
                 fileout.println(name3);
                 //System.out.println("Saved Game");
                 fileout.close();
             }catch (IOException e){
                 System.out.println("Sorry there was an error writing");
             }
         }
         
         public void eraseFile() {
        	 save.delete();
        	 level=0;
         }
         
         
        public void collides(){
            moveRight=true;
            moveLeft=true;
            moveUp=true;
            moveDown=true;
            platforms.stream().forEach(platform -> {
          	try{
                    Container jFrameContanier = this.getContentPane();
                    jFrameContanier.add(platform);
                    platform.move();
                    if(plr.x-platform.getPlatformX()>0&&plr.x-platform.getPlatformX()<=platform.getWidth()&&plr.y-platform.getPlatformY()<=platform.getHeight()-1&&plr.y-platform.getPlatformY()>=-49){
                        moveLeft=false;
                    }
                    
                    if(plr.x-platform.getPlatformX()<0&&plr.x-platform.getPlatformX()>=-50&&plr.y-platform.getPlatformY()<=platform.getHeight()-1&&plr.y-platform.getPlatformY()>=-49){
                        moveRight=false;
                    }
                     
                    if(plr.y-platform.getPlatformY()>0&&plr.y-platform.getPlatformY()<=platform.getHeight()&&plr.x-platform.getPlatformX()<=platform.getWidth()-1&&plr.x-platform.getPlatformX()>=-45){
                        moveUp=false;
                    }
                    
                    if(plr.y-platform.getPlatformY()<0&&plr.y-platform.getPlatformY()>=-50&&plr.x-platform.getPlatformX()<=platform.getWidth()-1&&plr.x-platform.getPlatformX()>=-45){
                        moveDown=false;
                    }
         	    }catch(Exception e){
                        e.printStackTrace();
                    }
          	});
            blocks.stream().forEach(block -> {
              	try{
                        Container jFrameContanier = this.getContentPane();
                        jFrameContanier.add(block);
                        block.move();
                        if(plr.x-block.getPlatformX()>0&&plr.x-block.getPlatformX()<=25+25&&plr.y-block.getPlatformY()<=25+24&&plr.y-block.getPlatformY()>=-25-24){
                            moveLeft=false;
                        }
                        
                        if(plr.x-block.getPlatformX()<0&&plr.x-block.getPlatformX()>=-25-25&&plr.y-block.getPlatformY()<=25+24&&plr.y-block.getPlatformY()>=-25-24){
                            moveRight=false;
                        }
                         
                        if(plr.y-block.getPlatformY()>0&&plr.y-block.getPlatformY()<=25+25&&plr.x-block.getPlatformX()<=49&&plr.x-block.getPlatformX()>=-45){
                            moveUp=false;
                        }
                        
                        if(plr.y-block.getPlatformY()<0&&plr.y-block.getPlatformY()>=-25-25&&plr.x-block.getPlatformX()<=49&&plr.x-block.getPlatformX()>=-45){
                            moveDown=false;
                        }
             	    }catch(Exception e){
                            e.printStackTrace();
                        }
              	});
         }
        
        
        	 
}