/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemskeOperacije.GenerisiId;
import sistemskeOperacije.KreirajIsacuvajObjekat;
import sistemskeOperacije.KreirajNoviObjekat;
import sistemskeOperacije.ObradiFakturu;
import sistemskeOperacije.OpstaSistemskaOperacija;
import sistemskeOperacije.PretraziObjekte;
import sistemskeOperacije.PretraziObjekteSaVezanimObjektom;
import sistemskeOperacije.PrikaziPodatke;
import sistemskeOperacije.PrikaziPodatkeSaVezanimObjektom;
import sistemskeOperacije.PrikaziPodatkeSaVezanimObjektomIStavkama;
import sistemskeOperacije.SacuvajObjekat;
import sistemskeOperacije.SacuvajFakturuSaStavkama;
import sistemskeOperacije.StornirajFakturu;
import sistemskeOperacije.VratiObjekteSaVezanimObjektom;
import sistemskeOperacije.VratiSveObjekte;

/**
 *
 * @author Jelena
 */
public class Kontroler {

    private static Kontroler k;
    private OpstaSistemskaOperacija oso;

    private Kontroler() {
    }

    public static Kontroler vratiObjekat() {
        if (k == null) {
            k = new Kontroler();
        }
        return k;
    }
    
    public void sacuvajObjekat(OpstiDomenskiObjekat odo) throws Exception{
        oso = new SacuvajObjekat(odo);
        oso.izvrsenjeSO();
    }

    public <T extends OpstiDomenskiObjekat> List<T> vratiSveObjekte(T odo) throws Exception {
        oso = new VratiSveObjekte(odo);
        oso.izvrsenjeSO();
        return ((VratiSveObjekte)oso).getLista();
    }
    
    public <T extends OpstiDomenskiObjekat> List<T> vratiObjekteSaVezanimObjektom(T odo)throws Exception{
        oso = new VratiObjekteSaVezanimObjektom(odo);
        oso.izvrsenjeSO();
        return ((VratiObjekteSaVezanimObjektom)oso).getLista();
    }

    public <T extends OpstiDomenskiObjekat> T kreirajObjekat(T odo) throws Exception {
        oso = new KreirajNoviObjekat<T>(odo);
        oso.izvrsenjeSO();
        return ((KreirajNoviObjekat<T>)oso).getObjekat();
    }

    public int generisiID(OpstiDomenskiObjekat odo) throws Exception {
        oso = new GenerisiId(odo);
        oso.izvrsenjeSO();
        return ((GenerisiId)oso).getId();
    }

    public void kreirajISacuvajObjekat(OpstiDomenskiObjekat odo) throws Exception {
        oso = new KreirajIsacuvajObjekat(odo);
        oso.izvrsenjeSO();
    }

    public List<OpstiDomenskiObjekat> pretraziObjekte(OpstiDomenskiObjekat odo) throws Exception {
        oso = new PretraziObjekte(odo);
        oso.izvrsenjeSO();
        return ((PretraziObjekte)oso).getLista();
    }
    
    public List<OpstiDomenskiObjekat> pretraziSaVezanimObjektom(OpstiDomenskiObjekat odo) throws Exception {
        oso = new PretraziObjekteSaVezanimObjektom(odo);
        oso.izvrsenjeSO();
        return ((PretraziObjekteSaVezanimObjektom)oso).getLista();
    }

    public OpstiDomenskiObjekat prikaziPodatke(OpstiDomenskiObjekat odo) throws Exception {
        oso = new PrikaziPodatke(odo);
        oso.izvrsenjeSO();
        return ((PrikaziPodatke)oso).getObjekat();
    }

    public OpstiDomenskiObjekat prikaziPodatkeSaVezanimObjektom(OpstiDomenskiObjekat odo) throws Exception {
        oso = new PrikaziPodatkeSaVezanimObjektom(odo);
        oso.izvrsenjeSO();
        return ((PrikaziPodatkeSaVezanimObjektom)oso).getObjekat();
    }
    
    public void sacuvajObjekatSaStavkama(OpstiDomenskiObjekat odo) throws Exception{
        oso = new SacuvajFakturuSaStavkama(odo);
        oso.izvrsenjeSO();
    }

    public OpstiDomenskiObjekat prikaziPodatkeSaVezanimObjektomIStavkama(OpstiDomenskiObjekat odo) throws Exception{
        oso = new PrikaziPodatkeSaVezanimObjektomIStavkama(odo);
        oso.izvrsenjeSO();
        return ((PrikaziPodatkeSaVezanimObjektomIStavkama)oso).getObjekat();
    }

    public void obradi(OpstiDomenskiObjekat odo) throws Exception {
        oso = new ObradiFakturu(odo);
        oso.izvrsenjeSO();
    }

    public void storniraj(OpstiDomenskiObjekat odo) throws Exception {
        oso = new StornirajFakturu(odo);
        oso.izvrsenjeSO();
    }
}
