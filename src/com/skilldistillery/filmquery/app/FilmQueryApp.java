package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

	private void test() throws SQLException {
		Film film = db.findFilmById(1);
		System.out.println(film);
	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {
		DatabaseAccessorObject dao = new DatabaseAccessorObject();

		System.out.println("+-----------------------------------------+");
		System.out.println("|  1. Look up a film by its id.           |");
		System.out.println("|  2. Look up a film by a search keyword. |");
		System.out.println("|  3. Exit the application.               |");
		System.out.println("+-----------------------------------------+ \n\n");

		System.out.print("Enter your menu choice:  ");
		String choice = input.nextLine();

		switch (choice) {
		case "1":
			System.out.print("Enter the id for the film you're looking for: ");
			int c1;
			try {
				c1 = input.nextInt();
				Film film = db.findFilmById(c1);

				if (film == null) {
					System.out.println("The film was not found");
				} else {
					System.out.println(film);
				}

			} catch (InputMismatchException inputWrong) {
				System.out.println("Please enter a number for the film id....\n");
				input.nextLine();
				int c1e = input.nextInt();
				Film film = db.findFilmById(c1e);
				System.out.println(film + "\n\n");

			}
			break;
		case "2":
			System.out.print("Enter a keyword to find your film: ");
			String c2 = input.nextLine();

			break;
		case "3":
			System.out.println("Have a good day!");
			System.exit(0);
			break;

		default:
			System.out.println("Please enter a 1 , 2, or 3:");

			break;
		}

	}

}
