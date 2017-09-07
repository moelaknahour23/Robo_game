package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Sprites.Obstacle;
import com.mygdx.game.Sprites.Robo;


/**
 * Created by mohammadlaknahour on 7/25/16.
 */

public class PlayState extends State {

    private static final int OBSTACLE_SPACING= 300;
    private static final int OBSTACLE_COUNT=100;


    private Robo robo;
    private Texture Background;
    private  Texture ground;
    private Vector2 groundPos1,groundPos2;
    private boolean gameOver;
    BitmapFont myBit;
    private int score;
    private float timeCount;

    private Array<Obstacle> obstacles;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        robo = new Robo(10,5);

        myBit = new BitmapFont();

        cam.setToOrtho(false, MyGdxGame.WIDTH / 1.8f, MyGdxGame.HEIGHT / 1.8f);
        Background = new Texture("background2.png");

        ground=new Texture("StoneGround.png");
        groundPos1=new Vector2(cam.position.x-cam.viewportWidth/2,0);
        groundPos2=new Vector2((cam.position.x-cam.viewportWidth/2)+ground.getWidth(),0);

        obstacles =new Array<Obstacle>();

        for(int i=1; i<OBSTACLE_COUNT;i++){
            obstacles.add(new Obstacle(i*(OBSTACLE_SPACING+Obstacle. OBSTACLE_WIDTH)));
        }

    }

    @Override
    protected void handleInput() {

        if(Gdx.input.justTouched())
            robo.jump();






      //  if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            //robo.laydown();

    }

    @Override
    public void update(float dt) {

        timeCount += dt;
        if (timeCount >= 1){
            score ++;
            timeCount = 0;
        }


        handleInput();
        robo.update(dt);
        updateGround();

        cam.position.x= robo.getPosition().x+80;


        for (int i=0; i<obstacles.size;i++){

            Obstacle obstacle=obstacles.get(i);

            if(cam.position.x-(cam.viewportHeight/2)> obstacle.getTopPosition().x+
                    obstacle.getTopBullet().getWidth()){
                obstacle.reposition(obstacle.getTopPosition().x+
                        ((Obstacle.OBSTACLE_WIDTH + OBSTACLE_SPACING) *
                                OBSTACLE_COUNT));

            }


            if(obstacle.collides(robo.getBounds()))
                gsm.set(new GameOver(gsm));

            gameOver=true;
        }




        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(Background, cam.position.x-(cam.viewportWidth/2),0);
        sb.draw(robo.getTexture(), robo.getPosition().x, robo.getPosition().y);

        myBit.setColor(10.0f,10.0f, 10.0f, 10.0f);
        myBit.draw(sb,"Score: " + String.valueOf(score),cam.position.x-(cam.viewportWidth/2),400);

        for(Obstacle obstacle:obstacles) {
            sb.draw(obstacle.getTopBullet(), obstacle.getTopPosition().x, obstacle.getTopPosition().y);
            sb.draw(obstacle.getDownBullet(), obstacle.getDownPosition().x, obstacle.getDownPosition().y);
            sb.draw(obstacle.getTrap(), obstacle.getGroundPosition().x, obstacle.getGroundPosition().y);
            sb.draw(obstacle.getMine(), obstacle.getMinePosition().x, obstacle.getMinePosition().y);



        }


        sb.draw(ground, groundPos1.x,groundPos1.y);
        sb.draw(ground, groundPos2.x,groundPos2.y);
        sb.end();



    }



    @Override
    public void dispose() {

        Background.dispose();
        robo.dispose();
        ground.dispose();

        for(Obstacle obstacle : obstacles)

            obstacle.dispose();


        System.out.println("Play state Dispose");

    }

    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }
}
