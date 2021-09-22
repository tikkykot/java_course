package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.UserData;
import ru.stqu.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase{


  @Test
  public void testUserCreation() throws Exception {
    Users before = app.contact().all();
    UserData user = new UserData()
            .withFirstname("Dmitry").withLastname("Zagumenny").withAddress("Saint_Petersburg").withPhone_home("+7123456789").withEmail("qa@java.com").withGroup("test1");
    app.contact().create(user);
    app.returnToHomePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Users after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(user.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));
  }
}
