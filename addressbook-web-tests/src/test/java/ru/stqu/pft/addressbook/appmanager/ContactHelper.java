package ru.stqu.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqu.pft.addressbook.model.UserData;

import java.util.ArrayList;
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

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
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

  public void selectUser(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void returnToHomePage1() {
    click(By.linkText("home"));
  }

  public void initUserModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
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
  }
  public void modify(int index, UserData user) {
    selectUser(index);
    initUserModification(index);
    fillUserForm(user, false);
    submitUserModification();
    returnToHomePage1();
  }
  public void closeAlert() {
    wd.switchTo().alert().accept();
  }

  public void delete(int index) {
    selectUser(index);
    deleteSelectedUser();
    closeAlert();
    returnToHomePage1();
  }
  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<UserData> list() {
    List<UserData> users = new ArrayList<UserData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int lastNameNum = 1;
      int firstNameNum = 2;
      String lastname = cells.get(lastNameNum).getText();
      String firstname = cells.get(firstNameNum).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("Value"));
      users.add(new UserData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return users;
  }
}
