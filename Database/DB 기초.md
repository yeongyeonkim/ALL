0. Oracle이 다양한 언어를 사용하는 데 있어서 유리하다. (e.g., JavaScript)

1. 날짜는 Date가 아닌 Varchar를 사용하는 것이 유용한 때가 많다.
   * https://stackoverflow.com/questions/4759012/when-to-use-varchar-and-date-datetime

2. Char vs Varchar
   * Char(20)으로 정의한 경우, 20 공간만큼 사용하지 않아도 미리 확보를 하여 낭비되는 공간이 생긴다.
   * Varchar(20)으로 정의한 경우, 데이터 크기 만큼만 공간을 사용한다.