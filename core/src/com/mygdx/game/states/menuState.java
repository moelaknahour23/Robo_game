package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.MyGdxGame;

/**
 * Created by mohammadlaknahour on 7/23/16.
 */
public class menuState extends State {

    
    private Texture background;
    private Texture playButton, ExitButton;




    public menuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH *1.8f, MyGdxGame.HEIGHT* 1.9f);


        background =new Texture("startCover-bigrobo.png");
        playButton =new Texture("playButton3.png");
        //ExitButton =new Texture("exit_button.png");
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
        sb.draw(background,0,0);
        sb.draw(playButton, (MyGdxGame.WIDTH / 0.9f) - (playButton.getWidth() / 2.5f), MyGdxGame.HEIGHT /2f);
        //sb.draw(ExitButton, (MyGdxGame.WIDTH / 2) - ExitButton.getWidth() / 2, MyGdxGame.HEIGHT / 2);




        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
        System.out.println("Menu state Dispose");
    }
}
