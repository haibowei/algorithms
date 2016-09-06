package queue;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;

/**
 * Created: haibowei
 * Date: 16/9/6.
 */
public class PriorityQueue<T> {
    private List<T> queue;
    private int size;
    private Comparator<T> cmp;
    
    public PriorityQueue(Comparator<T> cmp) {
        this.cmp = cmp;
        queue = Lists.newArrayList();
        size = 0;
    }
    
    private void swap(int idx1, int idx2) {
        T tp = queue.get(idx1);
        queue.set(idx1, queue.get(idx2));
        queue.set(idx2, tp);
    }
    
    private void up(int idx) {
        if (idx == 0) {
            return;
        }
        int father = (idx - 1) >> 1;
        
        if (cmp.compare(queue.get(father), queue.get(idx)) > 0) {
            swap(father, idx);
            up(father);
        }
    }
    
    private void down(int idx) {
        int left = (idx << 1) + 1;
        int right = (idx << 1) + 2;
        
        int maxIdx = idx;
        if (left < size && cmp.compare(queue.get(maxIdx), queue.get(left)) > 0) {
            maxIdx = left;
        }
        
        if (right < size && cmp.compare(queue.get(maxIdx), queue.get(right)) > 0) {
            maxIdx = right;
        }
        
        if (maxIdx != idx) {
            swap(maxIdx, idx);
            down(maxIdx);
        }
    }
    
    public T pop() {
        T ret = queue.get(0);
        queue.set(0, queue.get(--size));
        down(0);
        return ret;
    }
    
    public void add(T data) {
        queue.add(size++, data);
        up(size - 1);
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o1 - o2);
        
        queue.add(1);
        queue.add(3);
        queue.add(2);
        queue.add(4);
        queue.add(5);
        queue.add(7);
        queue.add(100);
        queue.add(22);
        queue.add(0);
        queue.add(-1);
        queue.add(99);
        while (!queue.isEmpty()) {
            System.out.println(queue.pop());
        }
    }
}
