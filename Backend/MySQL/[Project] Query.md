[목표]

* accountType = DeveloperManager 라는 것이 없는 상황이고
* DM(Developer Manager)의 경우 accountType = Developer 이면서, User_permission 테이블의 subType이 AM인 것을 DM으로 간주함.
* 위 DM만을 검색하는 쿼리뿐만 아니라 검색에 authority 조건을 추가하여 authority = AM 뿐만 아닌, GDPR이나 다른 그 외의 조건들에 해당하는 accountType 또한 있는지 검색하여 User List를 내려줄 수 있도록 한다.

* 전체 XML (보안상 내용에서 삭제)

* #1 해당 XML (foreach로 파싱하는 부분)

```xml
<!-- xml -->
		<if test="accountTypes != null and !accountTypes.isEmpty()">
		AND	(
			D.accountType IN
			<foreach item="accountType" collection="accountTypes" open="(" separator="," close=")">
				#{accountType}
			</foreach>
		)
		</if>
```

* #2 User Permission에서 Developer Manager만 가져오기 위해 where절에 추가한 것 (count > 0 으로)

```xml
<!-- xml -->
		<if test="accountTypes != null and accountTypes.contains('DEVELOPERMANAGER')">
		AND (SELECT COUNT(*) FROM USER_PERMISSION UP WHERE UP.accountCode=D.accountCode AND UP.aid=D.aid AND UP.subType='AM' AND UP.status='ACTIVE') > 0
        </if>
```

```sql
# SQL
SELECT *
FROM USER_DETAILS D, USER_PERMISSION UP
WHERE UP.accountCode=D.accountCode AND UP.aid=D.aid AND UP.subType='AM' AND UP.status='ACTIVE';
```

이것만으로는 accountType이 Developer, status가 active한게 나오는게 아니므로 다른 조건들이 필요

* 여기까지만 작성하여 쿼리를 돌리면

```sql
SELECT
			U.accountCode, D.aid, D.accountGroupCode, D.accountType,
			( SELECT GROUP_CONCAT(DISTINCT subType ORDER BY subType ASC SEPARATOR ' ' ) FROM USER_PERMISSION P where D.accountCode=P.accountCode AND P.aid = D.aid AND status='ACTIVE') as authority
FROM USERS U
INNER JOIN USER_DETAILS D ON U.accountCode = D.accountCode
WHERE 1=1 
AND (D.status != 'UNREGISTERED') 
AND (D.accountType IN ('DEVELOPERMANAGER', 'DEVELOPER'))  # 1 
AND (SELECT COUNT(*) FROM USER_PERMISSION UP WHERE UP.accountCode=D.accountCode AND UP.aid=D.aid AND UP.subType='AM' AND UP.status='ACTIVE') > 0  # 2
# order by
ORDER BY D.created DESC;
```

* DM을 모두 검색

* #3 DM 중 특정 권한만 가진 사람을 찾고 싶음. (쿼리상 GDPR)

```xml
<!-- xml -->
		<if test="authority != null">
		AND D.accountCode=( SELECT P.accountCode FROM USER_PERMISSION P WHERE D.aid=P.aid AND D.accountCode=P.accountCode AND status='ACTIVE' AND subType IN
		<foreach item="item" collection="authority" open="(" separator="," close=")">
			#{item}
		</foreach>
		GROUP BY P.accountCode)
		</if>
```

```sql
# SQL
# where 절 권한 검색 추가 쿼리 # 3
AND D.accountCode=(
SELECT P.accountCode
FROM USER_PERMISSION P
WHERE D.aid=P.aid AND D.accountCode=P.accountCode AND STATUS='ACTIVE' AND subType IN ('GDPR')
GROUP BY P.accountCode)
```
