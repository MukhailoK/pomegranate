package model;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Pomegranate implements Iterable<Seed> {
    List<Seed> seeds;

    public Pomegranate(List<Seed> seeds) {
        this.seeds = seeds;
    }

    public Pomegranate() {
    }

    @Override
    public String toString() {
        return "Pomegranate{" +
                "seeds=" + seeds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pomegranate that = (Pomegranate) o;
        return Objects.equals(seeds, that.seeds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seeds);
    }

    public List<Seed> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<Seed> seeds) {
        this.seeds = seeds;
    }

    @Override
    public Iterator<Seed> iterator() {
        return new Iterator<Seed>() {
            private int count;

            @Override
            public boolean hasNext() {
                return count < seeds.size();
            }

            @Override
            public Seed next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else
                    return seeds.get(count++);
            }
        };
    }
}
