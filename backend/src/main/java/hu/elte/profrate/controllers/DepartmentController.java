package hu.elte.profrate.controllers;

import hu.elte.profrate.entities.Department;
import hu.elte.profrate.entities.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import hu.elte.profrate.repositories.DepartmentRepository;
@CrossOrigin
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Department>> getAll() {
        return ResponseEntity.ok(departmentRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> get(@PathVariable Integer id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (!department.isPresent()) {
            ResponseEntity.notFound();
        }

        return ResponseEntity.ok(department.get());
    }

    @PostMapping("")
    public ResponseEntity<Department> post(@RequestBody Department department) {
        Department newDepartment = departmentRepository.save(department);
        return ResponseEntity.ok(newDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (!department.isPresent()) {
            ResponseEntity.notFound();
        }
        departmentRepository.delete(department.get());

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> put(@PathVariable Integer id, @RequestBody Department department) {
        Optional<Department> foundDepartment = departmentRepository.findById(id);
        if (!foundDepartment.isPresent()) {
            ResponseEntity.notFound();
        }

        Department departmentToUpdate = foundDepartment.get();
        if(department.getName() != null) {
            departmentToUpdate.setName(department.getName());
        }
        if(department.getAverageRating() != null) {
            departmentToUpdate.setAverageRating(department.getAverageRating());
        }
        if(department.getDoesContainKrisa() != null) {
            departmentToUpdate.setDoesContainKrisa(department.getDoesContainKrisa());
        }

        return ResponseEntity.ok(departmentRepository.save(departmentToUpdate));
    }

    /*@GetMapping("/{id}/professors")
    public ResponseEntity<Iterable<Professor>> getProfessors(@PathVariable Integer id) {
        Optional<Department> oDepartment = departmentRepository.findById(id);
        if (oDepartment.isPresent()) {
            return ResponseEntity.ok(oDepartment.get().getProfessors());
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
}