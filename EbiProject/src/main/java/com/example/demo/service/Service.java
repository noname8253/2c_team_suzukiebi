//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.http.HttpClient;
//import java.net.http.HttpResponse;
//
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.HttpClients;
//
//@Service
//public class Service {
//
//    public String convertMarkdownToHtml(String accessToken, String markdownText) throws IOException {
//        // GitHub APIのエンドポイントURL
//        String apiUrl = "https://api.github.com/markdown";
//
//        // HttpClientの初期化
//        HttpClient httpClient = HttpClients.createDefault();
//
//        // HTTPリクエストの構築
//        HttpPost httpPost = new HttpPost(apiUrl);
//        httpPost.setHeader("Authorization", "token " + accessToken);
//        httpPost.setHeader("Accept", "application/vnd.github.v3+json");
//
//        // リクエストボディにマークダウンテキストを設定
//        StringEntity requestEntity = new StringEntity("{\"text\": \"" + markdownText + "\"}");
//        httpPost.setEntity(requestEntity);
//
//        // HTTPリクエストの実行
//        HttpResponse response = httpClient.execute(httpPost);
//
//        // レスポンスの読み取り
//        StringBuilder result = new StringBuilder();
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                result.append(line);
//            }
//        }
//
//        return result.toString();
//    }
//}
