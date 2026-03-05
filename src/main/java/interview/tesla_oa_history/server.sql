
/**
  Given a table describing servers and the number of connections of each of them. A server is overload if it has more
  connections than the average number of connections for all the servers. Write a sql to calculate the status of every
  server. The table of result should contain three columns: id (id of the server), server_name(name of the server) and
  status ('OK' or 'OVERLOAD', depending on whether the number of connections is strictly bigger than the average). The
  rows should be ordered by increasing id.
 */

create table servers (
    id integer not null,
    server_name varchar not null,
    connections integer not null,
    unique(id),
    unique(server_name)
)

SELECT
    id,
    server_name,
    CASE
        WHEN connections > (SELECT AVG(connections) FROM servers) THEN 'OVERLOAD'
        ELSE 'OK'
        END AS status
FROM servers
ORDER BY id ASC;