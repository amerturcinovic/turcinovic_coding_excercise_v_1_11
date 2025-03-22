package impl.persistance;

import java.util.List;

/***
 * Sortable interface that need implementation to get List of sorted entities of matches
 * Implement you custom sort for your Repository
 */
public interface Sortable {
    List<MatchDetailsEntity> getSorted();
}
