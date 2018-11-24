package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Operaatio implements Komento {

    TextField tuloskentta, syotekentta;
    Button nollaa, undo;
    Sovelluslogiikka sovellus;
    private int edellinenTulos;

    public Operaatio(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
        edellinenTulos = 0;
    }

    public int lueSyote() {
        int syote;
        try {
            syote = Integer.parseInt(syotekentta.getText());
        } catch (NumberFormatException nfe) {
            syote = 0;
        }
        syotekentta.setText("");
        return syote;
    }

    public void asetaTulos() {
        asetaTuloskentta(false);
    }

    @Override
    public void peru() {
        sovellus.undo();
        asetaTuloskentta(true);
    }

    private void asetaTuloskentta(Boolean disableUndo) {
        tuloskentta.setText("" + sovellus.tulos());
        if (sovellus.tulos() == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
        undo.disableProperty().set(disableUndo);
    }

}
