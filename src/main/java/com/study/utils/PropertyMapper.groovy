package com.study.utils

import com.study.config.mybatis.Mapper
import com.study.utils.dto.ServiceProperty
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select;

@Mapper
interface PropertyMapper {

    @Select("""<script>
        SELECT
            VALUE
        FROM SERVICE_PROPERTY
        WHERE 1=1
            AND PRKEY = #{key}
    </script>""")
    List<String> selectPropertyListByKey(@Param("key") String key);

    @Select("""<script>
        SELECT
            id, prkey, subkey, value, description, created_date
        FROM SERVICE_PROPERTY
        WHERE 1=1
            AND PRKEY = #{prkey}
            and subkey = #{subkey}
    </script>""")
    List<ServiceProperty> selectPropertyList(ServiceProperty property);

}
