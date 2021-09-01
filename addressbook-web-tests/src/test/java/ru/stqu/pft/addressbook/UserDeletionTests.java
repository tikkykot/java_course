package ru.stqu.pft.addressbook;

import org.testng.annotations.Test;

public class UserDeletionTests extends TestBase{


  @Test
  public void testUserDeletion() throws Exception {
    selectUser();
    deleteSelectedUser();
    closeAlert();
    returnToHomePage1();
  }

}
