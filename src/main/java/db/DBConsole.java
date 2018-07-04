package db;

import models.Console;
import models.Game;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBConsole {

    private static Session session;
    private static Transaction transaction;

    public static List<Game> getAvailableGames(Console console){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Game> games = null;
        try {
            transaction = session.beginTransaction();
            Criteria cr = session.createCriteria(Game.class);
            cr.createAlias("consoles", "console");
            cr.add(Restrictions.eq("console.id", console.getId()));
            games = cr.list();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return games;
    }


    public static void addGameToConsole(Console console, Game game){
        console.addGame(game);
        game.addConsoles(console);
        DBHelper.update(console);
    }


}
