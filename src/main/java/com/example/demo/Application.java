package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student maria = new Student(
                    "Maria",
                    "Jones",
                    "mariajones@amigoscode.edu",
                    21
            );
            Student maria2 = new Student(
                    "Maria",
                    "Jones",
                    "mariajones2@amigoscode.edu",
                    25
            );
            Student ahmed = new Student(
                    "Ahmed",
                    "Ali",
                    "ahmedali@amigoscode.edu",
                    18
            );
            System.out.println("adding maria and ahmed");
            studentRepository.saveAll(List.of(maria,ahmed,maria2));
            studentRepository.findStudentByEmail("ahmedali@amigoscode.edu")
                    .ifPresentOrElse(System.out::println,() -> System.out.println("student with email ahmedali@amigoscode.edu not found"));
            studentRepository.selectStudentsByFirstNameEqualsAndAgeIsGreaterThanEqualNative("Maria",21).forEach(System.out::println);
        };
    }
}
