package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.StudentUpdateAgeDto;
import com.example.Student_Library_Management_System.DTOs.StudentUpdateMobNoDto;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public String createStudent(Student student){

        Card card= new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudentVariable(student);

        student.setCard(card);
        studentRepository.save(student);
        return "Student and card added";
    }

    public String findNameByEmail(String email){

        Student student=studentRepository.findByEmail(email);
        return student.getName();
    }


    public String updateMobileNo(StudentUpdateMobNoDto studentUpdateMobNoDto){

        Student orignalStudent=studentRepository.findById(studentUpdateMobNoDto.getId()).get();

        orignalStudent.setMobileNo(studentUpdateMobNoDto.getMobileNo());

        studentRepository.save(orignalStudent);

        return "Student updated successfully";
    }

    public String updateAge(StudentUpdateAgeDto studentUpdateAgeDto){

        Student orignalStudent=studentRepository.findById(studentUpdateAgeDto.getId()).get();

        orignalStudent.setAge(studentUpdateAgeDto.getAge());

        studentRepository.save(orignalStudent);

        return "student updated successfully";
    }
}
