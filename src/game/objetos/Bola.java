package game.objetos;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import java.awt.Rectangle;

import game.Pong;
import game.entidades.Enemy;

public class Bola{
    public static int positionX=Pong.ALT*Pong.ESC/2, positionY=Pong.LAR*Pong.ESC/2;
    public static double dx=-1,dy;
    public static int angulo;
    public static int speedBall=5;
    public static int enemyPoints=0;
    public static int playerPoints=0;
    private boolean isPlaying=false;
    private int frame=0,maxFrame=20,counter=0, counterMax=20;
    

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
        this.resetVelocity();
    }

    public void tick(){

        wallColision();
        bollReturn();
        this.points();
        this.updateBollSpeed();
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
        /*if(enemyPoints >=10 || playerPoints>=10){

        }*/
        if(enemyPoints >=5){
            Pong.ui.enemyWin=true;
            Pong.game_states="GAMEOVER";
        }else if(playerPoints>=5){
            Pong.ui.playerWin=true;
            Pong.game_states="GAMEOVER";
        }
    }

    public void updateBollSpeed(){
        frame++;
        if(frame>=maxFrame){
            frame=0;
            counter++;
            if(counter>=counterMax){
                counter=0;
                speedBall++;
            }
        }
    }

    public void resetVelocity(){
        frame=0;
        counter=0;
        speedBall=5;
    }

    public void wallColision(){
        if(positionX+(speedBall*dx) <=5){
            dx*=-1;
        }else if(positionX+speedBall+(speedBall*dx) >=Pong.ALT*Pong.ESC-15){
            dx*=-1;
        }
    }

    public void render(Graphics gs){
        gs.setColor(Color.white);
        gs.fillOval(positionX, positionY, 50,50);

    }
}