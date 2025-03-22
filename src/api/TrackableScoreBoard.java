package api;

import impl.models.MatchInfo;

import java.util.List;

/**
 * This interface represents a API for tracking score board of matches in sport.
 * It provides methods for common CRUD operations like starting / inserting match on score board, remove and update match.
 * Also, you can get ranking that will return all matches in progress sorted by most scores and most recent match.
 */
public interface TrackableScoreBoard {
    /**
     * <p>This method start new match and add it to score board. This method is idempotent</p>
     *
     * @param homeTeamName  - non-nullable and not empty name of home team as String
     * @param guestTeamName - non-nullable and not empty name of guest team as String
     * @return MatchInfo - return the match information with score for home team and guest team
     * @exception IllegalArgumentException - thrown if you pass wrong argument (empty or null)
     */
    MatchInfo startMatch(String homeTeamName, String guestTeamName) throws IllegalArgumentException;

    /**
     * <p>This method start new match and add it to score board. This method is idempotent</p>
     *
     * @param homeTeamName  - name of home team as String
     * @param guestTeamName - name of guest team as String
     * @return MatchInfo - return the match information with score for home team and guest team
     * @exception IllegalArgumentException - thrown if you pass wrong argument (empty or null)
     */
    MatchInfo finishMatch(String homeTeamName, String guestTeamName) throws IllegalArgumentException;

    MatchInfo updateMatch(MatchInfo matchInfo) throws IllegalArgumentException;

    List<MatchInfo> geBoardRanking();
}
