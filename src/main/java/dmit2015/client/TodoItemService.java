package dmit2015.client;

import dmit2015.data.TodoItem;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import java.util.List;

/**
 * The baseUri for the web service be set in either microprofile-config.properties (recommended)
 * or in this file using @RegisterRestClient(baseUri = "http://server/path").
 *
 * To set the baseUri in microprofile-config.properties:
 *    1) Open src/main/resources/META-INF/microprofile-config.properties
 *    2) Add a key/value pair in the following format:
 *          package-name.ClassName/mp-rest/url=baseUri
 *       For example:
 *          package-name:    dmit2015.client
 *          ClassName:       TodoItemService
 *          baseUri:         http://localhost:8080/contextPath
 *       The key/value pair you need to add is:
 *          dmit2015.client.TodoItemService/mp-rest/url=http://localhost:8080/contextPath
 *
 *
 * To use the client interface from an environment does support CDI, add @Inject and @RestClient before the field declaration such as:
 *
 *     @Inject
 *     @RestClient
 *     private TodoItemService _todoItemService;
 *
 * To use the client interface from an environment that does not support CDI, you can use the RestClientBuilder class to programmatically build an instance as follows:
 *
 *      URI apiURi = new URI("http://sever/path");
 *      TodoItemService _todoItemService = RestClientBuilder.newBuilder()
 *                 .baseUri(apiURi)
 *                 .build(TodoItemService.class);
 *
 */
@RegisterRestClient
@Path("/TodoItems")
public interface TodoItemService {

    @GET
    List<TodoItem> findAll();

    @GET
    @Path("{id}")
    TodoItem findOneById(@PathParam("id") Long todoId);

    @POST
    String create(TodoItem newTodoItem);

    @PUT
    @Path("/{id}")
    void update(@PathParam("id") Long todoId, TodoItem updatedTodoItem);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") Long todoId);

}
