import javax.swing.UIManager;

/**
 * Classe principale pour lancer l'application "Papillon".
 * <p>
 * Cette classe configure le LookAndFeel Nimbus pour l'interface Swing
 * et crée une instance de {@link MenuPapillon} pour afficher la fenêtre principale.
 * </p>
 */
public class Papillon{

	/**
     * Constructeur privé pour empêcher l’instanciation de la classe.
     * <p>
     * Cette classe ne contient que des méthodes statiques et ne doit pas être instanciée.
     * </p>
     */
    private Papillon() {
        // Empêche l’instanciation
    }
	
	/**
     * Méthode principale de l'application.
     * Configure le LookAndFeel Nimbus et lance la fenêtre principale.
     *
     * @param args arguments de la ligne de commande (non utilisés)
     */
	public static void main(String[] args) {
		try {
		    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException e) {
		    System.out.println("Impossible d'appliquer le LookAndFeel Nimbus : " + e);
		}
		MenuPapillon pap = new MenuPapillon();
	}
}