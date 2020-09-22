package com.example.testBackend.entity;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "news")
public class MiniUniversityEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "title", length = 999999999)
	private String title;
	@Column(name = "desc", length = 999999999)
	private String desc;
	@Column(name = "date", length = 999999999)
	private String date;
	@Column(name = "pic", length = 999999999)
	private String pic;

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	public String getDesc() { return desc; }
	public void setDesc(String desc) { this.desc = desc; }
	public String getDate() { return date; }
	public void setDate(String date) { this.date = date; }
	public String getPic() { return pic; }
	public void setPic(String pic) { this.pic = pic; }
}
