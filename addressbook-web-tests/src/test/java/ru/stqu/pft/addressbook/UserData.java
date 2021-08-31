package ru.stqu.pft.addressbook;

public class UserData {
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String phone_home;
  private final String email;

  public UserData(String firstname, String lastname, String address, String phone_home, String email) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.phone_home = phone_home;
    this.email = email;
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
}
