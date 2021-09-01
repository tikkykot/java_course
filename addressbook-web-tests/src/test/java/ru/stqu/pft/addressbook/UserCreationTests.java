package ru.stqu.pft.addressbook;

import org.testng.annotations.*;

public class UserCreationTests extends TestBase{


  @Test
  public void testUserCreation() throws Exception {
    initUserCreation();
    fillUserForm(new UserData("Dmitry", "Zagumenny", "Saint_Petersburg", "+7123456789", "qa@java.com"));
    submitUserCreation();
    returnToHomePage();
  }

}
