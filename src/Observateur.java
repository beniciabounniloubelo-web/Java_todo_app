import java.awt.event.*;
import javax.swing.*;

/**
 * La classe <code>Observateur</code> sert à fermer une boîte de dialogue lorsqu'une action est effectuée.
 * <p>
 * Cette classe implémente <code>ActionListener</code> et est typiquement utilisée pour des boutons
 * comme "OK" ou "Annuler" afin de fermer le <code>JDialog</code> associé.
 * </p>
 */
public class Observateur implements ActionListener {

    /** La boîte de dialogue cible qui sera fermée lors de l'action. */
    private JDialog target;   

    /**
     * Construit un nouvel observateur associé à une boîte de dialogue.
     *
     * @param target le <code>JDialog</code> à fermer lorsque l'action est déclenchée
     */
    public Observateur(JDialog target) {
        this.target = target;
    }

    /**
     * Méthode appelée lorsqu'une action est effectuée.
     * <p>
     * Ferme la boîte de dialogue cible si elle n'est pas nulle.
     * </p>
     *
     * @param e l'événement d'action déclencheur
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null) {
            target.dispose();
        }
    }
}
