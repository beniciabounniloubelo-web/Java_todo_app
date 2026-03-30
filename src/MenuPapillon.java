import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * La classe <code>MenuPapillon</code> représente le menu principal de l'application.
 * <p> Elle utilise un <code>CardLayout</code> pour gérer différentes "pages" : </p>
 * <ul>
 *   <li>La liste des rappels (<code>JListeRappels</code>).</li>
 *   <li>La création d'un nouveau rappel (<code>JCreerRappel</code>).</li>
 *   <li>Éventuellement d'autres cartes comme la fiche d'un rappel ou sa modification.</li>
 * </ul>
 * <p> Le menu principal reste toujours chargé pour permettre un accès rapide à la liste des rappels. </p>
 */
public class MenuPapillon extends JFrame{

	/** Le CardLayout permettant de gérer les différentes cartes de l'interface */
	private CardLayout cartes;

	/** La carte affichant la liste des rappels */
	private JListeRappels listerappels;


	/**
     * Constructeur de la fenêtre principale.
     * Initialise le CardLayout, la liste des rappels et la carte de création.
     */
	public MenuPapillon(){
		super("A retenir");
		this.setSize(500,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.cartes = new CardLayout();
		this.setLayout(cartes);
		this.listerappels = new JListeRappels();

		JCreerRappel createur = new JCreerRappel();
		this.setAlwaysOnTop(true); //permet à la fenêtre d'être au premier plan
		this.add(listerappels, "Liste");
		this.add(createur, "Creer");
		this.setVisible(true);

	}

	/**
     * Ajoute une nouvelle carte (panel) au CardLayout.
     *
     * @param p le JPanel à ajouter
     * @param nom le nom de la carte
     */
	public void ajouterCarte(JPanel p, String nom){
		this.add(p, nom);
	}

	/**
     * Affiche la carte correspondant au nom fourni.
     *
     * @param nom le nom de la carte à afficher
     */
	public void afficherCarte(String nom){
		this.cartes.show(this.getContentPane(), nom);
	}

	/**
     * Retourne automatiquement à la carte principale de la liste des rappels.
     */
	public void retourListe(){
		this.cartes.show(this.getContentPane(), "Liste");
	}

	/**
     * Supprime une carte du CardLayout.
     *
     * @param p le JPanel à supprimer
     */
	public void supprimerCarte(JPanel p){
		this.remove(p);
		cartes.removeLayoutComponent(p);
		this.revalidate();
		this.repaint();
	}

	/**
     * Met à jour la liste des rappels affichée dans la carte principale.
     */
	public void updateRappels(){
		this.listerappels.updateRappels();
	}

}