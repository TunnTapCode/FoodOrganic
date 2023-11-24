package com.Project.FoodOrganic.Entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.*;


@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "username" , length = 50 ,unique = true,nullable = false)
	private String username;
	@Column(name = "password" , length = 255)
	private String password;
	@Column(name = "email", unique = true )
	private String email;
	@Column(name = "fullName",length = 255)
	private String fullname;
	@Column(name = "address",length = 500)
	private String address;
	@Column(name = "phone",length = 500)
	private String phone;

	

	@ManyToOne(cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "role_id")
	private Role role ;
	@ManyToOne(cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "image_id")
	private Image_Avt image_Avt ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	

	public Image_Avt getImage_Avt() {
		return image_Avt;
	}

	public void setImage_Avt(Image_Avt image_Avt) {
		this.image_Avt = image_Avt;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}




	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	

	

	public User(Long id, String username, String password, String email, String fullname, String address, String phone,
			Role role, Image_Avt image_Avt) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
		this.address = address;
		this.phone = phone;
		this.role = role;
		this.image_Avt = image_Avt;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", fullname=" + fullname + ", role=" + role + "]";
	}


	
	
	
	
	
	

}
