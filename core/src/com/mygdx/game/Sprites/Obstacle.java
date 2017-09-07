package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;

/**
 * Created by mohammadlaknahour on 7/27/16.
 */


public class Obstacle {

    private static final int FLUCTUATION=200;
    private static final int OBSTACLE_GAP=190;
    private static final int LOWEST_OPENING=30;

    public static  final int OBSTACLE_WIDTH=52;

    private Rectangle boundsTop, boundsBottom, boundsGround,mineGround;

    private Vector2 topPosition,downPosition, groundPosition, minePosition;
    private Random rand;
    private Texture topBullet, downBullet, trap,mine ;




    public Obstacle(float x){

        topBullet=new Texture("object11-100.png");
        downBullet= new Texture("object11-100.png");
        trap=new Texture("object11-100.png");
        mine=new Texture("Seamine50.png");


        rand= new Random();

        topPosition=new Vector2(x-30,rand.nextInt(FLUCTUATION) + OBSTACLE_GAP +LOWEST_OPENING-20 );
        downPosition=new Vector2(x+75,rand.nextInt(FLUCTUATION) + OBSTACLE_GAP +LOWEST_OPENING+20 );
        groundPosition= new Vector2(x+70, topPosition.y - OBSTACLE_GAP - downBullet.getHeight()+60);
        minePosition= new Vector2(x-40, topPosition.y - OBSTACLE_GAP - downBullet.getHeight()-30);


        boundsTop = new Rectangle (topPosition.x+35,topPosition.y+15,topBullet.getWidth()/2,topBullet.getHeight()/2);
        boundsBottom = new Rectangle (downPosition.x+35,downPosition.y+15,downBullet.getWidth()/2,downBullet.getHeight()/2);
        boundsGround= new Rectangle (groundPosition.x+35,groundPosition.y+15,trap.getWidth()/2,trap.getHeight()/2);
        mineGround= new Rectangle (minePosition.x+25,minePosition.y+5,mine.getWidth()/2,mine.getHeight()/2f);

    }



    public Texture getTopBullet() {
        return topBullet;
    }

    public Texture getDownBullet() {
        return downBullet;
    }

    public Texture getTrap() {
        return trap;
    }

    public Texture getMine() {
        return mine;
    }






    public Vector2 getDownPosition() {
        return downPosition;
    }


    public Vector2 getTopPosition() {
        return topPosition;
    }


    public Vector2 getGroundPosition() {
        return groundPosition;
    }

    public Vector2 getMinePosition() {
        return minePosition;
    }



    public void reposition(float x){
        topPosition.set (x-30,rand.nextInt(FLUCTUATION) + OBSTACLE_GAP +LOWEST_OPENING-20 );
        downPosition.set(x+75,rand.nextInt(FLUCTUATION) + OBSTACLE_GAP +LOWEST_OPENING+20 );
        groundPosition.set(x+70, topPosition.y - OBSTACLE_GAP - downBullet.getHeight()+60);
        minePosition.set(x+70, topPosition.y - OBSTACLE_GAP - downBullet.getHeight()-40);


        boundsTop.setPosition(topPosition.x,topPosition.y);
        boundsBottom.setPosition(downPosition.x,downPosition.y);
        boundsGround.setPosition(groundPosition.x,groundPosition.y);
        mineGround.setPosition(minePosition.x,minePosition.y);

    }

    public boolean collides (Rectangle player) {

        return player.overlaps(boundsTop) || player.overlaps(boundsBottom)
                || player.overlaps(boundsGround) || player.overlaps(mineGround);

    }

    public void dispose(){

        topBullet.dispose();
        downBullet.dispose();
        trap.dispose();
        mine.dispose();


    }


}
