package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.Test;

public class UserDeletionTests extends TestBase{


  @Test
  public void testUserDeletion() throws Exception {
    app.getContactHelper().selectUser();
    app.getContactHelper().deleteSelectedUser();
    app.closeAlert();
    app.getContactHelper().returnToHomePage1();
  }

}
