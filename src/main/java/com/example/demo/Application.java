package com.example.demo;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            StudentIdCardRepository studentIdCardRepository
    ){
        return args -> {
            Faker faker = new Faker();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@amigoscode.edu",firstName,lastName);
            Student student = new Student
                    (
                            firstName,
                            lastName,
                            email,
                            faker.number().numberBetween(17,55)
                    );
            student.addBook(new Book(
                    "Clean Code",
                    LocalDateTime.now().minusDays(4))
            );

            student.addBook(new Book(
                    "Think and grow reach",
                    LocalDateTime.now())
            );

            student.addBook(new Book(
                    "Spring Data JPA",
                    LocalDateTime.now().minusYears(1))
            );

            StudentIdCard studentIdCard = new StudentIdCard
                    ("123456789", student);
            studentIdCardRepository.save(studentIdCard);
            studentRepository.findById(1L)
                            .ifPresent(System.out::println);
            studentIdCardRepository.findById(1L)
                    .ifPresent(System.out::println);
        };
    }

    private void generateRandomStudents(StudentRepository studentRepository) {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@amigoscode.edu",firstName,lastName);
            Student student = new Student
                    (
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17,55)
                    );
            studentRepository.save(student);
        }
    }
}
