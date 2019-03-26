package aplana;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Sol {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://speller.yandex.net/services/spellservice");

        String json = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:spel=\"http://speller.yandex.net/services/spellservice\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <spel:CheckTextRequest lang=\"en\" options=\"0\" format=\"\">\n" +
                "         <spel:text>toals</spel:text>\n" +
                "      </spel:CheckTextRequest>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept-Encoding","gzip,deflate");
        httpPost.setHeader("Content-Type","text/xml;charset=UTF-8");
        httpPost.setHeader("SOAPAction","http://speller.yandex.net/services/spellservice/checkText");

        CloseableHttpResponse response = client.execute(httpPost);
        String sResp = EntityUtils.toString(response.getEntity());
        System.out.println(sResp);
        client.close();
    }
}
