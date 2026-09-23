package game.entidades;
import java.awt.Color;
import java.awt.Graphics;

import game.Pong;

public class Enemy {
    public static int xE=450, yE=0;
    public int speedEnemy=12;

    public Enemy(){
        
    }

    public void render(Graphics gs){
        gs.setColor(Color.white);
        gs.fillRect(xE, yE, 150,30);
    }

    public void tick(){
        if(xE<=15){
            xE=15;
        }else if(xE+150>=Pong.LAR*Pong.ESC-15){
            xE=Pong.ALT*Pong.ESC*Pong.LAR*Pong.ESC-15;
        }

        xE+=(Pong.bola.positionX-xE-50)+90*0.2*0.5;

    }
}