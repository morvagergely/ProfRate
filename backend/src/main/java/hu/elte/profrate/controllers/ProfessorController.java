package hu.elte.profrate.controllers;


import hu.elte.profrate.entities.Course;
import hu.elte.profrate.entities.Professor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hu.elte.profrate.repositories.ProfessorRepository;

/**
 *
 * @author KeresztiKrisztián
 */
@CrossOrigin
@RestController
@RequestMapping("/professors")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Professor>> getAll() {
        return ResponseEntity.ok(professorRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> get(@PathVariable Integer id) {
        Optional<Professor> room = professorRepository.findById(id);
        if (room.isPresent()) {
            return ResponseEntity.ok(room.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Professor> post(@RequestBody Professor professor) {
        Professor savedCourse = professorRepository.save(professor);
        return ResponseEntity.ok(savedCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> put(@RequestBody Professor professor, @PathVariable Integer id) {
        Optional<Professor> oProfessor = professorRepository.findById(id);
        if (oProfessor.isPresent()) {
            professor.setId(id);
            return ResponseEntity.ok(professorRepository.save(professor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Professor> oProfessor = professorRepository.findById(id);
        if (!oProfessor.isPresent()) {
            ResponseEntity.notFound();
        }
        professorRepository.delete(oProfessor.get());
        return ResponseEntity.ok().build();       
    }
     
    
    @GetMapping("/{id}/courses")
    public ResponseEntity<Iterable<Course>> getCourses(@PathVariable Integer id) {
        Optional<Professor> oProfessor = professorRepository.findById(id);
        if (oProfessor.isPresent()) {
            return ResponseEntity.ok(oProfessor.get().getCourses());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}

