package org.smartregister.chw.activity;

import static org.smartregister.chw.core.utils.CoreConstants.JSON_FORM.getMalariaFollowUpVisitForm;

import android.app.Activity;
import android.content.Intent;

//import static org.smartregister.chw.core.utils.CoreConstants.JSON_FORM.getMalariaFollowUpVisitForm;

public class AngaFollowUpVisitActivity extends AngaRegisterActivity {

    public static void startAngaFollowUpActivity(Activity activity, String baseEntityID) {
//        Intent intent = new Intent(activity, AngaRegisterActivity.class);
        Intent intent = new Intent(activity, AngaFollowUpVisitActivity.class);
        intent.putExtra(org.smartregister.chw.anga.util.Constants.ACTIVITY_PAYLOAD.BASE_ENTITY_ID, baseEntityID);
        intent.putExtra(org.smartregister.chw.anga.util.Constants.ACTIVITY_PAYLOAD.MALARIA_FORM_NAME, getMalariaFollowUpVisitForm());
        intent.putExtra(org.smartregister.chw.anga.util.Constants.ACTIVITY_PAYLOAD.ACTION, org.smartregister.chw.anga.util.Constants.ACTIVITY_PAYLOAD_TYPE.FOLLOW_UP_VISIT);
        activity.startActivity(intent);
    }

}