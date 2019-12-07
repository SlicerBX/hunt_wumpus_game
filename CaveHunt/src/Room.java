

public class Room {

    private int x;
    private int y;
    private boolean hasPlayer;
    private boolean hasHole;
    private boolean hasMonster;
    private boolean hasBats;
    private boolean hasWall;
    
    public Room(int x, int y) {
        this.x = x;
        this.y = y;
        hasPlayer = false;
        hasHole = false;
        hasMonster = false;
        hasBats = false;
        hasWall = false;

    }
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setPlayer() {
        hasPlayer = true;
    }
    
    public void removePlayer() {
        hasPlayer = false;
    }
    
    public void setHole() {
        hasHole = true;
    }
    
    public void setMonster() {
        hasMonster = true;
    }
    
    public void setBats() {
        hasBats = true;
    }
    
    public void setWall(){
        hasWall = true;
    }
    
    public boolean getPlayer() {
        return hasPlayer;
    }
    
    public boolean getHole() {
        return hasHole;
    }
    
    public boolean getMonster() {
        return hasMonster;
    }
    
    public boolean getBats() {
        return hasBats;
    }
    
    public boolean getWall(){
        return hasWall;
    }
    
    public boolean isEmpty(){
        return !(getWall() || getBats() || getMonster() || getHole() || getPlayer() );
    }
    
    public boolean checkDeath() {
        return ((hasPlayer && hasHole) || (hasPlayer && hasMonster));
     }

    public boolean checkBats() {
        return (hasPlayer && hasBats);
    }

    
}
