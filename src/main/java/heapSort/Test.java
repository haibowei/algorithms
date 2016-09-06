package heapSort;

import com.google.common.base.Joiner;

import java.util.Iterator;

/**
 * Created: haibowei
 * Date: 16/9/6.
 */
public class Test {
    private static int[] sort = new int[]{1,0,10,20,3,5,6,4,9,8,12,17,34,11};
    
    public static void main(String[] args) {
        buildMaxHeapify(sort);
        heapSort(sort);
        print(sort);
    }
    
    private static void buildMaxHeapify(int[] data) {
        // 没有子节点的才需要创建最大堆，从最后一个的父节点开始
        int startIndex = (data.length >> 1) - 1;
        // 从尾端开始创建最大堆，每次都是正确的堆
        for(int i = startIndex ; i >= 0; i--){
            maxHeapify(data, data.length, i);
        }
    }
    
    /**
     *创建最大堆
     *
     *@param heapSize 需要创建最大堆的大小，一般在sort的时候用到，因为最多值放在末尾，末尾就不再归入最大堆了
     *@param index 当前需要创建最大堆的位置
     */
    private static void maxHeapify(int[] data, int heapSize, int index) {
        //当前点与左右子节点比较
        int left = (index << 1) + 1;
        int right = (index << 1 ) + 2;
        
        int largest = index;
        if(left < heapSize && data[index] < data[left]){
            largest = left;
        }
        if(right < heapSize && data[largest] < data[right]){
            largest = right;
        }
        
        // 得到最大值后可能需要交换，如果交换了，其子节点可能就不是最大堆了，需要重新调整
        if(largest != index){
            int temp = data[index];
            data[index] = data[largest];
            data[largest] = temp;
            maxHeapify(data, heapSize, largest);
        }
    }
    
    /**
     * 排序，最大值放在末尾，data虽然是最大堆，在排序后就成了递增的
     *
     */
    private static void heapSort(int[] data){
        //末尾与头交换，交换后调整最大堆
        for(int i = data.length-1; i>0; i--){
            int temp=data[0];
            data[0]=data[i];
            data[i]=temp;
            maxHeapify(data, i, 0);
        }
    }
    
    private static void print(int[] data) {
        Iterator<Integer> it = new Iterator<Integer>() {
            int cur = 0;
            @Override
            public boolean hasNext() {
                return cur < data.length;
            }
    
            @Override
            public Integer next() {
                return data[cur++];
            }
        };
        
        System.out.println(Joiner.on(',').join(it));
    }
}
