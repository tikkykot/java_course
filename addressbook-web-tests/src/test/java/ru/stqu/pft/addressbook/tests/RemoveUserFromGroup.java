package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.GroupData;
import ru.stqu.pft.addressbook.model.Groups;
import ru.stqu.pft.addressbook.model.UserData;

import java.util.Set;

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
      app.group().create(newGroup);
      assertTrue(app.db().groups().contains(newGroup.withId
              (app.db().groups().stream().mapToInt((g) -> g.getId()).max().getAsInt())));
    } else {
      newGroup = app.db().groups().iterator().next();
    }

    if (app.db().users().size() == 0){
      newUser = new UserData().
      withFirstname("Dmitry").withLastname("Zagumenny").withAddress("Saint_Petersburg").withPhone_home("+7123456789").withEmail("qa@java.com");
      app.returnToHomePage();
      app.contact().create(newUser);
      assertTrue(app.db().users().contains(newUser.withId
              (app.db().users().stream().mapToInt((c) -> c.getId()).max().getAsInt())));
      app.contact().addToGroup(newUser, newGroup);
      Set<GroupData> usersGroupsAfter = app.db().userById(newUser.getId()).getGroups();
      assertTrue(usersGroupsAfter.contains(newGroup));
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
