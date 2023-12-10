
# Read from the file words.txt and output the word frequency list to stdout.

# tr命令用于转换或者删除文件中的字符，后跟参数"-s:原字符 目标字符"
# sort -n：表示按照数字大小进行排序
cat words.txt | tr -s ' ' '\n' | sort | uniq -c | sort -nr | awk '{ print $2, $1 }'
