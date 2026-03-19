package ch.hslu.ad.sw04.hash_table_array;

import java.util.Arrays;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class HashSetArray<T> {
    private static final Logger LOG = LoggerFactory.getLogger(HashSetArray.class);
    private static final Object TOMBSTONE = new Object();

    private Object[] items;
    private final int capacity;
    private int size = 0;

    public HashSetArray(final int capacity) {
        this.capacity = capacity;
        this.items = new Object[capacity];
    }

    private int getIndex(final T item) {
        return Math.abs(item.hashCode() % this.capacity);
    }

    private int nextRightIndex(final int currenIndex) {
        int nextIndex;
        if (currenIndex + 1 >= this.capacity) {
            nextIndex = 0;
        } else {
            nextIndex = currenIndex + 1;
        }

        return nextIndex;
    }

    private int nextLeftIndex(final int currenIndex) {
        int nextIndex;
        if (currenIndex - 1 < 0) {
            nextIndex = this.capacity - 1;
        } else {
            nextIndex = currenIndex - 1;
        }

        return nextIndex;
    }

    public boolean add(final T item) {
        if (this.size >= this.capacity) {
            return false;
        }

        final int index = this.getIndex(item);

        if (Objects.equals(item, this.items[index])) {
            return false;
        }

        LOG.info("index of {}: {}", item, index);

        if (this.items[index] == null || this.items[index] == TOMBSTONE) {
            this.items[index] = item;
            this.size++;
            return true;
        }

        int nextIndex = this.nextRightIndex(index);
        for (int i = 0; i < this.capacity - 1; i++) {
            if (Objects.equals(item, this.items[nextIndex])) {
                return false;
            }

            if (this.items[nextIndex] == null || this.items[nextIndex] == TOMBSTONE) {
                LOG.info("nextIndex: {}", nextIndex);
                this.items[nextIndex] = item;
                this.size++;
                return true;
            }

            nextIndex = this.nextRightIndex(nextIndex + i);
        }

        return false;
    }

    public boolean contains(final T item) {
        if (this.size == 0) {
            return false;
        }

        final int index = this.getIndex(item);

        if (this.items[index] == null) {
            return false;
        }

        if (Objects.equals(item, this.items[index])) {
            return true;
        }

        int nextIndex = this.nextRightIndex(index);
        for (int i = 0; i < this.capacity - 1; i++) {

            if (this.items[nextIndex] == null) {
                return false;
            }

            if (Objects.equals(item, this.items[nextIndex])) {
                LOG.info("nextIndex: {}", nextIndex);
                return true;
            }

            nextIndex = this.nextRightIndex(nextIndex + i);
        }

        return false;
    }

    private boolean setNullOrTombstone(final int index) {
        if (this.items[this.nextRightIndex(index)] == null) {
            this.items[index] = null;
            int nextIndex = this.nextLeftIndex(index);

            for (int i = 0; i < this.capacity - 1; i++) {
                if (this.items[nextIndex] == TOMBSTONE) {
                    this.items[nextIndex] = null;
                    continue;
                }
                if (this.items[nextIndex] != null) {
                    break;
                }
            }

        } else {
            this.items[index] = TOMBSTONE;
        }

        this.size--;
        return true;
    }

    public boolean remove(final T item) {
        if (this.size == 0) {
            return false;
        }

        final int index = this.getIndex(item);

        if (this.items[index] == null) {
            return false;
        }

        if (Objects.equals(item, this.items[index])) {
            return this.setNullOrTombstone(index);

        } else {
            int nextIndex = this.nextRightIndex(index);
            for (int i = 0; i < this.capacity - 1; i++) {
                if (this.items[nextIndex] == null) {
                    return false;
                }

                if (Objects.equals(item, this.items[nextIndex])) {
                    LOG.info("nextIndex: {}", nextIndex);

                    return this.setNullOrTombstone(nextIndex);
                }

                nextIndex = this.nextRightIndex(nextIndex + i);
            }
        }

        return false;
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        return "HashTableArray{"
                + "items="
                + Arrays.toString(items)
                + ", capacity="
                + capacity
                + ", size="
                + size
                + '}';
    }
}
