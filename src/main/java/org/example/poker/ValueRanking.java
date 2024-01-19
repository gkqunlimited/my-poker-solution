package org.example.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record ValueRanking(Ranking rank, Value primary, Value secondary,
                           List<Value> kicker) implements Comparable<ValueRanking> {
    public ValueRanking(Ranking rank, Value primary, Value secondary, List<Value> kicker) {
        this.rank = rank;
        this.primary = primary == null ? Value.NULL : primary;
        this.secondary = secondary == null ? Value.NULL : secondary;
        List<Value> kickerTemp = new ArrayList<>(kicker);
        try {
            Collections.sort(kickerTemp);
        } catch (NullPointerException e) {
            System.out.println(kicker);
            throw e;
        }
        Collections.reverse(kickerTemp);
        this.kicker = Collections.unmodifiableList(kickerTemp);
    }

    @Override
    public int compareTo(ValueRanking o) {
        if (rank.compareTo(o.rank) != 0) return rank.compareTo(o.rank);
        else if (primary.compareTo(o.primary) != 0) return primary.compareTo(o.primary);
        else if (secondary.compareTo(o.secondary) != 0) return secondary.compareTo(o.secondary);
        else {
            for (int i = 0; i < kicker.size(); i++) {
                if (kicker.get(i).compareTo(o.kicker.get(i)) != 0) return kicker.get(i).compareTo(o.kicker.get(i));
            }
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format(
                "[%s,%s,%s,%s]",
                rank, primary, secondary, kicker);
    }
}
