import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * La classe <code>JListeRappels</code> représente le panneau graphique affichant
 * la liste de tous les rappels disponibles.
 * <p>
 * Elle permet à l’utilisateur de :
 * <ul>
 *   <li>consulter tous les rappels sous forme de composants JRappel,</li>
 *   <li>cliquer sur un rappel pour voir ses détails,</li>
 *   <li>ajouter un nouveau rappel via le bouton "+".</li>
 * </ul>
 * <p>
 * La classe gère également la mise à jour dynamique de la liste.
 * </p>
 */
public class JListeRappels extends JPanel implements ActionListener,MouseListener{

	/** Bouton permettant d'ajouter un nouveau rappel. */
	private JButton ajouter;
	
	/** Panneau contenant la liste de tous les rappels sous forme de composants graphiques. */
	private JPanel panelliste;

	/**
     * Construit le panneau contenant la liste des rappels et le bouton d'ajout.
     */
	public JListeRappels(){
		super(new BorderLayout());


		this.panelliste = new JPanel(new GridBagLayout());
		this.panelliste.setBackground(Color.WHITE);
		JPanel panelbouton = new JPanel();

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(5, 10, 5, 10);

		ListeRappels lr = new ListeRappels();
		List<Rappel> tabrap = lr.getListe();

		for(Rappel r : tabrap) {
			JRappel rap = new JRappel(r);
			rap.addMouseListener(this);
			rap.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
			rap.setBackground(new Color(245, 245, 245));
			rap.setOpaque(true);
			panelliste.add(rap, gbc);
			gbc.gridy++; // ligne suivante
		}

		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		panelliste.add(Box.createVerticalGlue(), gbc);

		JScrollPane scrollPane = new JScrollPane(panelliste);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); //pour ne pas scroller horizontalement

		JPanel panelBouton = new JPanel(new BorderLayout());
		panelBouton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelBouton.setBackground(Color.WHITE);

		ajouter = new JButton("+");
		this.ajouter.addActionListener(this);
		ajouter.setFont(new Font("Arial", Font.BOLD, 18));
		panelBouton.add(ajouter, BorderLayout.EAST);

		this.add(scrollPane,BorderLayout.CENTER);
		this.add(panelBouton, BorderLayout.SOUTH);
	}

	/**
     * Renvoie le bouton permettant d'ajouter un nouveau rappel.
     *
     * @return le bouton "+"
     */
	public JButton getAjouter(){
		return this.ajouter;
	}

	/**
     * Met à jour l’affichage de la liste des rappels.
     * <p>
     * Supprime les composants existants et recrée la liste complète à partir des données actuelles.
     * </p>
     */
	public void updateRappels(){
		
		panelliste.removeAll(); 

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(5, 10, 5, 10);

		ListeRappels lr = new ListeRappels();
		List<Rappel> tabrap = lr.getListe();

		for(Rappel r : tabrap) {
			JRappel rap = new JRappel(r);
			rap.addMouseListener(this);
			rap.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
			rap.setBackground(new Color(245, 245, 245));
			rap.setOpaque(true);
			panelliste.add(rap, gbc);
			gbc.gridy++; // ligne suivante
		}

		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		panelliste.add(Box.createVerticalGlue(), gbc);

		panelliste.revalidate(); 
		panelliste.repaint();  
	}

	/**
     * Gère l'action du bouton "+" pour créer un nouveau rappel.
     *
     * @param e l'événement déclenché par l'utilisateur
     */
	public void actionPerformed(ActionEvent e){
		String bouton = e.getActionCommand();
		Container parent = this.getParent();
		while (!(parent instanceof MenuPapillon)) {
		    parent = parent.getParent();
		}
		if (parent instanceof MenuPapillon) {
			if(bouton.equals("+")){
				((MenuPapillon) parent).afficherCarte("Creer");
			}
		}
	}

	/**
     * Gère le clic sur un rappel pour afficher ses détails.
     *
     * @param e l'événement de souris déclenché
     */
	public void mouseClicked(MouseEvent e){
		JRappel rappelclique = (JRappel) e.getSource();

		Container parent = this.getParent();
		while (!(parent instanceof MenuPapillon)) {
		    parent = parent.getParent();
		}
		if (parent instanceof MenuPapillon) {
		    ((MenuPapillon) parent).ajouterCarte(new JFicheRappel(LectureRappel.getRappel(rappelclique.getId())),"fiche");
			((MenuPapillon) parent).afficherCarte("fiche");
		}
	}

	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}

}