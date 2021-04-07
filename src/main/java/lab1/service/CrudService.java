package lab1.service;

import lab1.ValidationException;
import lab1.dao.VehicleDao;
import lab1.model.ValidatedVehicle;
import lab1.model.Vehicle;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("vehicle")
public class CrudService {

    @EJB
    private VehicleDao vehicleDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vehicle> getAll(@QueryParam("id") Long id,
                                @QueryParam("name") String name,
                                @QueryParam("creation_date") Long creationDate,
                                @QueryParam("type") String type,
                                @QueryParam("enginePower") Float enginePower,
                                @QueryParam("fuelType") String fuelType,
                                @QueryParam("fuelConsumption") Integer fuelConsumption,
                                @QueryParam("asc") List<String> ascColumns,
                                @QueryParam("desc") List<String> descColumns,
                                @QueryParam("page_size") @DefaultValue("10") int pageSize,
                                @QueryParam("page") @DefaultValue("1") int page) {
        return vehicleDao.filter(id, name, creationDate, type, enginePower, fuelType,
                fuelConsumption, ascColumns, descColumns, pageSize, page);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vehicle getById(@PathParam("id") long id) {
        return vehicleDao.getById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Vehicle vehicle) {
        vehicleDao.create(vehicle);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id") long id, Vehicle vehicle) {
        Vehicle validatedVehicle;
        try {
            validatedVehicle = new ValidatedVehicle(vehicle).getValidatedVehicle();
        } catch (ValidationException e) {
            throw new BadRequestException(e.getMessage());
        }
        validatedVehicle.setId(id);
        vehicleDao.update(validatedVehicle);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") long id) {
        vehicleDao.delete(id);
    }
}