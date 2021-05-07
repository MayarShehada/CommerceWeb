package com.example.Commerce_Web.Controller;

import com.example.Commerce_Web.Model.Order;
import com.example.Commerce_Web.Model.Warehouse;
import com.example.Commerce_Web.Repository.OrderRepository;
import com.example.Commerce_Web.Repository.WarehouseRepository;
import com.example.Commerce_Web.Service.OrderService;
import com.example.Commerce_Web.Service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService service;
    private final OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    //Get orders From HTML Page
    @GetMapping("/order")
    public String viewHomePage(Model model) {
        List<Order> listorder = service.listAll();
        model.addAttribute("listorder", listorder);
        System.out.print("Get / ");
        return "index_order";
    }

    //Post new Order on HTML Page
    @GetMapping("/neworder")
    public String add(Model model) {
        model.addAttribute("order", new Order());
        return "neworder";
    }

    @RequestMapping(value = "/saveorder", method = RequestMethod.POST)
    public String saveOrder(@ModelAttribute("order") Order std) {
        service.save(std);
        return "redirect:/order";
    }

    //Edit Order By id on HTML Page
    @RequestMapping("/editOrder/{id}")
    public ModelAndView showEditOrderPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("neworder");
        Order std = service.get(id);
        mav.addObject("order", std);
        return mav;
    }

    //Remove Order By id on HTML Page
    @RequestMapping("/deleteOrder/{id}")
    public String removeOrder(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/order";
    }

    //Get Order From Postman
    @GetMapping("/orders")
    @ResponseBody
    List<Order> allOrders() {
        return repository.findAll();
    }

    //Post new Order on Postman
    @PostMapping("/orders")
    @ResponseBody
    Order newOrder(@RequestBody Order newOrder) {
        return repository.save(newOrder);
    }

    //Get Order By id on Postman
    @GetMapping("/orders/{id}")
    @ResponseBody
    public Order viewIDOrder(@PathVariable int id) {
        Order order = repository.findById(id).get();
        return order;
    }

    //Delete Order By id on Postman
    @DeleteMapping("/orders/delete/{id}")
    @ResponseBody
    public String deleteOrder(@PathVariable int id) {
        repository.deleteById(id);
        return"{Order " + id + " : Deleted }";
    }

    //Edit Order By id on Postman
    @PutMapping("/orders/edit/{id}")
    @ResponseBody
    Order findWarehouse(@RequestBody Order newOrder, @PathVariable int id) {

        return repository.findById(id)
                .map(order -> {
                    order.setProduct_id(newOrder.getProduct_id());
                    order.setCustomer_id(newOrder.getCustomer_id());
                    order.setDate(newOrder.getDate());
                    return repository.save(order);
                })
                .orElseGet(() -> {
                    newOrder.setOrder_id(id);
                    return repository.save(newOrder);
                });
    }
}
