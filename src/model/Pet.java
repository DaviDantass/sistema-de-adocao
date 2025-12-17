package model;

import enums.Sex;
import enums.Type;

public class Pet {
    private static Long id;
    private String name;
    private Type type;
    private Sex sex;
    private String address;
    private Double age;
    private Double weight;
    private String breed;

    public Pet() {}

    public Pet(String name, Type type, Sex sex, String address, Double age, Double weight, String breed) {
        this.name = name;
        this.type = type;
        this.sex = sex;
        this.address = address;
        this.age = age;
        this.weight = weight;
        this.breed = breed;
    }

    public String getName() {
        return name;
    }
    public Type getType() {
        return type;
    }
    public Sex getSex() {
        return sex;
    }
    public String getAddress() {
        return address;
    }
    public Double getAge() {
        return age;
    }
    public Double getWeight() {
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
                ", sex=" + sex +
                ", address=" + address +
                ", age=" + age +
                ", weight=" + weight +
                ", breed='" + breed + '\'' +
                '}';
    }
}
