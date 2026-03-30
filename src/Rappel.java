/**
 * La classe <code>Rappel</code> représente un rappel avec ses informations principales.
 * <p>
 * Un rappel possède un identifiant unique, un titre, un contenu, un thème, un niveau d'importance,
 * ainsi qu'un statut indiquant s'il est validé ou non.
 * </p>
 * 
 * <p>Cette classe sert principalement de modèle pour l'affichage et la manipulation des rappels
 * dans l'application.</p>
 */
public class Rappel{

	/** Identifiant unique du rappel. */
	private int id;

	/** Titre du rappel. */
	private String titre;

	/** Contenu ou description du rappel. */
	private String contenu;

	/** Identifiant du thème associé au rappel. */
	private int theme;

	/** Niveau d'importance du rappel (plus le chiffre est petit, plus c'est important). */
	private int importance;

	/** Statut du rappel : true si validé, false sinon. */
	private boolean statut;
	
	/**
     * Construit un nouveau rappel avec toutes ses informations.
     *
     * @param i l'identifiant du rappel
     * @param t le titre du rappel
     * @param cont le contenu ou description du rappel
     * @param th le thème associé au rappel
     * @param imp l'importance du rappel
     * @param st le statut du rappel (true = validé, false = non validé)
     */
	public Rappel(int i, String t, String cont, int th, int imp, boolean st){
		this.id = i;
		this.titre = t;
		this.contenu = cont;
		this.theme = th;
		this.importance = imp;
		this.statut = st;
	}

	/**
     * Retourne l'identifiant unique du rappel.
     *
     * @return l'identifiant du rappel
     */
	public int getId(){
		return this.id;
	}

	/**
     * Retourne le titre du rappel.
     *
     * @return le titre du rappel
     */
	public String getTitre(){
		return this.titre;
	}

	/**
     * Retourne le contenu ou la description du rappel.
     *
     * @return le contenu du rappel
     */
	public String getContenu(){
		return this.contenu;
	}

	/**
     * Retourne l'identifiant du thème associé au rappel.
     *
     * @return l'identifiant du thème
     */
	public int getTheme(){
		return this.theme;
	}

	/**
     * Retourne le niveau d'importance du rappel.
     *
     * @return l'importance du rappel
     */
	public int getImportance(){
		return this.importance;
	}

    /**
     * Indique si le rappel est validé.
     *
     * @return true si le rappel est validé, false sinon
     */
	public boolean getStatut(){
		return this.statut;
	}

}