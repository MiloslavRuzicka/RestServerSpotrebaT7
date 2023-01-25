package restserverspotreba.odbernemisto;

import restserverspotreba.config.BaseDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import evidencespotrebyclasses.odbernemisto.OdberneMistoDB;
import evidencespotrebyclasses.odbernemisto.OdberneMistoDBList;
import java.sql.Statement;

/**
 *
 * @author PC-Milos
 */
public class OdberneMistoDAO extends BaseDAO{
    
    public OdberneMistoDBList getAll() throws SQLException{
                
        List<OdberneMistoDB> zaznamy = new ArrayList<>();
        
        try(java.sql.Connection con = ds.getConnection()) {
            java.sql.PreparedStatement ps = null;
            java.sql.ResultSet rs = null;
            try {
                ps = con.prepareStatement(" select * "+
                    " from TBLODBERNEMISTO "+
                    " order by id_odberneMisto ");
                rs = ps.executeQuery();
                while (rs.next ()) {
                    OdberneMistoDB odberneMisto = new OdberneMistoDB();
                    odberneMisto.setIdOdberneMisto(rs.getInt("id_odberneMisto"));
                    odberneMisto.setNazev(rs.getString("popis"));
                    zaznamy.add(odberneMisto);
                }
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        OdberneMistoDBList result = new OdberneMistoDBList();
        result.setList(zaznamy);
        return result;
    }
    
    public OdberneMistoDB updateInDB(OdberneMistoDB aZaznam) throws SQLException{
        
        java.sql.PreparedStatement ps = null;
        
        try(java.sql.Connection con = ds.getConnection()) {
            
            ps = con.prepareStatement(" update TBLODBERNEMISTO " +
                        " set popis = ? "+
                        " where id_ODBERNEMISTO = ? ");
            ps.setString(1, aZaznam.getNazev());
            // where
            ps.setInt(2, aZaznam.getIdOdberneMisto());
            ps.execute();
            return aZaznam;
        } finally {
            try {
                if (ps != null) ps.close();                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public OdberneMistoDB insertInDB(OdberneMistoDB aZaznam) throws SQLException{
        
        java.sql.PreparedStatement ps = null;
        
        try(java.sql.Connection con = ds.getConnection()) {
            ps = con.prepareStatement(" insert into TBLODBERNEMISTO " +
                        " (id_ODBERNEMISTO, popis ) "+
                        " values( null, ? ) ", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, aZaznam.getNazev());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null){
                if (rs.next()){   
                    int id = rs.getInt(1);
                    aZaznam.setIdOdberneMisto(id);
                }
                rs.close();
            }
            return aZaznam;
        } finally {
            try {
                if (ps != null) ps.close();                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean smazInDB(int aId) throws SQLException{

        try(java.sql.Connection con = ds.getConnection()) {
            java.sql.PreparedStatement ps = null;
            java.sql.ResultSet rs = null;
            try {
                ps = con.prepareStatement(" delete from tblOdberneMisto " +
                        " where id_OdberneMisto = ? ");
                // where
                ps.setInt(1, aId);
                ps.execute();
                return true;
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();                    
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
