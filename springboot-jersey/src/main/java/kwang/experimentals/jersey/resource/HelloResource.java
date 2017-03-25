package kwang.experimentals.jersey.resource;

import kwang.experimentals.jersey.model.Clock;
import kwang.experimentals.jersey.model.Hello;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.time.temporal.ChronoField;

/**
 * @author kevin.wang.cy@gmail.com
 */

@Component
@Path("/hello")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloResource.class);

    @GET
    @Path("/{name}")
    @Produces("application/vnd.myapp-v1+json")
    public Response getHelloVersionInAcceptHeader(@PathParam("name") String name) {
        LOGGER.info("getHelloVersionInAcceptHeader() v1");
        return this.getHello(name, "Version 1 - passed in Accept Header");
    }



    @POST
    @Consumes("application/vnd.myapp-v1+json")
    @Produces("application/vnd.myapp-v1+json")
    public Response createHelloVersionInAcceptHeader(Hello hello, @Context UriInfo uriInfo) {
        LOGGER.info("createHelloVersionInAcceptHeader() v1");
        return this.createHelloWorld(hello, uriInfo);
    }

    private Response getHello(String name, String partialMsg) {
        if ("404".equals(name)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Hello result = new Hello();
        result.setMsg(String.format("Hello %s. %s", name, partialMsg));
        result.setClock(new Clock());

        LOGGER.info("toString() = " + result.toString());

        LOGGER.info(String.format("zonedDateTime GetLong: %s, sqlDate.getTime: %s, UtilDate.getTime: %s", result.getClock().getZonedDateTime().toInstant().toEpochMilli(), result.getClock().getSqlDate().getTime(), result.getClock().getUtilDate().getTime()));

        return Response.status(Response.Status.OK).entity(result).build();
    }
    
    private Response createHelloWorld(Hello hello, UriInfo uriInfo) {
        // Creates resource and return 201 with reference to new resource in Location header
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();

        builder.path(hello.getMsg());

        return Response.created(builder.build()).build();
    }

}
