package com.example.Commerce_Web.Controller;

import com.example.Commerce_Web.Model.Customer;
import com.example.Commerce_Web.Repository.CustomerRepository;
import com.example.Commerce_Web.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService service;
    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    //Get Customers From HTML Page
    @GetMapping("/customer")
    public String viewHomePage(Model model) {
        List<Customer> listcustomer = service.listAll();
        model.addAttribute("listcustomer", listcustomer);
        System.out.print("Get / ");
        return "index_customer";
    }

    //Post new Customer on HTML Page
    @GetMapping("/newcustomer")
    public String add(Model model) {
        model.addAttribute("customer", new Customer());
        return "newcustomer";
    }

    @RequestMapping(value = "/savecustomer", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customer") Customer std) {
        service.save(std);
        return "redirect:/customer";
    }

    //Edit Student By id on HTML Page
    @RequestMapping("/editCustomer/{id}")
    public ModelAndView showEditCustomerPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("newcustomer");
        Customer std = service.get(id);
        mav.addObject("customer", std);
        return mav;
    }

    //Remove Customer By id on HTML Page
    @RequestMapping("/deleteCustomer/{id}")
    public String removeCustomer(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/customer";
    }

    //Get Customer From Postman
    @GetMapping("/customers")
    @ResponseBody
    List<Customer> allCustomers() {
        return repository.findAll();
    }

    //Post new Customer on Postman
    @PostMapping("/customers")
    @ResponseBody
    Customer newCustomer(@RequestBody Customer newCustomer) {
        return repository.save(newCustomer);
    }

    //Get Customer By id on Postman
    @GetMapping("/customers/{id}")
    @ResponseBody
    public Customer viewIDCustomer(@PathVariable int id) {
        Customer customer = repository.findById(id).get();
        return customer;
    }

    //Delete Customer By id on Postman
    @DeleteMapping("/customers/delete/{id}")
    @ResponseBody
    public String deleteCustomer(@PathVariable int id) {
        repository.deleteById(id);
        return"{Customer " + id + " : Deleted }";
    }

    //Edit Customer By id on Postman
    @PutMapping("/customers/edit/{id}")
    @ResponseBody
    Customer findCustomer(@RequestBody Customer newCustomer, @PathVariable int id) {

        return repository.findById(id)
                .map(customer -> {
                    customer.setCustomer_name(newCustomer.getCustomer_name());
                    customer.setAge(newCustomer.getAge());
                    customer.setPhonenumber(newCustomer.getPhonenumber());
                    customer.setAddress(newCustomer.getAddress());
                    customer.setVisacard_number(newCustomer.getVisacard_number());
                    return repository.save(customer);
                })
                .orElseGet(() -> {
                    newCustomer.setCustomer_id(id);
                    return repository.save(newCustomer);
                });
    }
}
