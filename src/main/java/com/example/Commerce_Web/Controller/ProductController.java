package com.example.Commerce_Web.Controller;

import com.example.Commerce_Web.Model.Product;
import com.example.Commerce_Web.Repository.ProductRepository;
import com.example.Commerce_Web.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;
    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    //Get Products From HTML Page
    @GetMapping("/product")
    public String viewHomePage(Model model) {
        List<Product> listproduct = service.listAll();
        model.addAttribute("listproduct", listproduct);
        System.out.print("Get / ");
        return "index_product";
    }

    //Post new Product on HTML Page
    @GetMapping("/newproduct")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        return "newproduct";
    }

    @RequestMapping(value = "/saveproduct", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product std) {
        service.save(std);
        return "redirect:/product";
    }

    //Edit Product By id on HTML Page
    @RequestMapping("/editProduct/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("newproduct");
        Product std = service.get(id);
        mav.addObject("product", std);
        return mav;
    }

    //Remove Product By id on HTML Page
    @RequestMapping("/deleteProduct/{id}")
    public String removeProduct(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/product";
    }

    //Get Product From Postman
    @GetMapping("/products")
    @ResponseBody
    List<Product> allProducts() {
        return repository.findAll();
    }

    //Post new Product on Postman
    @PostMapping("/products")
    @ResponseBody
    Product newProduct(@RequestBody Product newProduct) {
        return repository.save(newProduct);
    }

    //Get Product By id on Postman
    @GetMapping("/products/{id}")
    @ResponseBody
    public Product viewIDProduct(@PathVariable int id) {
        Product product = repository.findById(id).get();
        return product;
    }

    //Delete Product By id on Postman
    @DeleteMapping("/products/delete/{id}")
    @ResponseBody
    public String deleteProduct(@PathVariable int id) {
        repository.deleteById(id);
        return"{Product " + id + " : Deleted }";
    }

    //Edit Product By id on Postman
    @PutMapping("/products/edit/{id}")
    @ResponseBody
    Product findProduct(@RequestBody Product newProduct, @PathVariable int id) {

        return repository.findById(id)
                .map(product -> {
                    product.setProduct_name(newProduct.getProduct_name());
                    product.setPrice(newProduct.getPrice());
                    product.setSize(newProduct.getSize());
                    product.setStyle(newProduct.getStyle());
                    product.setColor(newProduct.getColor());
                    product.setQuantity(newProduct.getQuantity());
                    product.setWarehouse_id(newProduct.getWarehouse_id());
                    return repository.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setProduct_id(id);
                    return repository.save(newProduct);
                });
    }
}
