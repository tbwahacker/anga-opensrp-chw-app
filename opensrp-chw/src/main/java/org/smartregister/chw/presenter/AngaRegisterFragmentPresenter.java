package org.smartregister.chw.presenter;

//import org.smartregister.chw.core.presenter.CoreMalariaRegisterFragmentPresenter;
//import org.smartregister.chw.malaria.contract.MalariaRegisterFragmentContract;

import org.smartregister.chw.anga.contract.AngaRegisterFragmentContract;
import org.smartregister.chw.core.presenter.CoreAngaRegisterFragmentPresenter;

public class AngaRegisterFragmentPresenter extends CoreAngaRegisterFragmentPresenter {

    public AngaRegisterFragmentPresenter(AngaRegisterFragmentContract.View view,
                                         AngaRegisterFragmentContract.Model model, String viewConfigurationIdentifier) {
        super(view, model, viewConfigurationIdentifier);
    }

}
