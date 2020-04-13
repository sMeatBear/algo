package app.basic;

class MyPriorityQueue {
    private int capacity = 11;
    private int size;
    private int[] arr;

    public MyPriorityQueue() {
        arr = new int[capacity];
    }

    public MyPriorityQueue(int[] initial) {
        // increase the capacity
        if (initial == null) {
            arr = new int[capacity];
        } else {
            size = initial.length;
            while (capacity < size) {
                capacity *= 1.5;
            }
            arr = new int[capacity];
            for (int i = 0; i < initial.length; i++) {
                arr[i] = initial[i];
            }

            heapify();
        }
    }

    /**
     * Add an element to the queue from behind (heap)
     * @param num
     * @return
     */
    public void offer(int num) {
        if (size == capacity) {
            // increase the capacity by 1.5 times
            capacity *= 1.5;
            int[] newArr = new int[capacity];
            for (int i = 0; i < size; i++) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }

        // ** percolate up **
        // from the last element to upper layers
        arr[size] = num;
        int parentIdx = (size - 1) / 2;
        for (int i = size; i > 0 && arr[i] < arr[parentIdx];) {
            // swap i and parentIdx
            int tmp = arr[i];
            arr[i] = arr[parentIdx];
            arr[parentIdx] = tmp;

            // update i which is new added element's index and parent idx
            i = parentIdx;
            parentIdx = (i - 1) / 2;
        }

        // update size
        size++;
    }

    /**
     * Return the min value
     * @return null or int value
     */
    public Integer poll() {
        // corner case
        if (size == 0) {
            return null;
        }

        int polledElem = arr[0];

        // ** percolate down **
        // 1. swap the last element to the first one
        arr[0] = arr[size - 1];
        // 2. percolateDown
        percolateDown(0);

        size--;
        return polledElem;
    }

    /**
     * return the head of the heap
     * @return arr[0]
     */
    public Integer peek() {
        if (size == 0) {
            return null;
        }

        return arr[0];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * build the heap by given int array
     */
    private void heapify() {
        // build the heap
        // 1. find the first non-leaf node from behind
        // 2. do the percolateDown from bottom to up, from right to left
        // first non-left node index
        int i = (size - 2) / 2;

        for (; i >= 0; i--) {
            percolateDown(i);
        }
    }

    /**
     * percolate down to adjust the heap
     * @param i the index that need to adjust
     */
    private void percolateDown(int i) {
        //  ** percolate down **
        // 2. swap with the smaller child
        int left = i * 2 + 1, right = i * 2 + 2;
        // if left >= size then it doesn't have child node
        while (left < size) {
            // find the smallest element idx amony three nodes (parent, left child, right child)
            int minIdx = i;
            if (arr[left] < arr[minIdx]) {
                minIdx = left;
            }
            
            if (right < size && arr[right] < arr[minIdx]) {
                minIdx = right;
            }

            // no swap needed
            if (minIdx == i) {
                break;
            } else {
                // swap the smaller one to upper layer
                int tmp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = tmp;
            }

            // update value
            i = minIdx;
            left = i * 2 + 1;
            right = left + 1;
        }
    }
}

public class MyPriorityQueueImpl {
    public static void main(String[] args) {
        int[] initial = new int[] {10, 11, 7, 2, 8, 4, 6, 13, 3};
        MyPriorityQueue mpq = new MyPriorityQueue(initial), mpqNull = new MyPriorityQueue();
        int test = mpq.peek();
        System.out.println(test);

        for (int e : initial) {
            mpqNull.offer(e);
        }
        System.out.println("=============offer() and poll()=============");
        while (!mpq.isEmpty()) {
            System.out.println(mpq.poll());
        }
    }
}