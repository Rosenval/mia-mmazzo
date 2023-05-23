package com.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")

public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Integer categoryId;

	@UpdateTimestamp
	@Column(name = "last_update")
	private Timestamp lastUpdate;

	private String name;

	public Category() {
	}

	// costruttore per insert perche andiamo ad inserire solo il nome dato che id e
	// lastUpdate sono auto
	public Category(String name) {
		super();
		this.name = name;
	}

	// costruttore per insert perche ci serve l'id per ottenere il record da
	// modificare
	public Category(Integer categoryId, String name) {
		super();
		this.categoryId = categoryId;
		this.name = name;
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", lastUpdate=" + lastUpdate + ", name=" + name + "]";
	}
}