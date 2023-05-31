package com.alexgs.tfg_game.scr.ui_scr;

import com.alexgs.tfg_game.MyGdxGame;
import com.alexgs.tfg_game.managers.ResourceManager;

public class LoadScreen extends BScreen {
    private float loadDelay = 5;
    private float loadCount = 0;

    public LoadScreen(MyGdxGame game) {

        super(game);
        // this.resourceManager=new ResourceManager();
        // game.resourceManager=this.resourceManager;

        ResourceManager.loadAllResources();
        // while(!ResourceManager.update()){}

    }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
        super.render(delta);
        if (ResourceManager.update()) {

            ResourceManager.botones();
            ResourceManager.hudFont();
            ResourceManager.screenFont();
            game.setScreen(new TitleScreen(game));

        }

    }

}
