package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Genre;

public interface IGenreService {
	public boolean save(Genre genre);
	public void delete(int idGenre);
	public List<Genre> findAllSortNameAsc();
	public List<Genre> findAllSortIdAsc();
	public Optional<Genre>findById(int idGenre);
	public List<Genre> findByName(String nameGenre);
}
