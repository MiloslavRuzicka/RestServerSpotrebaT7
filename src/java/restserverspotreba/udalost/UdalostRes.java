package restserverspotreba.udalost;

import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import evidencespotrebyclasses.udalost.UdalostDB;
import evidencespotrebyclasses.udalost.UdalostDBList;

/**
 *
 * @author PC-Milos
 */
@Path("Udalost")
public class UdalostRes {
    
    private UdalostDAO dao = new UdalostDAO();    
    
    @GET
    @Path("{idOdberneMisto}")
    @Produces({ MediaType.APPLICATION_JSON})
    public UdalostDBList getAll(@PathParam("idOdberneMisto") String idOdberneMisto){
        try {
            return dao.getAll(Integer.valueOf(idOdberneMisto));
        } catch (SQLException ex) {
            return null;
        }
    }
    
    @POST // insert
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public UdalostDB create(UdalostDB aZaznam){
        try {
            return dao.insertInDB(aZaznam);
        } catch (SQLException ex) {
            return null;
        }
    }
    
    @PUT // update
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public UdalostDB update(UdalostDB aZaznam){
        try {
            return dao.updateInDB(aZaznam);
        } catch (SQLException ex) {
            return null;
        }
    }
    
    @DELETE
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public void delete(@PathParam("id") String id){
        try {
            dao.smazInDB(Integer.valueOf(id));
        } catch (SQLException ex) {
        }
    }
}
