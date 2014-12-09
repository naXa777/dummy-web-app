package by.naxa.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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
public @Data class Faculty {

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	@Column(name = "Name", nullable = false)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
	private List<Student> students;

}
