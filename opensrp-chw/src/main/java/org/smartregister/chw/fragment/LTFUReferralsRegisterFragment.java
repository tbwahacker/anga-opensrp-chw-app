package org.smartregister.chw.fragment;

import android.os.Handler;

import org.smartregister.chw.R;
import org.smartregister.chw.activity.LTFUReferralsDetailsViewActivity;
import org.smartregister.chw.application.ChwApplication;
import org.smartregister.chw.core.fragment.BaseReferralRegisterFragment;
import org.smartregister.chw.core.utils.CoreConstants;
import org.smartregister.chw.core.utils.Utils;
import org.smartregister.chw.presenter.LTFUReferralFragmentPresenter;
import org.smartregister.chw.provider.LTFURegisterProvider;
import org.smartregister.commonregistry.CommonPersonObjectClient;
import org.smartregister.configurableviews.model.View;
import org.smartregister.cursoradapter.RecyclerViewPaginatedAdapter;
import org.smartregister.domain.Task;
import org.smartregister.family.util.DBConstants;

import java.util.Set;

public class LTFUReferralsRegisterFragment extends BaseReferralRegisterFragment {
    public Handler handler = new Handler();
    private LTFUReferralFragmentPresenter referralFragmentPresenter;
    private CommonPersonObjectClient commonPersonObjectClient;

    @Override
    public void initializeAdapter(Set<View> visibleColumns, String tableName) {
        LTFURegisterProvider registerProvider = new LTFURegisterProvider(getActivity(), registerActionHandler, paginationViewHandler);
        clientAdapter = new RecyclerViewPaginatedAdapter(null, registerProvider, context().commonrepository(tablename));
        clientAdapter.setCurrentlimit(20);
        clientsView.setAdapter(clientAdapter);
    }

    @Override
    public void setClient(CommonPersonObjectClient commonPersonObjectClient) {
        setCommonPersonObjectClient(commonPersonObjectClient);
    }

    @Override
    protected int getToolBarTitle() {
        return R.string.menu_ltfu;
    }

    @Override
    protected String getMainCondition() {
        return "task.business_status = '" + CoreConstants.BUSINESS_STATUS.REFERRED + "' and ec_family_member_search.date_removed is null";
    }

    @Override
    public CommonPersonObjectClient getCommonPersonObjectClient() {
        return commonPersonObjectClient;
    }

    @Override
    public void setCommonPersonObjectClient(CommonPersonObjectClient commonPersonObjectClient) {
        this.commonPersonObjectClient = commonPersonObjectClient;
    }

    @Override
    protected void initializePresenter() {
        referralFragmentPresenter = new LTFUReferralFragmentPresenter(this);
        presenter = referralFragmentPresenter;

    }

    @Override
    protected void onViewClicked(android.view.View view) {
        CommonPersonObjectClient client = (CommonPersonObjectClient) view.getTag();
        referralFragmentPresenter.setBaseEntityId(Utils.getValue(client.getColumnmaps(), DBConstants.KEY.BASE_ENTITY_ID, false));
        referralFragmentPresenter.fetchClient();

        Task task = getTask(Utils.getValue(client.getColumnmaps(), "_id", false));
        referralFragmentPresenter.setTasksFocus(task.getFocus());
        goToReferralsDetails(client);

    }

    private Task getTask(String taskId) {
        return ChwApplication.getInstance().getTaskRepository().getTaskByIdentifier(taskId);
    }

    private void goToReferralsDetails(CommonPersonObjectClient client) {
        handler.postDelayed(() -> LTFUReferralsDetailsViewActivity.startLTFUReferralsDetailsViewActivity(getActivity(), client, getTask(Utils.getValue(client.getColumnmaps(), "_id", false)), CoreConstants.REGISTERED_ACTIVITIES.LTFU_REFERRALS_REGISTER_ACTIVITY), 100);
    }

}
