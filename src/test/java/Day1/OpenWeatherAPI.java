package Day1;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OpenWeatherAPI {
  @Test
  public void getWeatherInfo() {
	  RestAssured.given()
	  .when()
	  .get("f0cf45f69411773e1457cdaf5f1bfeed?q=chennai&appid=f0cf45f69411773e1457cdaf5f1bfeed")
	  .then()
	  .log()
	  .body()
	  .statusCode(200);
	  }
  @Test(enabled=false, description = "Getting weather API information generally")
  public void getWeatherInfo2() {
	  Response res = RestAssured.given()
			  .when()
			  .get("https://api.openweathermap.org/data/2.5/weather?q=Mumbai&appid=f0cf45f69411773e1457cdaf5f1bfeed");
  System.out.println(res.prettyPrint());
  System.out.println(res.getTime());
  System.out.println(res.getStatusCode());
  System.out.println(res.getContentType());
  
  
  }
  @Test(enabled=true, description = "Getting weather API information generally")
  public void getWeatherInfo3() {
	  Map<String,String> param=new HashMap<String,String>();	  
	  param.put("q", "Mumbai");
	  param.put("appid", "f0cf45f69411773e1457cdaf5f1bfeed");
	  RestAssured.given()
	  //.queryParam("q", "Mumbai")
	  //.queryParam("appid", "f0cf45f69411773e1457cdaf5f1bfeed")
	  .params(param)
	  .when()
	  .get("https://api.openweathermap.org/data/2.5/weather")
	  .then()
	  .log()
	  .body()
	  .statusCode(200);
  
  }
}