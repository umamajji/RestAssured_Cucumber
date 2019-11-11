import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.specification.RequestLogSpecification;
import com.jayway.restassured.specification.RequestSpecification;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.json.JSONObject;
import org.testng.Assert;
import com.jayway.restassured.*;
import org.testng.log4testng.Logger;

import static com.jayway.restassured.RestAssured.get;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
import static org.codehaus.groovy.tools.shell.util.Logger.io;

public class MyStepdefs {
    //private static final Logger logger = LoggerFactory.getLogger(MyStepdefs.class);
    String baseURI="http://restapi.demoqa.com/utilities/weather/city";

    RequestSpecification request = RestAssured.given();
    Response
    response=get("http://restapi.demoqa.com/utilities/weather/city/chennai");
    JSONObject requestParams;
    int empid=715;

    @Given("^I call get request to the API$")
    public void iCallGetRequestToTheAPI() throws Throwable {
      // response=get("http://restapi.demoqa.com/utilities/weather/city");
       int statuscode= response.getStatusCode();
       System.out.println(statuscode);
       try
       {
           if(statuscode==200||statuscode==201)
           {
               Assert.assertEquals(statuscode,200);
           }
           else
           {
               Assert.assertNotEquals(statuscode,200);
           }
       }
        catch (Exception e)
        {
            e.getMessage();
        }

    }

    @Then("^I Should see the response body$")
    public void iShouldSeeTheResponseBody() throws Throwable {
        // response=get("/chennai");
       // response=get("http://restapi.demoqa.com/utilities/weather/city/chennai");
        JsonPath jsonpathevaluator=response.jsonPath();

        ResponseBody responseBody=response.getBody();
        System.out.println(responseBody.asString());
        String body=responseBody.asString();
        Assert.assertTrue(true,body);
        System.out.println("City:"+jsonpathevaluator.get("City"));
        Assert.assertEquals(jsonpathevaluator.get("City"),"Chennai");
    }

    @Then("^I should see the response headers$")
    public void iShouldSeeTheResponseHeaders() throws Throwable {
       // response=get("http://restapi.demoqa.com/utilities/weather/city/chennai");
        response.headers().get("Content-Length");
        System.out.println(response.headers().get("Content-Length"));
        Assert.assertTrue(true,String.valueOf(response.headers().get("Content-Length")));
        //verify the content type
        String contenttype=response.header("Content-Type");
        System.out.println(contenttype);
        Assert.assertEquals(contenttype,"application/json");


    }


    @Given("^I call post request to the API$")
    public void iCallPostRequestToTheAPI() throws Throwable {
        RequestSpecification rest = RestAssured.given();

        Response response = RestAssured.given().contentType("application/json")
                .get("https://www.healthcare.gov/api/topics.json");
        // JSONObject JSONResponseBody = new JSONObject();
        String body = response.getBody().asString();
        System.out.println("result" + body);

        response = RestAssured.given()
                .contentType("application/json").

                        body("\"order\": \"<12>\",\r\n" +
                                "		\"meta-title\": \"<Get Insurance Health Insurance Marketplace>\"\r\n" +
                                "").
                        when().
                        post("https://www.healthcare.gov/api/topics.json");
        String body_post = response.getBody().asString();
        System.out.println(body_post);
        System.out.println("log" + rest.log().body());
    }

    @Given("^I call the put request and verify the response$")
    public void icallThePutRequestAndVerifyTheResponse() throws Throwable {

        RestAssured.baseURI ="http://dummy.restapiexample.com/";
        RequestSpecification request = RestAssured.given();

        requestParams = new JSONObject();
        requestParams.put("name", "tester"); // Cast
        requestParams.put("age", 20);
        requestParams.put("salary", 5000);

        request.body(requestParams.toString());
        Response response = request.put("/update/"+ empid);

        int statusCode = response.getStatusCode();
        System.out.println(response.asString());
        Assert.assertEquals(statusCode, 200);


    }

    @Given("^I call the delete request \"([^\"]*)\" and verify the response$")
    public void i_call_the_delete_request_and_verify_the_response(String uri) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
       // throw new PendingException();

        RestAssured.baseURI=uri;
        RequestSpecification request = RestAssured.given();
        request.header("contet-Type","application/json");
        /*requestParams=new JSONObject();
        requestParams.put("name","testerX");
        requestParams.put("age",20);
        requestParams.put("salary",3000);
        request.body(requestParams.toString());*/

        Response res=request.delete("/delete/"+empid);
        int statuscode_delete=res.getStatusCode();
        System.out.println(res.asString());
        System.out.println(statuscode_delete);
        Assert.assertEquals(res.asString(),"{\"success\":{\"text\":\"successfully! deleted Records\"}}");

    }

}


