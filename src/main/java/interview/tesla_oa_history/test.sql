
create table test_groups (
    name       varchar(40) not null,
    test_value integer     not null,
    unique (name)
)

create table test_cases (
    id         integer     not null,
    group_name varchar(40) not null,
    status     varchar(5)  not null,
    unique (id)
)

-- solution1
SELECT
    g.name name,
    COUNT(c.id) all_test_cases,
    SUM(CASE WHEN c.status = 'OK' THEN 1 ELSE 0 END) passed_test_cases,
    SUM(CASE WHEN c.status = 'OK' THEN 1 ELSE 0 END) * g.test_value total_value
FROM test_groups g
         LEFT JOIN test_cases c ON g.name = c.group_name
GROUP BY g.name, g.test_value
ORDER BY total_value DESC, name;

-- solution2
SELECT
    g.name name,
    COUNT(c.id) all_test_cases,
    COUNT(c.status = 'OK' OR NULL) passed_test_cases,
    COUNT(c.status = 'OK' OR NULL) * g.test_value total_value
FROM test_groups g
         LEFT JOIN test_cases c ON g.name = c.group_name
GROUP BY g.name, g.test_value
ORDER BY total_value DESC, name;
