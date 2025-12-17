package enums;

public enum Type {
    DOG("Dog"),
    CAT("Cat");

    private final String TYPE;

    Type(String type) {
        this.TYPE = type;
    }

    public String getTYPE() {
        return TYPE;
    }
}