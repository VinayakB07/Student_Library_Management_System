package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.DTOs.StudentUpdateAgeDto;
import com.example.Student_Library_Management_System.DTOs.StudentUpdateMobNoDto;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @GetMapping("/get_user")
    public String findNameByEmail(@RequestParam("email") String email){
        return studentService.findNameByEmail(email);
    }



    @PutMapping("/update_mob")
    public String updateMobileNo(@RequestBody StudentUpdateMobNoDto studentReqDto){

        return studentService.updateMobileNo(studentReqDto);
    }

    @PutMapping("/update_age")
    public String UpdateAge(@RequestBody StudentUpdateAgeDto studentUpdateAgeDto){

        return studentService.updateAge(studentUpdateAgeDto);
    }
}
