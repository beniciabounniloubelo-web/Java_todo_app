import javax.swing.*;
import java.awt.*;

/**
 * La classe <code>Confirmation</code> est utilisée pour ouvrir une boite de dialogue
 * afin que l'utilisateur confirme ou non la supression d'un rappel.
 * <p>
 * Elle affiche un message passé en paramètre, accompagné de deux boutons :
 * <b>"Continuer"</b> et <b>"Annuler"</b>. 
 */
public class Confirmation extends JDialog{

    /**
     * Chaîne représentant l'action choisie par l'utilisateur.
     */
    private String action = "";

    /**
     * Définit l'action sélectionnée par l'utilisateur.
     *
     * @param a la nouvelle valeur de l'action ("Continuer" ou "Annuler")
     */
    public void setAction(String a) {
        this.action = a;
    }

    /**
     * Renvoie l'action choisie par l'utilisateur.
     *
     * @return la valeur actuelle de l'action ("Continuer" ou "Annuler")
     */
    public String getAction(){
        return this.action;
    }

    /**
     * Construit une boîte de dialogue de confirmation avec le titre et le message spécifiés.
     * La boîte bloque l'interaction avec la fenêtre parente
     * tant que l'utilisateur n'a pas répondu.
     *
     * @param parent la fenêtre parente sur laquelle la boîte de dialogue est centrée
     * @param titre  le titre affiché dans la barre de la boîte de dialogue
     * @param message le message de confirmation à afficher à l'utilisateur
     */
    public Confirmation(Frame parent, String titre, String message) {
        super(parent, titre, true); 
        init(message);
    }

    /**
     * Initialise les composants graphiques de la boîte de dialogue :
     * message, boutons et gestionnaires d'événements.
     *
     * @param message le texte à afficher dans la boîte de dialogue
     */
    private void init(String message) {
        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel label = new JLabel();
        label.setText(message);
        label.setHorizontalTextPosition(SwingConstants.RIGHT);
        panel.add(label, BorderLayout.CENTER);
        JButton annuler = new JButton("Annuler");
        ObservateurConfirm obs2 = new ObservateurConfirm(this);
        annuler.addActionListener(obs2);
        JButton ok = new JButton("Continuer");
        ObservateurConfirm obs = new ObservateurConfirm(this);
        ok.addActionListener(obs);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(ok);
        btnPanel.add(annuler);
        panel.add(btnPanel, BorderLayout.SOUTH);

        setContentPane(panel);
        pack();
        setLocationRelativeTo(getOwner());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

}