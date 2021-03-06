package chillout.hackaton.tablefootballclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import chillout.hackaton.tablefootballclient.R;
import chillout.hackaton.tablefootballclient.fragment.CreateMatchFragment;
import chillout.hackaton.tablefootballclient.fragment.InvitationFragment;
import chillout.hackaton.tablefootballclient.fragment.MatchesListFragment;
import chillout.hackaton.tablefootballclient.fragment.NoInvitationFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Long id;

    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new CreateMatchFragment();
                moveToFragment(fragment,false);
//                Snackbar.make(view, "Open Create match", Snackbar.LENGTH_LONG)
//                       .show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initFragments();
    }

    private void initFragments() {
        Fragment noInvit = new NoInvitationFragment();
        moveToFragment(noInvit,true);

        Fragment invitation = new InvitationFragment();
        moveToFragment(invitation,false);


    }

    private void moveToFragment(Fragment fragment, Boolean isFirstFragnent) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content, fragment);
        if(!isFirstFragnent){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        MenuItem item= menu.findItem(R.menu.main);
//        item.setVisible(false);
//        super.onPrepareOptionsMenu(menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //TODO
        if (id == R.id.nav_matches) {
            Fragment fragment = new MatchesListFragment();
            moveToFragment(fragment, false);
        } else if (id == R.id.nav_invitations) {
        //TODO cos tam pobranie zaproszen
//        } else if (id == R.id.nav_manage_account) {

        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        setTitle(item.getTitle());
        return true;
    }

    public void setFabVisibility(int visibility){
        fab.setVisibility(visibility);
    }

//
//
//    public class GetInvitationAsyncTask extends AsyncTask<Void, Void, List<MatchInfo>> {
//
//        @Override
//        protected List<MatchInfo> doInBackground(Void... params) {
//            TableFootballService apiService = ApiClient.instance();
//            Call<List<MatchResponse>> call = apiService.getAllMatches(TFCApplication.getTOKEN());
//
//            try {
//                Response<List<MatchResponse>> response = call.execute();
//                if(response.isSuccessful()){
//                    List<MatchResponse> responseList = response.body();
//
//                    List<MatchInfo> resultlList = new ArrayList<>();
//                    for (MatchResponse resp : responseList) {
//                        resultlList.add(MatchInfo.mapMatchResponse(resp));
//                    }
//                    return resultlList;
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(final List<MatchInfo> list) {
//            getMatchesTask = null;
//
//            if (list != null) {
//                showProgress(false);
//                mRvAdapter.setMatchInfoList(list);
//            } else {
//                Snackbar.make(getActivity().findViewById(R.id.drawer_layout), "Connection Error", Snackbar.LENGTH_LONG)
//                        .setAction("Retry", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                getMatches();
//                            }
//                        }).show();
//            }
//        }
//
//        @Override
//        protected void onCancelled() {
//            getMatchesTask = null;
//            showProgress(false);
//        }
}
