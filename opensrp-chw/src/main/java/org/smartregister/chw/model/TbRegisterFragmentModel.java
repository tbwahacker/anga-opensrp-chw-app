package org.smartregister.chw.model;


import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;
import org.smartregister.chw.core.utils.ChildDBConstants;
import org.smartregister.chw.tb.model.BaseTbRegisterFragmentModel;
import org.smartregister.chw.tb.util.Constants.Tables;
import org.smartregister.chw.tb.util.DBConstants.Key;
import org.smartregister.chw.util.Constants;
import org.smartregister.cursoradapter.SmartRegisterQueryBuilder;
import org.smartregister.family.util.DBConstants;

import java.util.HashSet;
import java.util.Set;

public class TbRegisterFragmentModel extends BaseTbRegisterFragmentModel {

    @NonNull
    @Override
    public String mainSelect(@NonNull String tableName, @NonNull String mainCondition) {
        SmartRegisterQueryBuilder queryBuilder = new SmartRegisterQueryBuilder();
        queryBuilder.SelectInitiateMainTable(tableName, mainColumns(tableName));
        queryBuilder.customJoin("INNER JOIN " + Constants.TABLE_NAME.FAMILY_MEMBER + " ON  " + tableName + "." + DBConstants.KEY.BASE_ENTITY_ID + " = " + Constants.TABLE_NAME.FAMILY_MEMBER + "." + DBConstants.KEY.BASE_ENTITY_ID + " COLLATE NOCASE ");
        queryBuilder.customJoin("INNER JOIN " + Constants.TABLE_NAME.FAMILY + " ON  " + Constants.TABLE_NAME.FAMILY_MEMBER + "." + DBConstants.KEY.RELATIONAL_ID + " = " + Constants.TABLE_NAME.FAMILY + "." + DBConstants.KEY.BASE_ENTITY_ID);
        queryBuilder.customJoin("LEFT JOIN " + Constants.TABLE_NAME.FAMILY_MEMBER + " as T1 ON  " + Constants.TABLE_NAME.FAMILY + "." + DBConstants.KEY.PRIMARY_CAREGIVER + " = T1." + DBConstants.KEY.BASE_ENTITY_ID);
        queryBuilder.customJoin("LEFT JOIN " + Constants.TABLE_NAME.FAMILY_MEMBER + " as T2 ON  " + Constants.TABLE_NAME.FAMILY + "." + DBConstants.KEY.FAMILY_HEAD + " = T2." + DBConstants.KEY.BASE_ENTITY_ID);
        return queryBuilder.mainCondition(mainCondition);
    }

    @Override
    @NotNull
    public String[] mainColumns(String tableName) {
        Set<String> columnList = new HashSet<>();

        columnList.add(tableName + "." + DBConstants.KEY.BASE_ENTITY_ID);
        columnList.add(Constants.TABLE_NAME.FAMILY_MEMBER + "." + DBConstants.KEY.RELATIONAL_ID + " as " + ChildDBConstants.KEY.RELATIONAL_ID);
        columnList.add(Constants.TABLE_NAME.FAMILY_MEMBER + "." + DBConstants.KEY.FIRST_NAME);
        columnList.add(Constants.TABLE_NAME.FAMILY_MEMBER + "." + DBConstants.KEY.MIDDLE_NAME);
        columnList.add(Constants.TABLE_NAME.FAMILY_MEMBER + "." + DBConstants.KEY.LAST_NAME);
        columnList.add(Constants.TABLE_NAME.FAMILY_MEMBER + "." + DBConstants.KEY.DOB);
        columnList.add(Constants.TABLE_NAME.FAMILY_MEMBER + "." + DBConstants.KEY.GENDER);
        columnList.add(Constants.TABLE_NAME.FAMILY_MEMBER + "." + DBConstants.KEY.UNIQUE_ID);
        columnList.add(Constants.TABLE_NAME.FAMILY_MEMBER + "." + DBConstants.KEY.RELATIONAL_ID);
        columnList.add(Constants.TABLE_NAME.FAMILY_MEMBER + "." + DBConstants.KEY.PHONE_NUMBER);
        columnList.add(Constants.TABLE_NAME.FAMILY_MEMBER + "." + DBConstants.KEY.OTHER_PHONE_NUMBER);
        columnList.add("T2." + DBConstants.KEY.PHONE_NUMBER + " AS " + Key.FAMILY_HEAD_PHONE_NUMBER);
        columnList.add(Constants.TABLE_NAME.FAMILY + "." + DBConstants.KEY.VILLAGE_TOWN);
        columnList.add("T1." + DBConstants.KEY.FIRST_NAME + " || " + "' '" + " || " + "T1." + DBConstants.KEY.MIDDLE_NAME + " || " + "' '" + " || " + "T1." + DBConstants.KEY.LAST_NAME + " AS " + DBConstants.KEY.PRIMARY_CAREGIVER);
        columnList.add("T2." + DBConstants.KEY.FIRST_NAME + " || " + "' '" + " || " + "T2." + DBConstants.KEY.MIDDLE_NAME + " || " + "' '" + " || " + "T2." + DBConstants.KEY.LAST_NAME + " AS " + DBConstants.KEY.FAMILY_HEAD);
        columnList.add(Constants.TABLE_NAME.FAMILY + "." + DBConstants.KEY.FIRST_NAME + " as " + org.smartregister.chw.anc.util.DBConstants.KEY.FAMILY_NAME);
        columnList.add(Tables.TB + "." + Key.PLACE_OF_DOMICILE);
        columnList.add(Tables.TB + "." + Key.COMMUNITY_CLIENT_TB_REGISTRATION_NUMBER);
        columnList.add(Tables.TB + "." + Key.CLIENT_TB_STATUS_DURING_REGISTRATION);
        columnList.add(Tables.TB + "." + Key.CLIENT_TB_STATUS_AFTER_TESTING);

        return columnList.toArray(new String[columnList.size()]);
    }
}