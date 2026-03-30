import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * La classe <code>JFicheRappel</code> représente l'interface graphique affichant
 * les détails d’un {@link Rappel}. 
 * <p>
 * Elle permet à l’utilisateur de :
 * <ul>
 *   <li>consulter le titre et le contenu du rappel,</li>
 *   <li>modifier ou supprimer le rappel,</li>
 *   <li>retourner à la liste des rappels via {@link MenuPapillon}.</li>
 * </ul>
 * 
 * @see Rappel
 * @see LectureRappel
 * @see ModifierRappel
 * @see MenuPapillon
 */
public class JFicheRappel extends JPanel implements ActionListener{
	
	/** Le rappel dont les détails sont affichés dans cette fiche */
	private Rappel rappel;

	/** Label affichant le titre du rappel */
	private JLabel titre;

	/** Label affichant le contenu du rappel */
	private JLabel contenu;

	/** Rectangle indiquant visuellement le thème du rappel */
	private JPanel themeRectangle;

	/**
     * Construit une nouvelle fiche rappel affichant les informations
     * d’un rappel spécifique.
     *
     * @param rap le {@link Rappel} dont les détails doivent être affichés
     */
	public JFicheRappel(Rappel rap){
		super(new GridBagLayout());
		this.rappel = rap;
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Mise en place du layout et du contenu
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.gridy = 0;
		titre = new JLabel("<html><body style=width:100%;>" + rap.getTitre() + "</body></html>");
		titre.setFont(new Font("Arial", Font.BOLD, 24));
		titre.setForeground(Color.BLACK);
		this.add(titre, gbc);

		gbc.gridy = 1;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;

		contenu = new JLabel("<html><body style=width:100%;>" + rap.getContenu() + "</body></html>");
		contenu.setFont(new Font("Arial", Font.PLAIN, 14));
		contenu.setForeground(Color.DARK_GRAY);
		this.add(contenu, gbc);

		gbc.gridy = 2;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JPanel panelBoutons = new JPanel(new GridBagLayout());
		panelBoutons.setBackground(Color.WHITE);
		GridBagConstraints gbcBout = new GridBagConstraints();
		gbcBout.insets = new Insets(5, 10, 5, 10);

		JButton retour = new JButton("Retour à la liste");
		JButton modif = new JButton("Modifier");
		JButton supp = new JButton("Supprimer");
		supp.setForeground(Color.WHITE);
		supp.setBackground(Color.RED);

		retour.addActionListener(this);
		modif.addActionListener(this);
		supp.addActionListener(this);

		gbcBout.gridx = 0;
		panelBoutons.add(retour, gbcBout);
		gbcBout.gridx = 1;
		panelBoutons.add(modif, gbcBout);
		gbcBout.gridx = 2;
		panelBoutons.add(supp, gbcBout);

		this.add(panelBoutons, gbc);

		// Définition de la couleur du thème
		Color rectanglecolor = new Color(0,0,0);
		switch(this.rappel.getTheme()){
			case 1:
				rectanglecolor = new Color(249, 44, 44);
				break;
			case 2:
				rectanglecolor = new Color(10, 152, 250);
				break;
			case 3:
				rectanglecolor = new Color(250, 150, 20);
				break;
			case 4:
				rectanglecolor = new Color(25, 169, 0);
				break;
			default:
				rectanglecolor = new Color(0, 0, 0);
				break;
		}
		this.themeRectangle = new JPanel();
		this.themeRectangle.setBackground(rectanglecolor);
		this.themeRectangle.setPreferredSize(new Dimension(12, 0));

		GridBagConstraints gbcRect = new GridBagConstraints();
		gbcRect.gridx = 0;
		gbcRect.gridy = 0;
		gbcRect.gridheight = 3;
		gbcRect.fill = GridBagConstraints.VERTICAL;
		gbcRect.weighty = 1.0;
		gbcRect.insets = new Insets(0, 0, 0, 8);

		this.add(this.themeRectangle, gbcRect);
		
	}

	/**
     * Retourne l'identifiant du rappel affiché.
     *
     * @return l'identifiant du rappel
     */
	public int getId(){
		return this.rappel.getId();
	}

	/**
     * Met à jour l’affichage de la fiche en rechargeant les informations
     * du rappel depuis la base de données.
     */
	public void update(){
		Rappel nouveauRappel = LectureRappel.getRappel(this.rappel.getId());
		this.rappel = nouveauRappel;
		this.titre.setText("<html><body style=width:100%>" + rappel.getTitre() + "</body></html>");
		this.contenu.setText("<html><body style='width:100%;'>" + rappel.getContenu() + "</body></html>");
				Color rectanglecolor = new Color(0,0,0);
		switch(this.rappel.getTheme()){
			case 1:
				rectanglecolor = new Color(249, 44, 44);
				break;
			case 2:
				rectanglecolor = new Color(10, 152, 250);
				break;
			case 3:
				rectanglecolor = new Color(250, 150, 20);
				break;
			case 4:
				rectanglecolor = new Color(25, 169, 0);
				break;
			default:
				rectanglecolor = new Color(0, 0, 0);
				break;
		}
		this.themeRectangle.setBackground(rectanglecolor);
		this.revalidate();
		this.repaint();
	}

	/**
     * Gère les actions des boutons :
     * <ul>
     *   <li>« Supprimer » : supprime le rappel,</li>
     *   <li>« Retour à la liste » : retourne à la liste des rappels,</li>
     *   <li>« Modifier » : ouvre le panneau {@link JModifRappel} correspondant.</li>
     * </ul>
     *
     * @param e l’événement déclenché par une action utilisateur
     */
	public void actionPerformed(ActionEvent e){
		String bouton = e.getActionCommand();
		Container parent = this.getParent();
		while (!(parent instanceof MenuPapillon)) {
		parent = parent.getParent();
		}
		if (parent instanceof MenuPapillon) {
			if(bouton.equals("Supprimer")){
				ModifierRappel.supprimerRappel(this.rappel.getId());
				((MenuPapillon) parent).updateRappels();
				((MenuPapillon) parent).retourListe();

			}else if(bouton.equals("Retour à la liste")){
				((MenuPapillon) parent).retourListe();
				((MenuPapillon) parent).supprimerCarte(this);

			}else if(bouton.equals("Modifier")){
				((MenuPapillon) parent).ajouterCarte(new JModifRappel(this.rappel, this),"modif");
				((MenuPapillon) parent).afficherCarte("modif");
			}
		}

	}

}