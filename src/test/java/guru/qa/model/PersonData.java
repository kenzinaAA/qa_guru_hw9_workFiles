package guru.qa.model;

import org.apache.poi.ss.formula.functions.Address;

public class PersonData {
    private String fullName;
    private Integer age;
    private String appearance;
    private String contacts;
    private String work;
    private String hobbies;
    private Boolean insurance;

    public Boolean getInsurance() {
        return insurance;
    }

    public void setInsurance(Boolean insurance) {
        this.insurance = insurance;
    }

    public String getFullName(){
        return fullName;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public Integer getAge(){
        return age;
    }
    public void setAge(Integer age){
        this.age = age;
    }

    public String getAppearance(){
        return appearance;
    }
    public void setAppearance(String appearance){
        this.appearance = appearance;
    }

    public String getContacts(){
        return contacts;
    }
    public void setContacts(String contacts){
        this.contacts = contacts;
    }

    public String getWork(){
        return work;
    }
    public void setWork(String work){
        this.work = work;
    }

    public String getHobbies(){
        return hobbies;
    }
    public void setHobbies(String hobbies){
        this.hobbies = hobbies;
    }
}
