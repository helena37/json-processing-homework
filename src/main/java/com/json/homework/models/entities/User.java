package com.json.homework.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private int age;
    private Set<Product> sellerProducts;
    private Set<Product> buyerProducts;
    private Set<User> friends;

    public User() {
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    @Length(min = 3, message = "Wrong size")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @OneToMany(mappedBy = "seller", targetEntity = Product.class)
    public Set<Product> getProducts() {
        return sellerProducts;
    }

    public void setProducts(Set<Product> products) {
        this.sellerProducts = products;
    }

    @OneToMany(mappedBy = "buyer", targetEntity = Product.class)
    public Set<Product> getBuyerProducts() {
        return buyerProducts;
    }

    public void setBuyerProducts(Set<Product> buyerProducts) {
        this.buyerProducts = buyerProducts;
    }

    @ManyToMany
    @JoinTable(name = "users_friends",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }
}
