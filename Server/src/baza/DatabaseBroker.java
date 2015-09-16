/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import domen.OpstiDomenskiObjekat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author student1
 * @param <T>
 */
public class DatabaseBroker {

    private Connection konekcija;

    public void ucitajDriver() throws Exception {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    }

    public void otvoriKonekciju() throws Exception {
        konekcija = DriverManager.getConnection("jdbc:odbc:bazaProSoftSem");
        konekcija.setAutoCommit(false); // Zahteva eksplicitnu potvrdu transakcije
    }

    public void zatvoriKonekciju() throws Exception {
        konekcija.close();
    }

    public void commitTransakcije() throws Exception {
        try {
            konekcija.commit();
        } catch (SQLException ex) {
            throw new Exception("Transakcija nije potvrđena!");
        }
    }

    public void rollbackTransakcije() throws Exception {
        try {
            konekcija.rollback();
        } catch (SQLException ex) {
            throw new Exception("Transakcija nije potvrđena!");
        }
    }

    public void sacuvaj(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String sql = "UPDATE " + odo.vratiNazivTabele()
                    + " SET " + odo.vratiAtributeSaVrednostima() + " WHERE "
                    + odo.vratiNazivKljuca() + " = " + odo.vratiVrednostKljuca();
            System.out.println(sql);
            Statement sqlNaredba = konekcija.createStatement();
            sqlNaredba.executeUpdate(sql);
            sqlNaredba.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greška pri čuvanju!");
        }
    }

    public List<OpstiDomenskiObjekat> vratiSve(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String sql = "SELECT * FROM " + odo.vratiNazivTabele();
            System.out.println(sql);
            Statement sqlNaredba = konekcija.createStatement();
            ResultSet rs = sqlNaredba.executeQuery(sql);
            List<OpstiDomenskiObjekat> lista = odo.napuni(rs);

            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greška pri učitavanju!");
        }
    }

    public OpstiDomenskiObjekat kreiraj(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String upit = "INSERT INTO " + odo.vratiNazivTabele() + "("
                    + odo.vratiNazivKljuca() + ")" + " VALUES (" + odo.vratiVrednostKljuca() + ")";
            System.out.println(upit);
            Statement sqlNaredba = konekcija.createStatement();
            sqlNaredba.executeUpdate(upit);
            sqlNaredba.close();
            return odo;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Grepka pri kreiranju objekta!");
        }
    }

    public int generisiId(OpstiDomenskiObjekat odo) throws Exception {
        try {
            int broj = 0;
            String upit = "SELECT LAST(" + odo.vratiNazivKljuca() + ") AS poslednjiId FROM " + odo.vratiNazivTabele();
            System.out.println(upit);
            Statement sqlNaredba = konekcija.createStatement();
            ResultSet rs = sqlNaredba.executeQuery(upit);
            if (rs.next()) {
                broj = rs.getInt("poslednjiId") + 1;
            }
            return broj;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greška pri generisanju ID-a!");
        }
    }

    public void kreirajISacuvaj(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String upit = "INSERT INTO " + odo.vratiNazivTabele() + " VALUES (" + odo.vratiVrednostiAtributa() + ")";
            System.out.println(upit);
            Statement sqlNaredba = konekcija.createStatement();
            sqlNaredba.executeUpdate(upit);
            sqlNaredba.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greška pri kreiranju i čuvanju objekta!");
        }
    }

    public List<OpstiDomenskiObjekat> pretraziObjekte(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String sql = "SELECT * FROM " + odo.vratiNazivTabele() + " WHERE " + odo.vratiKriterijumPretrage();
            System.out.println(sql);
            Statement sqlNaredba = konekcija.createStatement();
            ResultSet rs = sqlNaredba.executeQuery(sql);
            return odo.napuni(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greška pri pretraživanju!");
        }
    }

    public OpstiDomenskiObjekat dajPodatke(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String upit = "SELECT * FROM " + odo.vratiNazivTabele() + " WHERE " + odo.vratiNazivKljuca()
                    + "=" + odo.vratiVrednostKljuca();
            System.out.println(upit);
            Statement sqlNaredba = konekcija.createStatement();
            ResultSet rs = sqlNaredba.executeQuery(upit);
            return odo.napuni(rs).get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Greška pri izvlačenju podataka!");
        }
    }

    public void brisiStavke(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String upit;
            OpstiDomenskiObjekat odo2;
            for (int i = 0; i < odo.vratiBrojStavki(); i++) {
                odo2 = odo.vratiStavku(i);
                upit = "DELETE FROM " + odo2.vratiNazivTabele() + " WHERE " + odo2.vratiNazivKljuca()
                        + "=" + odo.vratiVrednostKljuca() + " AND " + odo.vratiNazivKljuca() + "="
                        + odo.vratiVrednostKljuca();
                System.out.println(upit);
                Statement sqlNaredba = konekcija.createStatement();
                sqlNaredba.executeUpdate(upit);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Greška pri brisanju!");
        }
    }

    public void brisiSlabObjekat(OpstiDomenskiObjekat slab, OpstiDomenskiObjekat jak) throws Exception {
        try {
            String upit = "DELETE FROM " + slab.vratiNazivTabele() + " WHERE " + slab.vratiNazivKljuca()
                    + "=" + slab.vratiVrednostKljuca() + " AND " + jak.vratiNazivKljuca() + "="
                    + jak.vratiVrednostKljuca();
            System.out.println(upit);
            Statement sqlNaredba = konekcija.createStatement();
            sqlNaredba.executeUpdate(upit);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Greška pri brisanju!");
        }
    }

    public void sacuvajSlabObjekat(OpstiDomenskiObjekat slab, OpstiDomenskiObjekat jak) throws Exception {
        try {
            String upit = "INSERT INTO " + slab.vratiNazivTabele() + " VALUES ("
                    + slab.vratiVrednostiAtributa() + ", " + jak.vratiVrednostKljuca() + ")";
            System.out.println(upit);
            Statement sqlNaredba = konekcija.createStatement();
            sqlNaredba.executeUpdate(upit);
            sqlNaredba.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greška pri kreiranju i čuvanju objekta!");
        }
    }

    public List<OpstiDomenskiObjekat> vratiSlabeObjekte(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String upit = "SELECT * FROM " + odo.vratiTabeluStavke() + " WHERE " + odo.vratiNazivKljuca()
                    + "=" + odo.vratiVrednostKljuca();
            System.out.println(upit);
            Statement sqlNaredba = konekcija.createStatement();
            ResultSet rs = sqlNaredba.executeQuery(upit);
            return odo.vratiJednuStavku().napuni(rs);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Greška pri izvlačenju podataka!");
        }
    }

    public boolean vratiLogickuVrednostAtributa(String atribut, OpstiDomenskiObjekat odo) throws Exception {
        try {
            String upit = " SELECT *"
                    + " FROM " + odo.vratiNazivTabele()
                    + " WHERE " + odo.vratiNazivKljuca() + "=" + odo.vratiVrednostKljuca();
            System.out.println(upit);
            boolean s = false;
            Statement sqlNaredba = konekcija.createStatement();
            ResultSet rs = sqlNaredba.executeQuery(upit);
            rs.next();
            s = rs.getBoolean(atribut);
            return s;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Greška pri izvlačenju podataka!");
        }
    }
}
