
package hu.elte.profrate.controllers;


import hu.elte.profrate.entities.Course;
import hu.elte.profrate.entities.Professor;
import hu.elte.profrate.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Course>> getAll() {
        return ResponseEntity.ok(courseRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> get(@PathVariable Integer id) {
        Optional<Course> course = courseRepository.findById(id);
        if (!course.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(course.get());
    }

    @PostMapping("")
    public ResponseEntity<Course> post(@RequestBody Course course) {
        Course newCourse = courseRepository.save(course);
        return ResponseEntity.ok(newCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Course> course = courseRepository.findById(id);
        if (!course.isPresent()) {
            ResponseEntity.notFound();
        }
        courseRepository.delete(course.get());

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> put(@PathVariable Integer id, @RequestBody Course course) {
        Optional<Course> foundCourse = courseRepository.findById(id);
        if (!foundCourse.isPresent()) {
            ResponseEntity.notFound();
        }
        Course courseToUpdate = foundCourse.get();
        if (course.getName() != null) {
            courseToUpdate.setName(course.getName());
        }
        if (course.getAverageRating() != null) {
            courseToUpdate.setAverageRating(course.getAverageRating());
        }

        return ResponseEntity.ok(courseRepository.save(courseToUpdate));
    }

}


