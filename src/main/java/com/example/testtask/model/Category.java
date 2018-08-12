package com.example.testtask.model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
//
    @Column(name = "category", unique = true)
    private String category;

    public Category() {
    }

    public Category(String category) {
        this.category = category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object obj) {
        return (((Category)(obj)).getCategory().equals(this.category));
    }

    @Override
    public int hashCode() {
        return category.hashCode();
    }

    @Override
    public String toString() {
        return getCategory();
    }
}
