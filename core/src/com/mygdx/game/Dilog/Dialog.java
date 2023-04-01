package com.mygdx.game.Dilog;

import java.util.HashMap;
import java.util.Map;

public class Dialog {
    private Map<Integer, DialogueNode> nodes = new HashMap<Integer, DialogueNode>();

    public DialogueNode getNode(int id){
        return nodes.get(id);
    }
    public void addNode(DialogueNode node){
        this.nodes.put(node.getId(), node);
    }
    public int getStart(){
        return 0;
    }
}