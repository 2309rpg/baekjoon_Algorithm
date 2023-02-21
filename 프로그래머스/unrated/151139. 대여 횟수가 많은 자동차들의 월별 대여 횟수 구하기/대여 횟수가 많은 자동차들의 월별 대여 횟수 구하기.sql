SELECT TO_NUMBER(TO_CHAR(START_DATE,'MM'),'999,999') AS MONTH,
       CAR_ID,
       COUNT(*) AS RECORDS
  FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY A
 WHERE A.CAR_ID IN 
                    (SELECT CAR_ID
                      FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY C
                     WHERE TO_CHAR(START_DATE, 'YYYY-MM-DD') >= '2022-08-01'
                       AND TO_CHAR(START_DATE, 'YYYY-MM-DD') <= '2022-10-31'
                    HAVING COUNT(*) > 4 
                    GROUP BY CAR_ID)
                    and
                    (TO_CHAR(START_DATE, 'YYYY-MM-DD') >= '2022-08-01'
                     or TO_CHAR(START_DATE, 'YYYY-MM-DD') <= '2022-10-31')
 GROUP BY TO_NUMBER(TO_CHAR(START_DATE,'MM'),'999,999'), CAR_ID
 HAVING COUNT(*) > 0
 ORDER BY 1 ASC, 2 DESC