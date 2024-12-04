import java.util.*;

/*
 * Lauren Ramsey
 * CSC3465 Software Design
 * Robert Dutton and Shannon Duvall
 * ExtraCredit MovieDatabase
 * December 2-12th, 2024
 */

public class MovieApplication {
	private MovieDatabase database;
	private Scanner scanner;
	
	public MovieApplication() {
		database = new MovieDatabase();
		scanner = new Scanner(System.in);
	}
	
	public void initializeDatabase() {
		try {
			database.loadRatedMoviesData("imdb_movies_toprated.txt");
			database.loadGrossMoviesData("imdb_movies_gross.txt");
			database.loadCastData("imdb_movies_cast.txt");
		} catch (Exception e) {
			System.err.println("Error loading database: " + e.getMessage());
			System.exit(1);
		}
	}
	
	public void run() {
		
	}
	
	private void displayMenu() {
		
	}
	
	private int getUserChoice() {
		while (true) {
			try {
				return Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.print("Invalid input. Please enter a number: ");
			}
		}
	}
	
	private void queryTotalEarningsForYear() {
		System.out.print("Enter the year to check total earnings: ");
		try {
			int year = Integer.parseInt(scanner.nextLine());
			double earnings = database.getTotalEarningsForYear(year);
			System.out.printf("Total box office earnings in %d: $%.2f million%n", year, earnings);
		} catch (NumberFormatException e) {
			System.out.println("Invalid year format.");
		}
	}
	
	private void listAllDirectors() {
		ArrayList<String> directors = database.getSortedDirectors();
		System.out.println("\nList of all directors (alphabetically):");
		for (String director : directors) {
			System.out.println(director);
		}
		System.out.printf("Total directors: %d%n", directors.size());
	}
	
	private void listTopDirectors() {
		System.out.print("Enter number of top directors to list: ");
		try {
			int n = Integer.parseInt(scanner.nextLine());
			ArrayList<String> topDirectors = database.getTopDirectorsByFrequency(n);
			
			System.out.println("\nTop " + n + " Directors by Number of Movies:");
			for (int i = 0; i < topDirectors.size(); i++) {
				System.out.printf("%d, %s%n", i+1, topDirectors.get(i));
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid number format.");
		}
	}
	
	private void queryMovieByGrossRank() {
		System.out.print("Enter the gross rank of the movie: ");
		try {
			int rank = Integer.parseInt(scanner.nextLine());
			MovieInfo movieInfo = database.getMovieInfoByGrossRank(rank);
			displayMovieInfo(movieInfo);
		} catch (NumberFormatException e) {
			System.out.println("Invalid rank format.");
		}
	}
	
	private void displayMovieInfo(MovieInfo info) {
		if (info == null) {
			System.out.println("No movie found with the given rank.");
			return;
		}
		
		System.out.println("\nMovie Information:");
		System.out.println("Title: " + info.getTitle());
		System.out.println("Director: " + info.getDirector());
		System.out.println("Cast:");
		ArrayList<String> cast = info.getCastMembers();
		if (cast != null && !cast.isEmpty()) {
			for (String actor : cast) {
				System.out.println("- " + actor);
			}
		} else {
			System.out.println("No cast information available.");
		}
	}
	
	private void promptToContinue() {
		System.out.print("\nPress Enter to continue...");
		scanner.nextLine();
	}
	
}