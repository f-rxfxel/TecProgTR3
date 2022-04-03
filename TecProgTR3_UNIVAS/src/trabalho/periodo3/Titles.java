package trabalho.periodo3;

import java.util.ArrayList;

public class Titles {

	private boolean isMovie;
	private String title;
	private ArrayList<String> cast = new ArrayList<String>();
	private String description;
	private String genre;
	private String director;
	private int parentalRating;
	private ArrayList<Players> plataforms = new ArrayList<Players>();
	
	public String addCast(String name) {
		cast.add(name);
		return "Ator " + name + " adicionado!\n";
	}
	
	public boolean checkNames(String name) {
		for(String actor : cast) {
			if(name.equals(actor)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isMovie() {
		return isMovie;
	}

	public void setMovie(boolean isMovie) {
		this.isMovie = isMovie;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<String> getCast() {
		return cast;
	}

	public void setCast(ArrayList<String> cast) {
		this.cast = cast;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getParentalRating() {
		return parentalRating;
	}

	public void setParentalRating(int parentalRating) {
		this.parentalRating = parentalRating;
	}

	public ArrayList<Players> getPlataforms() {
		return plataforms;
	}

	public void setPlataforms(ArrayList<Players> plataforms) {
		this.plataforms = plataforms;
	}

	public String movieOrSerie() {
		if(isMovie) {
			return "Filme";
		}
		return "Série";
	}
	
	public String getCastString() {
		for(String name : cast) {
			System.out.println(name + ", ");
		}
		return "\n";
	}
	
	@Override
	public String toString() {
		return movieOrSerie() + ":\n" + "Título:" + title 
				+ "\nElenco:" + getCastString() 
				+ "Descrição: " + description
				+ "\nGênero=" + genre 
				+ "\nDiretor:" + director
				+ "\nClassificação indicativa:" + parentalRating 
				+ "\nOnde assistir:" + plataforms;
	}
	
}
	
	/*
	i. Tipo (filme ou série)
	ii. Título
	iii. Elenco
	iv. Descrição
	v. Gênero
	vi. Diretor
	vii. Classificação etária
	viii. Plataforma (pode ser um ou mais players)
	 */