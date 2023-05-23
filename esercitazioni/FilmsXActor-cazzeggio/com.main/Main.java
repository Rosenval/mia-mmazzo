package main;

import com.dao.FilmsXActorDAO;
import com.dao.FilmsXActorDAOImpl;

public class Main {

	public static void main(String[] args) {
		FilmsXActorDAO fxa = new FilmsXActorDAOImpl();
		fxa.getFilmsXActor().forEach(System.out::println);
	}
}
