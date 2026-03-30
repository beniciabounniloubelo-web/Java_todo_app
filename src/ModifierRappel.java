import java.sql.*;

/**
 * Fournit des méthodes statiques pour modifier, supprimer ou réorganiser les rappels
 * dans la base de données.
 * <p>
 * Les méthodes gèrent directement les requêtes SQL via {@link DatabaseManager#getConnection()}.
 * Certaines actions demandent confirmation à l'utilisateur via la classe {@link Confirmation}.
 * </p>
 */
public class ModifierRappel {

	/**
     * Constructeur privé pour empêcher l’instanciation de la classe.
     * <p>
     * Cette classe ne contient que des méthodes statiques et ne doit pas être instanciée.
     * </p>
     */
    private ModifierRappel() {
        // Empêche l’instanciation
    }

	/**
     * Inverse le rang d'un rappel avec celui qui se trouve directement au-dessus ou en-dessous.
     *
     * @param id1 l'identifiant du rappel à déplacer
     * @param monter si true, le rappel est déplacé vers le haut (rang inférieur), sinon vers le bas
     */
	public static void inverserRangs(int id1, boolean monter){
		try{
			Connection cnx = DatabaseManager.getConnection();
			PreparedStatement pst = cnx.prepareStatement("SELECT Rang FROM Rappel WHERE Id = ?");
			pst.setInt(1,id1);
			ResultSet rs = pst.executeQuery();
			rs.next();
			int rang = rs.getInt(1);

			PreparedStatement pst2 = cnx.prepareStatement("UPDATE Rappel SET Rang = ? WHERE Rang = ?;");
			pst2.setInt(1,rang);
			if(monter){
				pst2.setInt(2,rang-1);
			}else{
				pst2.setInt(2,rang+1);
			}
			pst2.executeQuery();

			PreparedStatement pst3 = cnx.prepareStatement("UPDATE Rappel SET Rang = ? WHERE Id = ?;");
			if(monter){
				pst3.setInt(1,rang-1);
			}else{
				pst3.setInt(1,rang+1);
			}
			pst3.setInt(2,id1);
			pst3.executeQuery();

			try{
				cnx.close();
			}catch(SQLException e){
				e.printStackTrace();
				//implémenter gestion des erreurs
			}
		}catch(SQLException e){
			e.printStackTrace();
			//implémenter gestion des erreurs
		}
	}

	/**
     * Supprime un rappel identifié par son identifiant.
     * <p>
     * Avant la suppression, une boîte de confirmation est affichée à l'utilisateur.
     * Si l'utilisateur confirme, le rappel est supprimé de la base.
     * </p>
     *
     * @param id l'identifiant du rappel à supprimer
     */
	public static void supprimerRappel(int id){
		try{
			Connection cnx = DatabaseManager.getConnection();
			PreparedStatement pst = cnx.prepareStatement("DELETE FROM Rappel WHERE id = ?");
			pst.setInt(1,id);
			Confirmation conf = new Confirmation(null,"Confirmation","Êtes-vous sûr de vouloir supprimer ce rappel ?");
			conf.setVisible(true);
			if(conf.getAction().equals("Continuer")){
				pst.executeQuery();
			}

			try{
				cnx.close();
			}catch(SQLException e){
				e.printStackTrace();
				//implémenter gestion des erreurs
			}
		}catch(SQLException e){
			e.printStackTrace();
			//implémenter gestion des erreurs
		}
	}

	/**
     * Modifie les informations d'un rappel existant dans la base de données.
     * <p>
     * Les champs modifiables sont : titre, contenu et thème. L'utilisateur
     * doit confirmer la modification via une boîte de dialogue.
     * </p>
     *
     * @param p le rappel contenant les nouvelles informations à mettre à jour
     */
	public static void modifierRappel(Rappel p){
		try{
			Connection cnx = DatabaseManager.getConnection();
			PreparedStatement pst = cnx.prepareStatement("UPDATE Rappel SET Titre = ?,Contenu = ?,Theme = ? WHERE Id = ?");
			pst.setString(1,p.getTitre());
			pst.setString(2,p.getContenu());
			pst.setInt(3,p.getTheme());
			pst.setInt(4,p.getId());
			Confirmation conf = new Confirmation(null,"Confirmation","Êtes-vous sûr de vouloir modifier ce rappel ?");
			conf.setVisible(true);
			if(conf.getAction().equals("Continuer")){
				pst.executeQuery();
			}

			try{
				cnx.close();
			}catch(SQLException e){
				e.printStackTrace();
				//implémenter gestion des erreurs
			}
		}catch(SQLException e){
			e.printStackTrace();
			//implémenter gestion des erreurs
		}
	}
}