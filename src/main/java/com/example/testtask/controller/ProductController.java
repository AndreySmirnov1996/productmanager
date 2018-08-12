package com.example.testtask.controller;

import com.example.testtask.model.Product;
import com.example.testtask.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/")
    public ModelAndView doHome(){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("lists",productRepository.findAll());
        return mv;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView doSave(@RequestParam("name") String name,
                               @RequestParam("description") String description,
                               @RequestParam("category") String category){
        ModelAndView mv = new ModelAndView("redirect:/");
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setCategory(category);
        productRepository.save(product);
        return mv;
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView doDelete(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("redirect:/");
        //mv.addObject("lists", productRepository.findById(id));
        productRepository.deleteById(id);
        return mv;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView doEdit(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("edit");
        mv.addObject("lists", productRepository.findById(id).get());
        return mv;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView doSearch(@RequestParam("name") String name,
                               @RequestParam("description") String description,
                               @RequestParam("category") String category){
        ModelAndView mv = new ModelAndView("redirect:/");
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setCategory(category);
        productRepository.save(product);
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
