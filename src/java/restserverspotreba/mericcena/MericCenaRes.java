package restserverspotreba.mericcena;

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
import evidencespotrebyclasses.mericcena.MericCenaDB;
import evidencespotrebyclasses.mericcena.MericCenaDBList;

/**
 *
 * @author Milos
 */
@Path("MericCena")
public class MericCenaRes {
    
    private MericCenaDAO dao = new MericCenaDAO();    
    
    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public MericCenaDBList getAll(@PathParam("id") String id){
        try {
            return dao.getAll(Integer.valueOf(id));
        } catch (SQLException ex) {
            return null;
        }
    }
    
    @POST // insert
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public MericCenaDB create(MericCenaDB aZaznam){
        try {
            return dao.insertInDB(aZaznam);
        } catch (SQLException ex) {
            return null;
        }
    }
    
    @PUT // update
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public MericCenaDB update(MericCenaDB aZaznam){
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
