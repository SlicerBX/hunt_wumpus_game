 

import java.util.Random;

public class Cave {

    public Room[][] board;
    public Random rand;
    
    public Cave(int side) {
        board = new Room[side+2][side+2];
        for(int i = 0; i < side+2; i++) {
            for(int j = 0; j < side+2; j++) {
                board[i][j] = new Room(i,j);
            }
        }
        
        rand = new Random();
    }
    
    public Room[][] getBoard() {
        return board;
    }
    
    public void placeHoles(int amount){
        for(int i = 0; i < amount; i++){
            int x = rand.nextInt(board.length-2)+1;//1-board.length -1
            int y = rand.nextInt(board.length-2)+1;
            if(board[x][y].isEmpty()) {
                board[x][y].setHole();
            }
            else {
                i--;
            }
        }
    }
    
    public void placeBats(int amount){
        for(int i = 0; i < amount; i++){
            int x = rand.nextInt(board.length-2)+1;
            int y = rand.nextInt(board.length-2)+1;
            if(board[x][y].isEmpty()) {
                board[x][y].setBats();
            }
            else {
                i--;
            }
        }
    }
    
    public boolean placePlayer(Player player) {
        int x = rand.nextInt(board.length - 4) + 2;
        int y = rand.nextInt(board.length - 4) + 2;
        if(checkSpawn(x, y)) {
            board[x][y].setPlayer();
            player.setPosX(x); // 
            player.setPosY(y); //
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean checkSpawn(int x, int y) {
        int count = 0;
        if(checkAdjacent(x,y)){            
            if(checkAdjacent(x,y-1))
                count++;
            if(checkAdjacent(x,y+1))
                count++;
            if(checkAdjacent(x-1,y))
                count++;
            if(checkAdjacent(x+1,y))
                count++;
            if(count > 0)
                return true;
        }
        return false;
    }
    
    public boolean checkAdjacent(int x, int y){
        if(board[x][y].isEmpty() && board[x-1][y].isEmpty() && board[x+1][y].isEmpty() && board[x][y-1].isEmpty() && board[x][y+1].isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    
    public void possibleBoard(Player player, int holes, int bats) {
        randomizeBoard(holes, bats);
        boolean foundGame = false;
        while (foundGame == false){
         if(placePlayer(player)){
             foundGame = true;
            }
            else{
            clearBoard();
            randomizeBoard(holes,bats);
        }
       }
    }
    
    public void placeMonster() {
        int x = rand.nextInt(board.length-2)+1;
        int y = rand.nextInt(board.length-2)+1;
        if(board[x][y].isEmpty()) {
            board[x][y].setMonster();
        }
        else {
            placeMonster();
        }
    }
    
    public void randomizeBoard(int holes, int bats){
        placeWalls();
        placeHoles(holes);
        placeBats(bats);
        placeMonster();
    }
    
    public boolean checkDeath(Player player) {
        return (board[player.getPosX()][player.getPosY()].checkDeath());
    }
    
    public void checkBats(Player player) {
        if(board[player.getPosX()][player.getPosY()].checkBats()) {
             board[player.getPosX()][player.getPosY()].removePlayer();
             int x = rand.nextInt(board.length - 2) + 1;
             int y = rand.nextInt(board.length - 2) + 1;
             player.setPosX(x);
             player.setPosY(y);
             board[x][y].setPlayer();
             checkDeath(player);
        }
    }
    
    public void printCave(){
        for(int i = 0; i < board.length; i++) {
            System.out.println("");
            for(int j = 0; j < board.length; j++) {
                if(board[i][j].isEmpty())
                    System.out.print("x");
                if(board[i][j].getPlayer())
                    System.out.print("P");
                if(board[i][j].getMonster())
                    System.out.print("M");
                if(board[i][j].getBats())
                    System.out.print("B");
                if(board[i][j].getHole())
                    System.out.print("H");
                if(board[i][j].getWall())
                    System.out.print("+");
            }
        }
        System.out.println("");
    }
    
    public void placeWalls(){
        for(int i = 0; i < board.length; i++) {
            board[0][i].setWall();
            board[board.length-1][i].setWall();
            board[i][0].setWall();
            board[i][board.length-1].setWall();
        }
    }
       
    public void clearBoard(){
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                board[i][j] = new Room(i,j);
            }
        }
    }
    
    public int countBatsNearby(Player player){
        int count = 0;
        int x = player.getPosX();
        int y = player.getPosY();
        if(board[x-1][y].getBats())
            count++;
        if(board[x+1][y].getBats())
            count++;
        if(board[x][y-1].getBats())
            count++;
        if(board[x][y+1].getBats())
            count++;
        return count;
    }
    
    public int countHolesNearby(Player player){
        int count = 0;
        int x = player.getPosX();
        int y = player.getPosY();
        if(board[x-1][y].getHole())
            count++;
        if(board[x+1][y].getHole())
            count++;
        if(board[x][y-1].getHole())
            count++;
        if(board[x][y+1].getHole())
            count++;
        return count;
    }
    
    public int countMonstersNearby(Player player){
        int count = 0;
        int x = player.getPosX();
        int y = player.getPosY();
        if(board[x-1][y].getMonster())
            count++;
        if(board[x+1][y].getMonster())
            count++;
        if(board[x][y-1].getMonster())
            count++;
        if(board[x][y+1].getMonster())
            count++;
        return count;
    }
    
}
