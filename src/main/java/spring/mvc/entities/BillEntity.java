package spring.mvc.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "bill")
@Entity(name = "bill")
@Getter
@Setter
@NoArgsConstructor
public class BillEntity {
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "nvarchar(100)", nullable = false)
	private String id;

	@Column(name = "status", columnDefinition = "int", nullable = false)
	private int status;

	@Column(name = "seatId", columnDefinition = "int",unique = false,  nullable = false)
	private int seatId;

	@Column(name = "deliverId", columnDefinition = "nvarchar(100)", nullable = false)
	private String deliverId;

	@Column(name = "discount", columnDefinition = "int", nullable = false)
	private int discount;

	@Column(name = "paymentId", columnDefinition = "int",unique = false,  nullable = false)
	private int paymentId;

	@Column(name = "total", columnDefinition = "bigint", nullable = false)
	private long total;

	@Column(name = "createAt", columnDefinition = "nvarchar(100)", nullable = true)
	private String createAt;

	@Column(name = "updateAt", columnDefinition = "nvarchar(100)", nullable = true)
	private String updateAt;

	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "paymentId", foreignKey = @ForeignKey(name = "FK_Bill_payment"), insertable = false, updatable = false)
	private PaymentEntity paymentEntity;

	@OneToMany(mappedBy = "billId", cascade = CascadeType.ALL)
	private List<OrderItemEntity> orderItemEntityList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seatId", foreignKey = @ForeignKey(name = "PK_Bill_Seat"), insertable = false, updatable = false)
	private SeatEntity seatEntity;

}
