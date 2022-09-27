package spring.mvc.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "seat")
@Entity(name = "seat")
@Getter
@Setter
@NoArgsConstructor
public class SeatEntity {
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

	@Column(name = "status", columnDefinition = "bit", nullable = true)
	private boolean status;

	@Column(name = "areaId", columnDefinition = "int", nullable = false)
	private int areaId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "areaId", foreignKey = @ForeignKey(name = "PK_Area_Seat"), insertable = false, updatable = false)
	private AreaEntity areaEntity;
	
	@OneToMany(mappedBy = "seatEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BillEntity> BillList;

}
