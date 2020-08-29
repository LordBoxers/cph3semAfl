/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.AnimalNoDB;

/**
 * REST Web Service
 *
 * @author Mibsen
 */
@Path("animals")
public class AnimalDemo {

    @Context
    private UriInfo context;
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public AnimalDemo() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson1() {
        //TODO return proper representation object
        return "vuf...";
    }
    
    @Path("/animal_list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson2() {
        //TODO return proper representation object
        return "[\"Dog\",\"Cat\",\"Mouse\",\"Birb\"]";
    }
    @Path("/animal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson3() {
        //TODO return proper representation object
        return "[\"Dog\",\"Cat\",\"Mouse\",\"Birb\"]";
    }
    @Path("/gson_animal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson4() {
        AnimalNoDB duck = new AnimalNoDB("Duck", "Quack-Quack");
        AnimalNoDB birb = new AnimalNoDB("Birb", "bok-bok");
        
        String jsonString = GSON.toJson(duck);
        //return jsonString;
        return new Gson().toJson(duck);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
