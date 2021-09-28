package ru.stqu.pft.addressbook.generators;

import ru.stqu.pft.addressbook.model.GroupData;
import ru.stqu.pft.addressbook.model.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserDataGenerator {

  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<UserData> users = generateUsers(count);
    save(users, file);
  }

  private static void save(List<UserData> users, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (UserData user : users) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s\n", user.getFirstname(), user.getLastname(), user.getAddress(), user.getPhone_home(), user.getEmail(), user.getGroup()));
    }
    writer.close();
  }

  private static List<UserData> generateUsers(int count) {
    List<UserData> users = new ArrayList<UserData>();
    for (int i = 0; i < count; i++) {
      users.add(new UserData().withFirstname(String.format("Dmitry %s", i)).withLastname(String.format("Zagumenny %s", i))
              .withAddress(String.format("Saint-Petersburg %s", i)).withPhone_home(String.format("+7123456 %s", i))
              .withEmail(String.format("qa@java %s" + ".com", i)).withGroup(String.format("test %s", i)));
    }
    return users;
  }
}
