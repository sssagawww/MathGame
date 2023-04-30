package com.mygdx.game.battle;

import com.mygdx.game.battle.events.B_TextEvent;
import com.mygdx.game.battle.events.BattleEvent;
import com.mygdx.game.battle.events.BattleEventPlayer;
import com.mygdx.game.battle.events.BattleEventQueue;
import com.mygdx.game.battle.steps.Step;
import com.mygdx.game.entities.BattleEntity;
import com.mygdx.game.entities.Player;

public class Battle implements BattleEventQueue {
    public enum STATE{
        READY_TO_PROGRESS,
        WIN,
        LOSE,
        RUN,
        ;
    }
    private STATE state;
    private BattleEventPlayer eventPlayer;
    private BattleEntity player;
    private BattleEntity enemy;
    private BattleMechanics mechanics;

    public Battle(BattleEntity player, BattleEntity enemy){
        this.player = player;
        this.enemy = enemy;
        mechanics = new BattleMechanics();
        this.state = STATE.READY_TO_PROGRESS;
    }

    public void beginBattle(){
        queueEvent(new B_TextEvent("хехехехехех!", 1f));
    }

    public void progress(int input){
        if(state != STATE.READY_TO_PROGRESS){
            return;
        }
        if(mechanics.isFirst(player, enemy)){
            playTurn(ENTITY_LIST.PLAYER, input);
            if (state == STATE.READY_TO_PROGRESS) {
                playTurn(ENTITY_LIST.ENEMY, 0);
            }
        }
    }

    private void playTurn(ENTITY_LIST entity, int input){
        ENTITY_LIST list = ENTITY_LIST.getEntities(entity);
        BattleEntity battleUser = null;
        BattleEntity battleTarget = null;

        if (entity == ENTITY_LIST.PLAYER) {
            battleUser = player;
            battleTarget = enemy;
        } else if (entity == ENTITY_LIST.ENEMY) {
            battleUser = enemy;
            battleTarget = player;
        }

        Step step = battleUser.getSteps(input);

        queueEvent(new B_TextEvent(battleUser.getName()+" used\n"+step.getName()+"!", 0.5f));

        if(mechanics.attemptHit(step, battleUser, battleTarget)){
            step.useMove(mechanics, battleUser, battleTarget, this);
        }
    }

    public void playerRun(){
        queueEvent(new B_TextEvent("Убежал...", true));
        this.state = STATE.RUN;
    }

    public BattleEntity getPlayer() {
        return player;
    }

    public BattleEntity getEnemy() {
        return enemy;
    }

    public STATE getState() {
        return state;
    }

    public BattleEventPlayer getEventPlayer() {
        return eventPlayer;
    }

    public void setEventPlayer(BattleEventPlayer player) {
        this.eventPlayer = player;
    }

    @Override
    public void queueEvent(BattleEvent event) {
        eventPlayer.queueEvent(event);
    }
}
