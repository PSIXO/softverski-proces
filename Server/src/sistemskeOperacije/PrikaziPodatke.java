/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemskeOperacije;

import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Jelena
 */
public class PrikaziPodatke <T extends OpstiDomenskiObjekat> extends OpstaSistemskaOperacija{
    T objekat;

    public PrikaziPodatke(T odo) {
        super(odo);
    }

    @Override
    public void izvrsiOperaciju() throws Exception {
        objekat = (T) dbbr.dajPodatke(odo);
    }

    public T getObjekat() {
        return objekat;
    }
}
