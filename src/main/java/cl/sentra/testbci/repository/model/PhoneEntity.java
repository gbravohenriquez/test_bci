package cl.sentra.testbci.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneEntity implements Serializable{

	@Id
	@GeneratedValue(generator="UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	private String number;
	private String citycode;
	private String countrycode;
	@ManyToOne
	@JoinColumn(name = "usuarios_id", referencedColumnName = "id")
	private UserEntity user;
}
