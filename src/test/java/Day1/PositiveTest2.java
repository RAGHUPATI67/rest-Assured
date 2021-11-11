package Day1;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
public class PositiveTest2 {
	String id;
  @Test(enabled=false,description="for getting all contact list")
  public void getAllContactList() {
	  given()
	  .when()
	  .get("http://3.13.86.142:3000/contacts")
	  .then()
	  .log()
	  .body()
	  .statusCode(200);
  }
  @Test(enabled=true,description="adding contact ")
  public void putContact() {
	  JSONObject loc=new JSONObject();
	  loc.put("city","pune");
	  loc.put("country", "India");
	  
	  JSONObject emp=new JSONObject();
	  emp.put("jobTitle", "Automation Testing");
	  emp.put("company","LTI");
	  
	  JSONObject ob=new JSONObject();
	  ob.put("firstName", "Mayank");
	  ob.put("lastName","Sharma");
	  ob.put("email","asmith@thinkingtester.com");
	  ob.put("location",loc);
	  ob.put("employer",emp);
	  
	   id= given()
	  .header("Content-Type","application/json")
	  .body(ob.toJSONString())//to convert object in jason type
	  .when()
	  .post("http://3.13.86.142:3000/contacts")
	  .then()
	  .log()
	  .body()
	  .statusCode(200)
	  .extract()// to extract id ,after contact creation every contact has a id
	  .jsonPath()
	  .get("_id");
	 System.out.println("ID is "+id);
  }
  @Test(enabled=true,dependsOnMethods="putContact",description="getting specific contact ")
  public void getSpecificContact() {
	  given()
	  .when()
	     .get("http://3.13.86.142:3000/contacts/"+id)
	  .then()
	      .log()
	      .body()
	      .statusCode(200);
	  
  
  }
  @Test(enabled=true,dependsOnMethods="getSpecificContact",description="adding contact ")
  public void updateContact() {
	  JSONObject loc=new JSONObject();
	  loc.put("city","pune");
	  loc.put("country", "India");
	  
	  JSONObject emp=new JSONObject();
	  emp.put("jobTitle", "Automation Testing");
	  emp.put("company","LTI");
	  
	  JSONObject ob=new JSONObject();
	  ob.put("firstName", "Sheetal");
	  ob.put("lastName","Sharma");
	  ob.put("email","asmith@thinkingtester.com");
	  ob.put("location",loc);
	  ob.put("employer",emp);
	  
	   given()
	  .header("Content-Type","application/json")
	  .body(ob.toJSONString())//to convert object in jason type
	  .when()
	  .put("http://3.13.86.142:3000/contacts"+id)
	  .then()
	  .log()
	  .body()
	  .statusCode(204);
	  
  }
  @Test(enabled=true,dependsOnMethods="updateContact",description="for getting all contact list")
  public void deleteContact() {
	  given()
	  .when()
	  .get("http://3.13.86.142:3000/contacts/"+id)
	  .then()
	  .log()
	  .body()
	  .statusCode(200);
  }
  
}