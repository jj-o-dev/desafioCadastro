package main.java.com.jprr.cadastroPets.model.entity;

public class PetAddress {
    private int number;
    private String city;
    private String street;

    public PetAddress(int number, String city, String street) {
        this.number = number;
        this.city = city;
        this.street = street;
    }

    @Override
    public String toString() {
        return "PetAddress{" +
                "number=" + number +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
