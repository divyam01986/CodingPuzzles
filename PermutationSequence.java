/**
 * The set [1, 2, 3, ..., n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.
 **/
class Solution {
    public String getPermutation(int n, int k) {
        int[] values = new int[n];
        for (int i = 1; i <= n; i++) {
            values[i - 1] = i;
        }
        
        permute(0, values.length - 1, values, k);
        StringBuilder bldr = new StringBuilder();
        for (int val: values) {
            bldr.append(val);
        }
        return bldr.toString();
    }
    
    private void permute(int start, int end, int[] arr, int k) {
        if (k == 1) {
            return;
        }
        
        int index = end;
        int countPerm = 1;
        while (index >= start) {
            countPerm = countPerm + (end - index) * countPerm;
            if (countPerm >= k) {
                break;
            }
            index --;
        }
        
        if (index < start) {
            return;
        }
        
        countPerm /= (end - index + 1);
        
        for (int i = index; i <= end; i++) {
            if ((k - countPerm) < 1) {
                reverse(index, i, arr);
                reverse(index + 1, i, arr);
                break;
            }
            
            k -= countPerm;
        }
        
        permute(index + 1, end, arr, k);   
    }
    
    private void reverse(int start, int end, int[] arr) {
        while (start <= end) {
            int tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            start++;
            end--;
        } 
    }
}