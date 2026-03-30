import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * La classe <code>JCreerRappel</code> représente le panneau graphique
 * permettant à l'utilisateur de créer un nouveau rappel.
 * <p>
 * Elle contient plusieurs champs de saisie :
 * <ul>
 *   <li>un champ pour le titre,</li>
 *   <li>un champ pour la description,</li>
 *   <li>un ensemble de cases à cocher pour sélectionner un thème,</li>
 *   <li>et deux boutons : <b>"Créer le rappel"</b> et <b>"Retour à la liste"</b>.</li>
 * </ul>
 * <p>
 * Lors de la validation, le panneau vérifie la validité des données saisies
 * (longueur du titre et du contenu). En cas d'erreur, une fenêtre
 * {@link JErreur} est affichée. Si tout est correct, le rappel est ajouté
 * dans la base de données.
 * </p>
 * <p>
 * Après la création ou le retour, le panneau communique avec 
 * {@link MenuPapillon} pour mettre à jour ou changer la vue affichée.
 * </p>
 *
 * @see EcritureRappel
 * @see JErreur
 * @see MenuPapillon
 */
public class JCreerRappel extends JPanel implements ActionListener {

	/** Bouton permettant de revenir à la liste des rappels. */
	private JButton retour;

	/** Bouton permettant de valider la création du rappel. */
	private JButton valider;

	/** Zone de texte utilisée pour saisir le titre du rappel. */
	private JTextArea titre;

	/** Zone de texte utilisée pour saisir le contenu (description) du rappel. */
	private JTextArea content;
	
	/** Valeur numérique correspondant au thème sélectionné. */
	private int themevalue;

	/**
     * Constructeur de la classe.
     * <p>
     * Initialise l’interface permettant de créer un nouveau rappel,
     * avec les champs de saisie, le choix du thème et les boutons d’action.
     * </p>
     */
	public JCreerRappel(){
		super();

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
		content.setRows(6);
		content.setLineWrap(true);
		content.setWrapStyleWord(true); //retour à la ligne	
		this.add(invitcontent,gbc);

		gbc.gridy++;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(this.content,gbc);

		JPanel choixtheme = new JPanel();
		choixtheme.add(new JLabel("Thème :"));
		ButtonGroup group = new ButtonGroup();
		for(int i = 1; i<=5; i++){
			JCheckBox box = new JCheckBox(""+i);
			choixtheme.add(box);
			group.add(box);
			if(i == 1){
				box.setSelected(true);
			}
			this.themevalue = 1;
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
		this.retour = new JButton("Retour à la liste");
		this.valider = new JButton("Créer le rappel");
		panelbouton.add(this.valider);
		panelbouton.add(this.retour);

		this.valider.addActionListener(this);
		this.retour.addActionListener(this);

		gbc.gridy++;
		this.add(choixtheme,gbc);
		gbc.gridy++;
		this.add(panelbouton,gbc);
	}

	/**
     * Renvoie le bouton de retour à la liste des rappels.
     *
     * @return le bouton "Retour à la liste"
     */
	public JButton getRetour(){
		return this.retour;
	}

	/**
     * Renvoie le bouton de validation de la création du rappel.
     *
     * @return le bouton "Créer le rappel"
     */
	public JButton getBoutonValider(){
		return this.valider;
	}

	/**
     * Gère les actions de l'utilisateur sur les boutons et les cases à cocher.
     * <ul>
     *   <li>Vérifie la validité des champs lors de la création d’un rappel.</li>
     *   <li>Affiche une {@link JErreur} en cas de saisie incorrecte.</li>
     *   <li>Ajoute le rappel.</li>
     *   <li>Informe {@link MenuPapillon} pour mettre à jour la vue ou revenir à la liste.</li>
     * </ul>
     *
     * @param e l'événement déclenché par l'utilisateur
     */
	public void actionPerformed(ActionEvent e){
		String action = e.getActionCommand();
		if(action.equals("Créer le rappel")){
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
				EcritureRappel.addRappel(titre.getText(),content.getText(),this.themevalue);
				this.titre.setText("");
				this.content.setText("");
				Container parent = this.getParent();
				while (!(parent instanceof MenuPapillon)) {
				    parent = parent.getParent();
				}
				if (parent instanceof MenuPapillon) {
					((MenuPapillon) parent).updateRappels();
				}

			}
		}else if(action.equals("Retour à la liste")){
			Container parent = this.getParent();
			while (!(parent instanceof MenuPapillon)) {
			    parent = parent.getParent();
			}
			if (parent instanceof MenuPapillon) {
				((MenuPapillon) parent).afficherCarte("Liste");
			}
		}else{
			this.themevalue = Integer.parseInt(action); // dans ce cas, actionPerformed a été sollicité par les JCheckBox
		}
	}


}