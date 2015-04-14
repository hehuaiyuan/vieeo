package org.vieeo.test.component.other
import static org.junit.Assert.assertEquals

import java.util.UUID;

import org.junit.Test

import com.vieeo.component.http.HttpClientComponent

class ArithmeticTest {
    @Test
    void additionIsWorking() {
        assertEquals 4, 2+2
    }

    @Test(expected=ArithmeticException)
    void divideByZero() {
        println 1/0
    }

    @Test
    void testHttp(){
        try {
			println UUID.randomUUID().toString().replaceAll("-", "")
            HttpClientComponent client = new HttpClientComponent();

            //String url = "http://api.thingture.com/Api/QueryAllProduct";

            //String url = "http://api.thingture.com/Api/QueryProductDetails";

            String url = "http://10.132.97.28:5050/yxcard-management-web/services/rest/consume/vertify";

            Map<String,String> params = new HashMap<String,String>();
            params.put("appId", "zf181");
            params.put("cardNo", "8013359900000500");
            params.put("gameCompanyType", "1");
            params.put("chargeAccount", "123456");
            params.put("version", "1.0");
            params.put("gameCompanyId", "105");
            params.put("userIp", "220.168.36.33");

            println client.doPost(url, params);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}

