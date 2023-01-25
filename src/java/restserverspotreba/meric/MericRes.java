package restserverspotreba.meric;

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
import evidencespotrebyclasses.meric.MericDB;
import evidencespotrebyclasses.meric.MericDBList;

/**
 *
 * @author PC-Milos
 */
@Path("Meric")
public class MericRes {
    
    private MericDAO dao = new MericDAO();    
    
    @GET
    @Path("{idOdberneMisto}")
    @Produces({ MediaType.APPLICATION_JSON})
    public MericDBList getAll(@PathParam("idOdberneMisto") String idOdberneMisto){
        try {
            return dao.getAll(Integer.valueOf(idOdberneMisto));
        } catch (SQLException ex) {
            return null;
        }
    }
    
    @POST // insert
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public MericDB create(MericDB aZaznam){
        try {
            return dao.insertInDB(aZaznam);
        } catch (SQLException ex) {
            return null;
        }
    }
    
    @PUT // update
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public MericDB update(MericDB aZaznam){
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
