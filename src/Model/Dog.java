package Model;

public class Dog extends Animal {

    public Dog(String name, String type, String race, char sex, String address, int age, double weight) {
        super(name, type, race, sex, address, age, weight);
    }

    @Override
    public String getType() {
        return "Dog";
    }
}
