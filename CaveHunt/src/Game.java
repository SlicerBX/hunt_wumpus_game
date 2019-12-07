
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

//i = 0; i < 8(Board size)
public class Game extends JComponent {
    private BufferedImage background,title,directions,statusBar;
    private BufferedImage holeMessage, monsterMessage, batsMessage,nothingMessage,deathMessage,winMessage;
    private BufferedImage monsterDeath, HoleDeath;
    private BufferedImage equippedMessage, notEquippedMessage;
    private ImageIcon caveBack,lookUp,lookDown,lookRight,lookLeft,spearUp,spearDown,spearRight,spearLeft;
    private ImageIcon continueIcon, moveUpIcon, moveDownIcon, moveRightIcon, moveLeftIcon, spearIcon;
    private Player player;
    private Cave cave;
    private Room[][] board;
    private boolean spearEquipped;
    private boolean titleUp;
    private boolean hasWon, hasLost;
    JButton moveUp, moveDown, moveRight, moveLeft,toggleSpear,continueButton,resetButton;

    public Game(Player player, Cave cave) {
        this.player = player;
        this.cave = cave;
        this.board = cave.getBoard();
        this.spearEquipped = false;
        this.titleUp = true;
        this.hasWon = false;
        this.hasLost = false;

        try{
            background = ImageIO.read(new File("BlackBackground960x960.jpg"));
            title = ImageIO.read(new File("GameTitleRe.jpg"));
            directions = ImageIO.read(new File("DirectionsRe.jpg"));
            statusBar = ImageIO.read(new File("StatusRe.jpg"));
            continueIcon = new ImageIcon("ContinueRe.jpg");
            caveBack = new ImageIcon("WallTest.gif");
            lookDown = new ImageIcon("FrontView.gif");
            moveUpIcon = new ImageIcon("upArrowRe.jpg");
            moveDownIcon = new ImageIcon("downArrowRe.jpg");
            moveRightIcon = new ImageIcon("rightArrowRe.jpg");
            moveLeftIcon = new ImageIcon("leftArrowRe.jpg");
            spearIcon = new ImageIcon("spearRe.jpg");
            holeMessage = ImageIO.read(new File("BreezeRe.jpg"));
            batsMessage = ImageIO.read(new File("NoisesRe.jpg"));
            monsterMessage = ImageIO.read(new File("MonsterRe.jpg"));
            nothingMessage = ImageIO.read(new File("NothingRe.jpg"));
            equippedMessage = ImageIO.read(new File("EquippedRe.jpg"));
            notEquippedMessage = ImageIO.read(new File("notEquippedRe.jpg"));
            deathMessage = ImageIO.read(new File("DiedRe.jpg"));
            winMessage = ImageIO.read(new File("WonRe.jpg"));
        } catch(Exception e) {
            System.out.println("Hey, get it right.");
        }

        cave.printCave();
        moveUp = new JButton(moveUpIcon);
        moveDown = new JButton(moveDownIcon);
        moveRight = new JButton(moveRightIcon);
        moveLeft = new JButton(moveLeftIcon);
        toggleSpear = new JButton(spearIcon);
        continueButton = new JButton(continueIcon);
        resetButton = new JButton(continueIcon);
        add(continueButton);
        continueButton.setBounds(962, 850,450,72);

        class ContinueButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                toggleTitle();
                add(moveUp);
                moveUp.setBounds(1400, 740, 60, 60);
                add(moveDown);
                moveDown.setBounds(1400, 900, 60, 60);
                add(moveRight);
                moveRight.setBounds(1480, 820, 60, 60);
                add(moveLeft);
                moveLeft.setBounds(1320, 820, 60, 60);
                add(toggleSpear);
                toggleSpear.setBounds(1400, 820, 60, 60);
                remove(continueButton);
            }
        }

        class ResetButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                hasWon = false;
                spearEquipped = false;
                hasLost = false;
                cave.possibleBoard(player, 3, 3);
                add(moveUp);
                moveUp.setBounds(1400, 740, 60, 60);
                add(moveDown);
                moveDown.setBounds(1400, 900, 60, 60);
                add(moveRight);
                moveRight.setBounds(1480, 820, 60, 60);
                add(moveLeft);
                moveLeft.setBounds(1320, 820, 60, 60);
                add(toggleSpear);
                toggleSpear.setBounds(1400, 820, 60, 60);
                remove(resetButton);
                cave.printCave();
            }
        }

        class MoveUpButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                if(spearEquipped){
                    if(fireSpearUp(player)){
                        System.out.println("You Win!");
                        hasWon = true;

                    } else{
                        System.out.println("RIP");
                        hasLost = true;
                    }
                } else {
                    moveUp(player);
                }
            }
        }

        class MoveDownButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                if(spearEquipped){
                    if(fireSpearDown(player)){
                        System.out.println("You Win!");
                        hasWon = true;

                    } else{
                        System.out.println("RIP");
                        hasLost = true;
                    }
                } else {
                    moveDown(player);
                }
            }
        }    

        class MoveRightButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                if(spearEquipped){
                    if(fireSpearRight(player)){
                        System.out.println("You Win!");
                        hasWon = true;

                    } else{
                        System.out.println("RIP");
                        hasLost = true;
                    }

                } else {
                    moveRight(player);
                }
            }
        }

        class MoveLeftButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                if(spearEquipped){
                    if(fireSpearLeft(player)){
                        System.out.println("You Win!");
                        hasWon = true;
                    } else{
                        System.out.println("RIP");
                        hasLost = true;
                    }
                } else {
                    moveLeft(player);
                }
            }
        }

        class ToggleSpearButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                toggleSpear();
            }
        }
        ActionListener ContinueButtonListener = new ContinueButtonListener();
        continueButton.addActionListener(ContinueButtonListener);
        ActionListener ResetButtonListener = new ResetButtonListener();
        resetButton.addActionListener(ResetButtonListener);

        ActionListener MoveUpButtonListener = new MoveUpButtonListener();
        moveUp.addActionListener(MoveUpButtonListener);
        ActionListener MoveDownButtonListener = new MoveDownButtonListener();
        moveDown.addActionListener(MoveDownButtonListener);
        ActionListener MoveRightButtonListener = new MoveRightButtonListener();
        moveRight.addActionListener(MoveRightButtonListener);
        ActionListener MoveLeftButtonListener = new MoveLeftButtonListener();
        moveLeft.addActionListener(MoveLeftButtonListener);
        ActionListener ToggleSpearButtonListener = new ToggleSpearButtonListener();
        toggleSpear.addActionListener(ToggleSpearButtonListener);

        System.out.println(player.getPosX() + " " + player.getPosY());
    }

    public void paintComponent (Graphics g){
        Graphics2D g2 = (Graphics2D)g;

        g2.drawImage(background, 0, 0, null);
        g2.drawImage(background, 960, 0, null);

        g2.drawImage(title, 1920/2, 0, null);
        if(titleUp){
            g2.drawImage(directions, 1920/2, 200, null);
        }
        else{
            g2.drawImage(statusBar,960,200,null);
        }

        int batsCount = cave.countBatsNearby(player);
        int holeCount = cave.countHolesNearby(player);
        int monsterCount = cave.countMonstersNearby(player);

        int statusBarY = 280;

        int y = player.getPosX()-1;
        int x = player.getPosY()-1;
        int xPixelRoom = x * 120;
        int yPixelRoom = y * 120;
        int xPixelPlayer = xPixelRoom + 47;
        int yPixelPlayer = yPixelRoom + 34;
        if(!cave.checkDeath(player)){
            caveBack.paintIcon(this, g2, xPixelRoom, yPixelRoom);
            lookDown.paintIcon(this, g2, xPixelPlayer, yPixelPlayer);
            cave.checkBats(player);
            if(hasWon){
                g2.drawImage(winMessage,960,statusBarY,null);
                gameOver();
                add(resetButton);
                resetButton.setBounds(962, 850,450,72);

            }else if(hasLost){
                g2.drawImage(deathMessage,960,statusBarY,null);
                gameOver();
                add(resetButton);
                resetButton.setBounds(962, 850,450,72);

            } else{

                for(int i = 0; i < batsCount; i++){
                    g2.drawImage(batsMessage, 960,statusBarY,null);
                    statusBarY += 100;
                }
                for(int i = 0; i < holeCount; i++){
                    g2.drawImage(holeMessage, 960,statusBarY,null);
                    statusBarY += 100;

                }
                for(int i = 0; i < monsterCount; i++){
                    g2.drawImage(monsterMessage, 960,statusBarY,null);
                    statusBarY += 100;

                }
                if(statusBarY == 280 && !titleUp){
                    g2.drawImage(nothingMessage,960,statusBarY,null);
                }
                if(!titleUp){
                    if(spearEquipped){
                        g2.drawImage(equippedMessage, 960, 700,null);
                    }
                    else{
                        g2.drawImage(notEquippedMessage, 960, 700,null);
                    }
                }
            }
        }
        else{
            gameOver();
            g2.drawImage(deathMessage,960,statusBarY,null);
        }

        g2.setColor(Color.WHITE);
        g2.drawLine(960,0,960,960);
        /*
        int xCoor = 0;
        int yCoor = 0;
        int spacing = (1920/2)/8; //Need to get board size for lengths for flexibility
        for(int i = 0; i < 9; i++) {
        xCoor = i * spacing;
        yCoor = i * spacing;
        g2.drawLine(xCoor, 0, xCoor, 960);
        g2.drawLine(0, yCoor, 960, yCoor);
        }*/
    }

    public void toggleTitle(){
        if(titleUp){
            titleUp = false;
        }
        else{
            titleUp = true;
        }
    }

    public void toggleSpear() {
        if(spearEquipped) {
            spearEquipped = false;
        }
        else {
            spearEquipped = true;
        }
    }

    public void moveUp(Player player) {
        if(!board[player.getPosX()-1][player.getPosY()].getWall()) {
            board[player.getPosX()][player.getPosY()].removePlayer();
            player.moveUp();
            board[player.getPosX()][player.getPosY()].setPlayer();
        } else {
            System.out.println("Can't move!");
        }
    }

    public void moveDown(Player player) {
        if(!board[player.getPosX()+1][player.getPosY()].getWall()) {
            board[player.getPosX()][player.getPosY()].removePlayer();
            player.moveDown();
            board[player.getPosX()][player.getPosY()].setPlayer();
        } else {
            System.out.println("Can't move!" + player.getPosX() + " " + player.getPosY());
        }
    }

    public void moveRight(Player player) {
        if(!board[player.getPosX()][player.getPosY()+1].getWall()) {
            board[player.getPosX()][player.getPosY()].removePlayer();
            player.moveRight();
            board[player.getPosX()][player.getPosY()].setPlayer();
        } else {
            System.out.println("Can't move!" + player.getPosX() + " " + player.getPosY());
        }
    }

    public void moveLeft(Player player) {	
        if(!board[player.getPosX()][player.getPosY()-1].getWall()) {
            board[player.getPosX()][player.getPosY()].removePlayer();
            player.moveLeft();
            board[player.getPosX()][player.getPosY()].setPlayer();
        }	
    }

    public boolean fireSpearUp(Player player) {
        gameOver();
        if(board[player.getPosX()-1][player.getPosY()].getMonster()) { 
            return true;
        } else {
            return false;
        }

    }

    public boolean fireSpearDown(Player player) {
        gameOver();
        if(board[player.getPosX()+1][player.getPosY()].getMonster()) { 
            return true;
        } else {
            return false;
        }
    }

    public boolean fireSpearLeft(Player player) {
        gameOver();
        if(board[player.getPosX()][player.getPosY()-1].getMonster()) { 
            return true;
        } else {
            return false;
        }
    }

    public boolean fireSpearRight(Player player) {
        gameOver();
        if(board[player.getPosX()][player.getPosY()+1].getMonster()) { 
            return true;
        } else {
            return false;
        }
    }

    public void gameOver(){
        remove(moveUp);
        remove(moveDown);
        remove(moveLeft);
        remove(moveRight);
        remove(toggleSpear);
        add(resetButton);
        resetButton.setBounds(962, 850,450,72);

    }

    public static void main(String[] args) {
        int length = 1920; //Optimal size: 1840
        int width = length/2;
        JFrame frame = new JFrame();
        frame.setSize(length, width);
        frame.setTitle("Test.");

        Player player = new Player();
        Cave cave = new Cave(8);
        cave.possibleBoard(player, 3, 3);

        Game test = new Game(player, cave);
        frame.add(test);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
