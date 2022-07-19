import java.io.PrintStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Acessar API
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI endereco = URI.create(url);

        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request,
                BodyHandlers.ofString());

        String body = response.body();

        // Tratar os dados recebidos
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // Manipular os dados
        for (Map<String, String> filme : listaDeFilmes) {
            System.out.print("\u001b[1m" + filme.get("title") + " (" + filme.get("imDbRating") + ")" + " | ");
            int nota = Integer.parseInt(filme.get("imDbRating").split("\\.")[0]);
            for (int i = 0; i < nota; i++) {
                System.out.print("\uD83D\uDD25");
            }
            System.out.println(" |");
            System.out.println(filme.get("image"));
            System.out.println();
        }

    }

}
