package getRequest;

import Constants.Constants;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetData extends Constants {

    @Test(priority = 1)
    public void testResponseCode() {
        Response response = get(APILink);
        int code          = response.getStatusCode();
        System.out.println("status code is  " + code);
        Assert.assertEquals(code, 200);
    }
    @Test(priority = 4)
    public void testBody() {
        Response response = get(APILink);
        String data       = response.asString();
        String json       = response.asString();
        JsonPath jp       = new JsonPath(json);
        System.out.println(data);
        System.out.println((String) jp.get("name"));
    }
    @Test(priority = 3)
    public void readArrayContents(){
        Response response   = get(APILink);
        String responsedata = response.asString();
        JSONObject root     = new JSONObject(responsedata );
        JSONObject data     = root.getJSONObject("data");
        System.out.println(root);
        System.out.println(data);
      //  JSONArray featureArray   = data.getJSONArray("feature");
        JSONArray featureArray   = data.getJSONArray("rooms");
        for (int i=0; i < featureArray.length(); i++) {
            System.out.println(featureArray.getJSONObject(i));
            JSONObject firstfeature   = featureArray.getJSONObject(i);
            String     name           = firstfeature.getString("name");
            int id                    = firstfeature.getInt("seatingCapacity");
            checkData(name,id);
            System.out.println("name =  "+ name);
            System.out.println("id = "+ id);
        }
        }
        public void checkData(String name,int id){
        Assert.assertNotEquals(name,"");
     }

     @Test(priority = 2)
    public void responseTime(){
        Response response   = get(APILink);
        System.out.println(response.getTime());
        int responsetime = (int) response.getTime();
        Assert.assertSame(responsetime,800);

     }
}
