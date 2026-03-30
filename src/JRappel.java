import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * La classe <code>JRappel</code> représente un composant graphique individuel
 * affichant un rappel dans la liste des rappels.
 * <p>
 * Chaque composant contient :
 * <ul>
 *   <li>un indicateur de thème coloré,</li>
 *   <li>une case à cocher pour le statut du rappel (fini / non fini),</li>
 *   <li>le titre du rappel,</li>
 *   <li>des boutons pour modifier l'ordre (importance) du rappel.</li>
 * </ul>
 *
 * Le composant est capable de notifier la liste parente lors de modifications
 * de statut ou de rang.
 */
public class JRappel extends JPanel implements ActionListener,ItemListener{
	
	/** Identifiant unique du rappel. */
	private int id;

	/** Thème du rappel (1 à 5). */
	private int theme;

	/** Importance du rappel (pour l'ordre d'affichage). */
	private int importance;

	/** Statut du rappel : true si terminé, false sinon. */
	private boolean statut;


	/** Case à cocher pour valider ou invalider le rappel. */
	private JCheckBox bouton;

	/** Label affichant le titre du rappel. */
	private JLabel titre;

	/** Rectangle coloré représentant le thème du rappel. */
	private JPanel rectangletheme;

	/** Bouton pour augmenter l'importance du rappel. */
	private JButton monter;

	/** Bouton pour diminuer l'importance du rappel. */
	private JButton descendre;

	/**
     * Construit le composant graphique pour un rappel donné.
     *
     * @param p le rappel à afficher
     */
	public JRappel(Rappel p){
		super(new GridBagLayout());

		this.id = p.getId();
		this.statut = p.getStatut();
		this.importance = p.getImportance();
		this.theme = p.getTheme();

		setBackground(Color.WHITE);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(2, 5, 2, 5);
		gbc.anchor = GridBagConstraints.CENTER;

		Color rectanglecolor = new Color(0,0,0);
		switch (theme) {
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
		rectangletheme = new JPanel();
		rectangletheme.setPreferredSize(new Dimension(9, 30));
		rectangletheme.setBackground(rectanglecolor);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.VERTICAL;
		this.add(rectangletheme, gbc);

		bouton = new JCheckBox();
		bouton.setSelected(this.statut);
		bouton.setOpaque(false);
		bouton.addItemListener(this);
		bouton.setPreferredSize(new Dimension(22, 22));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(bouton, gbc);

		titre = new JLabel("<html><body style='width: 100px;'>" + p.getTitre() + "</body></html>");
		titre.setFont(new Font("Arial", Font.PLAIN, 13));
		titre.setForeground(Color.DARK_GRAY);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		this.add(titre, gbc);

		JPanel panelBoutons = new JPanel();
		panelBoutons.setOpaque(false);

		monter = new JButton("▲");
		monter.setFont(new Font("Arial", Font.BOLD, 10));
		monter.setMargin(new Insets(2,4,2,4));
		monter.setFocusable(false);
		monter.addActionListener(this);
		monter.setPreferredSize(new Dimension(50, 40));
		panelBoutons.add(monter);

		descendre = new JButton("▼");
		descendre.setFont(new Font("Arial", Font.BOLD, 10));
		descendre.setMargin(new Insets(2,4,2,4));
		descendre.setFocusable(false);
		descendre.addActionListener(this);
		descendre.setPreferredSize(new Dimension(50, 40));
		panelBoutons.add(descendre);

		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(panelBoutons, gbc);

	}

	/**
     * Renvoie l'identifiant du rappel.
     *
     * @return l'identifiant du rappel
     */
	public int getId(){
		return this.id;
	}

	/**
     * Gère les actions sur les boutons pour modifier l'ordre d'importance du rappel.
     *
     * @param e l'événement déclenché par l'utilisateur
     */
	public void actionPerformed(ActionEvent e){
		String action = e.getActionCommand();
		if(action.equals("▲")){
			if(this.importance != 1){
				ModifierRappel.inverserRangs(this.id,true);
			}
		}else{
			if(this.importance != LectureRappel.getNbRappels()){
				ModifierRappel.inverserRangs(this.id,false);
			}
		}
		Container parent = this.getParent();
		while (!(parent instanceof JListeRappels)) {
		parent = parent.getParent();
		}
		if (parent instanceof JListeRappels) {
		((JListeRappels) parent).updateRappels();
		}
	}

	/**
     * Gère les changements de statut via la case à cocher.
     *
     * @param e l'événement de modification de l'état de la case à cocher
     */
	public void itemStateChanged(ItemEvent e){
		if(e.getStateChange() == 1){ //validé
			EcritureRappel.setStatut(id,"Fini");
		}else{
			EcritureRappel.setStatut(id,"Non fini");
		}
	}
}