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
public class PrikaziPodatkeSaVezanimObjektom <T extends OpstiDomenskiObjekat> extends OpstaSistemskaOperacija{

    T objekat; 
    
    public PrikaziPodatkeSaVezanimObjektom(T odo) {
        super(odo);
    }

    @Override
    public void izvrsiOperaciju() throws Exception {
        objekat = (T) dbbr.dajPodatke(odo);
        T o = (T) dbbr.dajPodatke(objekat.vratiVezaniObjekat());
        objekat.setVezaniObjekat(o);
    }

    public OpstiDomenskiObjekat getObjekat() {
        return objekat;
    }
    
    
}
