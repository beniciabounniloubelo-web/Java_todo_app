import java.sql.*;
import java.awt.*;

/**
 * La classe <code>EcritureRappel</code> est responsable de la gestion
 * des opérations d'écriture dans la base de données pour les rappels.
 * <p> Elle permet notamment : </p>
 * <ul>
 *   <li>de modifier le statut d'un rappel existant (soit "fini" soit "non fini");</li>
 *   <li>d'ajouter un nouveau rappel avec son titre, contenu et thème.</li>
 * </ul>
 * Les erreurs SQL rencontrées lors de ces opérations sont affichées
 * via une fenêtre générée par la classe {@link JErreur}.
 */
public class EcritureRappel {

	/**
     * Constructeur privé pour empêcher l’instanciation de la classe.
     * <p>
     * Cette classe ne contient que des méthodes statiques et ne doit pas être instanciée.
     * </p>
     */
    private EcritureRappel() {
        // Empêche l’instanciation
    }

	/**
     * Met à jour le statut d'un rappel identifié par son identifiant unique.
     * <p>
     * Cette méthode exécute une requête SQL de type <code>UPDATE</code> 
     * sur la table <code>Rappel</code> afin de modifier le champ <code>statut</code> 
	 * d'un rappel (fini / non fini).
     * </p>
     *
     * @param id l'identifiant du rappel à modifier
     * @param statut la nouvelle valeur du statut à appliquer
     */
	public static void setStatut(int id, String statut){
		try{
			Connection cnx = DatabaseManager.getConnection();
			PreparedStatement pst = cnx.prepareStatement("UPDATE Rappel SET statut = ? WHERE id = ?");
			pst.setString(1,statut);
			pst.setInt(2,id);
			pst.executeUpdate();

			try{
				cnx.close();
			}catch(SQLException e){
				e.printStackTrace();
				JErreur erreur = new JErreur((Frame) null,"Erreur","erreur lors de la fermeture de la connexion.");
				erreur.setVisible(true);
			}
		}catch(SQLException e){
			e.printStackTrace();
			JErreur erreur = new JErreur((Frame) null,"Erreur","erreur sql: verifier la requete.");
			erreur.setVisible(true);			
		}
	}

	/**
     * Ajoute un nouveau rappel dans la base de données avec les informations fournies.
     * <p> Le rappel est inséré dans la table <code>Rappel</code> avec : </p>
     * <ul>
     *   <li>un titre (<code>tit</code>),</li>
     *   <li>un contenu (<code>cont</code>),</li>
     *   <li>un identifiant de thème (<code>theme</code>),</li>
     *   <li>un rang calculé automatiquement à partir du nombre de rappels existants,</li>
     *   <li>et la date de création courante (<code>CURRENT_TIMESTAMP</code>).</li>
     * </ul>
     *
     * @param tit le titre du nouveau rappel
     * @param cont le contenu du nouveau rappel
     * @param theme l'identifiant du thème auquel le rappel est associé
     */
	public static void addRappel(String tit, String cont, int theme){
		try{
			Connection cnx = DatabaseManager.getConnection();
			PreparedStatement pst = cnx.prepareStatement("INSERT INTO Rappel(Titre, Contenu, Theme, Rang, Date_creation) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)");
			pst.setString(1,tit);
			pst.setString(2,cont);
			pst.setInt(3,theme);
			pst.setInt(4,(LectureRappel.getNbRappels()+1));
			pst.executeUpdate();

			try{
				cnx.close();
			}catch(SQLException e){
				e.printStackTrace();
				JErreur erreur = new JErreur((Frame) null,"Erreur","erreur lors de la fermeture de la connexion.");
				erreur.setVisible(true);
			}
		}catch(SQLException e){
			e.printStackTrace();
			JErreur erreur = new JErreur((Frame) null,"Erreur","erreur lors de l'ajout de rappel");
			erreur.setVisible(true);
		}
	}

}