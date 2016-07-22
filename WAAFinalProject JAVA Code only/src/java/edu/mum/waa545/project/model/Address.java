
package edu.mum.waa545.project.model;

import org.springframework.stereotype.Component;

@Component
public class Address {
    
    private String country;
    private String state;
    private String highschool;
    private String college;
    private String nationality;

    public Address() {
    }

    public Address(String country, String state, String highschool, String college, String nationality) {
        this.country = country;
        this.state = state;
        this.highschool = highschool;
        this.college = college;
        this.nationality = nationality;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getHighschool() {
        return highschool;
    }

    public void setHighschool(String highschool) {
        this.highschool = highschool;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    

    @Override
    public String toString() {
        return "Address{" + "country=" + country + ", state=" + state + ", highschool=" + highschool +
                ", college=" + college + ", nationality=" + nationality + '}';
    }
    
    
    
}
