package testModels;

import java.util.List;

public class Man {
    private String name;
    private int age;
    private List<String> favoriteBooks;

    private Phone phone;

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Man(String name, int age, List<String> favoriteBooks, Phone phone) {
        this.name = name;
        this.age = age;
        this.favoriteBooks = favoriteBooks;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getFavoriteBooks() {
        return favoriteBooks;
    }

    public void setFavoriteBooks(List<String> favoriteBooks) {
        this.favoriteBooks = favoriteBooks;
    }

}
