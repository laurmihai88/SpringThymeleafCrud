package com.laurentiu.spring.thymeleaf.controller;

import com.laurentiu.spring.thymeleaf.model.Product;
import com.laurentiu.spring.thymeleaf.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String viewProductsPage(Model model) {
        logger.info("list all products");

        List<Product> products = productService.findAll();

        model.addAttribute("products", products);

        return "index";
    }

    @GetMapping("/products/produs_nou")
    public String showNewProductPage(Model model) {
        logger.info("show the new product");

        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }

    //save button
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        logger.info("Save new product");
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("edit_product");
        Product existingProduct = productService.findById(id);
        modelAndView.addObject("product", existingProduct);
        return modelAndView;

    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        logger.info("delete product with id {}", id);
        productService.delete(id);
        return "redirect:/products";
    }

}
