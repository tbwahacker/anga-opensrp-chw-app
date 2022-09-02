package org.smartregister.chw.activity;

import static org.smartregister.chw.core.utils.CoreConstants.JSON_FORM.getMalariaConfirmation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import org.jetbrains.annotations.NotNull;
import org.smartregister.chw.anga.activity.BaseAngaRegisterActivity;
import org.smartregister.chw.anga.fragment.BaseAngaRegisterFragment;
import org.smartregister.chw.anga.util.Constants;
import org.smartregister.chw.core.activity.CoreAngaRegisterActivity;
import org.smartregister.chw.core.fragment.CoreAngaRegisterFragment;
import org.smartregister.chw.core.utils.CoreConstants;
import org.smartregister.chw.fragment.AngaRegisterFragment;
import org.smartregister.family.util.JsonFormUtils;
import org.smartregister.helper.BottomNavigationHelper;
import org.smartregister.listener.BottomNavigationListener;
import org.smartregister.view.fragment.BaseRegisterFragment;

public class AngaRegisterActivity extends CoreAngaRegisterActivity {
//    public class AngaRegisterActivity extends AngaRe {
//    public static void startAngaFormActivity(Activity activity, String baseEntityID, String formName, String payloadType) {
//        Intent intent = new Intent(activity, AngaRegisterActivity.class);
//        intent.putExtra(org.smartregister.chw.anga.util.Constants.ACTIVITY_PAYLOAD.BASE_ENTITY_ID, baseEntityID);
//        intent.putExtra(org.smartregister.chw.anga.util.Constants.ACTIVITY_PAYLOAD.ACTION, payloadType);
//        intent.putExtra(Constants.ACTIVITY_PAYLOAD.MALARIA_FORM_NAME, formName);
//        activity.startActivityForResult(intent, JsonFormUtils.REQUEST_CODE_GET_JSON);
//    }


    public static void startAngaFormActivity(Activity activity, String baseEntityID, @Nullable String familyBaseEntityID) {
        Intent intent = new Intent(activity, AngaRegisterActivity.class);
        intent.putExtra(org.smartregister.chw.anga.util.Constants.ACTIVITY_PAYLOAD.BASE_ENTITY_ID, baseEntityID);
        intent.putExtra(org.smartregister.chw.anga.util.Constants.ACTIVITY_PAYLOAD.FAMILY_BASE_ENTITY_ID, familyBaseEntityID);
        intent.putExtra(org.smartregister.chw.anga.util.Constants.ACTIVITY_PAYLOAD.MALARIA_FORM_NAME, getMalariaConfirmation());
        intent.putExtra(org.smartregister.chw.anga.util.Constants.ACTIVITY_PAYLOAD.ACTION, org.smartregister.chw.anga.util.Constants.ACTIVITY_PAYLOAD_TYPE.REGISTRATION);
        activity.startActivity(intent);
    }

    @Override
    protected void registerBottomNavigation() {
        bottomNavigationHelper = new BottomNavigationHelper();
        bottomNavigationView = findViewById(org.smartregister.R.id.bottom_navigation);
        FamilyRegisterActivity.registerBottomNavigation(bottomNavigationHelper, bottomNavigationView, this);
    }

//    @Override
//    protected BaseRegisterFragment getRegisterFragment() {
//        return new BaseAngaRegisterFragment();
//    }

    @Override
    protected BaseRegisterFragment getRegisterFragment() {
        return new AngaRegisterFragment();
    }

//    @Override
//    protected BaseRegisterFragment getRegisterFragment() {
//        return new CoreAngaRegisterFragment() {
//            @Override
//            public void setupViews(View view) {
//                super.setupViews(view);
//            }
//
//            @Override
//            public void onResume() {
//                super.onResume();
//            }
//        };
//    }


}
 