package dmit2015.view;

import dmit2015.client.MovieService;
import dmit2015.data.Movie;
import lombok.Getter;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.omnifaces.util.Messages;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("currentMovieCreateController")
@RequestScoped
public class MovieCreateController {

    @Inject
    @RestClient
    private MovieService _movieService;

    @Getter
    private Movie newMovie = new Movie();

    public String onCreateNew() {
        String nextPage = "";
        try {
            _movieService.create(newMovie);

            Messages.addFlashGlobalInfo("Create was successful.");
            nextPage = "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Create was not successful. {0}", e.getMessage());
        }
        return nextPage;
    }

}