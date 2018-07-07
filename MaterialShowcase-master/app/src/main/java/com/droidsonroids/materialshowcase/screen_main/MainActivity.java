package com.droidsonroids.materialshowcase.screen_main;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.droidsonroids.materialshowcase.R;
import com.droidsonroids.materialshowcase.data.entities.GithubRepo;
import com.droidsonroids.materialshowcase.screen_contact.ContactActivity;

import java.util.List;

import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends Activity implements MainView, AppBarLayout.OnOffsetChangedListener {

	private MainPresenter mainPresenter;
	private MainActivityRecyclerAdapter adapter;

	@Bind(R.id.sample_list) RecyclerView mRecyclerView;
	@Bind(R.id.activity_main_appbar) AppBarLayout mAppBarLayout;
	@Bind(R.id.fab) FloatingActionButton mFab;

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		adapter = new MainActivityRecyclerAdapter(this);
		mRecyclerView.setHasFixedSize(true);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.setAdapter(adapter);
		mainPresenter = new MainPresenterImpl(this);
		mainPresenter.subscribe();
		mainPresenter.loadData();
	}

	@Override
	protected void onDestroy() {
		mainPresenter.unsubscribe();
		ButterKnife.unbind(this);
		super.onDestroy();
	}

	@Override
	public void loadData(List<GithubRepo> githubRepoList) {
		adapter.setGithubRepoList(githubRepoList);
	}

	@Override
	public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
		int maxScroll = mAppBarLayout.getTotalScrollRange();
		float percentage = (float) Math.abs(i) / (float)maxScroll;
		//TODO: C'mon Android Team, do the magic here!
	}

	@OnClick(R.id.fab)
	public void onFabClicked() {
		startContactActivity();
	}

	private void startContactActivity() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			ActivityOptions options =
					ActivityOptions.makeSceneTransitionAnimation(this, mFab, mFab.getTransitionName());
			startActivity(new Intent(this, ContactActivity.class), options.toBundle());
		} else {
			startActivity(new Intent(this, ContactActivity.class));
		}
	}
}
