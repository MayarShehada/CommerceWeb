package com.example.Commerce_Web.Controller;

import com.example.Commerce_Web.Model.Warehouse;
import com.example.Commerce_Web.Repository.WarehouseRepository;
import com.example.Commerce_Web.Service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class WarehouseController {

    @Autowired
    private WarehouseService service;
    private final WarehouseRepository repository;

    public WarehouseController(WarehouseRepository repository) {
        this.repository = repository;
    }

    //Get Warehouses From HTML Page
    @GetMapping("/warehouse")
    public String viewHomePage(Model model) {
        List<Warehouse> listwarehouse = service.listAll();
        model.addAttribute("listwarehouse", listwarehouse);
        System.out.print("Get / ");
        return "index_warehouse";
    }

    //Post new Warehouse on HTML Page
    @GetMapping("/newwarehouse")
    public String add(Model model) {
        model.addAttribute("warehouse", new Warehouse());
        return "newwarehouse";
    }

    @RequestMapping(value = "/savewarehouse", method = RequestMethod.POST)
    public String saveWarehouse(@ModelAttribute("warehouse") Warehouse std) {
        service.save(std);
        return "redirect:/warehouse";
    }

    //Edit Warehouse By id on HTML Page
    @RequestMapping("/editWarehouse/{id}")
    public ModelAndView showEditWarehousePage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("newwarehouse");
        Warehouse std = service.get(id);
        mav.addObject("warehouse", std);
        return mav;
    }

    //Remove Warehouse By id on HTML Page
    @RequestMapping("/deleteWarehouse/{id}")
    public String removeWarehouse(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/warehouse";
    }

    //Get Warehouse From Postman
    @GetMapping("/warehouses")
    @ResponseBody
    List<Warehouse> allWarehouses() {
        return repository.findAll();
    }

    //Post new Warehouse on Postman
    @PostMapping("/warehouses")
    @ResponseBody
    Warehouse newWarehouse(@RequestBody Warehouse newWarehouse) {
        return repository.save(newWarehouse);
    }

    //Get Warehouse By id on Postman
    @GetMapping("/warehouses/{id}")
    @ResponseBody
    public Warehouse viewIDWarehouse(@PathVariable int id) {
        Warehouse warehouse = repository.findById(id).get();
        return warehouse;
    }

    //Delete Warehouse By id on Postman
    @DeleteMapping("/warehouses/delete/{id}")
    @ResponseBody
    public String deleteWarehouse(@PathVariable int id) {
        repository.deleteById(id);
        return"{Warehouse " + id + " : Deleted }";
    }

    //Edit Warehouse By id on Postman
    @PutMapping("/warehouses/edit/{id}")
    @ResponseBody
    Warehouse findWarehouse(@RequestBody Warehouse newWarehouse, @PathVariable int id) {

        return repository.findById(id)
                .map(warehouse -> {
                    warehouse.setLocation(newWarehouse.getLocation());
                    warehouse.setCapacity(newWarehouse.getCapacity());
                    return repository.save(warehouse);
                })
                .orElseGet(() -> {
                    newWarehouse.setWarehouse_id(id);
                    return repository.save(newWarehouse);
                });
    }
}
