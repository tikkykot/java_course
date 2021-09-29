package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.GroupData;
import ru.stqu.pft.addressbook.model.UserData;
import ru.stqu.pft.addressbook.model.Users;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validUsers() throws IOException {
    File photo = new File("src/test/resources/stru.png");
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[] {(new UserData().withFirstname(split[0]).withLastname(split[1]).withAddress(split[2])
              .withPhone_home(split[3]).withEmail(split[4]).withGroup(split[5]).withPhoto(photo))});
      line = reader.readLine();
    }
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


