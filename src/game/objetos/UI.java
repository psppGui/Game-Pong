package game.objetos;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.Pong;

public class UI{
    public static boolean playerWin=false;
    public static boolean enemyWin=false;

    public UI(){

    }

    public void render(Graphics gs){
            System.out.println(Pong.game_states);

        gs.setColor(Color.white);
        gs.setFont(new Font("Arial", Font.BOLD,50));
        if(Pong.game_states.equals("PLAYNG")){
            gs.drawString(" "+Pong.bola.playerPoints, 600, 500);//Aqui vai continuar desenhando
            gs.drawString(" "+Pong.bola.enemyPoints, 600, 300);//Aqui vai continuar desenhando
        }else if(Pong.game_states.equals("GAMEOVER")){
            if(playerWin){
                gs.setColor(Color.green);
                gs.drawString("Player win ", 500, 300);
            }else if(enemyWin){
                gs.setColor(Color.red);
                gs.drawString("Enemy win ", 500, 300);
            }
        }
    }
}
