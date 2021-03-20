package dmit2015.view;

import dmit2015.client.MovieService;
import dmit2015.data.Movie;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("currentMovieEditController")
@ViewScoped
public class MovieEditController implements Serializable {

    @Inject
    @RestClient
    private MovieService _movieService;

    @Inject
    @ManagedProperty("#{param.editId}")
    @Getter
    @Setter
    private Long editId;

    @Getter
    private Movie existingMovie;

    @PostConstruct
    public void init() {
        if (!Faces.isPostback()) {
            existingMovie = _movieService.findOneById(editId);
        }
    }

    public String onUpdate() {
        String nextPage = "";
        try {
            _movieService.update(editId, existingMovie);
            Messages.addFlashGlobalInfo("Update was successful.");
            nextPage = "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Update was not successful. {0}", e.getMessage());
        }
        return nextPage;
    }

    public String onDelete() {
        String nextPage = "";
        try {
            _movieService.delete(existingMovie.getId());
            Messages.addFlashGlobalInfo("Delete was successful.");
            nextPage = "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Delete not successful. {0}", e.getMessage());
        }
        return nextPage;
    }
}