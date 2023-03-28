import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top filmes

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI adress = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(adress).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, nota)

        var parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parse(body);

        // Manipular e exibir os dados

        var maker = new StickersFactory();

        for (Map<String, String> movie : movieList) {

            String urlImage = movie.get("image");
            String movieTitle = movie.get("title");

            InputStream inputStream = new URL(urlImage).openStream();
            String nameOfFile = movieTitle + ".png";

            maker.make(inputStream, nameOfFile);

            System.out.println(movieTitle);
            System.out.println();
        }
    }
}
