import java.awt.*;

public class Player {
    private String name;
    private int score;
    private Color color;

    Player(String name, Color color){
        setName(name);
        this.score = 0;
        setColor(color);
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public void addScore(){ this.score++; }
    public int getScore() {
        return this.score;
    }
    public Color getColor() { return this.color; }
    public void win(){
        this.score++;
    }

}
