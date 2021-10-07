package ru.stqu.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqu.pft.addressbook.model.GroupData;
import ru.stqu.pft.addressbook.model.UserData;
import ru.stqu.pft.addressbook.model.Users;

import java.util.List;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitUserCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillUserForm(UserData userData, boolean creation) {
    type(By.name("firstname"), userData.getFirstname());
    type(By.name("lastname"), userData.getLastname());
    type(By.name("address"), userData.getAddress());
    type(By.name("home"), userData.getPhone_home());
    type(By.name("email"), userData.getEmail());
    attach(By.name("photo"), userData.getPhoto());

    if (creation) {
      if (userData.getGroups().size() > 0) {
        Assert.assertTrue(userData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initUserCreation() {
    click(By.linkText("add new"));
  }

  public void deleteSelectedUser() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectUserById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void returnToHomePage1() {
    click(By.linkText("home"));
  }

 // public void initUserModification(int index) {
 //   wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
 // }

 // public void initUserModification(int id) {
 //   wd.findElement(By.xpath("//edit.php?id=" + id + "']")).click();
 // }

 public void initUserModificationById(int id) {
   wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
}

  public void submitUserModification() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public boolean isThereAUser() {
    return isElementPresent(By.name("selected[]"));
  }

  public void create(UserData user) {
    initUserCreation();
    fillUserForm(user, true);
    submitUserCreation();
    userCache = null;
  }
  public void modify(UserData user) {
    selectUserById(user.getId());
    initUserModificationById(user.getId());
    fillUserForm(user, false);
    submitUserModification();
    userCache = null;
    returnToHomePage1();
  }
  public void closeAlert() {
    wd.switchTo().alert().accept();
  }


  public void delete(UserData user) {
    selectUserById(user.getId());
    deleteSelectedUser();
    closeAlert();
    userCache = null;
    returnToHomePage1();
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Users userCache = null;

  public Users all() {
    if (userCache != null) {
      return new Users(userCache);
    }
    userCache = new Users();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      userCache.add(new UserData().withId(id).withFirstname(firstname).withLastname(lastname).withAllEmails(allEmails)
              .withAllPhones(allPhones).withAddress(address));
    }
    return new Users(userCache);
  }


  public UserData infoFromEditionForm(UserData user) {
    initUserModificationById(user.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new UserData().withId(user.getId()).withFirstname(firstname).withLastname(lastname).withPhone_home(home).withMobilePhone(mobile).withWorkPhone(work).
            withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  public void selectGroupByname(String name) {
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(name);

  }

  public void addToGroup(UserData contact, GroupData group) {
    returnToHomePage1();
    selectGroupByname(group.getName());
    selectUserById(contact.getId());
    click(By.name("add"));
  }

  public void removeFromGroup(UserData user, GroupData groupContact) {
    groupSearch(groupContact.getName());
    selectUserById(user.getId());
    removeGroup();
  }

  public void removeGroup() {
    click(By.name("remove"));
  }
  public void groupSearch(String nameGroup) {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(nameGroup);
  }
}
