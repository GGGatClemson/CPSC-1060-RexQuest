/*
Gordon Gregory
CSPC 1060
May 2
Enemy.java
 */

import java.util.Random;

/**
 * Creates and dictates enemy class
 */
public class Enemy {

    //enemy stats
    private int moveSpeed;

    private boolean alive;

    private int enemyLevel;

    private int[] enemyPos = new int[2];

    public int[] getEnemyPos(){
        return enemyPos;
    }

    public boolean isAlive() {
        return alive;
    }

    public void killEnemy(){
        alive = false;
    }
    /**
     * This moves the enemy, I was working on better dection for player pos but I think this is fine for 1060...
     * @param playerPos player position for finding next enemy move.
     * @return an int to explain what happened.
     */
    public void moveEnemy(int[] playerPos){
        int i = moveSpeed;
        int y = enemyPos[0] - playerPos[0];
        int x = enemyPos[1] - playerPos[1];
        int yDir;
        int xDir;
        if(y > 0){
            yDir = -1*enemyLevel;
        } else{
            yDir = enemyLevel;
        }
        if(x > 0){
            xDir = -1*enemyLevel;
        } else{
            xDir = enemyLevel;
        }
        Random rand = new Random(System.currentTimeMillis());
        if(x == 0 && y == 0){
            return;
        }else if(x == 0){
            enemyPos[0] = enemyPos[0]+yDir;
        }else if(y == 0){
            enemyPos[1] = enemyPos[1]+xDir;
        }else if((Math.abs(y) <= enemyLevel) && (Math.abs(x) <= enemyLevel)){
            if(rand.nextBoolean()){
                enemyPos[0] = enemyPos[0]+yDir;
            } else {
                enemyPos[1] = enemyPos[1]+xDir;
            }
        } else if((Math.abs(y) <= enemyLevel)){
            enemyPos[1] = enemyPos[1]+xDir;
        } else if (Math.abs(x) <= enemyLevel){
            enemyPos[0] = enemyPos[0]+yDir;
        } else{
            if(rand.nextBoolean()){
                enemyPos[0] = enemyPos[0]+yDir;
            } else {
                enemyPos[1] = enemyPos[1]+xDir;
            }
        }
    }

    /**
     * Constructor for enemy
     * @param enemyLevel dictaes how fast it moves maybe more later
     * @param height dictates room hieght for starting position
     * @param width dictates room width for starting position
     */
    Enemy(int enemyLevel,int height, int width){
        Random rand = new Random(System.currentTimeMillis());
        this.moveSpeed = enemyLevel;
        this.enemyLevel = enemyLevel;
        alive = true;
        enemyPos[0] = rand.nextInt(height);
        enemyPos[1] = rand.nextInt(width);
    }
}
