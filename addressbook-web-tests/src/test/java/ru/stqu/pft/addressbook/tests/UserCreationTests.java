package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqu.pft.addressbook.model.UserData;

public class UserCreationTests extends TestBase{


  @Test
  public void testUserCreation() throws Exception {
    app.initUserCreation();
    app.fillUserForm(new UserData("Dmitry", "Zagumenny", "Saint_Petersburg", "+7123456789", "qa@java.com"));
    app.submitUserCreation();
    app.returnToHomePage();
  }

}
