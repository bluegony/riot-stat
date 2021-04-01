package com.study.algorithm.backjoon;

import java.util.Scanner;

/**
 * Created on 31/10/2019.
 */
public class _2343기타레슨 {

    private int N, M;
    private int [] arr;

    private int totalLength = 0;
    private int minLength = 0;


    public static void main(String[] args) {

        _2343기타레슨 instance = new _2343기타레슨();
        instance.getIput();
        instance.solve();
    }


    private void getIput() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
            totalLength +=arr[i];
            minLength = arr[i]>minLength ? arr[i] : minLength;
        }

    }

    private void solve() {

        int left =  minLength;
        int right = totalLength;
        int answer = totalLength;

        while(right >= left) {
            int mid = (left+right)/2;

            int cnt = 1;
            int tempLength = 0;
            for(int i=0; i<N; i++) {
                if(tempLength+arr[i] > mid)  {
                    cnt++;
                    tempLength = arr[i];
                } else {
                    tempLength+=arr[i];
                }
            }
            if(cnt<=M && mid<answer) {
                answer = mid;
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

}
