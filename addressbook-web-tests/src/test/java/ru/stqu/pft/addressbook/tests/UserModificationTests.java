package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.UserData;
import ru.stqu.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new UserData()
              .withFirstname("Dmitry").withLastname("Zagumenny").withAddress("Saint_Petersburg").withPhone_home("+7123456789").withEmail("qa@java.com").withGroup("test1"));
    }
  }

  @Test
  public void testUserModification() {
    Users before = app.contact().all();
    UserData modifiedUser = before.iterator().next();
    UserData user = new UserData()
            .withId(modifiedUser.getId()).withFirstname("Dmitry").withLastname("Zagumenny").withAddress("Saint_Petersburg").withPhone_home("+7123456789").withEmail("qa@java.com").withGroup("test1");
    app.contact().modify(user);
    assertThat(app.contact().count(), equalTo(before.size()));
    Users after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));
  }


}