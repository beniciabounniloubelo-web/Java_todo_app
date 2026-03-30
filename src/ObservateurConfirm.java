import java.awt.event.*;
import javax.swing.*;

/**
 * Observateur pour les dialogues de confirmation.
 * <p>
 * Cette classe écoute les actions de boutons dans un {@link JDialog} 
 * (notamment {@link Confirmation}) et capture l'action choisie par l'utilisateur.
 * Elle ferme ensuite le dialogue.
 * </p>
 */ 
public class ObservateurConfirm implements ActionListener {
    private JDialog target;   
    private String action;

    /**
     * Crée un nouvel observateur pour le dialogue donné.
     *
     * @param target le dialogue à surveiller (souvent une instance de {@link Confirmation})
     */
    public ObservateurConfirm(JDialog target) {
        this.target = target;
        this.action = "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.action = e.getActionCommand();
        if (target instanceof Confirmation) {
            ((Confirmation) target).setAction(this.action);
        }
        if (target != null) {
            target.dispose();
        }
    }
}
