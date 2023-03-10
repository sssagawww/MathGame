package com.mygdx.game.handlers;
import com.badlogic.gdx.Game;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.states.GameState;
import com.mygdx.game.states.Play;

import java.util.Stack;

public class GameStateManager {
    private MyGdxGame game;
    private Stack<GameState> gameStates;
    public static final int PLAY = 912837;

    public GameStateManager(MyGdxGame game) {
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(PLAY);
    }

    public MyGdxGame game(){
        return game;
    }

    public void update(float dt){
        gameStates.peek().update(dt);
    }
    public void render(){
        gameStates.peek().render();
    }

    private GameState getState(int state){
        if (state == PLAY) {
            return new Play(this);
        }
        return null;
    }
    public void setState(int state){
        popState();
        pushState(state);
    }
    public void popState() {
        GameState gs = gameStates.pop();
        gs.dispose();
    }
    public void pushState(int state) {
        gameStates.push(getState(state));
    }

}