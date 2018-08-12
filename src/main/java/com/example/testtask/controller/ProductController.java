package com.example.testtask.controller;

import com.example.testtask.model.Category;
import com.example.testtask.model.Product;
import com.example.testtask.repository.CategoryRepository;
import com.example.testtask.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    ///////////////////////////////////
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView doSearch(@RequestParam("category") String category) {
        ModelAndView mv = new ModelAndView("index");
        List<Product> result = new ArrayList<>();
        Iterable<Product> all = productRepository.findAll();
        all.forEach(product -> {
            if (product.getCategory().contains(new Category(category)))
                result.add(product);
        });
        mv.addObject("lists", result);
        mv.addObject("categories", categoryRepository.findAll());

        return mv;
    }

//    @GetMapping("/greeting")
//    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World")
//                                   String name,
//                           Map<String, Object> model
//    ) {
//        model.put("name", name);
//        return "greeting";
//    }

//    @GetMapping
//    public String main(Map<String, Object> model) {
//        Iterable<Product> products = productRepository.findAll();
//        model.put("products", products);
//        return "main";
//    }

//    @PostMapping
//    public String addProduct(@RequestParam String name
//            , @RequestParam String description, @RequestParam String category, Map<String, Object> model) {
//        Product product = new Product(name, description, category);
//        productRepository.save(product);
//
//        Iterable<Product> products = productRepository.findAll();
//        model.put("products", products);
//        return "main";
//    }
//
//    @DeleteMapping
//    public String deleteProduct(@RequestParam Long id, Map<String, Object> model) {
//        productRepository.deleteById(id);
//        Iterable<Product> products = productRepository.findAll();
//        model.put("products", products);
//        return "main";
//    }
}
