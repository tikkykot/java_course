package ru.stqu.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().list().size() == 0) {
      app.contact().create(new UserData()
              .withFirstname("Dmitry").withLastname("Zagumenny").withAddress("Saint_Petersburg").withPhone_home("+7123456789").withEmail("qa@java.com").withGroup("test1"));
    }
  }

  @Test
  public void testUserModification() {
    List<UserData> before = app.contact().list();
    int index = before.size() - 1;
    UserData user = new UserData()
            .withId(before.get(index).getId()).withFirstname("Dmitry").withLastname("Zagumenny").withAddress("Saint_Petersburg").withPhone_home("+7123456789").withEmail("qa@java.com").withGroup("test1");
    app.contact().modify(index, user);
    List<UserData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(user);
    Comparator<? super UserData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }


}