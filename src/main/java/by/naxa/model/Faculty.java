package by.naxa.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * POJO Faculty.
 * Created by phomal on 09.12.2014.
 */
@Entity
@Table
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"id", "students"})
public @Data class Faculty {

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	@Column(name = "Name",
			nullable = false,
			unique = true)
	private String name;

	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "faculty")
	private List<Student> students;

}
