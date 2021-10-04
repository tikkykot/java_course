package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.GroupData;
import ru.stqu.pft.addressbook.model.Groups;
import ru.stqu.pft.addressbook.model.UserData;
import ru.stqu.pft.addressbook.model.Users;

import java.sql.*;

public class DbConnectionTest {

  @Test
  public void testDbConnectionGroups() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?" + "user=root&password=");
      Statement st = conn.createStatement();
      final ResultSet rs = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
      Groups groups = new Groups();
      while (rs.next()) {
        groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
                .withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
      }
      rs.close();
      st.close();
      conn.close();
      System.out.println(groups);
      // Do something with the Connection

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }

  @Test
  public void testDbConnectionUsers() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?" + "user=root&password=");
      Statement st = conn.createStatement();
      final ResultSet rs = st.executeQuery("select id, lastname, firstname, address, email, home from addressbook");
      Users users = new Users();
      while (rs.next()) {
        users.add(new UserData().withId(rs.getInt("id")).withLastname(rs.getString("lastname"))
                .withFirstname(rs.getString("firstname")).withAddress(rs.getString("address"))
                .withEmail(rs.getString("email")).withPhone_home(rs.getString("home")));
      }
      rs.close();
      st.close();
      conn.close();
      System.out.println(users);
      // Do something with the Connection

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}
