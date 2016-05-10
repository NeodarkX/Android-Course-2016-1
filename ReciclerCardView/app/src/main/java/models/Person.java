package models;

/**
 * Created by root on 09/05/16.
 */
public class Person {
    String firstName;
    String lastName;
    String pictureUrl;

    public Person(String firstName, String lastName, String pictureUrl){
        this.firstName =  firstName;
        this.lastName = lastName;
        this.pictureUrl = pictureUrl;
    }
}
