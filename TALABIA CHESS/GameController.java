public class GameController { //contol 
    private Board model;
    private GameView view;

    public GameController(Board model, GameView view){ 
        this.model = model;
        this.view = view;
    }

    public Board getGameModel(){
        return model;
    }

    public void setGameModel(Board model){
        this.model = model;
    }

    public GameView getGameView(GameView view){
        return view;
    }

    public void setGameView(GameView view){
        this.view = view;
    }
}
