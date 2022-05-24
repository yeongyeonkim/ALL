[목표]

* accountType = DeveloperManager 라는 것이 없는 상황이고
* DM(Developer Manager)의 경우 accountType = Developer 이면서, User_permission 테이블의 subType이 AM인 것을 DM으로 간주함.
* 위 DM만을 검색하는 쿼리뿐만 아니라 검색에 authority 조건을 추가하여 authority = AM 뿐만 아닌, GDPR이나 다른 그 외의 조건들에 해당하는 accountType 또한 있는지 검색하여 User List를 내려줄 수 있도록 한다.

* 전체 XML

```xml
	<sql id="selectOnlyUser">
		SELECT
			U.accountCode, D.aid, D.accountGroupCode, D.marketingGroupCode, D.accountType, D.desiredGroupCode, D.desiredAccountType, D.allowApi, D.lastLogin,
			U.guid, U.email encEmail, U.singleId, U.themeType, U.utcOption, D.status, U.countryCode, U.nickName, U.addInfo, U.withdrawRequestDate, D.updated, D.created, D.requested, U.contactEmail encContactEmail, U.blockSendMail,
			( SELECT status FROM USER_PERMISSION P WHERE D.accountCode = P.accountCode AND D.aid=P.aid AND P.subType='GDPR') AS gdprStatus,
			( SELECT GROUP_CONCAT(DISTINCT subType ORDER BY subType ASC SEPARATOR ' ' ) FROM USER_PERMISSION P where D.accountCode=P.accountCode AND P.aid = D.aid AND status='ACTIVE') as authority,
			( SELECT GROUP_CONCAT(DISTINCT subType ORDER BY subType ASC SEPARATOR ' ' ) FROM USER_PERMISSION P where D.accountCode=P.accountCode AND P.aid = D.aid AND status='BLOCK') as blockedAuthority,
			( SELECT GROUP_CONCAT(`status` ORDER BY subType ASC SEPARATOR ' ' ) FROM USER_PERMISSION P where D.accountCode=P.accountCode) as authorityStatus,
			( SELECT GROUP_CONCAT(DISTINCT keyword ORDER BY keyword ASC SEPARATOR ' ' )	FROM USER_DETAILS_INDEX I WHERE U.accountCode = I.accountCode AND D.aid = I.aid	AND I.status='ACTIVE' AND dataType = 'COUNTRY' GROUP BY I.accountCode) activeCountryCodes,
			( SELECT nickname FROM USERS P WHERE P.accountCode=D.desiredGroupCode) as desiredOwnerNickname,
			( SELECT CONCAT('{', GROUP_CONCAT(CONCAT('"', `type`, '":[', keywords, ']') ORDER BY `type` ASC), '}')
				FROM (
				SELECT accountCode, aid, `type`, GROUP_CONCAT(DISTINCT CONCAT('"', keyword, '"') ORDER BY keyword ASC) AS keywords FROM USER_DETAILS_INDEX WHERE dataType = 'DIVISION_CODE' AND status='ACTIVE' GROUP BY accountCode, serviceKind, `type`
				) C WHERE U.accountCode = C.accountCode AND D.aid = C.aid) divisionCodeMap,
			AU.email as encAccountGroupEmail,
			AU.nickName as accountGroupOwnerNickname,
			AU.addInfo as accountGroupOwnerAddInfo,
			AU.contactEmail as encAccountGroupOwnerContactEmail,
			MU.email as encMarketingGroupEmail,
			MU.nickName as marketingGroupOwnerNickname,
			MU.addInfo as marketingGroupOwnerAddInfo,
			MU.contactEmail as encMarketingGroupOwnerContactEmail
	</sql>
	<sql id="selectUser">
		<include refid="selectOnlyUser"/>
		FROM
			USERS U
		INNER JOIN USER_DETAILS D 
			ON U.accountCode = D.accountCode
		LEFT JOIN USERS AU 
			ON D.accountGroupCode=AU.accountCode AND AU.accountCode!=D.accountCode
		LEFT JOIN USERS MU 
			ON D.marketingGroupCode=MU.accountCode AND MU.accountCode!=D.accountCode
		WHERE
			1=1
			<if test="aid != null">
			AND D.aid = #{aid}
			</if>
	</sql>
	
	<select id="selectUsersWithSearchOptions" resultType="User">
		<include refid="selectUser"/>
		<include refid="whereClauseSearchOptions"/>
	</select>
	<sql id="whereClauseSearchOptions">
		<if test="aidList != null and !aidList.isEmpty()">
		AND D.accountGroupCode in (
			SELECT accountGroupCode
			FROM APPLICATIONS
			WHERE
				D.aid IN (
			<foreach collection="aidList" item="item" separator=",">
					#{item}
			</foreach>
				)
			)
		</if>
	
		AND	(
		<choose>
			<when test="accountStatuses == null or accountStatuses.isEmpty()">
			D.status != 'UNREGISTERED'
			</when>
			<when test="accountStatuses.size() == 1">
				<foreach item="accountStatus" collection="accountStatuses">
					<choose>
						<when test="accountStatus == 'CHANGE_REQUEST'">
							D.status='ACTIVE' AND desiredAccountType IS NOT NULL AND desiredGroupCode IS NOT NULL
						</when>
						<otherwise>
							D.status = #{accountStatus}
						</otherwise>
					</choose>
				</foreach>
			</when>
			<otherwise>
			D.status IN
				<foreach item="accountStatus" collection="accountStatuses" open="(" separator="," close=")">
				#{accountStatus}
				</foreach>
				<foreach item="accountStatus" collection="accountStatuses">
					<if test="accountStatus == 'CHANGE_REQUEST'">
						OR (D.status='ACTIVE' AND desiredAccountType IS NOT NULL AND desiredGroupCode IS NOT NULL)
					</if>
				</foreach>
			</otherwise>
		</choose>
		)
		<if test="accountTypes != null and !accountTypes.isEmpty()">
		AND	(
			D.accountType IN
			<foreach item="accountType" collection="accountTypes" open="(" separator="," close=")">
				#{accountType}
			</foreach>
		)
		</if>

		<if test="excludedAccountTypes != null and !excludedAccountTypes.isEmpty()">
		AND	(
			D.accountType NOT IN
			<foreach item="excludedAccountType" collection="excludedAccountTypes" open="(" separator="," close=")">
				#{excludedAccountType}
			</foreach>
		)
		</if>

		<if test="searchType != 'CHANGE_REQUEST'">
			<choose>
				<when test="accountGroupCode != null and marketingGroupCode != null">
					AND D.accountGroupCode = #{accountGroupCode}
					AND (D.marketingGroupCode = #{accountGroupCode} OR D.marketingGroupCode = U.accountCode OR D.desiredGroupCode = #{accountGroupCode})
					<if test="false == includeMe">
						AND U.accountCode != #{accountGroupCode}
					</if>
				</when>
				<when test="accountGroupCode != null">
					AND (D.accountGroupCode = #{accountGroupCode} OR D.desiredGroupCode = #{accountGroupCode})
					<if test="aid == 'aCie9ev7Sw'">
					    AND U.accountCode != #{accountCode}
					</if>
				</when>
				<when test="marketingGroupCode != null">
					AND (D.marketingGroupCode = #{marketingGroupCode} OR D.desiredGroupCode = #{marketingGroupCode})
					<if test="true != includeMe">
						AND U.accountCode != #{marketingGroupCode}
					</if>
				</when>
			</choose>
		</if>

		<if test="beginCreateDate != null">
		AND DATE(D.created) <![CDATA[>=]]> DATE(#{beginCreateDate})
		</if>
		<if test="endCreateDate != null">
		AND DATE(D.created) <![CDATA[<=]]> DATE(#{endCreateDate})
		</if>
		<if test="authority != null">
		AND D.accountCode=( SELECT P.accountCode FROM USER_PERMISSION P WHERE D.aid=P.aid AND D.accountCode=P.accountCode AND status='ACTIVE' AND subType IN
		<foreach item="item" collection="authority" open="(" separator="," close=")">
			#{item}
		</foreach>
		GROUP BY P.accountCode)
		</if>
		<if test="accountTypes != null and accountTypes.contains('DEVELOPERMANAGER')">
		AND (SELECT COUNT(*) FROM USER_PERMISSION UP WHERE UP.accountCode=D.accountCode AND UP.aid=D.aid AND UP.subType='AM' AND UP.status='ACTIVE') > 0
        </if>
		<if test="gcdmDivisionCodeList != null">
			AND D.aid = 'aCie9ev7Sw'
            AND U.accountCode = ( SELECT I.accountCode FROM USER_DETAILS_INDEX I WHERE U.accountCode=I.accountCode AND I.aid=D.aid AND I.type='GCDM' AND I.dataType='DIVISION_CODE' AND I.status='ACTIVE'
            AND I.keyword IN
            <foreach item="item" collection="gcdmDivisionCodeList" open="(" separator="," close=")">
				#{item}
			</foreach>
            GROUP BY I.accountCode )
        </if>
        <if test="cdmDivisionCodeList != null">
        	AND D.aid='aCie9ev7Sw'
            AND U.accountCode = ( SELECT I.accountCode FROM USER_DETAILS_INDEX I WHERE U.accountCode=I.accountCode AND I.aid=D.aid AND I.type='CDM' AND I.dataType='DIVISION_CODE' AND I.status='ACTIVE' AND I.keyword IN
            <foreach item="item" collection="cdmDivisionCodeList" open="(" separator="," close=")">
				#{item}
			</foreach>
            GROUP BY I.accountCode )
        </if>

		<if test="activeCountryCodeList != null and activeCountryCodeList.size() > 0">
		AND U.accountCode IN (
			SELECT DISTINCT accountCode
			FROM USER_DETAILS_INDEX I
			WHERE
					dataType = 'COUNTRY'
				AND serviceKind = #{serviceKind}
				AND status = 'ACTIVE'
				AND keyword IN
			<foreach item="item" collection="activeCountryCodeList" open="(" separator="," close=")">
						#{item}
			</foreach>
		)
		</if>
		<if test="searchText != null">
			<choose>
				<when test="'Email'.equalsIgnoreCase(searchType)">
		AND	(
			U.email = #{email}
		)
				</when>
				<when test="'Nickname'.equalsIgnoreCase(searchType) or 'Name'.equalsIgnoreCase(searchType)">
		AND
			U.nickName LIKE CONCAT('%', #{searchText}, '%')
				</when>
				<when test="'All'.equalsIgnoreCase(searchType)">
		AND
			(
				U.nickName LIKE CONCAT('%', #{searchText}, '%') OR
				U.email = #{email}
			)
				</when>
				<when test="'Account'.equalsIgnoreCase(searchType)">
		AND U.email = #{email}
				</when>
				<when test="'ContactEmail'.equalsIgnoreCase(searchType)">
		AND U.contactEmail = #{email}
				</when>
				<when test="'SingleId'.equalsIgnoreCase(searchType)">
		AND U.singleId LIKE CONCAT('%', #{searchText}, '%')
				</when>
				<when test="'AccountAll'.equalsIgnoreCase(searchType)">
		AND (
			U.email = #{email} OR
			U.singleId LIKE CONCAT('%', #{searchText}, '%') OR
			U.nickName LIKE CONCAT('%', #{searchText}, '%')
		)
				</when>
				<otherwise>
		AND (
			U.nickName LIKE CONCAT('%', #{searchText}, '%') OR
			U.addInfo LIKE CONCAT('%', #{searchText}, '%') OR
			U.email = #{email} OR
			U.contactEmail = #{email} OR
			U.accountCode = #{searchText} OR
			D.accountGroupCode = #{searchText} OR
			D.marketingGroupCode = #{searchText}
		)
				</otherwise>
			</choose>
		</if>
	</sql>
```



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

<img src="SQL\1.png" alt="1" style="zoom:25%;" />

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

<img src="SQL\2.png" alt="2" style="zoom:33%;" />

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

<img src="C:\Bespinglobal\DevOps - SMP\5월\SQL\3.png" alt="3" style="zoom:33%;" />



* 총 쿼리

```sql
# select
SELECT
			U.accountCode, D.aid, D.accountGroupCode, D.accountType,
			( SELECT GROUP_CONCAT(DISTINCT subType ORDER BY subType ASC SEPARATOR ' ' ) FROM USER_PERMISSION P where D.accountCode=P.accountCode AND P.aid = D.aid AND status='ACTIVE') as authority
# from
FROM USERS U
INNER JOIN USER_DETAILS D ON U.accountCode = D.accountCode
# where
WHERE 1=1 
AND (D.status != 'UNREGISTERED') 
AND (D.accountType IN ('DEVELOPERMANAGER', 'DEVELOPER'))  # 1 
AND (SELECT COUNT(*) FROM USER_PERMISSION UP WHERE UP.accountCode=D.accountCode AND UP.aid=D.aid AND UP.subType='AM' AND UP.status='ACTIVE') > 0  # 2
# order by
ORDER BY D.created DESC

# Where 절에 아래 DM 권한 검색 추가 쿼리 # 3
AND D.accountCode=(
SELECT P.accountCode
FROM USER_PERMISSION P
WHERE D.aid=P.aid AND D.accountCode=P.accountCode AND STATUS='ACTIVE' AND subType IN ('GDPR')
GROUP BY P.accountCode)

# 비슷하게 where 절을 다음과 같이 변경도 될듯 (where 절의 accountCode 구문을 select Count(*)로)
SELECT U.accountCode, D.aid, D.accountGroupCode, D.accountType,
(SELECT GROUP_CONCAT(DISTINCT subType ORDER BY subType ASC SEPARATOR ' ' ) FROM USER_PERMISSION P where D.accountCode=P.accountCode AND P.aid = D.aid AND status='ACTIVE') as authority
FROM USERS U
INNER JOIN USER_DETAILS D ON U.accountCode = D.accountCode
WHERE 1=1
AND D.status != 'UNREGISTERED'
AND D.accountType IN ('DEVELOPER', 'DEVELOPERMANAGER')
AND (SELECT COUNT(*) FROM USER_PERMISSION UP WHERE UP.accountCode = D.accountCode AND UP.aid = D.aid AND STATUS = 'ACTIVE' AND subType = 'AM') > 0
AND (SELECT COUNT(*) FROM USER_PERMISSION P  WHERE  P.accountCode = D.accountCode AND  P.aid = D.aid AND STATUS = 'ACTIVE' AND subType IN ('GDPR', 'PM'));
```

