package com.study.bmservice.ws

import com.study.bmservice.dto.Instrument
import com.study.config.mybatis.Mapper
import org.apache.ibatis.annotations.Insert

@Mapper
interface PriceMapper {

    @Insert("""<script>
        INSERT INTO PRICE_TICKER (
            CREATED_DATE,
            LAST_PRICE,
            BUY_PRICE,
            SELL_PRICE,
            USER_CODE
        )
        VALUES
        (
            #{timestamp},
            #{lastPrice},
            #{buyPrice},
            #{sellPrice},
            #{userCode}
        )
    </script>""")
    int insertPriceTicker(Instrument instrument);

}
