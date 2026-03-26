package main.java.com.jprr.cadastroPets.model.entity;

import main.java.com.jprr.cadastroPets.model.enums.PetType;
import main.java.com.jprr.cadastroPets.model.enums.PetSex;

public class Pet {
    private String name;
    private PetType type;
    private PetSex petSex;
    private PetAddress petAddress;
    private double age;
    private double weight;
    private String breed;

    public Pet(String name, PetType type, PetSex petSex,
               PetAddress petAddress, double age, double weight, String breed) {
        this.name = name;
        this.type = type;
        this.petSex = petSex;
        this.petAddress = petAddress;
        this.age = age;
        this.weight = weight;
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public PetType getType() {
        return type;
    }

    public PetSex getPetSex() {
        return petSex;
    }

    public PetAddress getPetAddress() {
        return petAddress;
    }

    public double getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", petSex=" + petSex +
                ", petAddress=" + petAddress +
                ", age=" + age +
                ", weight=" + weight +
                ", breed='" + breed + '\'' +
                '}';
    }
}
