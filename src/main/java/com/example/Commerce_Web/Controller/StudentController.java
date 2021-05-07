package com.example.Commerce_Web.Controller;

import com.example.Commerce_Web.Model.Student;
import com.example.Commerce_Web.Repository.StudentRepository;
import com.example.Commerce_Web.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;
    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    //Get From HTML Page
    @GetMapping("/student")
    public String viewHomePage(Model model) {
        List<Student> liststudent = service.listAll();
        model.addAttribute("liststudent", liststudent);
        System.out.print("Get / ");
        return "index";
    }

    //Post new Student on HTML Page
    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("student", new Student());
        return "new";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute("student") Student std) {
        service.save(std);
        return "redirect:/";
    }

    @RequestMapping("/student/edit/{id}")
    public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("new");
        Student std = service.get(id);
        mav.addObject("student", std);
        return mav;

    }
    @RequestMapping("/student/delete/{id}")
    public String removeStudent(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }

    //Get From Postman
    @GetMapping("/students")
    @ResponseBody
    List<Student> allStudents() {
        return repository.findAll();
    }

    //Post new Student on Postman
    @PostMapping("/students")
    @ResponseBody
    Student newStudent(@RequestBody Student newStudent) {
        return repository.save(newStudent);
    }

    //Get Student By id on Postman
    @GetMapping("/students/{id}")
    @ResponseBody
    public Student viewID(@PathVariable int id) {
        Student student = repository.findById(id).get();
        return student;
    }

    //Delete Student By id on Postman
    @DeleteMapping("/students/delete/{id}")
    @ResponseBody
    public String deleteStudent(@PathVariable int id) {
        repository.deleteById(id);
        return"{Response : Deleted }";
    }

    //Edit Student By id on Postman
    @PutMapping("/students/edit/{id}")
    @ResponseBody
    Student findStudent(@RequestBody Student newStudent, @PathVariable int id) {

        return repository.findById(id)
                .map(student -> {
                    student.setStudentname(newStudent.getStudentname());
                    student.setCourse(newStudent.getCourse());
                    student.setFee(newStudent.getFee());
                    return repository.save(student);
                })
                .orElseGet(() -> {
                    newStudent.setId(id);
                    return repository.save(newStudent);
                });
    }
}
