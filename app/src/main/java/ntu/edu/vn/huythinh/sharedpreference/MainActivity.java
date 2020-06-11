package ntu.edu.vn.huythinh.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String sharedRef = "shared_ref";
    public static final String keyName = "Name";
    public static final String keyDoB = "Birthday";
    public static final String keyPhone = "Number";
    public static final String keyMale = "Male";
    public static final String keyFemale = "Female";
    EditText edtName, edtDoB, edtPhone;
    RadioButton rdbMale, rdbFemale;
    Button btnSave, btnLoad, btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView();
    }

    public void addView(){
        edtName = findViewById(R.id.edtName);
        edtDoB = findViewById(R.id.edtDoB);
        edtPhone = findViewById(R.id.edtPhone);
        rdbMale = findViewById(R.id.rdbMale);
        rdbFemale = findViewById(R.id.rdbFemale);
        btnSave = findViewById(R.id.btnSave);
        btnLoad = findViewById(R.id.btnLoad);
        btnDelete = findViewById(R.id.btnDelete);

        btnSave.setOnClickListener(this);
        btnLoad.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnSave: Save(); break;
            case R.id.btnLoad: Load(); break;
            case R.id.btnDelete: Delete(); break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        getSharedPreferences(sharedRef, MODE_PRIVATE);
        Load();
    }

    private void Save(){
        SharedPreferences sharedPreferences = getSharedPreferences(sharedRef, MODE_PRIVATE);
        if(sharedPreferences != null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(keyName, edtName.getText().toString());
            editor.putString(keyDoB, edtDoB.getText().toString());
            editor.putString(keyPhone, edtPhone.getText().toString());
            editor.putBoolean(keyMale, rdbMale.isChecked());
            editor.putBoolean(keyFemale, rdbFemale.isChecked());
            editor.commit();
        }
    }
    private void Load(){
        SharedPreferences sharedPreferences = getSharedPreferences(sharedRef, MODE_PRIVATE);
        if(sharedPreferences != null){
            String name = sharedPreferences.getString(keyName, "Unknown");
            String dob = sharedPreferences.getString(keyDoB, "Unknown");
            String phone = sharedPreferences.getString(keyPhone, "Unknown");
            Boolean male = sharedPreferences.getBoolean(keyMale, true);
            Boolean female = sharedPreferences.getBoolean(keyFemale, false);

            edtName.setText(name);
            edtDoB.setText(dob);
            edtPhone.setText(phone);
            rdbMale.setChecked(male);
            rdbFemale.setChecked(female);
        }
    }
    private void Delete(){
        edtName.setText("");
        edtDoB.setText("");
        edtPhone.setText("");
        rdbMale.setChecked(true);
        rdbFemale.setChecked(false);
    }
}
