package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.GroupData;
import ru.stqu.pft.addressbook.model.UserData;
import ru.stqu.pft.addressbook.model.Users;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validUsers() {
    File photo = new File("src/test/resources/stru.png");
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new UserData().withFirstname("Dmitry1").withLastname("Zagumenny1").withAddress("Saint_Petersburg1").withPhone_home("+7123456789-1").withEmail("qa@java1.com").withGroup("test1").withPhoto(photo)});
    list.add(new Object[] {new UserData().withFirstname("Dmitry2").withLastname("Zagumenny2").withAddress("Saint_Petersburg2").withPhone_home("+7123456789-2").withEmail("qa@java2.com").withGroup("test2").withPhoto(photo)});
    list.add(new Object[] {new UserData().withFirstname("Dmitry3").withLastname("Zagumenny3").withAddress("Saint_Petersburg3").withPhone_home("+7123456780-3").withEmail("qa@java3.com").withGroup("test3").withPhoto(photo)});
    return list.iterator();
  }

  @Test (dataProvider = "validUsers")
  public void testUserCreation(UserData user) throws Exception {
    app.returnToHomePage();
    Users before = app.contact().all();
    app.contact().create(user);
    app.returnToHomePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Users after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(user.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));
  }

}


