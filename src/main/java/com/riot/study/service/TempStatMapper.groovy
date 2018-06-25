package com.riot.study.service

import com.riot.study.config.mybatis.Mapper
import com.riot.study.service.dto.Champion
import com.riot.study.service.dto.LMatch
import org.apache.ibatis.annotations.Update

/**
 * Created by bluegony on 2018. 6. 15..
 */
@Mapper
interface TempStatMapper {


    @Update("""<script>
        MERGE INTO temp_stat A
        USING dual
        ON (
            gid = #{gameId}
            and
            sid = #{summonerId}
        )
        WHEN NOT MATCHED THEN
         INSERT  (
            account_ID,
            owner,
            current_account_ID,
            sid,
            gid,
            cid,
            win,
            lose,
            start_time,
            duration,
            season,
            version,
            CREATED_DATE
        ) VALUES (
            #{accountId},
            #{owner},
            #{currentAccountId},
            #{summonerId},
            #{gameId},
            #{championId},
            #{win},
            #{lose},
            #{startTime},
            #{gameDuration},
            #{seasonId},
            #{gameVersion},
            SYSDATE
        )
    </script>""")
    int insertTempStat(LMatch match);


    @Update("""<script>
        MERGE INTO tempc A
        USING dual
        ON (
            id = #{id}
        )
        WHEN NOT MATCHED THEN
         INSERT  (
            id,
            name
        ) VALUES (
            #{id},
            #{name}
        )
    </script>""")
    int insertCham(Champion champion);
}
