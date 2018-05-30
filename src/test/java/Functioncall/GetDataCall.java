package Functioncall;

import getRequest.GetData;
import org.testng.annotations.Test;

public class GetDataCall extends GetData {
    @Test
   public void dataCall(){
     GetData data = new GetData();
     data.testResponseCode();
     data.testBody();
     data.readArrayContents();
     data.responseTime();
     }
}
