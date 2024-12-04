import java.util.*;

/*
 * Lauren Ramsey
 * CSC3465 Software Design
 * Robert Dutton and Shannon Duvall
 * ExtraCredit MovieDatabase
 * December 2-12th, 2024
 */

public class MovieInfo {
	private String title;
	private String director;
	private ArrayList<String> cast;
	
	public MovieInfo(String title, String director, ArrayList<String> cast) {
		this.title = title;
		this.director = director;
		this.cast = new ArrayList<>(cast);
	}
	
	public ArrayList<String> getCastMembers() {
		return cast;
	}
	
	public String getDirector() {
		return director;
	}
	
	public String getTitle() {
		return title;
	}
}