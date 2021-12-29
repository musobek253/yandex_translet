import com.google.gson.Gson;
import model.DictResult;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Yandex {
    public static final String APIKEY = "dict.1.1.20211229T060542Z.990281239d93b48f.29bfcf67f66907c06977faa2d01f11befec31baa";


    public static String[] getLangs() throws IOException {


        HttpGet httpGet = new HttpGet("https://dictionary.yandex.net/api/v1/dicservice.json/getLangs?key=" + APIKEY);
        HttpClient httpClient = HttpClients.createDefault();

        HttpResponse response = httpClient.execute(httpGet);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        Gson gson = new Gson();

        String[] result = gson.fromJson(bufferedReader, String[].class);
        System.out.println(Arrays.toString(result));

        return result;
    }

    public static DictResult lookUp(String lang, String text) throws IOException {
        HttpGet httpGet = new HttpGet("https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key=" +
                APIKEY + "&lang=" + lang + "&text=" + text);
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(httpGet);


        System.out.println(response);
        System.out.println(response.getEntity().getContent());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        Gson gson = new Gson();

        DictResult result = gson.fromJson(bufferedReader, DictResult.class);
        System.out.println(result);

        return result;
    }
}
