import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top filmes

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

        var http = new ClientHttp();
        String json = http.searchData(url);

        // Manipular e exibir os dados
        var extractor = new IMDBContentExtractor();
        List<Content> contents = extractor.extractContent(json);

        var maker = new StickersFactory();

        for (int i = 0; i < 10; i++) {
            Content content = contents.get(i);

            InputStream inputStream = new URL(content.getUrlImage()).openStream();
            String nameOfFile = content.getTitle() + ".png";

            maker.make(inputStream, nameOfFile);

            System.out.println(content.getTitle());
            System.out.println();
        }
    }
}
