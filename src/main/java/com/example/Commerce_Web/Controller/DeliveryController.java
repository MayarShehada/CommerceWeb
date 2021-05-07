package com.example.Commerce_Web.Controller;

import com.example.Commerce_Web.Model.Delivery;
import com.example.Commerce_Web.Model.Warehouse;
import com.example.Commerce_Web.Repository.DeliveryRepository;
import com.example.Commerce_Web.Repository.WarehouseRepository;
import com.example.Commerce_Web.Service.DeliveryService;
import com.example.Commerce_Web.Service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DeliveryController {

    @Autowired
    private DeliveryService service;
    private final DeliveryRepository repository;

    public DeliveryController(DeliveryRepository repository) {
        this.repository = repository;
    }

    //Get Delivery From HTML Page
    @GetMapping("/delivery")
    public String viewHomePage(Model model) {
        List<Delivery> listwarehouse = service.listAll();
        model.addAttribute("listdelivery", listwarehouse);
        System.out.print("Get / ");
        return "index_delivery";
    }

    //Post new Delivery on HTML Page
    @GetMapping("/newdelivery")
    public String add(Model model) {
        model.addAttribute("delivery", new Delivery());
        return "newdelivery";
    }

    @RequestMapping(value = "/savedelivery", method = RequestMethod.POST)
    public String saveDelivery(@ModelAttribute("delivery") Delivery std) {
        service.save(std);
        return "redirect:/delivery";
    }

    //Edit Delivery By id on HTML Page
    @RequestMapping("/editDelivery/{id}")
    public ModelAndView showEditDeliveryPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("newdelivery");
        Delivery std = service.get(id);
        mav.addObject("delivery", std);
        return mav;
    }

    //Remove Delivery By id on HTML Page
    @RequestMapping("/deleteDelivery/{id}")
    public String removeDelivery(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/delivery";
    }

    //Get Delivery From Postman
    @GetMapping("/deliveries")
    @ResponseBody
    List<Delivery> allDeliveries() {
        return repository.findAll();
    }

    //Post new Delivery on Postman
    @PostMapping("/deliveries")
    @ResponseBody
    Delivery newDelivery(@RequestBody Delivery newDeliveries) {
        return repository.save(newDeliveries);
    }

    //Get Delivery By id on Postman
    @GetMapping("/deliveries/{id}")
    @ResponseBody
    public Delivery viewIDDelivery(@PathVariable int id) {
        Delivery delivery = repository.findById(id).get();
        return delivery;
    }

    //Delete Warehouse By id on Postman
    @DeleteMapping("/deliveries/delete/{id}")
    @ResponseBody
    public String deleteDelivery(@PathVariable int id) {
        repository.deleteById(id);
        return"{Delivery " + id + " : Deleted }";
    }

    //Edit Delivery By id on Postman
    @PutMapping("/deliveries/edit/{id}")
    @ResponseBody
    Delivery findWarehouse(@RequestBody Delivery newDelivery, @PathVariable int id) {

        return repository.findById(id)
                .map(delivery -> {
                    delivery.setDelivery_name(newDelivery.getDelivery_name());
                    delivery.setCustomer_id(newDelivery.getCustomer_id());
                    return repository.save(delivery);
                })
                .orElseGet(() -> {
                    newDelivery.setDelivery_id(id);
                    return repository.save(newDelivery);
                });
    }
}
