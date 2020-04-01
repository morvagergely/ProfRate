package hu.elte.profrate.controllers;


import hu.elte.profrate.entities.Course;
import hu.elte.profrate.entities.Professor;
import hu.elte.profrate.repositories.CourseRepository;

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
import java.util.Objects;

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
    
    @Autowired
    private CourseRepository courseRepository;

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
    public ResponseEntity<Professor> put(@PathVariable Integer id, @RequestBody Professor professor) {
        Optional<Professor> foundProfessor = professorRepository.findById(id);
        if (!foundProfessor.isPresent()) {
            ResponseEntity.notFound();
        }
        Professor professorToUpdate = foundProfessor.get();
        if(professor.getName() != null) {
            professorToUpdate.setName(professor.getName());
        }
        if(professor.getAverageRating() != null) {
            professorToUpdate.setAverageRating(professor.getAverageRating());
        }
        if(professor.getScore() != null) {
            professorToUpdate.setScore(professor.getScore());
        }
        if(professor.getRateCount() != null) {
            professorToUpdate.setRateCount(professor.getRateCount());
        }
        if(professor.getRecommendationCount() != null) {
            professorToUpdate.setRecommendationCount(professor.getRecommendationCount());
        }
        if(professor.getDepartment() != null) {
            professorToUpdate.setDepartment(professor.getDepartment());
        }
        return ResponseEntity.ok(professorRepository.save(professorToUpdate));
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
    
    @PostMapping("/{id}/courses")
    public ResponseEntity<Course> addCourse(@PathVariable Integer id, @RequestBody Course course) {
        Optional<Professor> oProfessor = professorRepository.findById(id);
        Optional<Course> oCourse = courseRepository.findById(course.getId());
        if (oProfessor.isPresent() && oCourse.isPresent()) {
            Professor professor = oProfessor.get();
            professor.getCourses().add(oCourse.get());
            professorRepository.save(professor);
            return ResponseEntity.ok(oCourse.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}/courses")
    public ResponseEntity<Course> DropCourse(@PathVariable Integer id, @RequestBody Course course) {
        Optional<Professor> oProfessor = professorRepository.findById(id);
        if (oProfessor.isPresent()) {
            Course foundCourse = oProfessor.get().findCourseById(course.getId());
            if (Objects.nonNull(foundCourse)) {
                Professor user = oProfessor.get();
                user.getCourses().remove(foundCourse);
                professorRepository.save(user);  // have to trigger from the @JoinTable side
                return ResponseEntity.ok(foundCourse);
            }
        }
        return ResponseEntity.notFound().build();
    }
}   
    

