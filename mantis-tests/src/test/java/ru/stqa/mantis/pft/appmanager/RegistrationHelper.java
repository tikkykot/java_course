package ru.stqa.mantis.pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.mantis.pft.model.MailMessage;
import ru.stqa.mantis.pft.model.UserData;
import ru.stqa.mantis.pft.tests.TestBase;

import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeJava.type;

public class RegistrationHelper extends HelperBase {
  //private WebDriver wd;

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.cssSelector("input[type='submit']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.xpath("//form[@id='account-update-form']/fieldset/span/button/span"));
  }

  public void userAuth(String name, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login.php");
    type(By.name("username"), name);
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));
  }

  public String findConfimLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter(m -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  public void resetUserPassword(String username){
    wd.get(app.getProperty("web.baseUrl") + "/manage_overview_page.php");
    click(By.xpath("//div[@id='main-container']/div[2]/div[2]/div/ul/li[2]/a"));
    click(By.linkText(username));
    click(By.xpath("//form[@id='manage-user-reset-form']/fieldset/span/input"));
  }

  private UserData anyUser(List<UserData> allUsers) {
    List<UserData> copy = new ArrayList<>(allUsers);
    for (UserData user : copy){
      if(user.getUsername().equals("administrator")){
        allUsers.remove(user);
      }
    }
    return allUsers.get((int)Math.random() * allUsers.size());
  }
}