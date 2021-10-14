package ru.stqa.mantis.pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.mantis.pft.tests.TestBase;

import static jdk.nashorn.internal.objects.NativeJava.type;

public class RegistrationHelper extends HelperBase {
  //private WebDriver wd;

  public RegistrationHelper (ApplicationManager app) {
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
}
