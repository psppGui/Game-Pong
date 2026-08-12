import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;

public class Bola{
    public static int positionX=Pong.ALT*Pong.ESC/2, positionY=Pong.LAR*Pong.ESC/2;
    public static  double dx=-1,dy;
    public static int angulo;
    public static int speedBall=5;
    public static int enemyPoints=0;
    public static int playerPoints=0;
    private boolean isPlaying=false;

    public Bola(){ 
        this.incial();

        if(dx < 0.1 && dx >-0.1){
            incial();
        }
    }   

    public void incial(){
        angulo=new Random().nextInt(120-45)+45;//Para definir um intervalo de 45 a 20
        dx=Math.cos(angulo);
        dy=Math.sin(angulo);
        isPlaying=true;
    }

    public void tick(){

        wallColision();
        bollReturn();
        this.points();
        //Player, inimigo e bola
        //x,y, largura, altura
        Rectangle player = new Rectangle(Pong.player.x,Pong.player.y,150,30);
        Rectangle bola= new Rectangle(Pong.bola.positionX, Pong.bola.positionY, 50,50);
        Rectangle enemy=new Rectangle(Enemy.xE , Enemy.yE, 150, 30);

        //métodos intersects(parametro), verifica se dois blocos se colidem!
        if(bola.intersects(player) || bola.intersects(enemy)){
            incial();
            if(dx <0){
                dx*=-1;
            }
        }

        if(bola.intersects(enemy)){
            angulo=new Random().nextInt(120-45)+45;//Para definir um intervalo de 45 a 20
            dx=Math.cos(angulo);
            dy=Math.sin(angulo);
            dy*=-1;
        }
        
        //Movimento bola
        positionX+=speedBall*dx;
        positionY+=speedBall*dy;

    }

    public void bollReturn(){
        if(positionY+30<0 || positionY>Pong.ALT*Pong.ESC){
            positionX=Pong.ALT*Pong.ESC/2; 
            positionY=Pong.LAR*Pong.ESC/2;
            this.incial();
        }
    }

    public void points(){
        if(isPlaying){
            if(positionY<=0){
                playerPoints+=1;
                isPlaying=false;
            }else if(positionY + speedBall*dx>=Pong.ALT*Pong.ESC){
                enemyPoints+=1;
            }

            System.out.println(playerPoints+" "+ enemyPoints);
        }
    }

    public void wallColision(){
        if(positionX+(speedBall*dx) <=0){
            dx*=-1;
        }else if(positionX+speedBall+(speedBall*dx) >=Pong.ALT*Pong.ESC){
            dx*=-1;
        }
    }

    public void render(Graphics gs){
        gs.setColor(Color.white);
        gs.fillOval(positionX, positionY, 50,50);

    }
}