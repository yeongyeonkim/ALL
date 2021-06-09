```shell
SET=$(seq 1 50) #여기 띄어쓰기 하면 안됨.
for i in $SET
do
    echo $i
done

##################################################################

1
2
3
4
5
.
.
.
.
.
50
```



```shell
read input
if [[ $input == [yY] ]]; then
    echo "YES"
else
    echo "NO"
fi

##################################################################

Y나 y면 YES
나머지 NO
```



https://knight76.tistory.com/entry/expr-bc-%EB%AA%85%EB%A0%B9%EC%96%B4-%EC%A0%95%EC%88%98-%EB%B0%8F-%EC%8B%A4%EC%88%98-%EC%97%B0%EC%82%B0%EC%9D%84-bash%EC%97%90%EC%84%9C-%EC%93%B0%EA%B8%B0

expr & bc 명령어

```shell
a=1
b=2
c=`expr $a + $b`  ## 연산자 주변에 공백이 필수, 그렇지 않으면 문자열로 인식.
echo $c

3
```



```shell
echo $(printf %.3f $(bc -l))

# bc 리눅스 계산기
# -l : 표준 수학 라이브러리를 정의한다.
# bc -l 정확도 높은 실수 계산
########################################################################

input : 5+50*3/20 + (19*2)/7
output : 17.929
```



```shell
read n
for i in $(seq 1 $n);
    do
        read num
        sum=$((sum + num))
    done
printf "%.3f" $(echo "$sum/$n" | bc -l)

########################################################################

input
4
1
2
9
8

output
5.000
```







