/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemskeOperacije;

import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Jelena
 * @param <T>
 */
public class KreirajNoviObjekat<T extends OpstiDomenskiObjekat> extends OpstaSistemskaOperacija{

    T objekat;
    
    public KreirajNoviObjekat(T odo) {
        super(odo);
    }
    
    @Override
    public void izvrsiOperaciju() throws Exception {
        dbbr.kreiraj(odo);
    }

    public T getObjekat() {
        return objekat;
    }
}
