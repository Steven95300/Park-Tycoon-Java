package engine.mobile;

import engine.map.Block;

public class Visitor {
    public static final int HAUT = 0;
    public static final int BAS = 1;
    public static final int GAUCHE = 2;
    public static final int DROITE = 3;
    private Block position;

    private int direction = 0;

    public Visitor(Block position) {
        this.position = position;

    }

    public Block getPosition() {
        return position;
    }

    public void setPosition(Block position, int newDirection) {
        if (this.position != position || this.direction != newDirection) {
            this.position = position;
            this.direction = newDirection;

        }
    }
    
    public int getDirection() {
        return direction;
    }

}
