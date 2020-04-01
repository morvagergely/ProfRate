package hu.elte.profrate.repositories;

import hu.elte.profrate.entities.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Integer> {
    
}
