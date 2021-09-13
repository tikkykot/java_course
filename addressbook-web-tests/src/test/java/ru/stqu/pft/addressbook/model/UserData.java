package ru.stqu.pft.addressbook.model;

import java.util.Objects;

public class UserData {
  private final String id;
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String phone_home;
  private final String email;
  private String group;

  public String getId() {
    return id;
  }
  public UserData(String firstname, String lastname, String address, String phone_home, String email, String group, boolean b) {
    this.id = null;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.phone_home = phone_home;
    this.email = email;
    this.group = group;
  }
  public UserData(String id, String firstname, String lastname, String address, String phone_home, String email, String group, boolean b) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.phone_home = phone_home;
    this.email = email;
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone_home() {
    return phone_home;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "UserData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return Objects.equals(id, userData.id) && Objects.equals(firstname, userData.firstname) && Objects.equals(lastname, userData.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }
}
