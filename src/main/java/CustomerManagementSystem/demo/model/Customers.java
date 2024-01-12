package CustomerManagementSystem.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
@Entity
@Table(name = "customers")
public class Customers {
    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.UUID) //UUID column where id stores // same here
    @GenericGenerator(name = "uuid2", strategy = "uuid2") //strategy is generator who is generating uuid here version 2 is generating
    private UUID id;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private  String lastname;
    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false, length = 10, unique = true)
    private String phoneNumber;

    //constructor
    public Customers(){
        super();
    }

    public Customers(String firstname, String lastname, String gender, int age, String phoneNumber){
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }
    //for id
    public UUID getId(){
        return this.id;
    }

    public void setId(UUID id){
        this.id = id;
    }
   //lastname
    public String getFirstname(){
        return this.firstname;
    }

    public void setFirstName(String firsttname){
        this.firstname = firsttname;
    }

    public void setLastName(String lastname){
        this.lastname = lastname;
    }

    //firstname
    public String getLastname(){
        return this.lastname;
    }

    //gender
    public String getGender(){
        return this.gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    //age
    public int getAge(){
        return this.age;
    }

    public void setAge(int age){
        this.age = age;
    }


    //phone number
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    // for full name
    public String getFullName() {
        return this.firstname + " " + this.lastname;
    }

    public void setFullName(String name) {
        String[] parts = name.split(" ");
        this.firstname = parts[0];
        this.lastname = parts[1];
    }
}
