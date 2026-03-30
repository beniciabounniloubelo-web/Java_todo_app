import java.sql.*;
import java.util.ArrayList;

/**
 * La classe <code>LectureRappel</code> fournit des méthodes statiques
 * pour lire les rappels stockés dans la base de données.
 * <p>
 * Elle permet de récupérer le nombre de rappels, la liste complète des rappels
 * et un rappel précis par son identifiant.
 * </p>
 */
public class LectureRappel {

	/**
     * Constructeur privé pour empêcher l’instanciation de la classe.
     * <p>
     * Cette classe ne contient que des méthodes statiques et ne doit pas être instanciée.
     * </p>
     */
    private LectureRappel() {
        // Empêche l’instanciation
    }

	/**
     * Retourne le nombre total de rappels dans la base de données.
     *
     * @return le nombre de rappels
     */
	public static int getNbRappels(){
		int res = 0;
		try{
			Connection cnx = DatabaseManager.getConnection();
			PreparedStatement pst = cnx.prepareStatement("SELECT COUNT(*) FROM Rappel");
			ResultSet rs = pst.executeQuery();
			rs.next();
			res = rs.getInt(1);
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
		return res;
	}

	/**
     * Récupère tous les rappels présents dans la base de données,
     * triés par ordre de rang croissant.
     *
     * @return une liste d'objets <code>Rappel</code>
     */
	public static ArrayList<Rappel> getListeRappels(){
		ArrayList<Rappel> titres = new ArrayList<>();
		try{
			Connection cnx = DatabaseManager.getConnection();
			PreparedStatement pst = cnx.prepareStatement("SELECT id,titre,contenu,theme,rang,statut FROM Rappel ORDER BY rang ASC");
			ResultSet rs = pst.executeQuery();

			while(rs.next()){
				int id = rs.getInt(1);
				String titre = rs.getString(2);
				String contenu = rs.getString(3);
				int theme = rs.getInt(4);
				int importance = rs.getInt(5);
				String strstat = rs.getString(6);
				boolean statut = false; //false == non fini
				if(strstat.equals("Fini")){
					statut = true;
				}
				titres.add(new Rappel(id,titre,contenu,theme,importance,statut));
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

		return titres;
	}

	/**
     * Récupère un rappel spécifique à partir de son identifiant.
     *
     * @param i l'identifiant du rappel à récupérer
     * @return le rappel correspondant sous forme d'objet <code>Rappel</code>
     */
	public static Rappel getRappel(int i){
		int id = i;
		String titre = new String();
		String contenu = new String();
		int theme = 1;
		int importance = 1;
		boolean statut = false;

		try{
			Connection cnx = DatabaseManager.getConnection();
			PreparedStatement pst = cnx.prepareStatement("SELECT id,titre,contenu,theme,rang,statut FROM Rappel WHERE id = ?");
			pst.setInt(1,i);
			ResultSet rs = pst.executeQuery();
			rs.next();
			id = rs.getInt(1);
			titre = rs.getString(2);
			contenu = rs.getString(3);
			theme = rs.getInt(4);
			importance = rs.getInt(5);
			String strstat = rs.getString(6);
			if(strstat.equals("Fini")){
				statut = true;
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
		Rappel rap = new Rappel(id,titre,contenu,theme,importance,statut);
		return rap;
	}
}