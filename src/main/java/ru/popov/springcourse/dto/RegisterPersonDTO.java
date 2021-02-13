package ru.popov.springcourse.dto;

import ru.popov.springcourse.models.Person;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class RegisterPersonDTO {
    @NotEmpty(message = "Name should not empty!")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Min(value = 0, message = "Age should be greater than 0")
    private int age;

    @NotEmpty(message = "Email should be empty!")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty
    @Size(min = 5, message = "Password should be between 2 char")
    private String password;

    public RegisterPersonDTO() {
    }

    public RegisterPersonDTO(@NotEmpty(message = "Name should not empty!") @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters") String name, @Min(value = 0, message = "Age should be greater than 0") int age, @NotEmpty(message = "Email should be empty!") @Email(message = "Email should be valid") String email, @NotEmpty @Size(min = 5, message = "Password should be between 2 char") String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public Person toPerson() {
        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        person.setEmail(email);
        person.setPassword(password);
        return person;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
