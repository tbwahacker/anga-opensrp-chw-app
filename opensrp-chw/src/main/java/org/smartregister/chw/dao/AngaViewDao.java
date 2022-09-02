package org.smartregister.chw.dao;

import org.smartregister.chw.domain.PmtctReferralMemberObject;
import org.smartregister.dao.AbstractDao;

import java.util.ArrayList;
import java.util.List;

public class AngaViewDao  extends AbstractDao {
    
    public static List<AngaModel> RetrieveAllRows(String baseEntityId) {

        String sql = String.format(
                "SELECT * FROM condom_distribution"
        );

        DataMap<AngaModel> dataMap = cursor -> {
            AngaModel angaModel =
                    new AngaModel(
                            getCursorValue(cursor, "condom_type", ""),
                            getCursorValue(cursor, "no_condom_issued", ""),
                            getCursorValue(cursor, "condom_brand", ""));
            return angaModel;
        };

        List<AngaModel> res = readData(sql, dataMap);

        return res;

    }
    
}
