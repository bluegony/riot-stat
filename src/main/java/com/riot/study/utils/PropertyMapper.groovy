package com.riot.study.utils

import com.riot.study.config.mybatis.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PropertyMapper {

    @Select("""<script>
        SELECT
            VALUE
        FROM SERVICE_PROPERTY
        WHERE 1=1
            AND KEY = #{key}
    </script>""")
    public List<String> selectPropertyListByKey(@Param("key") String key);

}
