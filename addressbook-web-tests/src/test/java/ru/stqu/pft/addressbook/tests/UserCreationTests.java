package ru.stqu.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserCreationTests extends TestBase{


  @Test
  public void testUserCreation() throws Exception {
    List<UserData> before = app.contact().list();
    UserData user = new UserData("Dmitry", "Zagumenny", "Saint_Petersburg", "+7123456789", "qa@java.com", "test1", true);
    app.contact().create(user);
    app.returnToHomePage();
    List<UserData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);


    before.add(user);
    Comparator<? super UserData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
