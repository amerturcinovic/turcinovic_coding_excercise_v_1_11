package impl.persistance;

import impl.models.MatchInfo;

import java.util.List;

/***
 * Repository interface that has basic CRUD functions
 */
public interface Repository {
    MatchInfo findByNames(String homeTeamName, String guestTeamName);
    MatchInfo save(MatchInfo matchInfo);
    MatchInfo delete(MatchInfo matchInfo);
    MatchInfo update(MatchInfo matchInfo);
    List<MatchInfo> getAll();

    default MatchInfo toMatchInfo(MatchDetailsEntity matchDetails) {
        return new MatchInfo(
                matchDetails.homeTeamName(),
                matchDetails.homeTeamScore(),
                matchDetails.guestTeamName(),
                matchDetails.guestTeamScore()
        );
    }

    default MatchDetailsEntity toMatchDetailsEntity(MatchInfo matchInfo) {
        return new MatchDetailsEntity(
                matchInfo.homeTeamName(),
                matchInfo.homeTeamScore(),
                matchInfo.guestTeamName(),
                matchInfo.guestTeamScore()
        );
    }
}
