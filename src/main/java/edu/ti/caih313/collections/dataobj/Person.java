package edu.ti.caih313.collections.dataobj;

import java.util.HashMap;
import java.util.Map;

public class Person {
    private Name name;
    private Gender gender;

    private EmailAddress personsEmail;

    //age in years
    //TODO -- replace by Date birthday, and getAge that calculates age
    private Integer age;

    public enum Gender {MALE, FEMALE;}

    public Person(Name name, Gender gender, Integer age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        personsEmail = new EmailAddress();
        personsEmail.emailMap =  new HashMap<EmailAddress.Email, String>();
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Name getName(){
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setEmail(EmailAddress.Email emailType, String emailAddress){
        personsEmail.emailMap.put(emailType, emailAddress);
    }

    public HashMap<EmailAddress.Email, String> getEmailMap(){
        return personsEmail.emailMap;
    }

    public EmailAddress getEmailAddress(){
        return personsEmail;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", gender=" + gender +
                ", age=" + age
                + "}";
    }
}