package com.riot.study.service

import com.riot.study.config.mybatis.Mapper
import com.riot.study.service.dto.Champion
import com.riot.study.service.dto.LMatch
import org.apache.ibatis.annotations.Update

/**
 * Created by 1000773 on 2018. 6. 15..
 */
@Mapper
interface TempStatMapper {


    @Update("""<script>
        MERGE INTO temp_stat A
        USING dual
        ON (
            gid = #{gameId}
            and
            summoner_id = #{summonerId}
            
        )
        WHEN NOT MATCHED THEN
         INSERT  (
            account_ID,
            owner,
            current_account_ID,
            summoner_id,
            summoner_name,
            gid,
            champion_id,
            champion_name,
            win,
            start_time,
            duration,
            season_id,
            gversion,
            gmode,
            gtype,
            CREATED_DATE
        ) VALUES (
            #{accountId},
            #{owner},
            #{currentAccountId},
            #{summonerId},
            #{summonerName},
            #{gameId},
            #{championId},
            #{championName},
            #{win},
            #{startTime},
            #{gameDuration},
            #{seasonId},
            #{gameVersion},
            #{gameMode},
            #{gameType},
            SYSDATE
        )
    </script>""")
    int insertTempStat(LMatch match);


    @Update("""<script>
        MERGE INTO temp_cham A
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
