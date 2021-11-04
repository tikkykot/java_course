package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.GroupData;
import ru.stqu.pft.addressbook.model.Groups;
import ru.stqu.pft.addressbook.model.UserData;
import ru.stqu.pft.addressbook.model.Users;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;


public class RemoveUserFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    GroupData newGroup = new GroupData();
    UserData newUser = new UserData();

    if (app.db().groups().size() == 0) {
      newGroup = new GroupData().withName("test1");
      app.goTo().groupPage();
      Groups before = app.db().groups();
      app.group().create(newGroup);
      assertThat(app.group().count(), equalTo(before.size() + 1));
    }

    if (app.db().users().size() == 0){
      newUser = new UserData().
      withFirstname("Dmitry").withLastname("Zagumenny").withAddress("Saint_Petersburg").withPhone_home("+7123456789").withEmail("qa@java.com");
      app.returnToHomePage();
      Users before = app.db().users();
      app.contact().create(newUser);
      assertThat(app.contact().count(), equalTo(before.size() + 1));
    }
  }

  @Test
  public void removeUserFromGroupTest(){
    UserData changedUser = app.db().users().iterator().next();
    Groups groups = app.db().groups();
    GroupData deleteGroupFrom = new GroupData();
    app.returnToHomePage();
    if (changedUser.getGroups().size() == 0) {
      deleteGroupFrom = groups.iterator().next();
      app.contact().addToGroup(changedUser, deleteGroupFrom);
      Set<GroupData> contactGroupsAfter = app.db().userById(changedUser.getId()).getGroups();
      assertTrue(contactGroupsAfter.contains(deleteGroupFrom));
    } else {
      deleteGroupFrom = changedUser.getGroups().iterator().next();
    }
    app.contact().selectedUsersInGroups(deleteGroupFrom);
    app.contact().deleteFromGroup(changedUser);
    Set<GroupData> contactGroupsAfter = app.db().userById(changedUser.getId()).getGroups();
    assertFalse(contactGroupsAfter.contains(deleteGroupFrom));
  }

}
