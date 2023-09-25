package com.bilgeadam.springdata.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name="students")
public class Student {
    @Id
    //@GeneratedValue(Strategy=GenerationType.AUTO)-> student_no yu yani
    // primary keyi 1 den başlayarak her kayıtta otomatik 1 artırır idyi otomatik
    @Column(name="student_no")
    private Long studentNo;
    private String name;

   @Size
    @Column(name="last_name")
    private String lastName;
    private int age;
    @Column(name="is_active")
    private boolean isActive;
    public Student(){

    }
    public Student(Long studentNo, String name, String lastName, int age, boolean isActive) {
        this.studentNo = studentNo;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.isActive = isActive;
    }
}
