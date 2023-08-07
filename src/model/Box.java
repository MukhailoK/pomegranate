package model;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Box implements Iterable<Pomegranate> {
    List<Pomegranate> granates;
    private String name;

    public Box(String name, List<Pomegranate> granates) {
        this.name = name;
        this.granates = granates;
    }

    public Box() {
    }

    @Override
    public String toString() {
        return "Box{" +
                "name='" + name + '\'' +
                ", granates=" + granates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return Objects.equals(name, box.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pomegranate> getGranates() {
        return granates;
    }

    public void setGranates(List<Pomegranate> granates) {
        this.granates = granates;
    }

    @Override
    public Iterator<Pomegranate> iterator() {
        return new Iterator<>() {
            private int count;

            @Override
            public boolean hasNext() {
                return count < granates.size();
            }

            @Override
            public Pomegranate next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else

                    return granates.get(count++);
            }
        };
    }
}
