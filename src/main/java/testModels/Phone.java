package testModels;

public class Phone {
    private String model;
    private String number;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Phone(String model, String number) {
        this.model = model;
        this.number = number;
    }
}
