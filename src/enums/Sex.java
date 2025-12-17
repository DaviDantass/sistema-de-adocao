package enums;

public enum Sex {
    MALE("Male"),
    FEMALE("Female");

    private final String SEX;

    Sex(String sex) {
        this.SEX = sex;
    }

    public String getSEX() {
        return SEX;
    }

}