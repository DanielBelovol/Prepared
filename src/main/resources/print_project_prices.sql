SELECT p.ID AS Project_ID,
       c.NAME AS Client_Name,
       pw.Total_Monthly_Salary * (DATE_PART('month', p.FINISH_DATE - p.START_DATE) + 1) AS Total_Cost
FROM project p
JOIN (
    SELECT pw.PROJECT_ID,
           SUM(w.Salary) AS Total_Monthly_Salary
    FROM project_worker pw
    JOIN worker w ON pw.WORKER_ID = w.ID
    GROUP BY pw.PROJECT_ID
) pw ON p.ID = pw.PROJECT_ID
JOIN client c ON p.CLIENT_ID = c.ID;