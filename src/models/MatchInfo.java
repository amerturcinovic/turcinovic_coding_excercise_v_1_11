package models;

public record MatchInfo(
        String homeTeamName,
        Integer homeTeamScore,
        String guestTeamName,
        Integer guestTeamScore
) {
}
