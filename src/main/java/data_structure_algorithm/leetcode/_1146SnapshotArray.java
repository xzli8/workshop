package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _1146SnapshotArray {

    public static class Solution1 {

        /**
         BinarySearch
         */
        class SnapshotArray {

            private int snapCount;
            private List<int[]>[] data;

            public SnapshotArray(int length) {
                snapCount = 0;
                data = new List[length];
                for (int i = 0; i < length; i++) {
                    data[i] = new ArrayList<>();
                }
            }

            public void set(int index, int val) {
                data[index].add(new int[] {snapCount, val});
            }

            public int snap() {
                return snapCount++;
            }

            public int get(int index, int snap_id) {
                // BinarySearch: search for the latest snap_id that is lower than the given snap_id

                // 这样写更清晰，但是copy太耗费时间，特别是对于数据量大的测试用例
//                int[] snapIds = data[index].stream().map(item -> item[0]).mapToInt(Integer::intValue).toArray();
//                int n = snapIds.length, left = 0, right = n - 1;
//                while (left <= right) {
//                    int mid = left + ((right - left) >> 1);
//                    if (snapIds[mid] <= snap_id) {
//                        if (mid == n - 1 || snapIds[mid + 1] > snap_id) {
//                            return data[index].get(mid)[1];
//                        } else {
//                            left = mid + 1;
//                        }
//                    } else {
//                        right = mid - 1;
//                    }
//                }
//                return 0;

                List<int[]> pairs = data[index];
                int n = pairs.size(), left = 0, right = n - 1;
                while (left <= right) {
                    int mid = left + ((right - left) >> 1);
                    if (pairs.get(mid)[0] <= snap_id) {
                        if (mid == n - 1 || pairs.get(mid + 1)[0] > snap_id) {
                            return data[index].get(mid)[1];
                        } else {
                            left = mid + 1;
                        }
                    } else {
                        right = mid - 1;
                    }
                }
                return 0;
            }

        }

    }

}
