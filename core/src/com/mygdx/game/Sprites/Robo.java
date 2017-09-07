package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.states.PlayState;

/**
 * Created by Ayrin on 7/26/2016.
 */
public class Robo
{
    private static final int GRAVITY = -27;
    private  static final int RUNING=130;
    private Vector3 position; //position of x,Y and z axis
    private Vector3 velocity;
    private Texture robo;
    private Animation roboAnimation;
    private Rectangle bounds;
    private Sound jmp;



    public Robo(int x, int y)
    {
        position = new Vector3(x,y,0);    //we do not need the z-axis
        velocity = new Vector3(0, 0, 0);

        robo = new Texture("robo220.png");
        Texture robo=new Texture("robo220.png");
        roboAnimation = new Animation(new TextureRegion(robo), 4, 0.5f);


        bounds = new Rectangle(x,y,robo.getWidth()/3,robo.getHeight());
        jmp = Gdx.audio.newSound(Gdx.files.internal("jump.ogg"));
    }

    public void update(float dt) //dt stands for delta time
    {
        roboAnimation.update(dt);

        if (position.y > 0) // if position is grater than 0 then we can add gravity
            velocity.add(0, GRAVITY, 0); //add gravity to y-axis
        velocity.scl(dt);            //scale velocity by changing the time
        position.add(RUNING * dt, velocity.y, 0);
        if (position.y < 22)
            position.y=22; //wants to show if robot hits the ground it cannot go up again


        velocity.scl(1/dt);//it can speed up on the next frame

        bounds.setPosition(position.x, position.y);


    }

    public TextureRegion getTexture() {
        return roboAnimation.getFrame();
    }

    public Vector3 getPosition() {
        return position;
    }

    public void jump()
    {
        velocity.y = 180;  //in order to go up in y-axis
        jmp.play(0.3f);
    }



    public Rectangle getBounds (){

        return bounds;
    }

    public void dispose() {
        robo.dispose();
        jmp.dispose();
    }


}
