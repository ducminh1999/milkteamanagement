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

@Table(name = "role")
@Entity(name = "role")
@Getter
@Setter
@NoArgsConstructor
public class RoleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roleId")
	private int id;

	@Column(name = "name", columnDefinition = "nvarchar(50)",  nullable = false)
	private String name;

	@Column(name = "createAt", columnDefinition = "nvarchar(100)", nullable = true)
	private String createAt;

	@Column(name = "updateAt", columnDefinition = "nvarchar(100)", nullable = true)
	private String updateAt;

	@Column(name = "description", columnDefinition = "nvarchar(150)", nullable = true)
	private String description;

	@OneToMany(mappedBy = "roleEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<UserEntity> userList;

}
