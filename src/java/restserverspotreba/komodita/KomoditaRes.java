package restserverspotreba.komodita;

import java.sql.SQLException;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import evidencespotrebyclasses.komodita.KomoditaDB;
import evidencespotrebyclasses.komodita.KomoditaDBList;

/**
 *
 * @author Milos
 */
@Path("Komodita")
public class KomoditaRes {
    
    private KomoditaDAO dao = new KomoditaDAO();    
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    public KomoditaDBList getAll(){
        try {
            return dao.getAll();
        } catch (SQLException ex) {
            return null;
        }
    }
    
    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public KomoditaDB get(@PathParam("id") String id){
        try {
            return dao.get(Integer.valueOf(id));
        } catch (SQLException ex) {
            return null;
        }
    }
}
