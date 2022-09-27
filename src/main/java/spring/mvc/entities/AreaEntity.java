package spring.mvc.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "area")
@Entity(name = "area")
@Getter
@Setter
@NoArgsConstructor
public class AreaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name", columnDefinition = "nvarchar(50)",  nullable = false)
	private String name;


	@Column(name = "createAt", columnDefinition = "nvarchar(100)", nullable = true)
	private String createAt;

	@Column(name = "updateAt", columnDefinition = "nvarchar(100)", nullable = true)
	private String updateAt;

	@Column(name = "description", columnDefinition = "nvarchar(500)", nullable = true)
	private String description;
	
	@Column(name = "status", columnDefinition = "bit", nullable = false)
	private boolean status;
	
	@OneToMany(mappedBy = "areaEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SeatEntity> seatList;

}
