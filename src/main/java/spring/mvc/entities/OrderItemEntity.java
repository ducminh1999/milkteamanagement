package spring.mvc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "orderitem")
@Entity(name = "orderitem")
@Getter
@Setter
@NoArgsConstructor
public class OrderItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "quantity", columnDefinition = "int", nullable = false)
	private int quantity;

	@Column(name = "productId", columnDefinition = "int", nullable = false)
	private int productId;

	@Column(name = "billId", columnDefinition = "nvarchar(100)", nullable = false)
	private String billId;

	@Column(name = "createAt", columnDefinition = "nvarchar(100)", nullable = true)
	private String createAt;

	@Column(name = "updateAt", columnDefinition = "nvarchar(100)", nullable = true)
	private String updateAt;

	@Column(name = "description", columnDefinition = "nvarchar(150)", nullable = true)
	private String description;

	@Column(name = "userId", columnDefinition = "int", nullable = false)
	private int userId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "PK_Order_User"), insertable = false, updatable = false)
	private UserEntity userEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId",foreignKey = @ForeignKey(name = "FK_Order_Product"), insertable = false, updatable = false)
	private ProductEntity productEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "billId", foreignKey = @ForeignKey(name = "PK_Bill_OrderItem"), insertable = false, updatable = false)
	private BillEntity billEntity;

}
