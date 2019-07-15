package com.CK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int[] a = new int[]{2,1,4,3,6,7,4,8};
        Solution2 solution = new Solution2();
        System.out.println(solution.pancakeSort(a).toString());
    }
}

class Solution {
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> aList = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            aList.add(A[i]);
        }
        flip(aList, 1);
        int l, maxIndex, maxVal;
        for (l = A.length - 1; l >= 0; l--) {
            maxVal = maxInSubArray(aList, l+1);
            if (aList.get(l) != maxVal) {
                for (maxIndex = l; maxIndex >= 0; maxIndex--) {
                    if (aList.get(maxIndex) == maxVal) {
                        if (maxIndex != 0) {
                            flip(aList, maxIndex + 1);
                            res.add(maxIndex + 1);
                        }
                        flip(aList, l + 1);
                        res.add(l + 1);
                        break;
                    }
                }
            }
        }
        return res;
    }

    private void flip(List<Integer> arrList, int k) {
        int left = 0, right = k - 1, temp;
        while (left < right) {
            temp = arrList.get(right);
            arrList.set(right, arrList.get(left));
            arrList.set(left, temp);
            left++;
            right--;
        }
    }

    private int maxInSubArray(List<Integer> arrList, int l) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < l; i++) {
            if (arrList.get(i) >= max) max = arrList.get(i);
        }
        return max;
    }
}


// Sort from largest to smallest
class Solution2 {
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> ans = new ArrayList<>();
        int N = A.length;

        Integer[] B = new Integer[N];
        for (int i = 0; i < N; ++i)
            B[i] = i+1;
        Arrays.sort(B, (i, j) -> A[j-1] - A[i-1]);

        for (int i: B) {
            for (int f: ans)
                if (i <= f)
                    i = f+1 - i;
            ans.add(i);
            ans.add(N--);
        }
        return ans;
    }
}

//
class Solution3 {
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> res = new ArrayList<>();
        for (int x = A.length, i; x > 0; --x) {
            for (i = 0; A[i] != x; ++i);
            reverse(A, i + 1);
            res.add(i + 1);
            reverse(A, x);
            res.add(x);
        }
        return res;
    }

    public void reverse(int[] A, int k) {
        for (int i = 0, j = k - 1; i < j; i++, j--) {
            int tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
        }
    }
}