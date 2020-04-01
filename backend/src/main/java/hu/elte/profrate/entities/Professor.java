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
    
    @Column
    private Integer score;
    
    @Column
    private Integer rateCount;
    
    @Column
    private Float averageRating;
    
    @Column
    private Integer recommendationCount;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Course> courses;
    
    @JsonBackReference
    @ManyToOne
    private Department department;

    /*public Course getCourseById(Integer courseId) {
        for(Course course : courses) {
            if(course.getId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }*/
    
}
