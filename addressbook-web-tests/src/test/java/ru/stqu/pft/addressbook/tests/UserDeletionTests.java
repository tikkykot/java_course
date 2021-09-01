package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.Test;

public class UserDeletionTests extends TestBase{


  @Test
  public void testUserDeletion() throws Exception {
    app.selectUser();
    app.deleteSelectedUser();
    app.closeAlert();
    app.returnToHomePage1();
  }

}
