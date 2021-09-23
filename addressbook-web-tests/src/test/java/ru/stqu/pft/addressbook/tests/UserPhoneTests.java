package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.UserData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new UserData()
              .withFirstname("Dmitry").withLastname("Zagumenny").withAddress("Saint_Petersburg").withPhone_home("+7123456789").withEmail("qa@java.com").withGroup("test1"));
    }
  }

  @Test
  public void testContactPhone() {
    app.goTo().returnToHomePage();
    UserData user = app.contact().all().iterator().next();
    UserData userInfoFromEditForm = app.contact().infoFromEditionForm(user);

    assertThat(user.getAllPhones(), equalTo(mergePhones(userInfoFromEditForm)));
    assertThat(user.getAllEmails(), equalTo(mergeEmails(userInfoFromEditForm)));
    assertThat(user.getAddress(), equalTo(userInfoFromEditForm.getAddress()));
  }

  private String mergePhones(UserData user) {
    return Arrays.asList(user.getPhone_home(), user.getMobilePhone(), user.getWorkPhone())
            .stream().filter((s) -> ! s.equals(""))
            .map(UserPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(UserData user) {
    return Arrays.asList(user.getEmail(), user.getEmail2(), user.getEmail3())
            .stream().filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
  }

  public static String cleaned (String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
