package irrgarten.Main;

import irrgarten.UI.TextUI;
import irrgarten.controller.Controller;
import irrgarten.Game;

public class Main{
    public static void main(String[] args) {
        int nplayer = 2;
        // Crear instancias del juego, la vista y el controlador
        Game game = new Game(nplayer);
        TextUI textUI = new TextUI();
        Controller controller = new Controller(game, textUI);

        // Iniciar el juego a través del controlador
        controller.play();
    }
}
