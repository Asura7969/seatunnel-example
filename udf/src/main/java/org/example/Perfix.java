package org.example;

import com.google.auto.service.AutoService;
import org.apache.seatunnel.api.table.type.BasicType;
import org.apache.seatunnel.api.table.type.SeaTunnelDataType;
import org.apache.seatunnel.transform.sql.zeta.ZetaUDF;

import java.util.List;

/**
 * @author asura7969
 * @create 2024-06-12-21:31
 * @desc mvn clean & mvn package, 放到${SEATUNNEL_HOME}/lib
 */
@AutoService(ZetaUDF.class)
public class Perfix implements ZetaUDF {
    @Override
    public String functionName() {
        return "perfix";
    }

    @Override
    public SeaTunnelDataType<?> resultType(List<SeaTunnelDataType<?>> list) {
        return BasicType.STRING_TYPE;
    }

    @Override
    public Object evaluate(List<Object> list) {
        String str = list.get(0).toString();
        return "b:" + str;
    }
}
