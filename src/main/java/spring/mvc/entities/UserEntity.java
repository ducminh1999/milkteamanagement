/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 23, 2021
 * @version 1.0
 */
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

@Table(name = "userTable")
@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "fullName", columnDefinition = "nvarchar(50)", nullable = false)
	private String fullName;

	@Column(name = "userName", columnDefinition = "nvarchar(50)", unique = true, nullable = false)
	private String userName;

	@Column(name = "password", columnDefinition = "nvarchar(50)", nullable = false)
	private String password;

	@Column(name = "address", columnDefinition = "nvarchar(100)", nullable = true)
	private String address;

	@Column(name = "phone", columnDefinition = "nvarchar(13)", nullable = true)
	private String phone;

	@Column(name = "email", columnDefinition = "nvarchar(50)", nullable = true)
	private String email;

	@Column(name = "salary", columnDefinition = "money", nullable = false)
	private long salary;

	@Column(name = "gender", columnDefinition = "bit", nullable = false)
	private boolean gender;

	@Column(name = "status", columnDefinition = "bit", nullable = false)
	private boolean status;

	@Column(name = "description", columnDefinition = "nvarchar(150)", nullable = true)
	private String description;

	@Column(name = "createAt", columnDefinition = "nvarchar(50)", nullable = true)
	private String createAt;

	@Column(name = "updateAt", columnDefinition = "nvarchar(50)", nullable = true)
	private String updateAt;

	@Column(name = "roleId", columnDefinition = "int", nullable = false)
	private int roleId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", foreignKey = @ForeignKey(name = "FK_Role_User"), insertable = false, updatable = false)
	private RoleEntity roleEntity;

	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OrderItemEntity> orderList;
	
}
