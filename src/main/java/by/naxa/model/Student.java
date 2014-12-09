package by.naxa.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * POJO Student.
 * Created by phomal on 09.12.2014.
 */
@Entity
@Table(name = "Student")
@NoArgsConstructor
@RequiredArgsConstructor
public @Data class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	@Column(name = "Name", nullable = false)
	private String name;

	@Lob
	private byte[] photo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	private List<Rate> rates;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "faculty_id", nullable = false)
	private Faculty faculty;
}
