import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Représente une liste de rappels récupérés depuis la base de données.
 * <p>
 * Cette classe sert de modèle pour gérer et fournir un accès aux rappels
 * sous forme de liste. Elle initialise automatiquement sa liste en récupérant
 * les rappels via la classe {@link LectureRappel}.
 * </p>
 */
public class ListeRappels{

    /** La liste de rappels. */
    private List<Rappel> liste;

    /**
     * Construit une nouvelle instance de {@code ListeRappels}.
     * <p>
     * La liste est initialisée avec tous les rappels existants dans la base de données.
     * </p>
     */
    public ListeRappels(){
        this.liste = new ArrayList<>(LectureRappel.getListeRappels());
    }

    /**
     * Retourne la liste des rappels.
     *
     * @return la liste des rappels
     */
    public List<Rappel> getListe(){
        return this.liste;
    }
}