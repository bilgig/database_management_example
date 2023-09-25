package com.school.management.model;

import com.school.management.enums.Gender;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="student")
public class Student {
    @Id
    @Column(name="student_no")
    private Long studentNo;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="is_active")
    private boolean isActive;

    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    private Gender gender;

   public Student() {

    }
}
