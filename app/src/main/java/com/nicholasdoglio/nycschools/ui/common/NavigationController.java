package com.nicholasdoglio.nycschools.ui.common;

import android.support.v4.app.FragmentManager;

import com.nicholasdoglio.nycschools.R;
import com.nicholasdoglio.nycschools.ui.MainActivity;
import com.nicholasdoglio.nycschools.ui.detail.SchoolDetailFragment;
import com.nicholasdoglio.nycschools.ui.list.SchoolListFragment;

import javax.inject.Inject;

/**
 * @author Nicholas Doglio
 * <p>
 * Abastraction of navigation layer
 * Handles all fragment manager navigation
 */
public class NavigationController {
    private final int containerId;
    private final FragmentManager fragmentManager;

    @Inject
    public NavigationController(MainActivity mainActivity) {
        this.containerId = R.id.fragment_container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }

    /**
     * Opens up to the List Fragment
     */
    public void openListFragment() {
        SchoolListFragment schoolListFragment = new SchoolListFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, schoolListFragment)
                .setReorderingAllowed(true)
                .commit();
    }

    /**
     * Opens up to the Detail Fragment
     *
     * @param dbn:        Unique id assigned to each school used to query SAT scores
     * @param schoolName: Name of school passed in case SAT query comes back invalid
     */
    public void openDetailFragment(String dbn, String schoolName) {
        SchoolDetailFragment schoolDetailFragment = SchoolDetailFragment.newInstance(dbn, schoolName);
        fragmentManager.beginTransaction()
                .replace(containerId, schoolDetailFragment)
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit();
    }
}