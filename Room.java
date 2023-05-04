/*
Gordon Gregory
CSPC 1060
May 2
Room.java
 */

import java.util.ArrayList;

public class Room {
    //all the basic stuff a room has
    final private int entrance;
    final private int exit;
    final private String roomName;

    final private int height;

    final private int width;

    final private int roomLevel;

    // I call the enemys in here so they keep their state when changing rooms
    private Enemy enemy1;
    private Enemy enemy2;
    private boolean stairs;
    private int[] stairsPos = new int[2];

    private int enemyCount;

    private ArrayList<ArrayList<String>> layout;
    private boolean hasChest;

    final private int[] chestPos = new int[2];

    //builds the room and sets stairs and chest position
    public boolean getStairs(){
        return stairs;
    }
    public int[] getStairsPos(){
        return stairsPos;
    }
    public void setStairsPos(){
        stairs = true;
        stairsPos[0] = height/2;
        stairsPos[1] = width+1;
    }
    public int[] getChestPos(){
        return chestPos;
    }
    public void openChest(){
        hasChest = false;
    }
    public void setChest(int x, int y){
        chestPos[0] = y;
        chestPos[1] = x;
        hasChest = true;
    }
    public boolean getHasChest(){
        return hasChest;
    }

    public int getEnemyCount(){
        return enemyCount;
    }

    public Enemy getEnemy1(){
        return enemy1;
    }
    public Enemy getEnemy2(){
        return enemy2;
    }
    public int getRoomLevel() {
        return roomLevel;
    }

    public String getRoomName(){
        return roomName;
    }

    public int getEntrance(){
        return entrance;
    }

    public int getExit(){
        return exit;
    }

    public int getHeight() { return height;}
    public int getWidth() { return width;}

    public ArrayList<ArrayList<String>> getLayout(){
        return layout;
    }

    /**
     * The real bread and butter of this program, the room holds everything together
     * @param name Gives room name, currently jsut room+number but if i add shops or somthing i would change
     * @param layout A 2d arraylist with strings that holds how the room looks
     * @param entrance sets the entrace to a room
     * @param exit sets the exit to a room
     * @param height sets room hight
     * @param width sets room width
     * @param roomLevel roomlevel is a placehodler so i can create harder rooms as level increases in future
     */
    Room(String name, ArrayList<ArrayList<String>> layout, int entrance, int exit, int height, int width, int roomLevel) {
        roomName = name;
        this.roomLevel = roomLevel;
        this.entrance = entrance;
        this.exit = exit;
        this.layout = layout;
        this.height = height;
        this.width = width;
        if((height*width)/200 == 0){
            enemyCount = 1;
            this.enemy1 = new Enemy(roomLevel, height, width);
        }else{
            enemyCount = 2;
            this.enemy1 = new Enemy(roomLevel, height, width);
            this.enemy2 = new Enemy(roomLevel, height, width);
        }

    }
}
