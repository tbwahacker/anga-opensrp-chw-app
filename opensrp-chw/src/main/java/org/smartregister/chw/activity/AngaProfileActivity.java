package org.smartregister.chw.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import org.smartregister.chw.R;
import org.smartregister.chw.core.activity.CoreAngaProfileActivity;
import org.smartregister.chw.tb.util.Constants;

public class AngaProfileActivity extends CoreAngaProfileActivity {

    public static void startAngaProfileActivity(Activity activity, String baseEntityID)  {
        Intent intent = new Intent(activity, AngaProfileActivity.class);
        intent.putExtra(Constants.ActivityPayload.BASE_ENTITY_ID, baseEntityID);
        activity.startActivity(intent);
    }

    @Override
    protected void removeMember() {
        IndividualProfileRemoveActivity.startIndividualProfileActivity(
                AngaProfileActivity.this, getClientDetailsByBaseEntityID(getMemberObject().getBaseEntityId()),
                getMemberObject().getFamilyBaseEntityId(), getMemberObject().getFamilyHead(), getMemberObject().getPrimaryCareGiver(),
                AngaProfileActivity.class.getCanonicalName());
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
       if (view.getId()==R.id.textview_record_malaria){
           AngaFollowUpVisitActivity.startAngaFollowUpActivity(this, memberObject.getBaseEntityId());
       }
    }

    @Override
    protected void startAngaCaseClosure() {

    }

    @Override
    public void verifyHasPhone() {

    }

    @Override
    public void notifyHasPhone(boolean b) {

    }
}

