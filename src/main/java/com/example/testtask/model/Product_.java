package com.example.testtask.model;

import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel(Product.class)

public class Product_ {
    public static volatile SingularAttribute<Product,Long> id;
    public static volatile SingularAttribute<Product,String> name;
    public static volatile SingularAttribute<Product,String> description;
    public static volatile SingularAttribute<Product,String> category;
}