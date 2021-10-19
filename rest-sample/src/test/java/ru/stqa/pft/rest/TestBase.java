package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import java.util.Set;

public class TestBase {
  public boolean isIssueOpen(int issueId){
    String detailsIssueString = RestAssured.given()
            .get(String.format("https://bugify.stqa.ru/api/issues/%s.json",issueId)).asString();
    JsonElement detailIssueElement = new JsonParser().parseString(detailsIssueString).getAsJsonObject().get("issues");
    Set<Issue> set = new Gson().fromJson(detailIssueElement, new TypeToken<Set<Issue>>(){}.getType());
    Issue issue = set.iterator().next();
    if(issue.getState_name().equals("Closed")) {
      return true;
    } else {
      return false;
    }
  }

  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
