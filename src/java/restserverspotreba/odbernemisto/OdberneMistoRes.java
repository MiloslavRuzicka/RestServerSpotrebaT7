package restserverspotreba.odbernemisto;

import java.sql.SQLException;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import evidencespotrebyclasses.odbernemisto.OdberneMistoDB;
import evidencespotrebyclasses.odbernemisto.OdberneMistoDBList;

/**
 *
 * @author PC-Milos
 */
@Path("OdberneMisto")
public class OdberneMistoRes {
    
    private OdberneMistoDAO dao = new OdberneMistoDAO();    
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    public OdberneMistoDBList getAll(){
        try {
            return dao.getAll();
        } catch (SQLException ex) {
            return null;
        }
    }
    
    @POST // insert
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public OdberneMistoDB create(OdberneMistoDB aZaznam){
        try {
            return dao.insertInDB(aZaznam);
        } catch (SQLException ex) {
            return null;
        }
    }
    
    @PUT // update
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public OdberneMistoDB update(OdberneMistoDB aZaznam){
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
