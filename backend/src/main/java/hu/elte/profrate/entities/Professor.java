/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.profrate.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author KeresztiKrisztián
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull
    private String name;
    
    /*@Column
    @NotNull
    private List<Float> ratings;*/
    
    @Column
    @NotNull
    private Float averageRating;
    
    @Column
    @NotNull
    private Integer recommendationCount;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Course> courses;
    
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;

    /*public Course getCourseById(Integer courseId) {
        for(Course course : courses) {
            if(course.getId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }*/
    
    /*public Exam getRoomById(Integer examId) {
        for(Exam exam : exams) {
            if(exam.getId().equals(examId)) {
                return exam;
            }
        }
        return null;
    }*/
}
