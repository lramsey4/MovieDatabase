import java.io.*;
import java.util.*;

/*
 * Lauren Ramsey
 * CSC3465 Software Design
 * Robert Dutton and Shannon Duvall
 * ExtraCredit MovieDatabase
 * December 2-12th, 2024
 */

public class MovieDatabase {
	
	private ArrayList<Movie> movies = new ArrayList<>();
	
	public void loadRatedMoviesData(String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\t");
				Movie movie = new Movie();
				movie.setRatingRank(Integer.parseInt(parts[0]));
				movie.setTitle(parts[1]);
				movie.setYear(Integer.parseInt(parts[2]));
				movie.setRating(Double.parseDouble(parts[3]));
				movies.add(movie);
			}
			
		} catch (IOException e) {
			System.out.println("Error reading rated movies file: " + filename);
		}
	}
	
	public void loadGrossMoviesData(String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\t");
				Movie movie = new Movie();
				movie.setGrossRank(Integer.parseInt(parts[0]));
				movie.setTitle(parts[1]);
				movie.setYear(Integer.parseInt(parts[2]));
				movie.setBoxOfficeEarnings(Double.parseDouble(parts[3]));
				movies.add(movie);
			}
			
		} catch (IOException e) {
			System.out.println("Error reading gross movies file: " + filename);
		}
	}
	
	public void loadCastData(String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("\t");
				Movie movie = new Movie();
				movie.setTitle(parts[1]);
				movie.setYear(Integer.parseInt(parts[2]));
				movie.setDirector(parts[3]);
				ArrayList<String> castMembers = new ArrayList<>();
				for (int i = 4; i < Math.min(parts.length, 9); i++) {
					castMembers.add(parts[i]);
				}
				movie.setCastMembers(castMembers);
				movies.add(movie);
			}
			
		} catch (IOException e) {
			System.out.println("Error reading cast movies file: " + filename);
		}
	}
	
	public double getTotalEarningsForYear(int year) {
		double totalEarnings = 0.0;
		for (Movie movie : movies) {
			if (movie.getYear() == year && movie.getBoxOfficeEarnings() > 0) {
				totalEarnings += movie.getBoxOfficeEarnings();
			}
		}
		return totalEarnings;
	}
	
	public ArrayList<String> getSortedDirectors() {
		HashSet<String> directors = new HashSet<>();
		for (Movie movie : movies) {
			if (movie.getDirector() != null && !movie.getDirector().isEmpty()) {
				directors.add(movie.getDirector());
			}
		}
		return new ArrayList<>(directors);
	}
	
	public ArrayList<String> getTopDirectorsByFrequency(int number) {
		// counts the frequencies of each director
		HashMap<String, Integer> directorCount = new HashMap<>();
		for (Movie movie : movies) {
			if (movie.getDirector() != null && !movie.getDirector().isEmpty()) {
				directorCount.put(movie.getDirector(), directorCount.getOrDefault(movie.getDirector(), 0) + 1);
			}
		}
		
		// takes the counts and converts it into a list of entries and sorts
		ArrayList<HashMap.Entry<String, Integer>> sortedDirectors = new ArrayList<>(directorCount.entrySet());
		sortedDirectors.sort((director, num) -> num.getValue().compareTo(director.getValue()));
		
		// extract the top number directors
		ArrayList<String> topDirectors = new ArrayList<>();
		for (int i = 0; i < Math.min(number, sortedDirectors.size()); i++) {
			topDirectors.add(sortedDirectors.get(i).getKey());
		}
		
		return topDirectors;
	}
	
	public MovieInfo getMovieInfoByRatingRank(int rank) {
		for (Movie movie : movies) {
			if (movie.getRatingRank() == rank) {
				return new MovieInfo(
						movie.getTitle(),
						movie.getDirector(),
						movie.getCastMembers()
				);
			}
		}
		return null;
	}
	
	public MovieInfo getMovieInfoByGrossRank(int rank) {
		for (Movie movie : movies) {
			if (movie.getGrossRank() == rank) {
				return new MovieInfo(
						movie.getTitle(),
						movie.getDirector(),
						movie.getCastMembers()
				);
			}
		}
		return null;
	}
}