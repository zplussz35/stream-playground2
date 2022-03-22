package brickset;


import repository.Repository;

import java.util.List;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * Prints LEGO set names with more pieces than specified.
     *
     * @param MinCountOfPieces The minimum count of pieces which specified.
     */
    public void printLegoSetNamesWithMorePiecesThan(int MinCountOfPieces) {
        List<String> resultLegoSets = getAll().stream().
                filter(l -> l.getPieces() > MinCountOfPieces).
                map(LegoSet::getName).toList();
        System.out.println("Lego set names with more pieces than " + MinCountOfPieces + " :" + resultLegoSets);
    }

    /**
     * Returns the number of LEGO sets with the theme Duplo.
     *
     * @return the number of LEGO sets with the theme Duplo.
     */
    public long countLegoSetsWithThemeDuplo() {
        return getAll().stream()
                .filter(l -> l.getTheme().equals("Duplo"))
                .count();

    }

    /**
     * Returns the maximum piece count in all LEGO sets.
     *
     * @return the maximum piece count in all LEGO sets.
     */
    public int maxPiecesInLegoSets() {
        return getAll().stream()
                .mapToInt(LegoSet::getPieces)
                .max()
                .orElse(0);
    }

    /**
     * Returns List of LEGO set names with higher weight than specified.
     *
     * @param minWeight The minimum weight of pieces which specified.
     * @return list of LEGO set names with higher weight than specified.
     */
    public List<String> LegoSetNamesWithHigherWeightThan(double minWeight) {
        return getAll().stream()
                .filter(l -> l.getDimensions() != null && l.getDimensions().getWeight() != null && l.getDimensions().getWeight() > minWeight)
                .map(LegoSet::getName)
                .toList();
    }

    /**
     * Returns the LEGO set names which has subtheme.
     *
     * @return the LEGO set names which has subtheme.
     */
    public List<String> LEgoSetNamesWhichHasSubtheme() {
        return getAll().stream()
                .filter(l -> l.getSubtheme() != null)
                .map(LegoSet::getName)
                .toList();

    }


    public static void main(String[] args) {


        LegoSetRepository repository = new LegoSetRepository();
        repository.printLegoSetNamesWithMorePiecesThan(200);
        System.out.println("Lego set names which has subtheme: " + repository.LEgoSetNamesWhichHasSubtheme());
        System.out.println("Count of Lego sets with theme Duplo: " + repository.countLegoSetsWithThemeDuplo());
        System.out.println("Max pieces in Lego sets: " + repository.maxPiecesInLegoSets());
        System.out.println("Lego sets with higher weight than 1.0 kg: " + repository.LegoSetNamesWithHigherWeightThan(1.0));


    }

}
