import db.DBConsole;
import db.DBHelper;
import models.Console;
import models.Game;
import models.GenreType;

import java.util.List;

public class Runner {

    public static void main(String[] args) {
        Game game1 = new Game("Dark Souls", GenreType.FPS);
        DBHelper.save(game1);
        Game game2 = new Game("Dragon Quest", GenreType.PLATFORMER);
        DBHelper.save(game2);
        Game game3 = new Game ("Pacman", GenreType.ARCADE);
        DBHelper.save(game3);
        Game game4 = new Game("Mario Bross", GenreType.ARCADE);
        DBHelper.save(game4);

        Console console1 = new Console("Sony", "PS4", "Europe");
        DBHelper.save(console1);
        Console console2 = new Console("Nintendo", "Nintendo DS", "Latin America");
        DBHelper.save((console2));
        Console console3 = new Console("Microsoft", "Xbox 360", "Japon");
        DBHelper.save(console3);
        Console console4 = new Console("Microsoft", "Xbox One", "Europe");
        DBHelper.save(console4);

        DBConsole.addGameToConsole(console1, game1);
        DBConsole.addGameToConsole(console1, game2);
        DBConsole.addGameToConsole(console1, game3);
        DBConsole.addGameToConsole(console1, game4);
        DBConsole.addGameToConsole(console2, game1);
        DBConsole.addGameToConsole(console2, game2);
        DBConsole.addGameToConsole(console3, game4);
        DBConsole.addGameToConsole(console4, game2);


        List<Console> consoles = DBHelper.getAll(Console.class);

        List<Game> games = DBHelper.getAll(Game.class);

        game1.setTitle("Dark Soul");
        DBHelper.update(game1);

//        DBHelper.delete(game2);

        Console xbox360 = DBHelper.find(Console.class, 3);
        List<Game> foundGames = DBConsole.getAvailableGames(xbox360);

    }

}
