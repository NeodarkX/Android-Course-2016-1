package pe.com.neodark.reciclercardview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import models.PeopleAdapter;
import models.Person;

/**
 * Created by root on 10/05/16.
 */
public class CatalogActivity extends AppCompatActivity {
    private ArrayList<Person> people;
    private RecyclerView mPeopleRecyclerView;
    private RecyclerView.Adapter mPeopleAdapter;
    private RecyclerView.LayoutManager mPeopleLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        people = new ArrayList<>();
        initializeData();
        mPeopleRecyclerView = (RecyclerView) findViewById(R.id.peopleRecyclerView);
        mPeopleRecyclerView.setHasFixedSize(true);
        mPeopleLayoutManager = new LinearLayoutManager(this);
        mPeopleRecyclerView.setLayoutManager(mPeopleLayoutManager);
        mPeopleAdapter = new PeopleAdapter(people);
        mPeopleRecyclerView.setAdapter(mPeopleAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initializeData() {
        people = new ArrayList<>();
        people.add(new Person("Steve", "Jobs", Integer.toString(R.mipmap.ic_jobs_launcher)));
        people.add(new Person("Steve", "Wozniak", Integer.toString(R.mipmap.ic_wozniak_launcher)));


    }


}
