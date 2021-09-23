package ru.stqu.pft.addressbook.model;

import java.util.Objects;

public class UserData {
  private int id = Integer.MAX_VALUE;
  private String firstname;
  private String lastname;
  private String address;
  private String phone_home;
  private String email;
  private String email2;
  private String email3;
  private String group;
  private String mobilePhone;
  private String workPhone;

  public String getAllEmails() {
    return AllEmails;
  }

  public UserData withAllEmails(String allEmails) {
    AllEmails = allEmails;
    return this;
  }

  private String AllEmails;

  public String getAllPhones() {
    return allPhones;
  }

  public UserData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  private String allPhones;

  public UserData withId(int id) {
    this.id = id;
    return this;
  }
  public UserData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public UserData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public UserData withAddress(String address) {
    this.address = address;
    return this;
  }

  public UserData withPhone_home(String phone_home) {
    this.phone_home = phone_home;
    return this;
  }

  public UserData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }
  public UserData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }

  public UserData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public UserData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public UserData withGroup(String group) {this.group = group;return this;}

  public int getId() {return id;}


  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone_home()  {return phone_home;}

  public String getMobilePhone()  {return mobilePhone;}

  public String getWorkPhone()  {return workPhone;}

  public String getEmail() {return email;}

  public String getEmail2() {return email2;}

  public String getEmail3() {return email3;}

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
    return id == userData.id && Objects.equals(firstname, userData.firstname) && Objects.equals(lastname, userData.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }


}
