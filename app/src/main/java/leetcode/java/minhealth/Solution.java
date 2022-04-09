package leetcode.java.minhealth;

import java.util.Arrays;

public class Solution {
    public long minimumHealth(int[] damage, int armor) {
        // 1. Calculate the health spent and find the # of health defended by armor
        int sum =  0;
        int max = 0;
        for (int i = 0; i < damage.length; ++i) {
            sum += damage[i];
            max = Math.max(max, damage[i]);
        }

        int healthArmored = Math.min(max, armor);
        int result = sum - healthArmored + 1;
        return result;
    }


}
