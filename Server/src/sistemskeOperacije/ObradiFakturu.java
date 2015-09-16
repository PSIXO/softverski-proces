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
public class ObradiFakturu<T extends OpstiDomenskiObjekat> extends OpstaSistemskaOperacija {

    public ObradiFakturu(T odo) {
        super(odo);
    }

    @Override
    public void izvrsiOperaciju() throws Exception {
        if (!preduslov()) {
            throw new Exception("Nisu ispiunjeni preduslovi!");
        }
        if (!odo.vrednosnaOgranicenja()) {
            throw new Exception("Nisu zadovoljena vrednosna ograničenja!");
        }
        dbbr.brisiStavke(odo);
        for (int i = 0; i < odo.vratiBrojStavki(); i++) {
            dbbr.brisiSlabObjekat(odo.vratiStavku(i), odo);
        }
        dbbr.sacuvaj(odo);
        for (int i = 0; i < odo.vratiBrojStavki(); i++) {
            dbbr.sacuvajSlabObjekat(odo.vratiStavku(i), odo);
        }
    }

    private boolean preduslov() throws Exception {
        return !(dbbr.vratiLogickuVrednostAtributa("obradjena", odo)
                || dbbr.vratiLogickuVrednostAtributa("stornirana", odo));
    }
}
