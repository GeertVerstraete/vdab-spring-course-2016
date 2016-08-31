package be.vdab.spring_jdbc;

/**
 * Created by aryastark on 31/08/16.
 */
public class Film {
    private String title;
    private int releaseYear;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Film(String title, int releaseYear, String description) {

        this.title = title;
        this.releaseYear = releaseYear;
        this.description = description;
    }

    @Override
    public String toString() {
        return title + '\t' + releaseYear + '\t' + description;
    }
}
