package hu.elte.profrate.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull
    private String name;
    
    @Column
    @NotNull
    private Float averageRating;
    
    @Column
    @NotNull
    private Boolean doesContainKrisa;

    @OneToMany(mappedBy = "department",  orphanRemoval = true)
    private List<Professor> professors;
}
