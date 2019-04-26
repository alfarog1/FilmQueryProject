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
	public Film findFilmById(int filmId) throws SQLException{
		return null;
	}

	public Film findActorById(int actorId) throws SQLException{
		return null;
	}

	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		String user = "student";
		String pwd = "student";

		String sql = "SELECT actor.first_name, actor.last_name FROM actor JOIN film_actor ON actor.id = film_actor.actor_id  WHERE film_actor.film_id = ?";
		int count = 0;

		Connection conn = DriverManager.getConnection(URL, user, pwd);
		PreparedStatement pst = conn.prepareStatement(sql);

		pst.setString(1, "2");
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString(1));

		}
		return null;
	}

}
