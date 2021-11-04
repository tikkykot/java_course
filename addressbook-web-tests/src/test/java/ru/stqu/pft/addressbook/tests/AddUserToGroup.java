package ru.stqu.pft.addressbook.tests;


import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.UserData;

import ru.stqu.pft.addressbook.model.GroupData;
import ru.stqu.pft.addressbook.model.Groups;
import ru.stqu.pft.addressbook.model.Users;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.AssertJUnit.assertTrue;

public class AddUserToGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().users().size() == 0) {
      app.contact().create(new UserData()
              .withFirstname("Dmitry").withLastname("Zagumenny").withAddress("Saint_Petersburg").withPhone_home("+7123456789").withEmail("qa@java.com"));
    }
    app.db().groups();
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  void addUserToGroup() {
    Map<UserData, GroupData> groupToAddAndContact = getAloneUserAndGroupForIt(app.db().users(), app.db().groups());
    GroupData groupToAdd = null;
    UserData contact = null;
    for (Map.Entry<UserData, GroupData> map : groupToAddAndContact.entrySet()) {
      contact = map.getKey();
      groupToAdd = map.getValue();
    }
    app.contact().addToGroup(contact, groupToAdd);
    Groups groupsAfterOperation = getGroupsAfterOperation(contact);
    assertThat(groupsAfterOperation.size(), equalTo(contact.getGroups().size() + 1));
    int maxIdFromGroupsAfterOperation = groupsAfterOperation.stream()
            .mapToInt(g -> g.getId())
            .max()
            .getAsInt();
    assertThat(groupsAfterOperation, equalTo(contact.getGroups()
            .withAdded(groupToAdd
                    .withId(maxIdFromGroupsAfterOperation))));
  }

  private Groups getGroupsAfterOperation(UserData contactBeforeOperation) {
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

  public Map<UserData, GroupData> getAloneUserAndGroupForIt(Users contacts, Groups groups) {
    Map<UserData, GroupData> contactDataAndGroupData = new HashMap<>();
    for (UserData user : contacts) {
      Groups groupUser = user.getGroups();
       if (groups.size() != user.getGroups().size()) {

         for (GroupData group : groups) {
           if (!groupUser.contains(group)) {
             contactDataAndGroupData.put(user, group);
             return contactDataAndGroupData;
           }
         }
       }
    }
    if (contactDataAndGroupData.isEmpty()) {
      app.group().groupPage();
      GroupData groupToAdd = new GroupData().withName("test_new").withHeader("test_2").withFooter("test_3");
      app.group().create(groupToAdd);
      Groups after = app.db().groups();
      GroupData gr = groupToAdd.withId(after.stream().mapToInt((g) ->g.getId()).max().getAsInt());
      groupToAdd = gr;
      UserData contact = contacts.iterator().next();
      contactDataAndGroupData.put(contact, groupToAdd);
    }
    return contactDataAndGroupData;
  }
}
