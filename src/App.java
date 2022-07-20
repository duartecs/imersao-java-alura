import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Acessar API

        // IMDB
        String url = "https://api.mocki.io/v2/549a5d8b";
        var extrator = new ExtratorDeConteudoDoIMDB();

        // NASA
        // String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
        // var extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var gerador = new GeradorDeSticker();
        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo() + ".png";

            gerador.criar(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
        }
    }

}
