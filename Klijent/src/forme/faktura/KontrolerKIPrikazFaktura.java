/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.faktura;

import domen.Faktura;
import domen.OpstiDomenskiObjekat;
import domen.PoslovniPartner;
import forme.faktura.model.ModelTableFaktura;
import java.util.List;
import javax.swing.JOptionPane;
import klijent.Komunikacija;
import konstante.Operacije;
import transfer.TransferObjekat;

/**
 *
 * @author Jelena
 */
public class KontrolerKIPrikazFaktura {

    public static void prikaziFakture(FmPretragaFaktura aThis) throws Exception {
//Ovde cu odrediti da ce polje  parametar biti iskljucivo tipa faktura
//Takodje posto je u pitanju java 7 mogu da stavim <> desno (diamond syntax) jer kompajler zakljucuje na osnovu ovoga levo
        TransferObjekat<Faktura> toZahtev = new TransferObjekat<>();
        toZahtev.setOperacija(Operacije.PRETRAZI_FAKTURE);
        Faktura f = new Faktura();
        PoslovniPartner partner = (PoslovniPartner) aThis.getCbPartneri().getSelectedItem();
        f.setPoslovniPartner(partner);
// Zbog genericke klase pri kreiranju Transfer objekta zakljucao sam tip za parametar na Faktura
// Ovime sam dobio compile time safety, tj. ne mogu ovde greskom navesti parametar pogresnog tipa
//tako da ovo recimo ne moze vise:
//        toZahtev.setParametar(new String());
//ne moze cak ni ovo
        // toZahtev.setParametar(new OpstiDomenskiObjekat() {...implementacije abstraktnih metoda...});
//a ovo gore bi moglo da sam recimo stavio u  
/** @see TransferObjekat#parametar  **/
// umesto Object OpstiDomenskiObjekat
// jedini tip koji sada radi je tip faktura
       toZahtev.setParametar(f);
        Komunikacija.vratiObjekat().posalji(toZahtev);
        TransferObjekat toOdgovor = Komunikacija.vratiObjekat().procitaj();
        if (toOdgovor.getIzuzetak() != null) {
            throw (Exception) toOdgovor.getIzuzetak();
        }
        List<Faktura> lista = (List<Faktura>) toOdgovor.getRezultat();

        aThis.getTblFakture().setModel(new ModelTableFaktura(lista));
    }

    public static List<Faktura> vratiFakture(FmPretragaFaktura aThis) throws Exception {
        TransferObjekat kto = new TransferObjekat();
        kto.setOperacija(Operacije.VRATI_SVE_FAKTURE);
        kto.setParametar(new Faktura());
        Komunikacija.vratiObjekat().posalji(kto);
        TransferObjekat sto = Komunikacija.vratiObjekat().procitaj();
        if (sto.getIzuzetak() != null) {
            throw (Exception) sto.getIzuzetak();
        }
        List<Faktura> lista = (List<Faktura>) sto.getRezultat();

        return lista;
    }

    public static void prikaziPodatke(Faktura faktura) throws Exception {
        TransferObjekat toZahtev = new TransferObjekat();
        toZahtev.setOperacija(Operacije.PRIKAZI_FAKTURU);
        toZahtev.setParametar(faktura);
        Komunikacija.vratiObjekat().posalji(toZahtev);
        TransferObjekat toOdgovor = (TransferObjekat) Komunikacija.vratiObjekat().procitaj();
        FmUnosFakture unos = new FmUnosFakture(null, true);
        if (toOdgovor.getIzuzetak() != null) {
            JOptionPane.showMessageDialog(unos, toOdgovor.getPoruka(), "Greška", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(unos, toOdgovor.getPoruka(), "Informacija", JOptionPane.INFORMATION_MESSAGE);

            Faktura f = (Faktura) toOdgovor.getRezultat();
            unos.setFaktura(f);
            unos.popuniPodatke(f);
            if (faktura.isStornirana()) {
                unos.setStornirana();
            } else if (faktura.isObradjena()) {
                unos.setObradjena();
            } else {
                unos.setSacuvana();
            }
            unos.setVisible(true);
        }
    }
}
