package org.smartregister.chw.model;

import static org.smartregister.AllConstants.TEAM_ROLE_IDENTIFIER;

import org.smartregister.chw.BuildConfig;
import org.smartregister.chw.R;
import org.smartregister.chw.core.model.NavigationModel;
import org.smartregister.chw.core.model.NavigationOption;
import org.smartregister.chw.core.utils.CoreConstants;
import org.smartregister.chw.util.Constants;
import org.smartregister.repository.AllSharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NavigationModelFlv implements NavigationModel.Flavor {

    private static List<NavigationOption> navigationOptions = new ArrayList<>();

    @Override
    public List<NavigationOption> getNavigationItems() {
        if (navigationOptions.size() == 0) {
            NavigationOption op1 = new NavigationOption(R.mipmap.sidemenu_families, R.mipmap.sidemenu_families_active, R.string.menu_all_families, Constants.DrawerMenu.ALL_FAMILIES, 0);
            NavigationOption op2 = new NavigationOption(R.mipmap.sidemenu_children, R.mipmap.sidemenu_children_active, R.string.menu_child_clients, Constants.DrawerMenu.CHILD_CLIENTS, 0);

            NavigationOption op3 = new NavigationOption(R.mipmap.sidemenu_anc, R.mipmap.sidemenu_anc_active, R.string.menu_anc, Constants.DrawerMenu.ANC, 0);
            NavigationOption op5 = new NavigationOption(R.mipmap.sidemenu_pnc, R.mipmap.sidemenu_pnc_active, R.string.menu_pnc, Constants.DrawerMenu.PNC, 0);
            NavigationOption op6 = new NavigationOption(R.mipmap.sidemenu_fp, R.mipmap.sidemenu_fp_active, R.string.menu_family_planing, Constants.DrawerMenu.FAMILY_PLANNING, 0);
            NavigationOption op7 = new NavigationOption(R.mipmap.sidemenu_malaria, R.mipmap.sidemenu_malaria_active, R.string.menu_malaria, Constants.DrawerMenu.MALARIA, 0);
            NavigationOption op8 = new NavigationOption(R.mipmap.sidemenu_referrals, R.mipmap.sidemenu_referrals_active, R.string.menu_referrals, Constants.DrawerMenu.REFERRALS, 0);
            NavigationOption op9 = new NavigationOption(R.mipmap.sidemenu_updates, R.mipmap.sidemenu_updates_active, R.string.updates, CoreConstants.DrawerMenu.UPDATES, 0);
            NavigationOption op10 = new NavigationOption(R.drawable.sidemenu_all_clients, R.drawable.sidemenu_all_clients_active, R.string.menu_all_clients, CoreConstants.DrawerMenu.ALL_CLIENTS, 0);
            NavigationOption op11 = new NavigationOption(R.mipmap.sidemenu_hiv, R.mipmap.sidemenu_hiv_active, R.string.menu_hiv, CoreConstants.DrawerMenu.CBHS_CLIENTS, 0);
            NavigationOption op12 = new NavigationOption(R.mipmap.sidemenu_hiv, R.mipmap.sidemenu_hiv_active, R.string.menu_hiv_index_contacts, CoreConstants.DrawerMenu.HIV_INDEX_CLIENTS, 0);
            NavigationOption op13 = new NavigationOption(R.drawable.sidemenu_pmtct, R.drawable.sidemenu_pmtct_active, R.string.mother_champion, CoreConstants.DrawerMenu.MOTHER_CHAMPION, 0);
            NavigationOption op14 = new NavigationOption(R.mipmap.sidemenu_tb, R.mipmap.sidemenu_tb_active, R.string.menu_tb, CoreConstants.DrawerMenu.TB_CLIENTS, 0);
            NavigationOption op15 = new NavigationOption(R.mipmap.sidemenu_referrals, R.mipmap.sidemenu_referrals_active, R.string.menu_ltfu, Constants.DrawerMenu.LTFU, 0);
            NavigationOption op16 = new NavigationOption(R.mipmap.sidemenu_pnc, R.mipmap.sidemenu_pnc_active, R.string.angamodule, Constants.DrawerMenu.ANGA, 0);


            if (BuildConfig.USE_UNIFIED_REFERRAL_APPROACH && BuildConfig.BUILD_FOR_BORESHA_AFYA_SOUTH) {
                AllSharedPreferences allSharedPreferences = org.smartregister.util.Utils.getAllSharedPreferences();
                String teamRoleIdentifier = allSharedPreferences.getPreferences().getString(TEAM_ROLE_IDENTIFIER, "");
                if (teamRoleIdentifier.equals("mother_champion")) {
                    navigationOptions.addAll(Arrays.asList(op10, op13, op8));
                } else if (teamRoleIdentifier.equals("cbhs_provider")) {
                    navigationOptions.addAll(Arrays.asList(op10, op11, op12, op8, op15));
                } else {
                    navigationOptions.addAll(Arrays.asList(op10, op1, op11, op12, op3, op5, op2, op13, op8, op15));
                }
            } else {
                navigationOptions.addAll(Arrays.asList(op1, op3, op5, op2, op6, op7));
                if (BuildConfig.USE_UNIFIED_REFERRAL_APPROACH)
                    navigationOptions.add(op8);
                navigationOptions.add(op9);
            }
            navigationOptions.add(op16);
        }

        return navigationOptions;
    }
}
