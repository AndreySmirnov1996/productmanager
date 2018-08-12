package com.example.testtask.model;

import javax.persistence.*;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    @JoinColumn(name = "id")
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Category> category;

    public Product() {
    }

    public Product(String name, String description, Set<Category> category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<Category> getCategory() {
        return category;
    }

    public String getCategories() {
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (Category c : category)
            stringJoiner.add(c.getCategory());
        return stringJoiner.toString();
    }


}
