package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Specialization;

import java.util.List;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@NoArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_gen")
    @SequenceGenerator(name = "instructor_gen" ,sequenceName = "instructor_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    @ManyToMany(mappedBy = "instructor",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Company> companies;
    @OneToMany(mappedBy = "instructor",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Course> courses;
    @OneToOne(cascade = {CascadeType.ALL})
    private User user;

}
