package com.study.algorithm.hackerrank;

import java.util.*;
public class DequeTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deque deque = new ArrayDeque<>();
        int n = in.nextInt();
        int m = in.nextInt();

        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            deque.addLast(num);
        }

        final int maxUnique = m;
        int uniqueCount = 0;


        Deque subDeque = new LinkedList();
        Map duplicatedCountMap = new HashMap();
        int dupCount = 0;

        Set uniqueSet = new HashSet();

        for(int j=0; j<n; j++) {

            int temp = (int)deque.removeFirst();

            subDeque.addLast(temp);
            if (!uniqueSet.add(temp)) {
                int count = 0;
                if(duplicatedCountMap.containsKey(temp))
                    count = (int)duplicatedCountMap.get(temp);
                duplicatedCountMap.put(temp, count + 1);
                dupCount++ ;
            }

            if(subDeque.size()==m) {

                if (dupCount == 0) {
                    uniqueCount = maxUnique;
                    break;
                }

                int a = m-dupCount;
                if(a> uniqueCount)
                    uniqueCount = a;

                int temp2 = (int)subDeque.removeFirst();
                if(duplicatedCountMap.containsKey(temp2) ){
                    int count = (int)duplicatedCountMap.get(temp2);
                    if(count!=0) {

                        duplicatedCountMap.put(temp2, count - 1);
                        dupCount--;
                    } else{
                        uniqueSet.remove(temp2);
                    }
                }
                else {
                    uniqueSet.remove(temp2);

                }

            }
        }

        System.out.println(uniqueCount);
        return;

    }
}
