package brickset;


import repository.Repository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * Prints that Legoset with subtheme is exist or not.
     *
     * @return true if there any Legoset with subtheme,otherwise false
     */
    public boolean hasAnyLegoSetSubTheme() {
        return getAll().stream().
                anyMatch(l->l.getSubtheme()!=null);
    }

    /**
     * Returns the number of tags of LEGO sets.
     *
     * @return the number of tags of LEGO sets.
     */
    public long countAllTagsOfAllLegoSets() {
        return getAll().stream()
                .filter(l->l.getTags()!=null)
                .flatMap(l->l.getTags().stream())
                .count();

    }

    /**
     * Returns the sum of piece counts in all LEGO sets.
     *
     * @return the sum of piece counts in all LEGO sets.
     */
    public int sumOfPiecesInLegoSets() {
        return getAll().stream()
                .mapToInt(LegoSet::getPieces)
                .reduce(new IntBinaryOperator() {
                    @Override
                    public int applyAsInt(int left, int right) {
                        return left+right;
                    }
                }).getAsInt();
    }

    /**
     * Returns LEGO set themes with its counts in a map;
     *
     * @return LEGO set themes with its counts in a map;
     */
    public Map<String,Long> LegoSetThemesWithItsCount() {
        return getAll().stream()
                .map(LegoSet::getTheme)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
    }

    /**
     * Returns the LEGO set names with the number of  its tags.
     *
     * @return the LEGO set names with the number of  its tags.
     */
    public Map<String,Integer> legoSetNamesWithNumberOfTags() {
        return getAll().stream()
                .filter(l->l.getTags()!=null)
                .collect(Collectors.toMap(LegoSet::getName,l->l.getTags().size(),(r1,r2)->r1));

    }


    public static void main(String[] args) {


        LegoSetRepository repository = new LegoSetRepository();
        if(repository.hasAnyLegoSetSubTheme()){
            System.out.println("There is at least one logoset with subtheme.");
        }
        else{
            System.out.println("There are not logosets with subtheme.");
        }
        System.out.println("Count of all tags of LEGO sets: " + repository.countAllTagsOfAllLegoSets());
        System.out.println("Sum of pieces in Lego sets: " + repository.sumOfPiecesInLegoSets());
        System.out.println("Lego set names with the number of tags:\n" + repository.legoSetNamesWithNumberOfTags());
        System.out.println("Lego set themes with its counts:\n" + repository.LegoSetThemesWithItsCount());



    }

}
