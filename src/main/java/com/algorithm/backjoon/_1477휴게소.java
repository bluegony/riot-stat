package com.algorithm.backjoon;

import java.util.*;

/**
 * Created on 22/10/2019.
 */
public class _1477휴게소 {
    private int N, M, L;
    private int [] arr;
    
    public static void main(String[] args) {
        
        _1477휴게소 instance = new _1477휴게소();
        instance.getIput();
        instance.solve2();

        instance.solve();
    }


    private void getIput() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        L = sc.nextInt();

        arr = new int[N+2];
        arr[0] = 0; // why?
        for(int i = 1; i < N+1; i++) {
            arr[i] = sc.nextInt();
        }
        arr[N+1] = L;//why?

        Arrays.sort(arr);
    }

    private void solve2() {


        int left =  0;
        int right = L;
        int answer = L;
        while(right >= left) {
            int mid = (left+right)/2;
            int cnt = 0;
            for(int i = 1; i < N+2; i++) {
                if(mid == 0) break;
                int count = (arr[i]-arr[i-1])/mid;
                int remainder = (arr[i]-arr[i-1])%mid;
                if (remainder==0 && count>0)
                    count --;
                cnt += count;
//                cnt += (arr[i]-arr[i-1]-1)/mid; // why -1 ?
//                System.out.println(String.format("i %d cnt %d",i, cnt));
            }
//            System.out.println(String.format("left %d right %d mid %d cnt %d",left, right, mid, cnt));
            if(cnt<=M && mid<answer) {
                answer = mid;
//                System.out.println(String.format("answer %d",answer));
            }
            if(cnt >  M) {
                left = mid+1 ;
            }
            else if (cnt<=M) {
                right = mid-1;
            }
        }
        System.out.println(answer);
    }

    private void solve() {
        int left =  1;
        int right = L-1;

        while(right >= left) {
            int mid = (left+right)/2;
            int cnt = 0;
            for(int i = 1; i < N+2; i++) {
                if(mid == 0) break;
                cnt += (arr[i]-arr[i-1]-1)/mid; // why -1 ?
            }

            if(cnt > M) left = mid+1;
            else right = mid-1;
        }

        System.out.println(left);
    }
}
