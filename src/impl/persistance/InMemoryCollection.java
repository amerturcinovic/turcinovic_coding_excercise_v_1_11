package impl.persistance;

import impl.models.MatchInfo;

import java.util.*;

public class InMemoryCollection implements Repository, Sortable {
    private final Map<Integer, MatchDetailsEntity> storage = new LinkedHashMap<>();

    @Override
    public MatchInfo save(MatchInfo matchInfo) {
        var mathId = getStorageHashId(matchInfo);
        storage.putIfAbsent(mathId, toMatchDetails(matchInfo));
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
        storage.computeIfPresent(matchId, (_, _) -> toMatchDetails(matchInfo));
        return matchInfo;
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
        return toMatchDetails(matchInfo).hashCode();
    }
}
