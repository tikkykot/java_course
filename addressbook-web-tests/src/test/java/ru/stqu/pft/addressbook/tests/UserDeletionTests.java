package ru.stqu.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.UserData;

import java.util.List;

public class UserDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().list().size() == 0) {
      app.contact().create(new UserData()
              .withFirstname("Dmitry").withLastname("Zagumenny").withAddress("Saint_Petersburg").withPhone_home("+7123456789").withEmail("qa@java.com").withGroup("test1"));
    }
  }

  @Test
  public void testUserDeletion() throws Exception {
    List<UserData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<UserData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
      Assert.assertEquals(before, after);
    }

}
