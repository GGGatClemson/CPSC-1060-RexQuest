import java.util.HashMap;

public class Player {
    private int[] playerPos = new int[2];
    private int health;
    private boolean bossKey;

    private int bossKeyFrags;

    private boolean moved;

    public boolean getMoved(){
        boolean placeHolder = moved;
        moved = false;
        return placeHolder;
    }
    public int getBossKeyFrags(){
        return bossKeyFrags;
    }

    public void attainKeyFrags(Room room){
        if(room.getHasChest()){
            bossKeyFrags++;
            System.out.println("You've found a the key fragment!!!");
        }
    }
    public void keyComplete(){
        if(bossKeyFrags == 3){
            bossKey = true;
            System.out.println("You've found all the key fragments!!!\n Head to the stairs to move up the tower.");
        }
    }

    public boolean useKey(){
        if(bossKey){
            bossKeyFrags = 0;
            System.out.println("You've ascended to the next level!\nYour journety continues...");
            return true;
        } else{
            System.out.println("You haven't completed the key to move to the next floor!");
            return false;
        }
    }

    public void healing(){
        health += 1;
    }
    public void damage(){
        health -=1;
        System.out.println("The enemy found you, you've lost a heart");
    }

    public int getHealth(){
        return health;
    }

    public int[] getPlayerPos(){
        return playerPos;
    }

    public boolean getBossKey(){
        return bossKey;
    }

    public void movePlayer(String move){
        switch (move){
            case "UP":
                playerPos[0]=playerPos[0]-1;
                //System.out.println(move);

                moved = true;
                break;
            case "DOWN":
                playerPos[0]=playerPos[0]+1;
                //System.out.println(move);

                moved = true;
                break;
            case "LEFT":
                playerPos[1]=playerPos[1]-1;
                //System.out.println(move);

                moved = true;
                break;
            case "RIGHT":
                playerPos[1]=playerPos[1]+1;
                //System.out.println(move);
                moved = true;
                break;
        }
    }

    public void entrancePlayerPos(Room room){
        playerPos[0] = room.getHeight() + 1;
        playerPos[1] = room.getEntrance();
    }
    public void exitPlayerPos(Room room){
        playerPos[0] = 0;
        playerPos[1] = room.getExit();
    }

    public void printStats(){
        if(getBossKey()){
            System.out.printf("Hearts: %d%nBoss Key Frags: Completed%n",getHealth());
        } else{
            System.out.printf("Hearts: %d%nBoss Key Frags: %d%n",getHealth(),getBossKeyFrags());

        }
    }
    public void setInitialPos(Room startRoom){
        playerPos[0] = startRoom.getHeight() + 1;
        playerPos[1] = startRoom.getEntrance();
    }
    Player(){
        health = 3;
        bossKey = false;
        bossKeyFrags = 0;
    }
}
