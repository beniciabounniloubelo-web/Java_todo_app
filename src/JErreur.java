import javax.swing.*;
import java.awt.*;

/**
 * La classe <code>JErreur</code> représente une boîte de dialogue modale
 * utilisée pour afficher un message d’erreur à l’utilisateur.
 * <p>
 * Elle affiche un message passé en paramètre, accompagné d’un bouton <b>OK</b> 
 * permettant de fermer la fenêtre. 
 * </p>
 */
public class JErreur extends JDialog{

    /**
     * Construit une boîte de dialogue d’erreur avec le titre et le message spécifiés.
     * <p>
     * La boîte est modale : elle bloque la fenêtre parente jusqu’à ce que 
     * l’utilisateur clique sur le bouton <b>OK</b>.
     * </p>
     *
     * @param parent la fenêtre parente sur laquelle la boîte de dialogue est centrée
     * @param titre  le titre affiché dans la barre de la boîte de dialogue
     * @param message le message d’erreur à afficher à l’utilisateur
     */
    public JErreur(Frame parent, String titre, String message) {
        super(parent, titre, true); 
        init(message);
    }

    /**
     * Initialise les composants graphiques de la boîte de dialogue :
     * message, bouton et gestionnaire d’événement.
     *
     * @param message le texte à afficher dans la boîte de dialogue
     * @see Observateur
     */
    private void init(String message) {
        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel label = new JLabel();
        label.setText(message);
        label.setHorizontalTextPosition(SwingConstants.RIGHT);
        panel.add(label, BorderLayout.CENTER);

        JButton ok = new JButton("OK");
        Observateur obs = new Observateur(this);
        ok.addActionListener(obs);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(ok);
        panel.add(btnPanel, BorderLayout.SOUTH);

        setContentPane(panel);
        pack();
        setLocationRelativeTo(getOwner());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }



}
