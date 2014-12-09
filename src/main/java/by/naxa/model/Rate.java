package by.naxa.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * POJO Rate.
 * Created by phomal on 09.12.2014.
 */
@Entity
@Table(name = "Rate")
public @Data class Rate {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "Value", nullable = false)
	private int value;

	@NonNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;
}
