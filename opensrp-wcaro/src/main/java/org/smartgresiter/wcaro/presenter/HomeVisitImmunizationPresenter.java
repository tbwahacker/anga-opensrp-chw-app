package org.smartgresiter.wcaro.presenter;

import org.smartgresiter.wcaro.contract.HomeVisitGrowthNutritionContract;
import org.smartgresiter.wcaro.contract.HomeVisitImmunizationContract;
import org.smartgresiter.wcaro.fragment.GrowthNutritionInputFragment;
import org.smartgresiter.wcaro.interactor.HomeVisitGrowthNutritionInteractor;
import org.smartgresiter.wcaro.interactor.HomeVisitImmunizationInteractor;
import org.smartgresiter.wcaro.util.HomeVisitVaccineGroupDetails;
import org.smartgresiter.wcaro.util.ImmunizationState;
import org.smartregister.commonregistry.CommonPersonObjectClient;
import org.smartregister.domain.Alert;
import org.smartregister.immunization.ImmunizationLibrary;
import org.smartregister.immunization.db.VaccineRepo;
import org.smartregister.immunization.domain.ServiceSchedule;
import org.smartregister.immunization.domain.ServiceWrapper;
import org.smartregister.immunization.domain.Vaccine;
import org.smartregister.immunization.domain.VaccineWrapper;
import org.smartregister.immunization.repository.RecurringServiceRecordRepository;
import org.smartregister.util.Utils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HomeVisitImmunizationPresenter implements HomeVisitImmunizationContract.Presenter, HomeVisitImmunizationContract.InteractorCallBack {


    HomeVisitImmunizationInteractor homeVisitImmunizationInteractor;
    private WeakReference<HomeVisitImmunizationContract.View> view;
    ArrayList<VaccineRepo.Vaccine> vaccinesDueFromLastVisit = new ArrayList<VaccineRepo.Vaccine>();
    private ArrayList<HomeVisitVaccineGroupDetails> allgroups = new ArrayList<HomeVisitVaccineGroupDetails>();
    private ArrayList<VaccineWrapper> notGivenVaccines = new ArrayList<VaccineWrapper>();
    private HomeVisitVaccineGroupDetails currentActiveGroup;


    public HomeVisitImmunizationPresenter(HomeVisitImmunizationContract.View view){
        this.view = new WeakReference<>(view);
        homeVisitImmunizationInteractor = new HomeVisitImmunizationInteractor();
    }

    public void createAllVaccineGroups(List<Alert> alerts, List<Vaccine> vaccines){
        allgroups = homeVisitImmunizationInteractor.determineAllHomeVisitVaccineGroupDetails(alerts,vaccines,notGivenVaccines);
    }

    public void getVaccinesNotGivenLastVisit(){
        if(homeVisitImmunizationInteractor.hasVaccinesNotGivenSinceLastVisit(allgroups)){
            vaccinesDueFromLastVisit =  homeVisitImmunizationInteractor.getNotGivenVaccinesLastVisitList(allgroups);
        }
    }

    public void setCurrentActiveGroup(){
        currentActiveGroup = homeVisitImmunizationInteractor.getCurrentActiveHomeVisitVaccineGroupDetail(allgroups);
        if(currentActiveGroup == null){
            currentActiveGroup = homeVisitImmunizationInteractor.getLastActiveHomeVisitVaccineGroupDetail(allgroups);
        }
    }

    @Override
    public HomeVisitImmunizationContract.View getView() {
        if (view != null) {
            return view.get();
        } else {
            return null;
        }
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {

    }

}