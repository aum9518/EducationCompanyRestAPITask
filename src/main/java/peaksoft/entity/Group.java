package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_gen")
    @SequenceGenerator(name = "group_gen" ,sequenceName = "group_seq", allocationSize = 1)
    private Long id;
    private String groupName;
    private String imageLink;
    private String description;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    private List<Course> courses;
    @OneToMany(mappedBy = "group",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    private List<Student>students;
}
