SELECT c.Name,
COUNT(p.ID) AS ProjectCount
FROM client c
JOIN project p ON c.ID = p.CLIENT_ID
GROUP BY c.ID, c.Name
ORDER BY ProjectCount DESC
LIMIT 5;