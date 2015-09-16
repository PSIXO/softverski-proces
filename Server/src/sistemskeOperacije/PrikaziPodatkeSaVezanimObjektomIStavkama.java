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
public class PrikaziPodatkeSaVezanimObjektomIStavkama <T extends OpstiDomenskiObjekat> extends OpstaSistemskaOperacija{

    T objekat;
    
    public PrikaziPodatkeSaVezanimObjektomIStavkama(T odo) {
        super(odo);
    }

    @Override
    public void izvrsiOperaciju() throws Exception {
        objekat = (T) dbbr.dajPodatke(odo);
        objekat.setVezaniObjekat(dbbr.dajPodatke(odo.vratiVezaniObjekat()));
        List<OpstiDomenskiObjekat> lista = dbbr.vratiSlabeObjekte(odo);
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
            opstiDomenskiObjekat.setVezaniObjekat(dbbr.dajPodatke(opstiDomenskiObjekat.vratiVezaniObjekat()));
        }
        objekat.setujStavke(lista);
    }

    public OpstiDomenskiObjekat getObjekat() {
        return objekat;
    }
}
