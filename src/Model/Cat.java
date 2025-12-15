package Model;

public class Cat extends Animal {
    public Cat(String name, String type, String race, char sex, String address, int age, double weight) {
        super(name, type, race, sex, address, age, weight);
    }
    @Override
    public String getType() {
        return "Cat";
    }
}
