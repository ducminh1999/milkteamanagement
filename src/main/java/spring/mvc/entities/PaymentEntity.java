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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "payment")
@Entity(name = "payment")
@Getter
@Setter
@NoArgsConstructor
public class PaymentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "paymentMethod", columnDefinition = "nvarchar(50)", nullable = false)
	private String paymentMethod;

	@Column(name = "createAt", columnDefinition = "nvarchar(100)", nullable = true)
	private String createAt;

	@Column(name = "updateAt", columnDefinition = "nvarchar(100)", nullable = true)
	private String updateAt;

	@Column(name = "description", columnDefinition = "nvarchar(500)", nullable = true)
	private String description;

	@OneToMany(mappedBy = "paymentEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BillEntity> billEntity;

}
