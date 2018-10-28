import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import util.Util;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class IntegrationTest {
    public static final String DELTA = "5";
    private static HttpClient client;
    private static PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
    private static final int PORT = Util.getIntegerProperty("port", 9998);
    private URIBuilder builder = new URIBuilder().setScheme("http").setHost("localhost:" + PORT);

    @BeforeClass
    public static void setup() {
        connManager.setDefaultMaxPerRoute(100);
        connManager.setMaxTotal(200);
        client = HttpClients.custom()
                .setConnectionManager(connManager)
                .setConnectionManagerShared(true)
                .build();
    }

    @Test
    @Ignore
    public void testTransfer() throws URISyntaxException, IOException {
        URI uriScore1 = builder.setPath("/score").addParameter("scoreId", "1")
                .build();
        builder.clearParameters();
        URI uriScore2 = builder.setPath("/score").addParameter("scoreId", "2")
                .build();
        HttpGet getForScore1 = new HttpGet(uriScore1);
        HttpGet getForScore2 = new HttpGet(uriScore2);
        HttpResponse response1 = client.execute(getForScore1);
        HttpResponse response2 = client.execute(getForScore2);
        String jsonString1 = EntityUtils.toString(response1.getEntity());
        String jsonString2 = EntityUtils.toString(response2.getEntity());
        BigDecimal balance1 = new JSONObject(jsonString1).getBigDecimal("balance");
        BigDecimal balance2 = new JSONObject(jsonString2).getBigDecimal("balance");
        BigDecimal subBalance1 = balance1.subtract(new BigDecimal(DELTA));
        BigDecimal addBalance2 = balance2.add(new BigDecimal(DELTA));
        URI uri = builder.setPath("/bank").build();
        HttpPost request = new HttpPost(uri);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("fromScore", "1"));
        params.add(new BasicNameValuePair("toScore", "2"));
        params.add(new BasicNameValuePair("currency", "USD"));
        params.add(new BasicNameValuePair("amount", DELTA));
        request.setEntity(new UrlEncodedFormEntity(params));
        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(200, statusCode);
        System.out.println(response.getEntity().toString());
        getForScore1 = new HttpGet(uriScore1);
        getForScore2 = new HttpGet(uriScore2);
        response1 = client.execute(getForScore1);
        response2 = client.execute(getForScore2);
        jsonString1 = EntityUtils.toString(response1.getEntity());
        jsonString2 = EntityUtils.toString(response2.getEntity());
        Assert.assertEquals(subBalance1, new JSONObject(jsonString1).getBigDecimal("balance"));
        Assert.assertEquals(addBalance2, new JSONObject(jsonString2).getBigDecimal("balance"));
    }
}
