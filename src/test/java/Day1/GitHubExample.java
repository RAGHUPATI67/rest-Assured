package Day1;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class GitHubExample {
	
  @Test(enabled=false,description ="Getting all repositories")
  public void   getAllRepo(){
	  given()
	 .auth()
	 .oauth2("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")
	 .when()
	 .get("https://api.github.com/user/repos")
	 .then()
	 .log()
	 .body()
	 .statusCode(200)
	 .time(Matchers.lessThan(2000L),TimeUnit.MILLISECONDS);
  }
  @Test(enabled=true,description ="Adding repository")
  public void   addRepository(){
	  JSONObject js = new JSONObject();
	  js.put("name","tsl968-restAssured2");
	  js.put("description", "I am created by RestAssured");
	  js.put("homepage","http://github.com/RAGHUPATI67");
	  
	 given()
	 .auth()
	 .oauth2("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")
	 .header("Content-Type","application/json")
	 .body(js.toJSONString())
   .when()
	 .post("https://api.github.com/user/repos")
   .then()
	 .log()
	 .body()
	 .statusCode(201)
	 .time(Matchers.lessThan(5000L),TimeUnit.MILLISECONDS);
}}

