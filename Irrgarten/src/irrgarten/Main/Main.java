package irrgarten.Main;

import irrgarten.UI.TextUI;
import irrgarten.UI.UI;
import irrgarten.controller.Controller;
import irrgarten.Game;
import irrgarten.UI.GameUI;

public class Main {
    public static void main(String[] args) {

        int nplayer = 2;

        // Crear instancias del juego, la vista y el controlador
        Game game = new Game(nplayer);
        UI ui = GameUI.getInstance();  // Puedes cambiar a otra implementación de UI en el futuro
        Controller controller = new Controller(game, ui);

        // Iniciar el juego a través del controlador
        controller.play();
    }
}