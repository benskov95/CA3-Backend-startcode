package dto;

public class DadDTO {
    
    private String joke;
    private String url;

    public DadDTO(String joke) {
        this.joke = joke;
        this.url = "https://icanhazdadjoke.com";
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
