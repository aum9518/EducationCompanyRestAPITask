package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import peaksoft.enums.Gender;
import peaksoft.enums.StudyFormat;
@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_gen")
    @SequenceGenerator(name = "student_gen" ,sequenceName = "student_seq", allocationSize = 1)
    private Long id;
    @Value("first_name")
    private String firstName;
    @Value("last_name")
    private String lastName;
    @Value("phone_number")
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @Value("study_format")
    private String studyFormat;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Boolean isBlocked;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private Group group;
    @OneToOne(cascade = {CascadeType.ALL})
    private User user;
}
