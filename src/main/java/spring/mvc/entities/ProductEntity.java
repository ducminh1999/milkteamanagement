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

@Table(name = "product")
@Entity(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name", columnDefinition = "nvarchar(50)", nullable = false)
	private String name;

	@Column(name = "createAt", columnDefinition = "nvarchar(100)", nullable = true)
	private String createAt;

	@Column(name = "updateAt", columnDefinition = "nvarchar(100)", nullable = true)
	private String updateAt;

	@Column(name = "description", columnDefinition = "nvarchar(500)", nullable = true)
	private String description;

	@Column(name = "price", columnDefinition = "bigint", nullable = false)
	private int price;

	@Column(name = "imageFile", columnDefinition = "nvarchar(200)", nullable = true)
	private String imageFile;

	@Column(name = "categoryId", columnDefinition = "int", nullable = false)
	private int categoryId;
	
	@Column(name = "status", columnDefinition = "bit", nullable = false)
	private boolean status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId", foreignKey = @ForeignKey(name = "PK_Product_Category"), insertable = false, updatable = false)
	private CategoryEntity categoryEntity;

	@OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderItemEntity> orderItemEntity;
	
	@OneToMany(mappedBy = "productCartEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CartEntity> cartEntity;

}
