package by.naxa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;

/**
 * POJO Student.
 * Created by phomal on 09.12.2014.
 */
@Entity
@Table(name = "Student")
@EqualsAndHashCode(exclude = {"id", "photo", "rates", "faculty"})
public @Data class Student {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "Name",
			nullable = false/*,
			unique = true*/)
	private String name;
	// Throws MySQLIntegrityConstraintViolationException: Duplicate entry %name% for key 'UK_sswrj5ou6xbmy9u79iwo91vkk'.
	// TODO: Add exception handling, then uncomment "unique = true".

	@Lob
	@Column(name = "Photo")
	private byte[] photo;   // TODO: add profile photo support.

	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "student",
			orphanRemoval = true,
			cascade = javax.persistence.CascadeType.ALL)
	private Collection<Rate> rates;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(
			name = "Faculty_id",
			nullable = false)
	private Faculty faculty;

}
