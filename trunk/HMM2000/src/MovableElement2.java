import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.map.Movable;

public abstract class MovableElement2 implements Movable, FightableContainer {

    private Player player;
    
    public MovableElement2(Player player) {
        this.player = player;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public void setPlayer(Player player){
      this.player = player;
    }
    
    public abstract double getStepCount();
    
    public abstract void setStepCount(double stepCount);
}

