package nikam.com.listview_multiplechoicelistener;

/**
 * Created by Nikam on 24/08/2017.
 */
public class MovieData {
   private String movie;
   private int poster;
   private String rate;

    public MovieData(String movie,int poster, String rate) {
        this.movie = movie;
        this.poster = poster;
        this.rate = rate;
    }
    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
