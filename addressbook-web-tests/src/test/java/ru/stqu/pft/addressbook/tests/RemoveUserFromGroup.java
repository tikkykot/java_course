package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.UserData;
import ru.stqu.pft.addressbook.model.Users;
import ru.stqu.pft.addressbook.model.GroupData;
import ru.stqu.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class RemoveUserFromGroup extends TestBase {

  @BeforeMethod
  //public void ensurePreconditions() {
  // AddUserToGroup  helper = new AddUserToGroup();
  //  helper.ensurePreconditions();
  //  Users allUsers = app.db().users();
  //  boolean isAllUsers = allUsers.stream().allMatch(contactData -> contactData.getGroups().size() > 0);
  //  for (UserData contact : allUsers){
  //    if(contact.getGroups().size() > 0){
  //      return;
  //    }
  //  }
  //  helper.addUserToGroupTest();
  //  }
  //public void ensurePreconditions() {
  // UserData contact = app.db().users().iterator().next();
  //  GroupData groupToAdd = new AddUserToGroup().prepareGroupsBeforeOperation(contact);
  //  app.contact().addToGroup(contact, groupToAdd);}

  public void ensurePreconditions() {
    if (app.db().users().size() == 0) {
      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("test1"));
      }
      Groups groups = app.db().groups();
      app.returnToHomePage();
      app.contact().initUserCreation();
      app.contact().create(new UserData()
              .withFirstname("Dmitry").withLastname("Zagumenny").withAddress("Saint_Petersburg").withPhone_home("+7123456789").withEmail("qa@java.com")
              .inGroup(groups.iterator().next()));
      app.returnToHomePage();
      UserData contact = app.db().users().iterator().next();
      app.contact().addToGroup(contact, groups.iterator().next());
      app.returnToHomePage();
    }
  }

  @Test
  public void removeUserFromGroupTest(){
    app.returnToHomePage();
    UserData before = Users.getUserWithGroup(app.db().users());
    Groups groupsBefore = before.getGroups();
    GroupData deletedGroup = before.getGroups().iterator().next();
    app.contact().removeFromGroup(before, deletedGroup);
    UserData after = app.db().users().getInfoOnUser(before);
    Groups groupsAfter = after.getGroups();
    assertThat(groupsAfter.size(), equalTo(groupsBefore.size()-1));
    assertThat(groupsAfter, equalTo(groupsBefore.without(deletedGroup)));

  }

}
