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
/*
    @Autowired
    private AuthenticatedUser authenticatedUser;*/

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
    public ResponseEntity<Professor> post(@RequestBody Professor room) {
        Professor savedCourse = professorRepository.save(room);
        return ResponseEntity.ok(savedCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> put(@RequestBody Professor room, @PathVariable Integer id) {
        Optional<Professor> oCourse = professorRepository.findById(id);
        if (oCourse.isPresent()) {
            room.setId(id);
            return ResponseEntity.ok(professorRepository.save(room));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Professor> oCourse = professorRepository.findById(id);
        if (oCourse.isPresent()) {
            professorRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /*@GetMapping("/{id}/courses")
    public ResponseEntity<Iterable<Course>> getCourses(@PathVariable Integer id) {
        Optional<Professor> oCurse = professorRepository.findById(id);
        if (oCurse.isPresent()) {
            return ResponseEntity.ok(oCurse.get().getCourses());
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
    
}

