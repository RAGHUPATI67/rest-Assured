package Day1;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class PositiveTest {


String id;
	@Test(enabled = false, description = "For getting all Contact List")
	public void getAllContactList() {
		given().when().get("http://3.13.86.142:3000/contacts")
		.then()
		.log()
		.body()
		.statusCode(200);
	}

	@Test(enabled = true, description = "Adding Contact")
	public void addContact() {
		JSONObject loc = new JSONObject();
		loc.put("city", "Pune");
		loc.put("country", "India");
		JSONObject emp = new JSONObject();
		emp.put("jobTitle", "Automation Tester");
		emp.put("company", "LTI");
		JSONObject ob = new JSONObject();
		ob.put("firstName", "Mayank");
		ob.put("lastName", "sharma");
		ob.put("email", "asmith@thinkingtester.com");
		ob.put("location", loc);
		ob.put("employer", emp);

		 id = given().header("Content-Type", "application/json").body(ob.toJSONString()).when()
				.post(" http://3.13.86.142:3000/contacts")
				.then()
				.log()
				.body()
				.statusCode(200)
				.extract()
				.jsonPath()
				.get("_id");
		System.out.println("ID is " + id);

	}
	@Test(enabled=true,description="Getting Contact")
	public void getContact() {
		  
		  given()
		  .when()
		 .get("http://3.13.86.142:3000/contacts"+id)	
		  .then()
		  .log()
		  .body()
		  .statusCode(404);
	}
	@Test(enabled = true,dependsOnMethods="addContact", description = "updateContact")
	public void updateContact() {
		JSONObject loc = new JSONObject();
		loc.put("city", "Pune");
		loc.put("country", "India");
		JSONObject emp = new JSONObject();
		emp.put("jobTitle", "Automation Tester");
		emp.put("company", "LTI");
		JSONObject ob = new JSONObject();
		ob.put("firstName", "Mayank");
		ob.put("lastName", "sharma");
		ob.put("email", "asmith@thinkingtester.com");
		ob.put("location", loc);
		ob.put("employer", emp);
		
		given().header("Content-Type", "application/json")
		.body(ob.toJSONString()).when()
		.put(" http://3.13.86.142:3000/contacts"+id)
		.then()
		.log()
		.body()
		.statusCode(404);
	}
	@Test(enabled = true,dependsOnMethods="updateContact",description = "deleting Specification Contact")
	public void deleteSpecificationContact() {

		  given()
		  .when()
		 .delete("http://3.13.86.142:3000/contacts/"+id)	
		  .then()
		  .log()
		  .body()
		  .statusCode(204);

	}}

