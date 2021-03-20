package dmit2015.view;

import dmit2015.client.MovieService;
import dmit2015.data.Movie;
import lombok.Getter;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named("currentMovieListController")    // allows you access an object of this using this JSF-EL name
@ViewScoped // must implement Serializable interface
            // keep object in memory if the next view is the same page
public class MovieListController implements Serializable {

    private Logger logger = Logger.getLogger(MovieListController.class.getSimpleName());

    @Inject
    @RestClient
    private MovieService _movieService;

    @Getter
    private List<Movie> movies;

    @PostConstruct
    void init() {
        try {
            movies = _movieService.findAll();
        } catch (Exception ex) {
            Messages.addGlobalFatal("Error fetching movies from system. {0}", ex.getMessage());
            logger.info(ex.getMessage());
        }
    }


}
