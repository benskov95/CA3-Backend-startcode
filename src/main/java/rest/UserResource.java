package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.UserDTO;
import facades.UserFacade;
import utils.EMF_Creator;
import facades.FacadeExample;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("users")
public class UserResource {


    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final UserFacade USER_FACADE = UserFacade.getUserFacade(EMF);
            


    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_JSON})
    public String getNumberOfUsers() {
        int numberOfUsers = USER_FACADE.getAllUsers().size();
        return "{\"count\":" + numberOfUsers + "}";
    }
    
    @GET
    @RolesAllowed("admin")
    @Produces({MediaType.APPLICATION_JSON})
    public String getUsers() {
        List<UserDTO> dtoList = USER_FACADE.getAllUsers();
        return GSON.toJson(dtoList);

    }

    @DELETE
    @Path("{userName}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed("admin")
    public String deletePerson(@PathParam("userName") String userName)  {
        UserDTO userDTO = USER_FACADE.deleteUser(userName);

        return GSON.toJson(userDTO);
    }


}
