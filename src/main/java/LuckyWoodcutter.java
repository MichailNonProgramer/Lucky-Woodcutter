import config.GameConfig;
import game.Game;
import visualizer.Display;



public class LuckyWoodcutter {
    public static void main(String[] args){
        Display display = new Display(GameConfig.getScreenWidth(), GameConfig.getScreenHeight(), new Game());
    }
}
