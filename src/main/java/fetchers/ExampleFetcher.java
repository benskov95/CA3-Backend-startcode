package fetchers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ChuckDTO;
import dto.CombinedDTO;
import dto.DadDTO;
import dto.YesNoDTO;
import java.io.IOException;
import utils.HttpUtils;

import java.util.concurrent.*;

public class ExampleFetcher {

    private static String chuckURL = "https://api.chucknorris.io/jokes/random";
    private static String dadURL = "https://icanhazdadjoke.com";
    private static String yesNoURL = "https://yesno.wtf/api";

    public static String fetchJokes (ExecutorService threadPool, Gson gson) throws InterruptedException, ExecutionException, TimeoutException, IOException {


        Callable<ChuckDTO> chuckTask = new Callable<ChuckDTO>() {
            @Override
            public ChuckDTO call() throws Exception {
                String chuck = HttpUtils.fetchData(chuckURL);
                ChuckDTO chuckDTO = gson.fromJson(chuck, ChuckDTO.class);
                return chuckDTO;
            }
        };


        Callable<DadDTO> dadTask = new Callable<DadDTO>() {
            @Override
            public DadDTO call() throws Exception {
                String dad = HttpUtils.fetchData(dadURL);
                DadDTO dadDTO = gson.fromJson(dad, DadDTO.class);
                return dadDTO;
            }
        };

        Future<ChuckDTO> futureChuck = threadPool.submit(chuckTask);
        Future<DadDTO> futureDad = threadPool.submit(dadTask);

        ChuckDTO chuck = futureChuck.get(5, TimeUnit.SECONDS);
        DadDTO dad = futureDad.get(5, TimeUnit.SECONDS);

        CombinedDTO combinedDTO = new CombinedDTO(chuck,dad);
        String combinedJson = gson.toJson(combinedDTO);

        return combinedJson;
    }
    
    public static void main(String[] args) throws IOException {
        String x = HttpUtils.fetchData(yesNoURL);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        YesNoDTO test = gson.fromJson(x, YesNoDTO.class);
        System.out.println(test.getImage());
    }
}
