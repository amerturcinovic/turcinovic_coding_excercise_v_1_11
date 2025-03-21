package models;

public record MatchResultChangeRequest(
        String homeTeamName,
        Integer homeTeamScore,
        String guestTeamName,
        Integer guestTeamScore
) {
}
