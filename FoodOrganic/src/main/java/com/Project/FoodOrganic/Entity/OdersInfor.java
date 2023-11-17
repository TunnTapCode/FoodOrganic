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

@Entity
@Table(name = "Oders_Infor")
public class OdersInfor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	@Column(columnDefinition = "NVARCHAR(255)")
	private String fullname ;
	@Column(columnDefinition = "NVARCHAR(255)")
	private String tinh ;
	@Column(columnDefinition = "NVARCHAR(255)")
	private String huyen ;
	@Column(columnDefinition = "NVARCHAR(255)")
	private String xa ;
	@Column(columnDefinition = "VARCHAR(13)" ,length = 13)
	private String phone ;
	
	private String email ;
	@Column(columnDefinition = "NVARCHAR(555)")
	private String note ;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "order_id")
	private Orders order ;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public String getTinh() {
		return tinh;
	}


	public void setTinh(String tinh) {
		this.tinh = tinh;
	}


	public String getHuyen() {
		return huyen;
	}


	public void setHuyen(String huyen) {
		this.huyen = huyen;
	}


	public String getXa() {
		return xa;
	}


	public void setXa(String xa) {
		this.xa = xa;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Orders getOrder() {
		return order;
	}


	public void setOrder(Orders order) {
		this.order = order;
	}
	


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public OdersInfor(Long id, String fullname, String tinh, String huyen, String xa, String phone, String email,
			String note, Orders order) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.tinh = tinh;
		this.huyen = huyen;
		this.xa = xa;
		this.phone = phone;
		this.email = email;
		this.note = note;
		this.order = order;
	}


	public OdersInfor() {
		
	}
	
	@Override
	public String toString() {
		return "OdersInfor [id=" + id + ", fullname=" + fullname + ", tinh=" + tinh + ", huyen=" + huyen + ", xa=" + xa
				+ ", phone=" + phone + ", email=" + email + ", note=" + note + ", order=" + order + "]";
	}



}
