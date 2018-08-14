package com.riot.study.service

import com.riot.study.config.mybatis.Mapper
import com.riot.study.service.dto.Champion
import com.riot.study.service.dto.LMatch
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Update

/**
 * Created by bluegony on 2018. 6. 15..
 */
@Mapper
interface TempStatMapper {


    @Update("""<script>
        insert into TEMP_STAT (
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
            now()
        )
        on duplicate key update
        created_date = now()
    </script>""")
    int insertTempStat(LMatch match);


    @Update("""<script>
        insert INTO TEMPC (
            id,
            name
        ) VALUES (
            #{id},
            #{name}
        )
        on duplicate key update
        name = #{name}
    </script>""")
    int insertCham(Champion champion);

//    /**
//     * 대량 insert할때 빠르게.
//     * @return
//     */
//    @Insert("""<script>
//        INSERT INTO REWARD_POINT (
//            ID,
//            BATCH_HISTORY_ID,
//            CORP_CODE,
//            PROD_CODE,
//            PARTNER_PROD_CODE,
//            PARTNER_TR_KEY,
//            PARTNER_POINT_CODE,
//            CI,
//            EARNING_MONTH,
//            EARNING_AMOUNT,
//            REWARD_MONTH,
//            POINT_AMOUNT,
//            RECOGNIZED_EARNING,
//            CALCULATION_FEE,
//            GRADE,
//            POINT_STATUS,
//            PERIOD_START,
//            PERIOD_END,
//            EXCHANGE_DATE,
//            CREATED_DATE
//        )
//        <foreach collection="list" item="item" index="index" separator=" UNION ALL ">
//            SELECT
//                #{item.id},
//                #{item.batchHistoryId},
//                #{item.corpCode},
//                #{item.prodCode},
//                #{item.partnerProdCode},
//                #{item.partnerTrKey},
//                #{item.partnerPointCode},
//                #{item.ci},
//                #{item.earningMonth},
//                #{item.earningAmount},
//                #{item.rewardMonth},
//                #{item.pointAmount},
//                #{item.recognizedEarning},
//                #{item.calculationFee},
//                #{item.grade, jdbcType=VARCHAR},
//                #{item.pointStatus, jdbcType=VARCHAR},
//                #{item.periodStart, jdbcType=VARCHAR},
//                #{item.periodEnd, jdbcType=VARCHAR},
//                #{item.exchangeDate, jdbcType=VARCHAR},
//                SYSDATE
//            FROM DUAL
//        </foreach>
//    </script>""")
//    int insertRewardPointList(List<RewardPoint> rewardPointList)


}
