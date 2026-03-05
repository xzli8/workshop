
# Ref: https://leetcode.cn/problems/word-frequency/solutions/1046618/qie-ge-pai-xu-dan-ci-tong-ji-ci-shu-pai-8sdgt/
cat words.txt | tr -s ' ' '\n' | sort | uniq -c | sort -r | awk '{ print $2, $1 }'

