package com.mygdx.game.Dilog;

import java.util.List;

public class DialogueGo {
    private Dialog dialogue;
    private DialogueNode currentNode;

    public DialogueGo(Dialog dialogue){
        this.dialogue = dialogue;
        currentNode = dialogue.getNode(dialogue.getStart());
    }
    public DialogueNode getNextNode(int pointerIndex){
        DialogueNode nextNode = dialogue.getNode(currentNode.getPointers().get(pointerIndex));
        currentNode = nextNode;
        return nextNode;
    }
    public List<String> getOptions(){
        return currentNode.getLabels();
    }
    public String getText(){
        return currentNode.getText();
    }
    public DialogueNode.NODE_TYPE getType(){
        return currentNode.getType();
    }
}