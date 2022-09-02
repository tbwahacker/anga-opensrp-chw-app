package org.smartregister.chw.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.vijay.jsonwizard.activities.JsonFormActivity;
import com.vijay.jsonwizard.activities.JsonWizardFormActivity;
import com.vijay.jsonwizard.constants.JsonFormConstants;
import com.vijay.jsonwizard.domain.Form;
import com.vijay.jsonwizard.factory.FileSourceFactoryHelper;
import com.vijay.jsonwizard.utils.FormUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.smartregister.chw.R;
import org.smartregister.chw.anc.util.Constants;
import org.smartregister.chw.anc.util.NCUtils;
import org.smartregister.chw.dao.AngaViewDao;
import org.smartregister.clientandeventmodel.Event;
import org.smartregister.repository.AllSharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class AngaFormActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_GET_JSON = 1234;
    String jsonToString;
    String baseEntityId;
    AppCompatButton gotoform,viewdatabase;
    ListView listView ;
    ArrayAdapter<String> adapter;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anga_form);

        gotoform = findViewById(R.id.gotoform);
        viewdatabase = findViewById(R.id.viewdatabase);
        listView = findViewById(R.id.listview);
        baseEntityId = getIntent().getStringExtra("basekey");
       list = new ArrayList<>();

        gotoform.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startForm(REQUEST_CODE_GET_JSON, "condom_distribution", null, true);
             }
         });

        viewdatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             populateToListView();

            }
        });


    }

    private void populateToListView() {
        list.clear();
        for (int i =0; i<AngaViewDao.RetrieveAllRows(baseEntityId).size(); i++){
            list.add("Condom brand : "+AngaViewDao.RetrieveAllRows(baseEntityId).get(i).getCondom_brand()
                    +" \n Condom type : "+  AngaViewDao.RetrieveAllRows(baseEntityId).get(i).getCondom_type()
                    +" \n No condom issued : "+  AngaViewDao.RetrieveAllRows(baseEntityId).get(i).getNo_condom_issued()+"\n");
        }
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            jsonToString = data.getStringExtra("json");
            Log.i(getClass().getName(), "Result json String !!!! " + jsonToString);
            saveFormTODatabase();
            populateToListView();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void saveFormTODatabase() {
        AllSharedPreferences allSharedPreferences = org.smartregister.util.Utils.getAllSharedPreferences();
        Event baseEvent = org.smartregister.chw.anc.util.JsonFormUtils.processJsonForm(allSharedPreferences, jsonToString, "condom_distribution"); //table must be similar to the classification
        baseEvent.setBaseEntityId(baseEntityId);
        try {
            NCUtils.processEvent(baseEvent.getBaseEntityId(), new JSONObject(org.smartregister.chw.anc.util.JsonFormUtils.gson.toJson(baseEvent)));
        } catch (Exception e) {
            Log.d("error2",""+e.getMessage());
        }
    }


    public void startForm(int jsonFormActivityRequestCode, String formName, String entityId, boolean translate)  {

        final String STEP1 = "step1";
        final String FIELDS = "fields";
        final String KEY = "key";
        final String ZEIR_ID = "ZEIR_ID";
        final String VALUE = "value";

        String currentLocationId = "Kenya";

        JSONObject jsonForm = null;
        try {
            jsonForm = FileSourceFactoryHelper.getFileSource("").getFormFromFile(getApplicationContext(), formName);
            if (jsonForm != null) {
                jsonForm.getJSONObject("metadata").put("encounter_location", currentLocationId);
                Intent intent = new Intent(this, JsonFormActivity.class);
                intent.putExtra("json", jsonForm.toString());
                Log.d(getClass().getName(), "form is " + jsonForm.toString());
                Form form = new Form();
                form.setName(getString(R.string.condomdistribution));
                form.setWizard(true);
                form.setActionBarBackground(R.color.accent);
                form.setNavigationBackground(R.color.primary);
                form.setHideSaveLabel(true);
//                form.setNextLabel(getString(R.string.next));
//                form.setPreviousLabel(getString(R.string.previous));
                form.setSaveLabel(getString(R.string.save));
                form.setBackIcon(R.drawable.ic_icon_positive);
                intent.putExtra(JsonFormConstants.JSON_FORM_KEY.FORM, form);

                startActivityForResult(intent, jsonFormActivityRequestCode);
            }
        } catch (Exception e) {
            Log.d("error1",""+e.getMessage());
        }


    }
}