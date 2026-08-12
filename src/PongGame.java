import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class PongGame extends Canvas implements Runnable, KeyListener {
    private static final int WIDTH = 900;
    private static final int HEIGHT = 600;
    private static final int PADDLE_WIDTH = 14;
    private static final int PADDLE_HEIGHT = 100;
    private static final int BALL_SIZE = 14;
    private static final int WIN_SCORE = 10;

    private volatile boolean running;
    private boolean paused;
    private boolean upPressed;
    private boolean downPressed;
    private boolean gameOver;

    private int playerY = (HEIGHT - PADDLE_HEIGHT) / 2;
    private int aiY = (HEIGHT - PADDLE_HEIGHT) / 2;

    private double ballX = WIDTH / 2.0 - BALL_SIZE / 2.0;
    private double ballY = HEIGHT / 2.0 - BALL_SIZE / 2.0;
    private double ballVX = -6;
    private double ballVY = 4;

    private int playerScore;
    private int aiScore;

    public PongGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
    }

    @Override
    public void run() {
        long previousTime = System.nanoTime();
        double nsPerUpdate = 1_000_000_000.0 / 60.0;
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - previousTime) / nsPerUpdate;
            previousTime = now;

            while (delta >= 1) {
                update();
                delta--;
            }

            render();

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private void update() {
        if (paused || gameOver) {
            return;
        }

        int paddleSpeed = 8;
        if (upPressed) {
            playerY -= paddleSpeed;
        }
        if (downPressed) {
            playerY += paddleSpeed;
        }
        playerY = clamp(playerY, 0, HEIGHT - PADDLE_HEIGHT);

        int aiCenter = aiY + PADDLE_HEIGHT / 2;
        if (aiCenter < ballY) {
            aiY += 5;
        } else if (aiCenter > ballY) {
            aiY -= 5;
        }
        aiY = clamp(aiY, 0, HEIGHT - PADDLE_HEIGHT);

        ballX += ballVX;
        ballY += ballVY;

        if (ballY <= 0 || ballY + BALL_SIZE >= HEIGHT) {
            ballVY = -ballVY;
            ballY = clamp((int) ballY, 0, HEIGHT - BALL_SIZE);
        }

        int playerX = 24;
        int aiX = WIDTH - 24 - PADDLE_WIDTH;

        if (intersects(playerX, playerY, PADDLE_WIDTH, PADDLE_HEIGHT, (int) ballX, (int) ballY, BALL_SIZE, BALL_SIZE)) {
            ballX = playerX + PADDLE_WIDTH;
            ballVX = Math.abs(ballVX) * 1.03;
            ballVY += ((ballY + BALL_SIZE / 2) - (playerY + PADDLE_HEIGHT / 2.0)) * 0.02;
        } else if (intersects(aiX, aiY, PADDLE_WIDTH, PADDLE_HEIGHT, (int) ballX, (int) ballY, BALL_SIZE, BALL_SIZE)) {
            ballX = aiX - BALL_SIZE;
            ballVX = -Math.abs(ballVX) * 1.03;
            ballVY += ((ballY + BALL_SIZE / 2) - (aiY + PADDLE_HEIGHT / 2.0)) * 0.02;
        }

        if (ballX < -BALL_SIZE) {
            aiScore++;
            checkWin();
            resetBall(false);
        } else if (ballX > WIDTH) {
            playerScore++;
            checkWin();
            resetBall(true);
        }
    }

    private void checkWin() {
        if (playerScore >= WIN_SCORE || aiScore >= WIN_SCORE) {
            gameOver = true;
        }
    }

    private void resetBall(boolean toRight) {
        ballX = WIDTH / 2.0 - BALL_SIZE / 2.0;
        ballY = HEIGHT / 2.0 - BALL_SIZE / 2.0;
        ballVX = toRight ? 6 : -6;
        ballVY = Math.random() > 0.5 ? 4 : -4;
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    private static boolean intersects(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2;
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.DARK_GRAY);
        for (int y = 0; y < HEIGHT; y += 34) {
            g.fillRect(WIDTH / 2 - 3, y, 6, 20);
        }

        g.setColor(Color.WHITE);
        int playerX = 24;
        int aiX = WIDTH - 24 - PADDLE_WIDTH;
        g.fillRect(playerX, playerY, PADDLE_WIDTH, PADDLE_HEIGHT);
        g.fillRect(aiX, aiY, PADDLE_WIDTH, PADDLE_HEIGHT);
        g.fillOval((int) ballX, (int) ballY, BALL_SIZE, BALL_SIZE);

        g.setFont(new Font("SansSerif", Font.BOLD, 42));
        g.drawString(String.valueOf(playerScore), WIDTH / 2 - 80, 60);
        g.drawString(String.valueOf(aiScore), WIDTH / 2 + 55, 60);

        g.setFont(new Font("SansSerif", Font.PLAIN, 18));
        g.drawString("W/S: mover  P: pausar  R: reiniciar", 20, HEIGHT - 20);

        if (paused && !gameOver) {
            drawCenteredText(g, "PAUSADO", 34, HEIGHT / 2);
        }

        if (gameOver) {
            String winner = playerScore > aiScore ? "Você venceu!" : "IA venceu!";
            drawCenteredText(g, winner + " (R para reiniciar)", 30, HEIGHT / 2);
        }

        g.dispose();
        bs.show();
    }

    private void drawCenteredText(Graphics2D g, String text, int fontSize, int y) {
        g.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        int textWidth = g.getFontMetrics().stringWidth(text);
        g.drawString(text, (WIDTH - textWidth) / 2, y);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                upPressed = true;
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                break;
            case KeyEvent.VK_P:
                if (!gameOver) {
                    paused = !paused;
                }
                break;
            case KeyEvent.VK_R:
                playerScore = 0;
                aiScore = 0;
                playerY = (HEIGHT - PADDLE_HEIGHT) / 2;
                aiY = (HEIGHT - PADDLE_HEIGHT) / 2;
                paused = false;
                gameOver = false;
                resetBall(Math.random() > 0.5);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            upPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            downPressed = false;
        }
    }

    public static void main(String[] args) {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Ambiente sem interface gráfica detectado. Execute em um ambiente com suporte a janela.");
            return;
        }

        JFrame frame = new JFrame("Game Pong - Java AWT");
        PongGame game = new PongGame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.running = true;
        new Thread(game, "pong-loop").start();
        game.requestFocus();
    }
}
