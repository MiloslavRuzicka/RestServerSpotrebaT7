package restserverspotreba.udalost;

import restserverspotreba.config.BaseDAO;
import restserverspotreba.config.Config;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import evidencespotrebyclasses.udalost.UdalostDB;
import evidencespotrebyclasses.udalost.UdalostDBList;
import java.sql.Statement;

/**
 *
 * @author PC-Milos
 */
public class UdalostDAO extends BaseDAO {
    
    public UdalostDBList getAll(int aIdOdberneMisto) throws SQLException {
        
        List<UdalostDB> zaznamy = new ArrayList<UdalostDB>();
        
        try(java.sql.Connection con = ds.getConnection()) {
            java.sql.PreparedStatement ps = null;
            java.sql.ResultSet rs = null;
            try {
                ps = con.prepareStatement(" select u.* "+
                                          " from TBLUDALOST u "+
                                          " where u.id_odberneMisto = ? "+
                                          " order by u.datum ");
                ps.setInt(1, aIdOdberneMisto);
                rs = ps.executeQuery();
                while (rs.next()){
                    UdalostDB obj = new UdalostDB();
                    obj.setIdUdalost(rs.getInt("ID_UDALOST"));
                    obj.setIdOdberneMisto(rs.getInt("ID_ODBERNEMISTO"));
                    obj.setDatum(rs.getDate("DATUM").getTime());
                    obj.setText(rs.getString("TEXT"));
                    zaznamy.add(obj);
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
        UdalostDBList result = new UdalostDBList();
        result.setList(zaznamy);
        return result;
    }
    
    public UdalostDB updateInDB(UdalostDB aZaznam) throws SQLException{
                
        java.sql.PreparedStatement ps = null;
        
        try(java.sql.Connection con = ds.getConnection()) {
            ps = con.prepareStatement(" update TBLUDALOST " +
                                      " set datum = ?, text = ? "+
                                      " where id_udalost = ? ");
            ps.setDate(1, new java.sql.Date(aZaznam.getDatum()));
            ps.setString(2, aZaznam.getText());
            // where
            ps.setInt(3, aZaznam.getIdUdalost());
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

    public UdalostDB insertInDB(UdalostDB aZaznam) throws SQLException{

        java.sql.PreparedStatement ps = null;
        
        try(java.sql.Connection con = ds.getConnection()) {
            ps = con.prepareStatement(" insert into TBLUDALOST " +
                                      " ( id_udalost, id_odberneMisto, datum, text ) "+
                                      " values( null, ?, ?, ? ) ", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, aZaznam.getIdOdberneMisto());
            ps.setDate(2, new java.sql.Date(aZaznam.getDatum()));
            ps.setString(3, aZaznam.getText());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()){
                int id = rs.getInt(1);
                aZaznam.setIdUdalost(id);
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

    public boolean smazInDB(int aIdUdalost) throws SQLException{

        try(java.sql.Connection con = ds.getConnection()) {
            java.sql.PreparedStatement ps = null;
            java.sql.ResultSet rs = null;
            try {
                ps = con.prepareStatement(" delete from TBLUDALOST " +
                                          " where id_Udalost = ? ");
                // where
                ps.setInt(1, aIdUdalost);
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
