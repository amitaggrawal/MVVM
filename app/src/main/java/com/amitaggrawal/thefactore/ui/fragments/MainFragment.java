package com.amitaggrawal.thefactore.ui.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.amitaggrawal.thefactore.R;
import com.amitaggrawal.thefactore.adapter.MyAdapter;
import com.amitaggrawal.thefactore.data.remote.ListMyEntry;
import com.amitaggrawal.thefactore.utilities.InjectorUtils;
import com.amitaggrawal.thefactore.utilities.SortingUtils;
import com.amitaggrawal.thefactore.viewmodel.MainFragmentViewModel;
import com.amitaggrawal.thefactore.viewmodel.MainFragmentViewModelFactory;

import java.util.List;

public class MainFragment extends Fragment {

    private MainFragmentViewModel mViewModel;
    private Context mContext;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private List<ListMyEntry> mData;
    private Button btn;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mProgressBar = getView().findViewById(R.id.prgressBar);
        mRecyclerView = getView().findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new MyAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);

        MainFragmentViewModelFactory factory = InjectorUtils.provideMainActivityViewModelFactory(mContext);
        mViewModel = ViewModelProviders.of(this, factory).get(MainFragmentViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();

        mViewModel.getData().observeForever(myEntities -> {

            mAdapter.refreshData(myEntities);
            mData = myEntities;

            Toast.makeText(mContext, "data: " + myEntities.size(), Toast.LENGTH_SHORT).show();
            if (null != myEntities && myEntities.size() > 0) showData();
            else showLoading();
        });
    }

    private void showLoading() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);

    }

    private void showData() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_first:
                sortByNew();
                break;
            case R.id.progress_first:
                sortByProgress();
                break;
            case R.id.completed_first:
                sortByCompleted();
                break;
            default:
                super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void sortByCompleted() {
        mData = SortingUtils.sortByCompleted(mData);
        mAdapter.refreshData(mData);
    }

    private void sortByProgress() {
        //Collections.sort(mData, new MyComparator());
        mData = SortingUtils.sortByProgress(mData);
        mAdapter.refreshData(mData);
    }

    private void sortByNew() {
        mData = SortingUtils.sortByNew(mData);
        mAdapter.refreshData(mData);
    }
}
