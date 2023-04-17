package com.mygdx.game.Battle.Events;

public abstract class BattleEvent {
    private BattleEventPlayer player;

    public void begin(BattleEventPlayer player){
        this.player = player;
    }

    public abstract void update(float dt);

    public abstract boolean finished();

    protected BattleEventPlayer getPlayer(){
        return player;
    }
}
