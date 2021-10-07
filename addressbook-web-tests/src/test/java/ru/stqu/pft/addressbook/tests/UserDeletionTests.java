package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.UserData;
import ru.stqu.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().users().size() == 0) {
      app.contact().create(new UserData()
              .withFirstname("Dmitry").withLastname("Zagumenny").withAddress("Saint_Petersburg").withPhone_home("+7123456789").withEmail("qa@java.com"));//.withGroup("test1"));
    }
  }

  @Test
  public void testUserDeletion() throws Exception {
    app.returnToHomePage();
    Users before = app.db().users();
    UserData deletedUser = before.iterator().next();
    app.contact().delete(deletedUser);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Users after = app.db().users();
    assertThat(after, equalTo(before.without(deletedUser)));
    verifyUserListInUI();
    }
}
