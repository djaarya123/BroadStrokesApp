package dhiraj.projectapp.Models;

/**
 * Created by Dhiraj on 01-03-2018.
 */

public class User {
    public String name,age,city,state,country;

    public User(){}

    public User(String name,String age,String address){
        this.name=name;
        this.age=age;
        String[] add=address.split(",");

        this.city=add[0];
        this.state=add[1];
        this.country=add[2];
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }
}
