package Some_Other_Algorithms;

import java.util.ArrayList;
import java.util.Arrays;

public class ThreeSum {
    /**
     * @param nums
     *            : Give an array numbers of n integer
     * @return : Find all unique triplets in the array which gives the sum of zero.
     */
    public ArrayList<ArrayList<Integer>> threeSum(int[] nums) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();

        if (nums == null || nums.length < 3) {
            return results;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {     // -------------> complexity O(n)
            // skip duplicate triples with the same first number
            if (i > 0 && nums[i] == nums[i - 1]) {    // avoid duplicate numbers
                continue;
            }

            int left = i + 1, right = nums.length - 1;
            int target = -nums[i];

            twoSum(nums, left, right, target, results);
        }

        return results;
    }

    public void twoSum(int[] nums, int left, int right, int target, ArrayList<ArrayList<Integer>> results) {
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                ArrayList<Integer> triple = new ArrayList<>();
                triple.add(-target);
                triple.add(nums[left]);
                triple.add(nums[right]);
                results.add(triple);

                left++;    // there won't be anymore fitted pairs, so just move both pointer.
                right--;
                // skip duplicate pairs with the same left
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }
                // skip duplicate pairs with the same right
                while (left < right && nums[right] == nums[right + 1]) {
                    right--;
                }
            }
            else if (nums[left] + nums[right] < target) {
                left++;
            }
            else {
                right--;
            }
        }
    }
}