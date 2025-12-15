package Model;

public abstract class Animal {
    private String name;
    private String type;
    private String race;
    private char sex;
    private String address;
    private int age;
    private double weight;

    public Animal(String name, String type, String race, char sex, String address, int age, double weight) {
        this.name = name;
        this.type = type;
        this.race = race;
        this.sex = sex;
        this.address = address;
        this.age = age;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getRace() {
        return race;
    }
    public char getSex() {
        return sex;
    }
    public String getAddress() {
        return address;
    }
    public int getAge() {
        return age;
    }
    public double getWeight() {
        return weight;
    }
}
