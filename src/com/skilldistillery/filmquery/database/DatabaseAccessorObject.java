package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		String user = "student";
		String pwd = "student";

		String sql = "SELECT title FROM film WHERE id = ?";
		Connection conn = DriverManager.getConnection(URL, user, pwd);
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, filmId);
		ResultSet filmResult = pst.executeQuery();
		if (filmResult.next()) {
			film = new Film(filmId); 
			film.setTitle(filmResult.getString(1));
			
			filmResult.close();
		    pst.close();
		    conn.close();
		
		}

		return film;
	}

	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		String user = "student";
		String pwd = "student";

		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		Connection conn = DriverManager.getConnection(URL, user, pwd);
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, actorId);
		ResultSet actorResult = pst.executeQuery();
		if (actorResult.next()) {
			actor = new Actor(actorId); // Create the object
			// Here is our mapping of query columns to our object fields:
			actor.setId(actorResult.getInt(1));
			actor.setFirstName(actorResult.getString(2));
			actor.setLastName(actorResult.getString(3));
			
			actorResult.close();
		    pst.close();
		    conn.close();
		}

		return actor;
	}

	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		String user = "student";
		String pwd = "student";

		String sql = "SELECT actor.first_name, actor.last_name FROM actor JOIN film_actor ON actor.id = film_actor.actor_id  WHERE film_actor.film_id = ?";

		Connection conn = DriverManager.getConnection(URL, user, pwd);
		PreparedStatement pst = conn.prepareStatement(sql);

		pst.setInt(1, filmId);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString(1));
			
			rs.close();
		    pst.close();
		    conn.close();

		}
		return null;
	}
	
	

}
