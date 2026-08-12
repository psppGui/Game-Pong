import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Player implements KeyListener{
    public static int x=450,y=768;
    public static boolean right=false,left=false;
    private int speed=15;

    public Player(){
        
    }

    public void render(Graphics gs){
        gs.setColor(Color.white);
        gs.fillRect(x, y, 150, 30);    
    }
    
    public void tick(){
        if (right) {
            x += speed;
        }else if (left) {
            x -= speed;
        }
        
        if (x < 0) {
            x = 0;
        }else if (x + 150 > Pong.ALT * Pong.ESC) {
            x = Pong.ALT * Pong.ESC - 150;
        }
    }

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            right=true;
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            left=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
         if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
            right=false;
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            left=false;
        }    
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}