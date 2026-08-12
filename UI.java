import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UI{
    public UI(){

    }

    public void render(Graphics gs){
        gs.setColor(Color.white);
        gs.setFont(new Font("Arial", Font.BOLD,50));
        gs.drawString(""+Pong.bola.enemyPoints, 600, 300);
        gs.drawString(""+Pong.bola.playerPoints, 600, 500);
        gs.setColor(Color.white);
        gs.fillRect(0, (Pong.LAR*Pong.ESC)/2,Pong.ALT*Pong.ESC,2);
    }
}
