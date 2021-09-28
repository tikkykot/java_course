package ru.stqu.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqu.pft.addressbook.model.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserDataGenerator {

  @Parameter(names = "-c", description = "User count")
  public int count;

  @Parameter (names = "-f", description = "Target files")
  public String file;

  public static void main(String[] args) throws IOException {
    UserDataGenerator generator = new UserDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    }
    catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<UserData> users = generateUsers(count);
    save(users, new File(file));
  }

  private void save(List<UserData> users, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (UserData user : users) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s\n", user.getFirstname(), user.getLastname(), user.getAddress(), user.getPhone_home(), user.getEmail(), user.getGroup()));
    }
    writer.close();
  }

  private List<UserData> generateUsers(int count) {
    List<UserData> users = new ArrayList<UserData>();
    for (int i = 0; i < count; i++) {
      users.add(new UserData().withFirstname(String.format("Dmitry %s", i)).withLastname(String.format("Zagumenny %s", i))
              .withAddress(String.format("Saint-Petersburg %s", i)).withPhone_home(String.format("+7123456 %s", i))
              .withEmail(String.format("qa@java %s" + ".com", i)).withGroup(String.format("test %s", i)));
    }
    return users;
  }
}
