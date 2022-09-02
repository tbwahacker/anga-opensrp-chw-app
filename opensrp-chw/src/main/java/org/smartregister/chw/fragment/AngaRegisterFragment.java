package org.smartregister.chw.fragment;

import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.smartregister.chw.activity.AngaProfileActivity;
import org.smartregister.chw.activity.MalariaFollowUpVisitActivity;
import org.smartregister.chw.core.fragment.CoreAngaRegisterFragment;
import org.smartregister.chw.core.model.CoreAngaRegisterFragmentModel;
import org.smartregister.chw.presenter.AngaRegisterFragmentPresenter;
import org.smartregister.chw.presenter.MalariaRegisterFragmentPresenter;
import org.smartregister.view.activity.BaseRegisterActivity;

public class AngaRegisterFragment extends CoreAngaRegisterFragment {

    @Override
    protected void initializePresenter() {
        if (getActivity() == null) {
            return;
        }
        String viewConfigurationIdentifier = ((BaseRegisterActivity) getActivity()).getViewIdentifiers().get(0);
        presenter = new AngaRegisterFragmentPresenter(this, new CoreAngaRegisterFragmentModel(), viewConfigurationIdentifier);
    }

    @Override
    protected void openProfile(String baseEntityId) {
        AngaProfileActivity.startAngaProfileActivity(getActivity(), baseEntityId);
    }

    @Override
    protected void openFollowUpVisit(String  baseEntityId) {
        MalariaFollowUpVisitActivity.startMalariaFollowUpActivity(getActivity(), baseEntityId);
    }

    @Override
    protected void toggleFilterSelection(View dueOnlyLayout) {
        super.toggleFilterSelection(dueOnlyLayout);
    }

    @Override
    protected String searchText() {
        return super.searchText();
    }

    @Override
    protected void dueFilter(View dueOnlyLayout) {
        super.dueFilter(dueOnlyLayout);
    }

    @Override
    protected void normalFilter(View dueOnlyLayout) {
        super.normalFilter(dueOnlyLayout);
    }
}
