package com.study.bmservice.ws

import com.study.bmservice.dto.Instrument
import com.study.bmservice.dto.Price
import com.study.bmservice.dto.TrueRange
import com.web.config.mybatis.Mapper
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

@Mapper
interface PriceMapper {

    @Insert("""<script>
        INSERT INTO PRICE_TICKER (
            LAST_PRICE,
            BUY_PRICE,
            SELL_PRICE,
            USER_CODE
        )
        VALUES
        (
            #{lastPrice},
            #{buyPrice},
            #{sellPrice},
            #{userCode}
        )
    </script>""")
    int insertPriceTicker(Instrument instrument);


    @Update("""<script>
        insert into price (
            timestamp,
            LAST_PRICE,
            HIGH_PRICE,
            LOW_PRICE,
            USER_CODE
        ) VALUES (
            #{timestamp},
            #{lastPrice},
            #{highPrice},
            #{lowPrice},
            #{userCode}
        )
        on duplicate key update
        high_price = #{highPrice},
        low_price = #{lowPrice},
        last_price = #{lastPrice}
    </script>""")
    int insertPrice(Price price);

    @Select("""
        select last_price from price where timestamp=addtime( date_format(now(),'%Y-%m-%d 00:00:00'), '-0:1:0')
    """)
    float selectLastPrice()


    @Select("""
        select date_format(timestamp,'%Y-%m-%d') timestamp, max(high_price) high_price, min(low_price) low_price from price
        where date_format(timestamp,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d')
    """)
    TrueRange selectHighLowPrice();


}
