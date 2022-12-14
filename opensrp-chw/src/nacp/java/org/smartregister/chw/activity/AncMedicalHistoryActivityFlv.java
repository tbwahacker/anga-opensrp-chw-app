package org.smartregister.chw.activity;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import org.smartregister.chw.R;
import org.smartregister.chw.core.activity.DefaultAncMedicalHistoryActivityFlv;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class AncMedicalHistoryActivityFlv extends DefaultAncMedicalHistoryActivityFlv {

    @Override
    protected void processAncCard(String has_card, Context context) {
        // super.processAncCard(has_card, context);
        linearLayoutAncCard.setVisibility(View.GONE);
    }

    @Override
    protected void processHealthFacilityVisit(List<Map<String, String>> hf_visits, Context context) {
        //super.processHealthFacilityVisit(hf_visits, context);

        if (hf_visits != null && hf_visits.size() > 0) {
            linearLayoutHealthFacilityVisit.setVisibility(View.VISIBLE);

            int x = 1;
            for (Map<String, String> vals : hf_visits) {
                View view = inflater.inflate(R.layout.medial_history_anc_visit, null);

                TextView tvTitle = view.findViewById(R.id.title);
                TextView tvTests = view.findViewById(R.id.tests);
                tvTests.setVisibility(View.GONE);

                view.findViewById(R.id.weight).setVisibility(View.GONE);
                view.findViewById(R.id.bp).setVisibility(View.GONE);
                view.findViewById(R.id.hb).setVisibility(View.GONE);
                view.findViewById(R.id.ifa_received).setVisibility(View.GONE);


                tvTitle.setText(MessageFormat.format(context.getString(R.string.anc_visit_date), (x), vals.get("anc_hf_visit_date")));

                linearLayoutHealthFacilityVisitDetails.addView(view, 0);

                x++;
            }
        }
    }
}
