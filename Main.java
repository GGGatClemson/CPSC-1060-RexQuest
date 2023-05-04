/*
Gordon Gregory
CSPC 1060
May 2
Main.java
https://github.com/GGGatClemson/RexQuest
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.lang.Thread;

public class Main {


    /**
     * Just the intro info
     */
    public static void startPrint(){
        System.out.println("Welcome to Rex Quest\nA semirogue-like tower climber\nCollect 3 keys per floor and then head up the stairs"+
                "\n the game has no end just high scores. You will lose a heart if an enemy touches you"+
                "\nwhen you quit playing the game will print your map seeds and scores.");
    }
    /**
     * Builds the rooms and map, should move most of this to room method
     * @param level level helps determine the majority of dificulty affecting parameters
     * @return returns a hash map of rooms
     */
    public static HashMap<String, Room> genMap(int level, Random rand){
        int minWidth = 5;
        int maxWidth = 30;
        int minHeight = 5;
        int maxHeight = 10;
        int maxRooms = 7;
        int minRooms = 3;
        int height;
        int width;
        boolean hasChest;
        int chestCount = 0;
        HashMap<String, Room> bigMap = new HashMap<String, Room>();
        switch(level){
            case 1:
                for(int e = 0; e < (rand.nextInt(maxRooms-minRooms)+minRooms+level); e++) {
                    height = rand.nextInt(maxHeight-minHeight) + minHeight;
                    width = rand.nextInt(maxWidth-minWidth) + minWidth;
                    int entrance = rand.nextInt(width - 2) + 1;
                    int exit = rand.nextInt(width - 2) + 1;
                    ArrayList<ArrayList<String>> aRoom = new ArrayList<ArrayList<String>>();
                    for (int i = 0; i < height+2; i++) {
                        ArrayList<String> roomLine = new ArrayList<String>();
                        for (int j = 0; j < width + 2; j++) {
                            if(i == 0){
                                if(j == exit){
                                    roomLine.add("   ");
                                } else {
                                    roomLine.add("@@@");
                                }
                            }else if(i == height+1){
                                if(j == entrance){
                                    roomLine.add("   ");
                                } else {
                                    roomLine.add("@@@");
                                }
                            }else if (j == 0) {
                                roomLine.add("@@@");
                            } else if (j == width + 1) {
                                roomLine.add("@@@");
                            } else {
                                roomLine.add("___");
                            }
                        }
                        aRoom.add(roomLine);
                    }
                    Room room = new Room("Room"+e,aRoom, entrance, exit, height, width, level);
                    bigMap.put(room.getRoomName(),room);
                    }
                ArrayList<Integer> chestAva = new ArrayList<Integer>();
                for(int i = 0; i < bigMap.size(); i++){
                    chestAva.add(i);
                }
                while(chestCount < 3){
                    int i = rand.nextInt(bigMap.size() - 1);
                    if(chestAva.contains(i)){
                        chestAva.remove(Integer.valueOf(i));
                        bigMap.get("Room" + i).setChest(rand.nextInt(bigMap.get("Room"+i).getWidth()-2)+1,(bigMap.get("Room" + i).getHeight()-2)+1);
                        chestCount++;
                    }
                }
                bigMap.get("Room"+rand.nextInt(bigMap.size()-1)).setStairsPos();
                break;
            case 2:
                break;
            case 3:
                break;
        }
        return bigMap;
    }

    /**
     * Prints all the generated maps, used for debuging
     * @param map takes the hash map that contains all the rooms
     */
    public static void printAllMap(HashMap<String, Room> map){
        for (String room : map.keySet()){
            System.out.println(room);
            for(ArrayList<String> line : map.get(room).getLayout()){
                for(String i : line){
                    System.out.print(i);
                }
                System.out.println();
            }
        }

    }

    /**
     * Prints the individual room, this basic just takes player pos and is used when enemys are dead
     * @param room room object to print
     * @param playerPos player position as int[2]
     */
    public static void printRoom(Room room, int[] playerPos){
        int j = 0;
        for(ArrayList<String> line : room.getLayout()){
            for(int e = 0; e < line.size(); e++){
                if(playerPos[0] == j && playerPos[1] == e){
                    System.out.print("REX");
                }else if(room.getStairsPos()[0] == j && room.getStairsPos()[1] == e && room.getStairs()) {
                System.out.print("|||");
                } else if(room.getChestPos()[0] == j && room.getChestPos()[1] == e && room.getHasChest() && room.getHasChest()) {
                    System.out.print("CHE");
                }else {
                    System.out.print(line.get(e));
                }
            }
            System.out.println();
            j++;
        }
    }

    /**
     *      * Prints the individual room,  is used when enemys are alive
     * @param room room object to be printed
     * @param playerPos player position as int[2]
     * @param enemyPos enemy position as int[2]
     */
    public static void printRoom(Room room, int[] playerPos, int[] enemyPos){
        int j = 0;
        for(ArrayList<String> line : room.getLayout()){
            for(int e = 0; e < line.size(); e++){
                if(playerPos[0] == j && playerPos[1] == e){
                    System.out.print("REX");
                }else if(room.getStairsPos()[0] == j && room.getStairsPos()[1] == e && room.getStairs()) {
                System.out.print("|||");
            } else if(enemyPos[0] == j && enemyPos[1] == e) {
                    System.out.print("ENE");
                }else if(room.getChestPos()[0] == j && room.getChestPos()[1] == e&& room.getHasChest()) {
                        System.out.print("CHE");
                }else{
                        System.out.print(line.get(e));
                }
            }
            System.out.println();
            j++;
        }
    }

    /**
     * Prints the individual room,  is used when 2 enemy's are alive
     * @param room room object to be printed
     * @param playerPos player position as int[2]
     * @param enemyPos enemy position as int[2]
     * @param enemyPos2 enemy position as int[2]
     */
    public static void printRoom(Room room, int[] playerPos, int[] enemyPos, int[] enemyPos2){
        int j = 0;
        for(ArrayList<String> line : room.getLayout()){
            for(int e = 0; e < line.size(); e++){
                if(playerPos[0] == j && playerPos[1] == e){
                    System.out.print("REX");
                }else if(room.getStairsPos()[0] == j && room.getStairsPos()[1] == e && room.getStairs()) {
                    System.out.print("|||");
                } if(room.getChestPos()[0] == j && room.getChestPos()[1] == e && room.getHasChest()) {
                    System.out.print("CHE");
                }else if(enemyPos[0] == j && enemyPos[1] == e) {
                    System.out.print("ENE");
                }else if(enemyPos2[0] == j && enemyPos2[1] == e) {
                    System.out.print("ENE");
                }else{
                    System.out.print(line.get(e));
                }
            }
            System.out.println();
            j++;
        }
    }

    /**
     * Stops players from getting in the wall or off the map, starts by checking if there at door or stairs then fixes
     * @param room room to be checked aginst
     * @param playerPos player pos as int[2]
     * @param chestPos chest pos as int[2]
     * @param stairsPos stairs pos as int[2]
     * @return returns an int dictating next steps, maybe should all be done here...
     */
    public static int verifyPlayerPos(Room room, int[] playerPos, int[] chestPos, int[] stairsPos) {
        if(playerPos[0] == room.getHeight()+1 && playerPos[1]== room.getEntrance()){
            return -1;
        } else if(playerPos[0] == 0 && playerPos[1] == room.getExit()){
            return 1;
        }
        if(playerPos[0] == chestPos[0] && playerPos[1] == chestPos[1]){
            return 2;
        }
        if(playerPos[0] == stairsPos[0] && playerPos[1] == stairsPos[1]){
            return 3;
        }
        if (playerPos[0] > room.getHeight()) {
            playerPos[0] = room.getHeight();
        } else if (playerPos[0] < 1){
            playerPos[0] = 1;
        }else if (playerPos[1] > room.getWidth()) {
            playerPos[1] = room.getWidth();
        } else if (playerPos[1] < 1){
            playerPos[1] = 1;
        }
        return 0;
    }

    /**
     * Used to keep enemys in the map and test if they hit a player
     * @param room room to be checked
     * @param playerPos player pos as int[2]
     * @param enemyPos enemy pos as int[2]
     * @return returns and int for hit detection
     */
    public static int verifyEnemyPos(Room room, int[] playerPos, int[] enemyPos) {
        if(enemyPos[0] == playerPos[0] && enemyPos[1]== playerPos[1]){
            return 1;
        }
        if (enemyPos[0] > room.getHeight()) {
            enemyPos[0] = room.getHeight();
        } else if (enemyPos[0] < 1){
            enemyPos[0] = 1;
        }else if (enemyPos[1] > room.getWidth()) {
            enemyPos[1] = room.getWidth();
        } else if (enemyPos[1] < 1){
            enemyPos[1] = 1;
        }
        return 0;
    }
    public static void main(String[] args) {

        //Starts the game, creates classes used entire time
        startPrint();
        Scanner input = new Scanner(System.in);
        DateTimeFormatter time = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        FileOutputs myFile = new FileOutputs("RexQuest" + time.format(now));

        //While loop to keep the game running until you don't wanna play anymore
        boolean play = true;
        while(play) {
            Player player = new Player();
            new KeyInput(player);
            //rand used to create a seed thats easy to print
            Random rand1 = new Random(100000);
            int seed;
            int score = 0;
            int clearCounter = 0;
            //Allows for players to choose their seed.
            while (true) {
                System.out.println("Would you like to input a seed?\n'Yes' or 'No'");
                if (input.nextLine().toLowerCase().equals("yes")) {
                    System.out.println("Input the seed now,");
                    try {
                        seed = Integer.parseInt(input.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Please input a number or don't use a seed.");
                    }
                } else {
                    System.out.println("Starting a new Adventure!!!");
                    seed = rand1.nextInt(99999);
                    break;
                }
            }
            Random rand = new Random(seed);
            //This keeps the player in a single game repeating each time they move up the stairs
            do {
                HashMap<String, Room> currMap = new HashMap<String, Room>();
                currMap = genMap(1, rand);
                player.setInitialPos(currMap.get("Room0"));
                int roomNum = 0;
                int moveRoom;
                int enemyTouch1 = 0;
                int enemyTouch2 = 0;
                String roomNumString = "Room" + roomNum;
                //intialy prints the room depeding on whats in it
                if (currMap.get(roomNumString).getEnemyCount() == 1) {
                    if (currMap.get(roomNumString).getEnemy1().isAlive()) {
                        printRoom(currMap.get(roomNumString), player.getPlayerPos(), currMap.get(roomNumString).getEnemy1().getEnemyPos());
                    } else {
                        printRoom(currMap.get(roomNumString), player.getPlayerPos());
                    }
                } else {
                    if (currMap.get(roomNumString).getEnemy1().isAlive() && currMap.get(roomNumString).getEnemy2().isAlive()) {
                        printRoom(currMap.get(roomNumString), player.getPlayerPos(), currMap.get(roomNumString).getEnemy1().getEnemyPos(), currMap.get(roomNumString).getEnemy2().getEnemyPos());
                    } else if (currMap.get(roomNumString).getEnemy1().isAlive()) {
                        printRoom(currMap.get(roomNumString), player.getPlayerPos(), currMap.get(roomNumString).getEnemy1().getEnemyPos());
                    } else if (currMap.get(roomNumString).getEnemy2().isAlive()) {
                        printRoom(currMap.get(roomNumString), player.getPlayerPos(), currMap.get(roomNumString).getEnemy2().getEnemyPos());
                    } else {
                        printRoom(currMap.get(roomNumString), player.getPlayerPos());
                    }
                }
                //Handles the single level and all rooms stays in till player goes up stairs or dies
                int holdLevel = 1;
                while (holdLevel == 1 && player.getHealth() != 0) {
                    while (player.getMoved()) {
                        moveRoom = verifyPlayerPos(currMap.get(roomNumString), player.getPlayerPos(), currMap.get(roomNumString).getChestPos(), currMap.get(roomNumString).getStairsPos());
                        if (currMap.get(roomNumString).getEnemyCount() == 1) {
                            if (currMap.get(roomNumString).getEnemy1().isAlive()) {
                                currMap.get(roomNumString).getEnemy1().moveEnemy(player.getPlayerPos());
                                enemyTouch1 = verifyEnemyPos(currMap.get(roomNumString), player.getPlayerPos(), currMap.get(roomNumString).getEnemy1().getEnemyPos());
                            }
                        } else {
                            if (currMap.get(roomNumString).getEnemy1().isAlive()) {
                                currMap.get(roomNumString).getEnemy1().moveEnemy(player.getPlayerPos());
                                enemyTouch1 = verifyEnemyPos(currMap.get(roomNumString), player.getPlayerPos(), currMap.get(roomNumString).getEnemy1().getEnemyPos());
                            }
                            if (currMap.get(roomNumString).getEnemy2().isAlive()) {
                                currMap.get(roomNumString).getEnemy2().moveEnemy(player.getPlayerPos());
                                enemyTouch2 = verifyEnemyPos(currMap.get(roomNumString), player.getPlayerPos(), currMap.get(roomNumString).getEnemy2().getEnemyPos());
                            }
                        }
                        //Enemy hit detectoin
                        if (enemyTouch1 == 1) {
                            player.damage();
                            currMap.get(roomNumString).getEnemy1().killEnemy();
                            enemyTouch1 = 0;
                        }
                        if (enemyTouch2 == 1) {
                            player.damage();
                            currMap.get(roomNumString).getEnemy2().killEnemy();
                            enemyTouch2 = 0;
                        }

                        //player goes upstairs
                        if (moveRoom == 3) {
                            if (player.useKey()) {
                                holdLevel = 0;
                                clearCounter++;
                            } else {
                                moveRoom = 0;
                            }
                        }
                        //gets keys and disapears chests
                        if (moveRoom == 2) {
                            player.attainKeyFrags(currMap.get(roomNumString));
                            currMap.get(roomNumString).openChest();
                            player.keyComplete();
                            moveRoom = 0;
                        }
                        //moves rooms
                        if (moveRoom == 0) {
                            if (currMap.get(roomNumString).getEnemyCount() == 1) {
                                if (currMap.get(roomNumString).getEnemy1().isAlive()) {
                                    printRoom(currMap.get(roomNumString), player.getPlayerPos(), currMap.get(roomNumString).getEnemy1().getEnemyPos());
                                } else {
                                    printRoom(currMap.get(roomNumString), player.getPlayerPos());
                                }
                            } else {
                                if (currMap.get(roomNumString).getEnemy1().isAlive() && currMap.get(roomNumString).getEnemy2().isAlive()) {
                                    printRoom(currMap.get(roomNumString), player.getPlayerPos(), currMap.get(roomNumString).getEnemy1().getEnemyPos(), currMap.get(roomNumString).getEnemy2().getEnemyPos());
                                } else if (currMap.get(roomNumString).getEnemy1().isAlive()) {
                                    printRoom(currMap.get(roomNumString), player.getPlayerPos(), currMap.get(roomNumString).getEnemy1().getEnemyPos());
                                } else if (currMap.get(roomNumString).getEnemy2().isAlive()) {
                                    printRoom(currMap.get(roomNumString), player.getPlayerPos(), currMap.get(roomNumString).getEnemy2().getEnemyPos());
                                } else {
                                    printRoom(currMap.get(roomNumString), player.getPlayerPos());
                                }
                            }
                        } else {
                            if (roomNum == 0 && moveRoom == -1) {
                                roomNumString = "Room" + (currMap.size() - 1);
                                roomNum = currMap.size() - 1;
                                player.exitPlayerPos(currMap.get(roomNumString));
                            } else if (roomNum == currMap.size() - 1 && moveRoom == 1) {
                                roomNumString = "Room" + 0;
                                roomNum = 0;
                                player.entrancePlayerPos(currMap.get(roomNumString));
                            } else {
                                roomNum += moveRoom;
                                roomNumString = "Room" + roomNum;
                                if (player.getPlayerPos()[0] == 0) {
                                    player.entrancePlayerPos(currMap.get(roomNumString));
                                } else {
                                    player.exitPlayerPos(currMap.get(roomNumString));
                                }
                            }
                            if (currMap.get(roomNumString).getEnemyCount() == 1) {
                                if (currMap.get(roomNumString).getEnemy1().isAlive()) {
                                    printRoom(currMap.get(roomNumString), player.getPlayerPos(), currMap.get(roomNumString).getEnemy1().getEnemyPos());
                                } else {
                                    printRoom(currMap.get(roomNumString), player.getPlayerPos());
                                }
                            } else {
                                if (currMap.get(roomNumString).getEnemy1().isAlive() && currMap.get(roomNumString).getEnemy2().isAlive()) {
                                    printRoom(currMap.get(roomNumString), player.getPlayerPos(), currMap.get(roomNumString).getEnemy1().getEnemyPos(), currMap.get(roomNumString).getEnemy2().getEnemyPos());
                                } else if (currMap.get(roomNumString).getEnemy1().isAlive()) {
                                    printRoom(currMap.get(roomNumString), player.getPlayerPos(), currMap.get(roomNumString).getEnemy1().getEnemyPos());
                                } else if (currMap.get(roomNumString).getEnemy2().isAlive()) {
                                    printRoom(currMap.get(roomNumString), player.getPlayerPos(), currMap.get(roomNumString).getEnemy2().getEnemyPos());
                                } else {
                                    printRoom(currMap.get(roomNumString), player.getPlayerPos());
                                }
                            }
                        }
                    }
                    //this is to slow the loop down so it actually takes inputs.....  I couddnt find a better way
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        System.out.println("baddd");
                    }
                    //calculates score each time the player moves.... maybe over kill
                    score += (player.getBossKeyFrags() * 100) + (clearCounter * 500);
                }
            //conditons for do while
            } while (player.getHealth() != 0);
            System.out.println("Would you like to play again?\nYes or No");
            if(input.nextLine().toLowerCase().equals("no")){
                play = false;
            }
            //uses fileoutput to print info
            myFile.addPlay(score, seed);
        }
        myFile.printFile();
        //ends teh game
        System.exit(0);
    }
}
