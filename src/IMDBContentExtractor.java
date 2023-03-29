import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IMDBContentExtractor {
    
    public List<Content> extractContent(String json) {

             // extrair só os dados que interessam (titulo, poster, nota)
             var parser = new JsonParser();
             List<Map<String, String>> attributesList = parser.parse(json);

            List<Content> contents = new ArrayList<>();
            
            // popular a lista de conteúdos
            for(Map<String, String> attributes : attributesList) {
                String title = attributes.get("title");
                String urlImagem = attributes.get("image");
                var content = new Content(title, urlImagem);

                contents.add(content);
            }
            
            return contents;

    }
}
