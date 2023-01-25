package restserverspotreba.komodita;

import restserverspotreba.config.BaseDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import evidencespotrebyclasses.komodita.KomoditaDB;
import evidencespotrebyclasses.komodita.KomoditaDBList;

/**
 *
 * @author Milos
 */
public class KomoditaDAO extends BaseDAO{
    
    public KomoditaDBList getAll() throws SQLException
    {
        List<KomoditaDB> zaznamy = new ArrayList<KomoditaDB>();
        
        try(java.sql.Connection con = ds.getConnection()) {
            java.sql.PreparedStatement ps = null;
            java.sql.ResultSet rs = null;
            try {
                ps = con.prepareStatement(" select * "+
                                          " from TBLKOMODITA "+
                                          " order by nazev ");                
                rs = ps.executeQuery();
                while (rs.next()){
                    KomoditaDB komodita = new KomoditaDB();
                    komodita.setId_komodita(rs.getInt("ID_KOMODITA"));
                    komodita.setNazev(rs.getString("NAZEV"));
                    komodita.setJednotka(rs.getString("JEDNOTKA"));
                    komodita.setBarva(rs.getInt("BARVA"));
                    zaznamy.add(komodita);
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
        KomoditaDBList result = new KomoditaDBList();
        result.setList(zaznamy);
        return result;
    }
    
    public KomoditaDB get(int aId) throws SQLException{
        
        KomoditaDB result = null;
        
        try(java.sql.Connection con = ds.getConnection()) {
            java.sql.PreparedStatement ps = null;
            java.sql.ResultSet rs = null;
            try {
                ps = con.prepareStatement(" select * "+
                                          " from tblkomodita "+
                                          " where ID_KOMODITA=? "+
                                          " order by nazev ");                
                ps.setInt(1, aId);
                rs = ps.executeQuery();
                while (rs.next()){
                    KomoditaDB komodita = new KomoditaDB();
                    komodita.setId_komodita(rs.getInt("ID_KOMODITA"));
                    komodita.setNazev(rs.getString("NAZEV"));
                    komodita.setJednotka(rs.getString("JEDNOTKA"));
                    komodita.setBarva(rs.getInt("BARVA"));
                    result = komodita;
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
        return result;
    }
}
