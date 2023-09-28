package com.example.thymeleafemailgenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ThymeleafEmailGeneratorApplication implements CommandLineRunner {

    @Autowired
    TemplateEngine templateEngine;

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafEmailGeneratorApplication.class, args);
	}

    @Override
    public void run(String... args) {
        List<Person> persons = new ArrayList<>();

        persons.add(new Person("John", 65, LocalDate.now()));
        persons.add(new Person("Alex", 43, LocalDate.now()));
        persons.add(new Person("Pam", 23, LocalDate.now()));

        Context context = new Context();
        // using personsInThymeleaf to have a different variable name in the thymeleaf engine
        context.setVariable("personsInThymeleaf", persons);

        String message = templateEngine.process("email", context);

        System.out.println(message);
    }

    static class Person{
        private String name;
        private int age;
        private LocalDate dateRegistered;

        public Person(String name, int age, LocalDate dateRegistered) {
            this.name = name;
            this.age = age;
            this.dateRegistered = dateRegistered;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public LocalDate getDateRegistered() {
            return dateRegistered;
        }

        public void setDateRegistered(LocalDate dateRegistered) {
            this.dateRegistered = dateRegistered;
        }
    }
}
