
/**
  Given a prices table, find days when the price spiked (days on which the price was strictly larger than on the day
  before and the day after), the first day and the last day can never be spikes. Write a sql returns the spiked days,
  rows should be ordered in increasing order by 'day'.
 */

create table prices (
    day integer NOT NULL UNIQUE,
    price integer NOT NULL
);


-- 窗口函数
SELECT day
FROM (
    SELECT
    day,
    price,
    -- 前一天价格
    LAG(price) OVER (ORDER BY day) AS prev_price,
    -- 后一天价格
    LEAD(price) OVER (ORDER BY day) AS next_price
    FROM prices
    ) t
-- 当天 > 前一天 且 当天 > 后一天
WHERE price > prev_price
  AND price > next_price
ORDER BY day;

-- 自连结
SELECT p1.day
FROM prices p1
         JOIN prices p2 ON p2.day = p1.day - 1
         JOIN prices p3 ON p3.day = p1.day + 1
WHERE p1.price > p2.price
  AND p1.price > p3.price
ORDER BY p1.day;