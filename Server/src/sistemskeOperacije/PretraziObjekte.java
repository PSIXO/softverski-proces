/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemskeOperacije;

import domen.OpstiDomenskiObjekat;
import java.util.List;

/**
 *
 * @author Jelena
 */
public class PretraziObjekte<T extends OpstiDomenskiObjekat> extends OpstaSistemskaOperacija{

    List<T> lista;
    
    public PretraziObjekte(T odo) {
        super(odo);
    }

    @Override
    public void izvrsiOperaciju() throws Exception {
        lista = (List<T>) dbbr.pretraziObjekte(odo);
    }

    public List<T> getLista() {
        return lista;
    }
}
