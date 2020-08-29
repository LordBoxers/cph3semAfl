package rest;

import com.google.gson.Gson;
import entities.Employee;
import entities.RenameMe;
import facades.FacadeExample;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("employee")
public class EmployeeResource {
    
    //NOTE: Change Persistence unit name according to your setup
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    FacadeExample facade =  FacadeExample.getFacadeExample(emf);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(RenameMe entity) {
        throw new UnsupportedOperationException();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(RenameMe entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
    
    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson1() {
        List<Employee> facadeAllEmp = facade.getAllEmployee();
        //return jsonString;
        return new Gson().toJson(facadeAllEmp);
    }
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson2(@PathParam("id") long id) {
        Employee facadeByID = facade.getEmployeeByID(id);
        return new Gson().toJson(facadeByID);
    }
    @Path("/highestpaid")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson3() {
        Employee facadeHighestPaid = facade.highestSalary();
        return new Gson().toJson(facadeHighestPaid);
    }
    @Path("/name/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson4(@PathParam("name") String name) {
        List<Employee> facadeEmp = facade.getEmployeeByName(name);
        //return jsonString;
        return new Gson().toJson(facadeEmp);
    }
    
}
