package ru.stqu.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.UserData;

import ru.stqu.pft.addressbook.model.GroupData;
import ru.stqu.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class AddUserToGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().users().size() == 0) {
      app.contact().create(new UserData()
              .withFirstname("Dmitry").withLastname("Zagumenny").withAddress("Saint_Petersburg").withPhone_home("+7123456789").withEmail("qa@java.com"));//.withGroup("test1"));
    }
    app.db().groups();
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  void addUserToGroupTest() {
    UserData contact = app.db().users().iterator().next();
    GroupData groupToAdd = prepareGroupsBeforeOperation(contact);
    Groups groupsAfterOperation = getGroupsBeforeOperation(contact);
    app.contact().addToGroup(contact, groupToAdd);
    assertThat(groupsAfterOperation.size(), equalTo(contact.getGroups().size() + 1));
    int maxIdFromGroupsAfterOperation = groupsAfterOperation.stream()
            .mapToInt(g -> g.getId())
            .max()
            .getAsInt();
    assertThat(groupsAfterOperation, equalTo(contact.getGroups()
            .withAdded(groupToAdd
                    .withId(maxIdFromGroupsAfterOperation))));
  }

  private Groups getGroupsBeforeOperation(UserData contactBeforeOperation) {
    UserData contactAfterOperation = app.db().users().getInfoOnUser(contactBeforeOperation);
    return contactAfterOperation.getGroups();
  }

  public GroupData prepareGroupsBeforeOperation(UserData contact) {
    Groups contactGroups = contact.getGroups();
    Groups allGroups = app.db().groups();
    return getGroupDataToAdd(allGroups, contactGroups);
  }

  private GroupData getGroupDataToAdd(Groups allGroups, Groups contactGroups) {
    GroupData groupToAdd;
    for (GroupData groupContact : contactGroups) {
      if (allGroups.contains(groupContact)) {
        allGroups.remove(groupContact);
      }
    }
    if (allGroups.isEmpty()) {
      app.group().groupPage();
      groupToAdd = new GroupData().withName("test_new").withHeader("test 2").withFooter("test 3");
      app.group().create(groupToAdd);
    } else {
      groupToAdd = allGroups.iterator().next();
    }
    return groupToAdd;
  }
}
