package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

import game.entidades.Enemy;
import game.entidades.Player;
import game.objetos.Bola;
import game.objetos.UI;

public class Pong extends Canvas implements Runnable, KeyListener {
    public static void main(String[] args){
        Pong game = new Pong();
        new Thread(game).start();
    }
    private boolean isRun=true;
    private Thread thread;
    public static final int ALT=300,LAR=200,ESC=4;
    private JFrame pixel;
    private BufferedImage image;

    //Objs:
    public static Player player;
    public static Enemy enemy;
    public static Bola bola;
    public static UI ui;

    //Game state estado do jogo
    public static String game_states="PLAYNG"; //"MENU", "GAME_OVER";

    public Pong(){
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(ALT*ESC, LAR*ESC));
        this.image=new BufferedImage(ALT*ESC, LAR*ESC, BufferedImage.TYPE_INT_RGB);
        this.window();

        player= new Player();
        enemy=new Enemy();
        bola=new Bola();
        ui=new UI();
    }

    public void window(){
        pixel=new JFrame("Ping pong");
        pixel.add(this);
        pixel.pack();
        pixel.setVisible(true);
        pixel.setResizable(false);
        pixel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pixel.setLocation(300,300);
        
        this.requestFocus();
    }

    public void render(){
        BufferStrategy bs=this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics gs=image.getGraphics();
        gs.setColor(Color.black);
        gs.fillRect(0,0,ALT*ESC, LAR*ESC);

        gs.setColor(Color.white);
        gs.fillRect(0, 0, 10, Pong.LAR*Pong.ESC);
        gs.fillRect(Pong.ALT*Pong.ESC-10, 0, 10, Pong.LAR*Pong.ESC);
        
        
        for(int posicaoInicial=0; posicaoInicial<20; posicaoInicial++){
            gs.fillRect(posicaoInicial*60+10, (Pong.LAR*Pong.ESC)/2-5,50,5);//UI game barra meio
        }

        player.render(gs);
        enemy.render(gs);
        bola.render(gs);
        ui.render(gs);

        gs=bs.getDrawGraphics();
        gs.drawImage(image,0, 0, ALT*ESC, LAR*ESC, null);
        bs.show();
    }

    public void tick(){
        if(game_states.equals("PLAYNG")){
            player.tick();
            enemy.tick();
            bola.tick();
        }else if(game_states.equals("GAME OVER")){

        }
    }

    @Override
    public void keyTyped(KeyEvent e){
        
    }

    @Override
    public void keyPressed(KeyEvent e){
       player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e){
       player.keyReleased(e);
    }

    @Override
    public void run(){
        while(isRun){
            tick();
            render();
            try {
                this.requestFocus();
                Thread.sleep(1000/75);    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
