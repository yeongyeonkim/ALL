0. Oracle이 다양한 언어를 사용하는 데 있어서 유리하다. (e.g., JavaScript)

1. 날짜는 Date가 아닌 Varchar를 사용하는 것이 유용한 때가 많다.
   * https://stackoverflow.com/questions/4759012/when-to-use-varchar-and-date-datetime

2. Char vs Varchar
   * Char(20)으로 정의한 경우, 20 공간만큼 사용하지 않아도 미리 확보를 하여 낭비되는 공간이 생긴다.
   * Varchar(20)으로 정의한 경우, 데이터 크기 만큼만 공간을 사용한다.

3. Index는 select 할 때 사용하는 것이 좋다.
4. Auto Commit 하지 않기 ★
5. Join
   * 주로 left, inner 조인 사용

6. 명령어

   * DISTINCT, SUBSTR, UPPER, CONCAT, REPLACE, LTRIM, ROUND, TRUNC 등 중요

     * SELECT DISTINCT NAME FROM TABLE;

     * SELECT ENAME, SUBSTR(ENAME, 2, 3) FROM EMP;

       * SUBSTR("문자열", "시작 위치", "길이");

     * SELECT UPPER('tistory')

     * SELECT CONCAT(COUNTRY_ID, COUNTRY_NAME) FRON COUNTRIES;

       > KRKorea
       > CNChina

     * REPLACE('문자열', '치환할 문자열', '대체할 문자열')
       REPLACE('ABCDE', 'A', '1')

       > '1BCDE'

     * LTRIM('    ABCDE    ')

       > 'ABCDE    '

     * ROUND('값', '자리수')
       ROUND(1235.443, 0)

       > 1235

     * TRUNC('값', '옵션') - 주로 소수점 절사 및 날짜의 시간을 없앨 때 사용
       TRUNC('18/11/27', 'year')

       > '18/01/01' 

   * 그룹함수

     * COUNT, AVG, SUM, MIN, MAX

7. SQL 실행순서

   1. FROM
   2. WHERE
   3. GROUP BY
   4. HAVING
   5. SELECT
   6. ORDER BY