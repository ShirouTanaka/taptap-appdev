package com.badlogic.drop.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;

    //essentially we are trying to create layers called game states so its like a pause state will be on
    //top of the actual game state but when we continue we are gonna pop the pause state and have the
    //actual game state continue thus we are going to use a stack
    public GameStateManager(){
        states = new Stack<State>();
    }

    //push and pop are the standard for a stack; data structures
    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop().dispose();
    }

    //this one will do the 2 jobs
    public void set(State state){
        states.pop();
        states.push(state);
    }

    //look at the top of the stack
    public void update(float deltaTime){
        states.peek().update(deltaTime);
    }

    //we have to render the current topmost state onto the screen
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }


}