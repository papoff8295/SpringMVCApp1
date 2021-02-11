package ru.popov.springcourse.dto;

public class AuthRequestDto {
    private String personName;
    private String password;

    public AuthRequestDto() {
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPassword() {
        return password;
    }

    public AuthRequestDto(String personName, String password) {
        this.personName = personName;
        this.password = password;
    }
}
