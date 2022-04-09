package leetcode.java.lfu;

import org.checkerframework.checker.units.qual.C;

import java.util.*;
import java.util.function.BiConsumer;

/** Maintain map of cache entries as well as frequencies */
//

public class LFUCache {

    public class CacheEntry  {
        private final BiConsumer<CacheEntry, Integer> onAccess;
        CacheEntry prev;
        CacheEntry next;

        private int accessCounter = 0;
        private final int key;
        private int value;

        CacheEntry() {
            this.onAccess = null;
            this.key = -1;
        }

        @Override
        public String toString() {
            if (key == -1) {
                return "[CacheEntry] list tail";
            } else {
                return String.format(
                        "[CacheEntry] key='%s'; value='%s'; accessCounter='%s'", key,value,accessCounter);
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof CacheEntry) {
                return ((CacheEntry) obj).key == key;
            }
            return false;
        }

        CacheEntry(int key, int value, BiConsumer<CacheEntry, Integer> onAccess) {
            this.onAccess = onAccess;
            this.key = key;
            set(value);

        }

        public void set(int value) {
            this.value = value;
            updateAccessCounter();
        }

        private void updateAccessCounter() {
            accessCounter++;
            onAccess.accept(this, accessCounter);
        }

        public int get() {
            updateAccessCounter();
            return value;
        }

    }

    public class EntryList {
        CacheEntry head;
        private final Runnable callWhenShouldRemove;
        CacheEntry tail;

        EntryList(CacheEntry first, Runnable callWhenShouldRemoveMe) {
            this.callWhenShouldRemove = callWhenShouldRemoveMe;
            head = first;
            tail = new CacheEntry();
            tail.prev = head;
            head.next = tail;
        }

        public void remove(CacheEntry cacheEntry) {
            if (cacheEntry.equals(head)) {
                if ((head.next.equals(tail))) {
                    callWhenShouldRemove.run();
                    System.out.printf("Removing last value: %s\n", head);
                    return;
                }
                head = cacheEntry.next;
            } else {
                cacheEntry.prev.next = cacheEntry.next;
            }
            cacheEntry.next.prev = cacheEntry.prev;
        }

        public CacheEntry removeLast() {
            CacheEntry last = tail.prev;
            if (head.equals(last)) {
                callWhenShouldRemove.run();
                return last;
            }
            tail.prev = last.prev;
            tail.prev.next = tail;
            return last;
        }

        public void add(CacheEntry cacheEntry) {
            head.prev = cacheEntry;
            cacheEntry.next = head;
            cacheEntry.prev = null;
            head = cacheEntry;
        }
    }

    HashMap<Integer, EntryList> accessCountToEntries = new HashMap<>();
    HashMap<Integer,CacheEntry> cache = new HashMap<>();
    int minAccessCount;
    int capacity;
    int operationCounter;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.operationCounter = 0;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            return cache.get(key).get();
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (cache.containsKey(key)) {
            cache.get(key).set(value);
        } else {
            tryEvict();
            cache.put(key, new CacheEntry(key, value, this::handleCounterChange));
        }
    }

    private void tryEvict() {
        if (cache.size() == capacity) {
            CacheEntry last = accessCountToEntries.get(minAccessCount).removeLast();
            cache.remove(last.key);
        }
    }

    private void handleCounterChange(CacheEntry cacheEntry, Integer newAccessCount) {
        System.out.printf("%s: changing counter: %s -> %s\n", cacheEntry, newAccessCount - 1, newAccessCount);

        // Remove this entry from old access count
        if (newAccessCount == 1) {
            minAccessCount = 1;
        } else {
            var entries = accessCountToEntries.get(newAccessCount - 1);
            if (entries == null) {
                return;
            }
            entries.remove(cacheEntry);
        }

        // add this entry to new access count
        if (accessCountToEntries.containsKey(newAccessCount)) {
            accessCountToEntries.get(newAccessCount).add(cacheEntry);
        } else {
            accessCountToEntries.put(newAccessCount, new EntryList(cacheEntry,
                    () -> {
                        accessCountToEntries.remove(newAccessCount);
                        if (newAccessCount == minAccessCount) {
                            minAccessCount++;
                        }
                    }));
        }
    }
}
