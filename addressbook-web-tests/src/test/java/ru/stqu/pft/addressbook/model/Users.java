package ru.stqu.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<UserData> {

  private Set<UserData> delegate;

  public Users(Users users) {
    this.delegate = new HashSet<UserData>(users.delegate);
  }

  public Users() {
    this.delegate = new HashSet<UserData>();
  }

  public Users(Collection<UserData> users) {
    this.delegate = new HashSet<UserData>(users);
  }

  @Override
  protected Set<UserData> delegate() {
    return delegate;
  }

  public Users withAdded(UserData user) {
    Users users = new Users(this);
    users.add(user);
    return users;
  }

  public Users without(UserData user) {
    Users users = new Users(this);
    users.remove(user);
    return users;
  }

  public UserData getInfoOnUser(UserData contact) {
    UserData desiredUser = null;
    for(UserData contactData : delegate){
      if(contactData.getId() == contact.getId()){
        desiredUser = contactData;
        break;
      }
    }
    return desiredUser;
  }
  public static UserData getUserWithGroup(Users users) {
    UserData contactWithGroup = null;
    for (UserData con : users ){
      if(con.getGroups().size() > 0){
        contactWithGroup = con;
      }
    }
    return contactWithGroup;
  }

}