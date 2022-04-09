package leetcode.java.lfu;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.LruCache;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class LFUCacheTest {

    @Test
    void testPriorityQueue() {
        PriorityQueue<Integer> pq  = new PriorityQueue<>();

        pq.add(3);
        pq.add(2);
        pq.add(4);
        pq.add(-1);

        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.poll());
    }

    @Test
    void testCache() {
        LFUCache cache = new LFUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        assertEquals(1, cache.get(1));
        cache.put(3,3);
        assertEquals(-1, cache.get(2));
        assertEquals(3, cache.get(3));
        cache.put(4,4);
        assertEquals(-1, cache.get(1));
        assertEquals(3, cache.get(3));
        assertEquals(4, cache.get(4));
    }

    @Test
    void testCache2() {
        LFUCache cache = new LFUCache(2);
        cache.put(3, 1);
        cache.put(2, 1);
        cache.put(2, 2);
        cache.put(4, 4);
        assertEquals(2, cache.get(2));
    }

    @Test
    void testEmptyCache() {
        LFUCache cache = new LFUCache(0);
        assertEquals(-1, cache.get(0));
        cache.put(0,0);
        assertEquals(-1, cache.get(0));
    }

}