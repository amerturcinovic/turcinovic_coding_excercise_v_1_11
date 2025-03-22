package impl.persistance;

import impl.models.MatchInfo;

import java.util.List;

public interface Repository {
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

    default MatchDetailsEntity toMatchDetails(MatchInfo matchInfo) {
        return new MatchDetailsEntity(
                matchInfo.homeTeamName(),
                matchInfo.homeTeamScore(),
                matchInfo.guestTeamName(),
                matchInfo.guestTeamScore()
        );
    }
}
