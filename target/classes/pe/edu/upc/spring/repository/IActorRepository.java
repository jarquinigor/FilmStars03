package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Actor;

@Repository
public interface IActorRepository extends JpaRepository<Actor, Integer> {
	@Query("from Actor a where a.nameActor like %:nameActor% order by a.idActor ASC")
	List<Actor> findByName(@Param("nameActor") String nameActor);
}
