package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

/**
 * Created by mohammadlaknahour on 7/23/16.
 */
public class GameOver extends State {
    private Texture background1;
    private Texture gameOver;

    public GameOver(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH *2, MyGdxGame.HEIGHT *2);


        background1 =new Texture(“bg.png");
        gameOver =new Texture("gameOver2.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background1,0,0);
        sb.draw(gameOver,cam.position.x - gameOver.getWidth()/2,
        cam.position.y);
        sb.end();

    }

    @Override
    public void dispose() {
        background1.dispose();
        gameOver.dispose();
        System.out.println("Game Over");
    }
}
