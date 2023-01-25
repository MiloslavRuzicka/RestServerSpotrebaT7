package restserverspotreba.meric;

import restserverspotreba.config.BaseDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import evidencespotrebyclasses.meric.MericDB;
import evidencespotrebyclasses.meric.MericDBList;

/**
 *
 * @author PC-Milos
 */
public class MericDAO extends BaseDAO{
    
    public MericDBList getAll(int aidOdberneMisto) throws SQLException{

        List<MericDB> zaznamy = new ArrayList<MericDB>();
        
        try(java.sql.Connection con = ds.getConnection()) {
            java.sql.PreparedStatement ps = null;
            java.sql.ResultSet rs = null;
            try {
                ps = con.prepareStatement(" select m.* "+
                    " from TBLMERIC m "+
                    " left join TBLKOMODITA k on k.id_komodita = m.id_komodita "+
                    " where m.id_odberneMisto = ?"+
                    " order by k.nazev ");
                ps.setInt(1, aidOdberneMisto);
                rs = ps.executeQuery();
                while (rs.next()){
                    MericDB obj = new MericDB();
                    obj.setId_meric(rs.getInt("id_Meric"));
                    obj.setPopis(rs.getString("popis"));
                    obj.setId_odberneMisto(rs.getInt("id_odberneMisto"));
                    obj.setId_komodita(rs.getInt("id_komodita"));
                    if (rs.getObject("barva") == null)
                        obj.setBarva(0);
                    else
                        obj.setBarva(rs.getInt("barva"));
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
        MericDBList result = new MericDBList();
        result.setList(zaznamy);
        return result;
    }
    
    public MericDB updateInDB(MericDB aZaznam) throws SQLException{
        
        java.sql.PreparedStatement ps = null;
        
        try(java.sql.Connection con = ds.getConnection()) {
            
            ps = con.prepareStatement(" update TBLMERIC " +
                        " set id_odberneMisto = ?, id_Komodita = ?, popis = ?, barva = ? "+
                        " where id_Meric = ? ");
            ps.setInt(1, aZaznam.getId_odberneMisto());
            ps.setInt(2, aZaznam.getId_komodita());
            ps.setString(3, aZaznam.getPopis());
            ps.setInt(4, aZaznam.getBarva());
            // where
            ps.setInt(5, aZaznam.getId_meric());
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

    public MericDB insertInDB(MericDB aZaznam) throws SQLException{
        
        java.sql.PreparedStatement ps = null;
        
        try(java.sql.Connection con = ds.getConnection()) {
            ps = con.prepareStatement(" insert into tblMeric " +
                        " ( id_OdberneMisto, id_komodita, popis, barva ) "+
                        " values( ?, ?, ?, ? ) ");
            ps.setInt(1, aZaznam.getId_odberneMisto());
            ps.setInt(2, aZaznam.getId_komodita());
            ps.setString(3, aZaznam.getPopis());
            ps.setInt(4, aZaznam.getBarva());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null){
                if (rs.next()){
                    int id = rs.getInt(1);
                    aZaznam.setId_meric(id);
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

    public boolean smazInDB(int aIdMeric) throws SQLException{

        try(java.sql.Connection con = ds.getConnection()) {
            java.sql.PreparedStatement ps = null;
            java.sql.ResultSet rs = null;
            try {
                ps = con.prepareStatement(" delete from tblMeric " +
                                    " where id_meric = ? ");
                // where
                ps.setInt(1, aIdMeric);
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
