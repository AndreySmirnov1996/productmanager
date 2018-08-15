package com.example.testtask.controller;

import com.example.testtask.model.Category;
import com.example.testtask.model.Product;
import com.example.testtask.model.Product_;
import com.example.testtask.repository.CategoryRepository;
import com.example.testtask.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PersistenceContext
    private EntityManager em;

    @RequestMapping("/")
    public ModelAndView doHome() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("lists", productRepository.findAll());
        mv.addObject("categories", categoryRepository.findAll());

        return mv;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView doSave(@RequestParam("name") String name,
                               @RequestParam("description") String description,
                               @RequestParam("category") String category) {
        ModelAndView mv = new ModelAndView("redirect:/");
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        String[] categories = category.split(" ");
        Set<Category> catSet = new HashSet();
        // Set<Category> allCategories = Collections.setcategoryRepository.findAll();
        for (String c : categories) {
            Category categoryToFind = categoryRepository.findByCategory(c);
            if (categoryToFind == null) {
                categoryToFind = categoryRepository.save(new Category(c));
            }
            catSet.add(categoryToFind);
        }
        product.setCategory(catSet);
        productRepository.save(product);
        return mv;
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView doDelete(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("redirect:/");
        //mv.addObject("lists", productRepository.findById(id));
        productRepository.deleteById(id);
        categoryRepository.deleteById(id);
        return mv;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView doEdit(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("edit");
        mv.addObject("list", productRepository.findById(id).get());
        return mv;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView doUpdate(@RequestParam("id") Long id,
                                 @RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam("category") String category) {
        ModelAndView mv = new ModelAndView("redirect:/");
        Product product = productRepository.findById(id).get();
        product.setName(name);
        product.setDescription(description);
        String[] categories = category.split(" ");
        Set<Category> catSet = product.getCategory();
        for (String c : categories) {
            Category categoryToFind = categoryRepository.findByCategory(c);
            if (categoryToFind == null) {
                categoryToFind = categoryRepository.save(new Category(c));
            }
            catSet.add(categoryToFind);
        }
        product.setCategory(catSet);
        productRepository.save(product);
        return mv;
    }

    @RequestMapping(value = "/searchcategory", method = RequestMethod.POST)
    public ModelAndView doSearchCategory(@RequestParam("category") String category) {
        ModelAndView mv = new ModelAndView("/resultOfSearching");
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> product = criteriaQuery.from(Product.class);
        Join <Product, Category> associate = product.join("category");
        List<Predicate>predicates=new ArrayList<Predicate>();
        predicates.add(criteriaBuilder.like(associate.<String>get("category"), category));
        criteriaQuery.select(product);
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        Query query = em.createQuery(criteriaQuery);
        List<Product> result = query.getResultList();
        mv.addObject("lists", result);
        return mv;
    }

    @RequestMapping(value = "/searchsubname", method = RequestMethod.POST)
    public ModelAndView doSearchSubName(@RequestParam("subName") String subName) {
        ModelAndView mv = new ModelAndView("/resultOfSearching");
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> product = criteriaQuery.from(Product.class);
        criteriaQuery.select(product);
        criteriaQuery.where(criteriaBuilder.equal(
                criteriaBuilder.substring(product.<String>get("name"), 1, subName.length()), subName));
        Query query = em.createQuery(criteriaQuery);
        List<Product> result = query.getResultList();
        mv.addObject("lists", result);
        return mv;
    }
}
