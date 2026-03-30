import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 

/**
 * La classe <code>JModifRappel</code> représente un panneau graphique
 * permettant de modifier les informations d’un rappel existant.
 * <p>
 * L’utilisateur peut :
 * <ul>
 *   <li>modifier le titre et le contenu du rappel,</li>
 *   <li>changer le thème du rappel,</li>
 *   <li>valider les modifications ou retourner à la fiche du rappel.</li>
 * </ul>
 * <p>
 * Le panneau est associé à une fiche existante afin que les changements
 * soient reflétés immédiatement.
 */
public class JModifRappel extends JPanel implements ActionListener{

	/** La fiche du rappel associée à cette modification. */
	private JFicheRappel ficheassoc;

	/** Bouton pour retourner à la fiche sans modifier le rappel. */
	private JButton retour;

	/** Bouton pour valider les modifications du rappel. */
	private JButton valider;

	/** Zone de texte pour modifier le titre du rappel. */
	private JTextArea titre;

	/** Zone de texte pour modifier le contenu du rappel. */
	private JTextArea content;

	/** Rang (importance) du rappel. */
	private int rang;

	/** Thème sélectionné pour le rappel (de 1 à 5). */
	private int themevalue;

	/** Le rappel à modifier. */
	private Rappel rap;

	/**
     * Construit le panneau de modification pour un rappel donné.
     *
     * @param p  le rappel à modifier
     * @param fa la fiche associée à ce rappel
     */
	public JModifRappel(Rappel p, JFicheRappel fa){
		super();

		this.ficheassoc = fa;
		this.rap = p;
		this.rang = p.getImportance();

		setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		this.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1.0;

		JLabel invittitre = new JLabel("Nom du rappel :");
		this.titre = new JTextArea();
		this.titre.setText(rap.getTitre());
		this.titre.setRows(2);	
		this.titre.setLineWrap(true);	
		this.add(invittitre,gbc);

		gbc.gridy++;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(this.titre,gbc);

		gbc.gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		JLabel invitcontent = new JLabel("Description :");
		this.content = new JTextArea();
		content.setText(rap.getContenu());
		content.setRows(6);
		content.setLineWrap(true); //retour à la ligne
		content.setWrapStyleWord(true);	
		this.add(invitcontent,gbc);

		gbc.gridy++;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(this.content,gbc);

		JPanel choixtheme = new JPanel();
		choixtheme.add(new JLabel("Thème :"));
		ButtonGroup group = new ButtonGroup();
		int theme = rap.getTheme();
		for(int i = 1; i<=5; i++){
			JCheckBox box = new JCheckBox(""+i);
			choixtheme.add(box);
			group.add(box);
			if(i == theme){
				box.setSelected(true);
			}
			this.themevalue = theme;
			box.addActionListener(this);
			box.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 4));
			box.setOpaque(true);
			switch(i){
				case 1:
					box.setBackground(new Color(249, 44, 44));
					break;
				case 2:
					box.setBackground(new Color(10, 152, 250));
					break;
				case 3:
					box.setBackground(new Color(250, 150, 20));
					break;
				case 4:
					box.setBackground(new Color(25, 169, 0));
					break;
				default:
					box.setBackground(new Color(0, 0, 0));
					box.setForeground(Color.WHITE);
					break;
			}
		}

		JPanel panelbouton = new JPanel();
		this.retour = new JButton("Retour au rappel");
		this.valider = new JButton("Modifier le rappel");
		panelbouton.add(this.valider);
		panelbouton.add(this.retour);

		this.retour.addActionListener(this);
		this.valider.addActionListener(this);

		gbc.gridy++;
		this.add(choixtheme,gbc);
		gbc.gridy++;
		this.add(panelbouton,gbc);

	}

	/**
     * Gère les actions sur les boutons et sur les cases à cocher des thèmes.
     * <p>
     * Si l'utilisateur valide la modification, le rappel est mis à jour,
     * la fiche associée est rafraîchie et le panneau de modification est supprimé.
     * Si l'utilisateur clique sur retour, le panneau est simplement supprimé
     * et la fiche est réaffichée.
     *
     * @param e l'événement déclenché par l'utilisateur
     */
	public void actionPerformed(ActionEvent e){
		String action = e.getActionCommand();

		Container parent = this.getParent();
		while (!(parent instanceof MenuPapillon)) {
		    parent = parent.getParent();
		}
		if (parent instanceof MenuPapillon) {
			if(action.equals("Modifier le rappel")){
				boolean continuer = true;
				if(titre.getText().isEmpty()||titre.getText().length()>50){
					JErreur erreur = new JErreur((Frame)SwingUtilities.getWindowAncestor(this),"Erreur","Le titre ne peut pas etre vide ou dépasser 50 caractères.");
					erreur.setVisible(true);
					continuer = false;
				}
				if(content.getText().length()>200){
					JErreur erreur = new JErreur((Frame)SwingUtilities.getWindowAncestor(this),"Erreur","Le contenu ne peut pas dépasser 200 caractères.");
					erreur.setVisible(true);
					continuer = false;
				}
				if(continuer){
					Rappel p = new Rappel(this.rap.getId(),this.titre.getText(),this.content.getText(),this.themevalue,this.rang,this.rap.getStatut());
					ModifierRappel.modifierRappel(p);
					((MenuPapillon) parent).updateRappels();
					this.ficheassoc.update();
					((MenuPapillon) parent).afficherCarte("fiche");
					((MenuPapillon) parent).supprimerCarte(this);					
				}
			}else if(action.equals("Retour au rappel")){
				((MenuPapillon) parent).afficherCarte("fiche");
				((MenuPapillon) parent).supprimerCarte(this);
			}else{
				this.themevalue = Integer.parseInt(action); // dans ce cas, actionPerformed a été sollicité par les JCheckBox
			}
		}
	}
}