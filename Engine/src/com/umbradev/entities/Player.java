package com.umbradev.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.umbradev.main.Game;

public class Player extends Entity {

    public boolean right, left, up, down;
    public double spd = 2.0;

    public static final int DIR_DOWN = 0;
    public static final int DIR_RIGHT = 1;
    public static final int DIR_UP = 2;
    public static final int DIR_LEFT = 3;

    private int dir = DIR_DOWN;

    private int frames = 0, maxFrames = 13, index = 0, maxIndex = 1;
    private int walkIndex = 0, walkMaxIndex = 3;

    private boolean moved = false;

    // WALK // ANDANDO
    private BufferedImage[] walkDown, walkRight, walkUp;

    // IDLE (2 frames cada)
    private BufferedImage[] idleDownAnim, idleRightAnim, idleUpAnim, idleLeftAnim;

    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);

        walkDown  = new BufferedImage[4];
        walkRight = new BufferedImage[4];
        walkUp    = new BufferedImage[4];

        // ANDAR PARA BAIXO – Y = 112
        for(int i = 0; i < 4; i++)
            walkDown[i] = Game.playersheet.getSprite(i * 32, 112, 32, 32);

        // ANDAR PARA DIREITA – Y = 144
        for(int i = 0; i < 4; i++)
            walkRight[i] = Game.playersheet.getSprite(i * 32, 144, 32, 32);

        // ANDAR PARA CIMA – Y = 176
        for(int i = 0; i < 4; i++)
            walkUp[i] = Game.playersheet.getSprite(i * 32, 176, 32, 32);


        // IDLE ANIMADO (2 frames)
        idleDownAnim = new BufferedImage[2];
        idleRightAnim = new BufferedImage[2];
        idleUpAnim = new BufferedImage[2];
        idleLeftAnim = new BufferedImage[2];

        // Linha 0 = idle baixo
        idleDownAnim[0] = Game.playersheet.getSprite(0, 0, 32, 32);
        idleDownAnim[1] = Game.playersheet.getSprite(32, 0, 32, 32);

        // idle direita (linha 0 também, assumindo)
        idleRightAnim[0] = Game.playersheet.getSprite(0, 32, 32, 32);
        idleRightAnim[1] = Game.playersheet.getSprite(32,32, 32, 32);
 
        // idle cima (linha 32 → você disse linha 2)
        idleUpAnim[0] = Game.playersheet.getSprite(0, 64, 32, 32);
        idleUpAnim[1] = Game.playersheet.getSprite(32, 64, 32, 32);

        // idle esquerda → espelha idleRightAnim
        idleLeftAnim[0] = idleRightAnim[0];
        idleLeftAnim[1] = idleRightAnim[1];
    }


    public void tick() {
        moved = false;

        if(right) {
            moved = true;
            dir = DIR_RIGHT;
            x += spd;
        }
        else if(left) {
            moved = true;
            dir = DIR_LEFT;
            x -= spd;
        }

        if(up) {
            moved = true;
            dir = DIR_UP;
            y -= spd;
        }
        else if(down) {
            moved = true;
            dir = DIR_DOWN;
            y += spd;
        }

        // Animação
        frames++;
        if(frames >= maxFrames) {
            frames = 0;

            if(moved) {
                walkIndex++;
                if(walkIndex > walkMaxIndex)
                    walkIndex = 0;
            } else {
                index++;
                if(index > maxIndex)
                    index = 0;
            }
        }
    }


    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        BufferedImage img = null;

        // IDLE
        if(!moved) {
            if(dir == DIR_DOWN)  img = idleDownAnim[index];
            if(dir == DIR_RIGHT) img = idleRightAnim[index];
            if(dir == DIR_UP)    img = idleUpAnim[index];
            if(dir == DIR_LEFT)  img = idleLeftAnim[index];
        }

        // WALK
        else {
            if(dir == DIR_DOWN)  img = walkDown[walkIndex];
            if(dir == DIR_RIGHT) img = walkRight[walkIndex];
            if(dir == DIR_UP)    img = walkUp[walkIndex];
            if(dir == DIR_LEFT)  img = walkRight[walkIndex];
        }

        // ESPELHAR ESQUERDA
        if(dir == DIR_LEFT) {
            g2.drawImage(img, this.getX() + 32, this.getY(), -32, 32, null);
            return;
        }

        g2.drawImage(img, this.getX(), this.getY(), null);
    }
}
