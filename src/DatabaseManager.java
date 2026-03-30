import java.sql.*;

/**
 * Classe permettant de récupérer la connection à la base de données MariaDB
 * <p>
 * Cette classe centralise les informations de connexion (URL, identifiant, mot de passe)
 * et fournit une méthode statique {@link #getConnection()} pour créer une connexion.
 * </p>
 * 
 * <p><strong>Remarque :</strong> Les identifiants de connexion sont stockés en clair 
 * pour simplifier le développement. Dans un contexte réel, mieux vaudrait 
 * utiliser un fichier de configuration ou des variables d'environnement.</p>
 */
public class DatabaseManager {
	
	/** URL de connexion à la base de données. */
	private static String URL = "jdbc:mariadb://dwarves.iut-fbleau.fr/lecheval";

	/** Login */
    private static String USER = "lecheval";

    /** Mdp (en clair malheureusement) */
    private static String MDP = "Camcamupec1190!";

    /**
     * Constructeur privé pour empêcher l’instanciation de la classe.
     */
    private DatabaseManager() {
        // Empêche la création d'instances
    } 
    
	/**
     * Retourne une connexion à la base de données.
     * <p>
     * L'utilisateur est responsable de fermer la connexion une fois qu'elle n'est plus utilisée,
     * avec {@link Connection#close()}.
     * </p>
     *
     * @return un objet {@link Connection} connecté à la base de données.
     * @throws SQLException si la connexion échoue.
     */
    public static Connection getConnection() throws SQLException{
    	 return DriverManager.getConnection(URL, USER, MDP);
    }
}