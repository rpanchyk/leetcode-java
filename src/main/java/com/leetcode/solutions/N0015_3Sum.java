package com.leetcode.solutions;

import java.util.*;
import java.util.stream.Stream;

import static com.leetcode.utils.AssertUtils.assertThat;

public class N0015_3Sum {

    public static void main(String[] args) {
        assertThat(new N0015_3Sum().threeSum(new int[]{-1, 0, 1, 2, -1, -4}), List.of(
            List.of(-1, -1, 2),
            List.of(-1, 0, 1)
        ));
        assertThat(new N0015_3Sum().threeSum(new int[]{0, 0, 0, 0}), List.of(
            List.of(0, 0, 0)
        ));
        assertThat(new N0015_3Sum().threeSum(new int[]{-1, 0, 1, 0}), List.of(
            List.of(-1, 0, 1)
        ));
        assertThat(new N0015_3Sum().threeSum(new int[]{3, 0, -2, -1, 1, 2}), List.of(
            List.of(-2, -1, 3),
            List.of(-2, 0, 2),
            List.of(-1, 0, 1)
        ));
        assertThat(new N0015_3Sum().threeSum(new int[]{1, 2, -2, -1}), List.of(
        ));
        assertThat(new N0015_3Sum().threeSum(new int[]{-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0}), List.of(
            List.of(-2, -2, 4),
            List.of(-5, 1, 4),
            List.of(-4, 1, 3),
            List.of(-2, 1, 1),
            List.of(-4, 0, 4),
            List.of(0, 0, 0)
        ));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        if (nums[0] == nums[nums.length - 1]) {
            nums = Arrays.copyOfRange(nums, 0, 3);
        }

//        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    List<Integer> list = Stream.of(nums[i], nums[left], nums[right]).sorted().toList();
//                    if (!result.contains(list)) {
                    result.add(list);
//                    }
                    right--;
                    left++;
                } else {
                    if (sum > 0) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return result.stream().toList();
    }

    public List<List<Integer>> threeSum5(int[] nums) {
        Arrays.sort(nums);

        int zero = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0 && (i == nums.length - 1 || nums[i + 1] > 0)) {
                zero = i;
                break;
            }
        }
//        int zero = getPivot(nums, 0, nums.length - 1);

        List<List<Integer>> result = new ArrayList<>();
        if (zero != -1) {
            if (zero + 1 > nums.length / 2) { // go right
                while (zero < nums.length) {
                    for (int i = zero - 1; i > 0; i--) {
                        for (int j = i - 1; j >= 0; j--) {
                            int sum = nums[i] + nums[j];
                            if (-sum == nums[zero]) {
                                List<Integer> list = List.of(nums[j], nums[i], nums[zero]);
                                if (!result.contains(list)) {
                                    result.add(list);
                                }
                            }
                            if (-sum >= nums[zero]) {
                                break;
                            }
                        }
                    }
                    zero++;
                }
            } else { // go left
                while (zero >= 0) {
                    for (int i = zero + 1; i < nums.length; i++) {
                        for (int j = i + 1; j < nums.length; j++) {
                            int sum = nums[i] + nums[j];
                            if (sum == -nums[zero]) {
                                List<Integer> list = List.of(nums[zero], nums[i], nums[j]);
                                if (!result.contains(list)) {
                                    result.add(list);
                                }
                            }
                            if (sum >= -nums[zero]) {
                                break;
                            }
                        }
                    }
                    zero--;
                }
            }
        }

        return result;
    }

    private int getPivot(int[] nums, int start, int end) {
        if (end - start <= 1) {
            return end;
        }
        int half = (end + start) / 2;
        if (nums[half] > 0) {
            return getPivot(nums, start, half);
        } else {
            return getPivot(nums, half, end);
        }
    }

    public List<List<Integer>> threeSum4(int[] nums) {
        Arrays.sort(nums);

        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (Math.abs(nums[i] + nums[j]) > nums[nums.length - 1]) {
                    break;
                }

                for (int k = nums.length - 1; k > j; k--) {

                    if (Math.abs(nums[i] + nums[j]) > nums[k]) {
                        break;
                    }

                    if (nums[i] + nums[j] + nums[k] == 0) {
                        result.add(List.of(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        return result.stream().toList();
    }

    public List<List<Integer>> threeSum3(int[] nums) {
//        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> result = new HashSet<>();

        Map<Integer, Integer> map = new TreeMap<>();
        for (int key : nums) {
            if (map.containsKey(key)) {
                map.computeIfPresent(key, (k, v) -> v + 1);
            } else {
                map.put(key, 1);
            }
        }

        Iterator<Map.Entry<Integer, Integer>> iterator1 = map.entrySet().iterator();
        while (iterator1.hasNext()) {
            Map.Entry<Integer, Integer> next1 = iterator1.next();
            Integer key1 = next1.getKey();
            Integer value1 = next1.getValue();

            if (key1 == 0 && value1 >= 3) {
                result.add(List.of(0, 0, 0));
                continue;
            }

            if (value1 == 2) {
                int key3 = key1 + key1;
                if (key1 != -key3 && map.containsKey(-key3)) {
//                    result.add(List.of(-key3, key1, key1));
                    result.add(Stream.of(-key3, key1, key1).sorted().toList());
                }
                continue;
            }

            Iterator<Map.Entry<Integer, Integer>> iterator2 = map.entrySet().iterator();
            while (iterator2.hasNext()) {
                Map.Entry<Integer, Integer> next2 = iterator2.next();
                Integer key2 = next2.getKey();
                Integer value2 = next2.getValue();

                if (key2 <= key1) {
                    continue;
                }

                if (value2 == 1) {
                    int key3 = key1 + key2;
                    if (key2 != -key3 && map.containsKey(-key3)) {
//                        result.add(List.of(-key3, key1, key2));
                        result.add(Stream.of(-key3, key1, key2).sorted().toList());
                    }
                } else if (value2 == 2) {
                    int key3 = key2 + key2;
                    if (key1 != -key3 && map.containsKey(-key3)) {
//                        result.add(List.of(-key3, key2, key2));
                        result.add(Stream.of(-key3, key2, key2).sorted().toList());
                    }
                }
            }
        }

//        return result;
        return result.stream().toList();
    }

    public List<List<Integer>> threeSum2(int[] nums) {

        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            if (!map.containsKey(key)) {
                map.put(key, 1);
            } else {
                map.computeIfPresent(key, (k, v) -> v + 1);
            }
        }

        List<List<Integer>> result = new ArrayList<>();

        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            Integer key1 = next.getKey();
            if (key1 < 0) {
                continue;
            }

//            Integer value = next.getValue();
//            if (key1 == 0 && value == 3) {
//                result.add(List.of(0, 0, 0));
//            }

            Iterator<Map.Entry<Integer, Integer>> iterator2 = map.entrySet().iterator();
            while (iterator2.hasNext()) {
                Map.Entry<Integer, Integer> next2 = iterator2.next();
                Integer key2 = next2.getKey();
                if (key2 <= key1) {
                    continue;
                }

                Integer value2 = next2.getValue();
                if (value2 == 1) {
                    int key3 = key1 + key2;
                    if (map.containsKey(-key2)) {
                        result.add(List.of(-key3, key1, key2));
                    }
                } else if (value2 == 2) {
                    int key3 = key2 + key2;
                    if (map.containsKey(-key3)) {
                        result.add(List.of(-key3, key2, key2));
                    }
                }
            }
        }

        return result;
    }

    public List<List<Integer>> threeSum1(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        result.add(Stream.of(nums[i], nums[j], nums[k]).sorted().toList());
                    }
                }
            }
        }
        return result.stream().toList();
    }
}
