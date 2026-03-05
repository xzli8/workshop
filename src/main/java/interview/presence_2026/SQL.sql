
-- ## 题目二：sql
-- 有一个员工表，employees，包含三个字段： id、name、managerId（直属主管），请实现sql，查询至少包含三个直接下属的员工的 id、name、prodCount（员工数量）

SELECT
    e.id,
    e.name,
    COUNT(*) AS prodCount
FROM employees e
JOIN employees sub ON e.id = sub.managerId
GROUP BY e.id
HAVING COUNT(*) >= 3;