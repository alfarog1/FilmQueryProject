package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

		String sql = "SELECT  title, description, release_year, rating FROM film WHERE id = ?";
		Connection conn = DriverManager.getConnection(URL, user, pwd);
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, filmId);
		ResultSet filmResult = pst.executeQuery();
		while (filmResult.next()) {
			film = new Film();
			film.setTitle(filmResult.getString(1));
			film.setDescription(filmResult.getString(2));
			film.setYear(filmResult.getString(3));
			film.setRating(filmResult.getString(4));
			film.setActor(findActorsByFilmId(5));

		}
			filmResult.close();
			pst.close();
			conn.close();


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

		List<Actor> actorList = new ArrayList<>();
		String user = "student";
		String pwd = "student";
		
		String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor JOIN film_actor ON actor.id = film_actor.actor_id  WHERE film_actor.film_id = ?";

		Connection conn = DriverManager.getConnection(URL, user, pwd);
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, filmId);
		ResultSet rs = pst.executeQuery();
		actorList = new ArrayList<>();
		while (rs.next()) {
			Actor actor = new Actor(rs.getInt("actor.id"), rs.getString("actor.first_name"),
					rs.getString("actor.last_name"));
			actorList.add(actor);
		}

		rs.close();
		pst.close();
		conn.close();

		return actorList;
	}

	
	public List<Film> findFilmByKeyword(String keyword) throws SQLException {
		Film film = null;
		List<Film> filmList = new ArrayList<>();
		String user = "student";
		String pwd = "student";
		

		String sql = "SELECT  f.title, f.description, f.release_year, f.rating, l.name, f.id FROM film f JOIN language l ON f.language_id = l.id WHERE title LIKE ? OR description LIKE ?";
		Connection conn = DriverManager.getConnection(URL, user, pwd);
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, "%" + keyword + "%");
		pst.setString(2, "%" + keyword + "%");
		ResultSet filmResult = pst.executeQuery();
		filmList = new ArrayList<>();
		while (filmResult.next()) {
			film = new Film();
			film.setTitle(filmResult.getString(1));
			film.setDescription(filmResult.getString(2));
			film.setYear(filmResult.getString(3));
			film.setRating(filmResult.getString(4));
			film.setActor(findActorsByFilmId(filmResult.getInt(6)));
			film.setLanguage(filmResult.getString(5));

			filmList.add(film);
		}
			filmResult.close();
			pst.close();
			conn.close();


		return filmList;
	}
	
}
