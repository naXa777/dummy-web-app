package by.naxa.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

/**
 * POJO Rate.
 * Created by phomal on 09.12.2014.
 */
@Entity
@Table(name = "Rate")
@NoArgsConstructor
public @Data class Rate {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "Value", nullable = false)
	private int value;

	@NonNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Student_id", nullable = false)
	private Student student;

	@Override
	public String toString() {
		return Integer.toString(value);
	}
}
