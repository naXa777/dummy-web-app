package by.naxa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * POJO Student.
 * Created by phomal on 09.12.2014.
 */
@Entity
@Table(name = "Student")
@EqualsAndHashCode(exclude = {"id", "photo", "rates", "faculty"})
public @Data class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "Name",
			nullable = false,
			unique = true)
	private String name;

	@Lob
	@Column(name = "Photo")
	private byte[] photo;

	@OneToMany(
			fetch = FetchType.EAGER,
			mappedBy = "student",
			orphanRemoval = true,
			cascade = javax.persistence.CascadeType.ALL)
	private List<Rate> rates;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(
			name = "Faculty_id",
			nullable = false)
	private Faculty faculty;

}
