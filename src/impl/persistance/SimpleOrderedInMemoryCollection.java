package impl.persistance;

import impl.models.MatchInfo;

import java.util.*;

/***
 * Implementation of Repository as simple in memory ordered hash map
 * This implementation is not thread safe, but we could wrap this as synchronized collection
 * or has another concurrent thread safe implementation like ConcurrentHashMap
 */
public class SimpleOrderedInMemoryCollection implements Repository, Sortable {
    private final Map<Integer, MatchDetailsEntity> storage = new LinkedHashMap<>();

    @Override
    public MatchInfo save(MatchInfo matchInfo) {
        var mathId = getStorageHashId(matchInfo);
        storage.putIfAbsent(mathId, toMatchDetailsEntity(matchInfo));
        return toMatchInfo(storage.get(mathId));
    }

    @Override
    public MatchInfo delete(MatchInfo matchInfo) {
        var matchId = getStorageHashId(matchInfo);
        var matchDetailsEntity = storage.remove(matchId);
        return toMatchInfo(matchDetailsEntity);
    }

    @Override
    public MatchInfo update(MatchInfo matchInfo) {
        var matchId = getStorageHashId(matchInfo);
        storage.computeIfPresent(matchId, (_, matchDetails) -> {
            if (isScoreEqualOrGreater(matchInfo, matchDetails))
                return matchDetails;

            return toMatchDetailsEntity(matchInfo);
        });
        return toMatchInfo(storage.get(matchId));
    }

    private static boolean isScoreEqualOrGreater(MatchInfo matchInfo, MatchDetailsEntity matchDetails) {
        return matchDetails.homeTeamScore() > matchInfo.homeTeamScore() || matchDetails.guestTeamScore() > matchInfo.guestTeamScore();
    }

    @Override
    public List<MatchInfo> getAll() {
        return getSorted()
                .stream()
                .map(this::toMatchInfo)
                .toList();
    }

    @Override
    public String toString() {
        List<MatchDetailsEntity> sorted = getSorted();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < sorted.size(); i++) {
            stringBuilder.append((i + 1)).append(". ").append(sorted.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public List<MatchDetailsEntity> getSorted() {
        return new ArrayList<>(storage.values()).reversed().stream().sorted().toList();
    }

    private Integer getStorageHashId(MatchInfo matchInfo) {
        return toMatchDetailsEntity(matchInfo).hashCode();
    }
}
