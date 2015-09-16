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
public class KreirajIsacuvajObjekat<T extends OpstiDomenskiObjekat> extends OpstaSistemskaOperacija{

    OpstiDomenskiObjekat objekat;

    public KreirajIsacuvajObjekat(OpstiDomenskiObjekat odo) {
        super(odo);
    }
    
    @Override
    public void izvrsiOperaciju() throws Exception {
        if (!odo.vrednosnaOgranicenja()) {
            throw new Exception("Nisu zadovoljena vrednosna ograničenja!");
        }
         dbbr.kreirajISacuvaj(odo);
    }

    public OpstiDomenskiObjekat getObjekat() {
        return objekat;
    }
}
