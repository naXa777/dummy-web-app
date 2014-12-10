package by.naxa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

/**
 * POJO Student.
 * Created by phomal on 09.12.2014.
 */
@Entity
@Table(name = "Student")
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id", "photo", "rates"})
public @Data class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	@Column(name = "Name",
			nullable = false,
			unique = true)
	private String name;

	@Lob
	@Column(name = "Photo")
	private byte[] photo;

	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "student",
			orphanRemoval = true,
			cascade = javax.persistence.CascadeType.ALL)
	private List<Rate> rates;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(
			name = "Faculty_id",
			nullable = false)
	private Faculty faculty;
}
