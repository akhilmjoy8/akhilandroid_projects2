package com.droidsonroids.materialshowcase.screen_contact;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.droidsonroids.materialshowcase.R;
import com.droidsonroids.materialshowcase.screen_details.OnRevealAnimationListener;
import com.droidsonroids.materialshowcase.utils.GUIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ContactActivity extends Activity {

	@Bind(R.id.activity_contact_rl_container) RelativeLayout mRlContainer;
	@Bind(R.id.activity_contact_fab)
	FloatingActionButton mFab;
	@Bind(R.id.activity_contact_ll_container) LinearLayout mLlContainer;
	@Bind(R.id.activity_contact_iv_close) ImageView mIvClose;

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		ButterKnife.bind(this);
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			setupEnterAnimation();
			setupExitAnimation();
		} else {
			initViews();
		}

	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	private void setupEnterAnimation() {
		Transition transition = TransitionInflater.from(this)
			.inflateTransition(R.transition.changebounds_with_arcmotion);
		getWindow().setSharedElementEnterTransition(transition);
		transition.addListener(new Transition.TransitionListener() {
			@Override
			public void onTransitionStart(Transition transition) {

			}

			@Override
			public void onTransitionEnd(Transition transition) {
				transition.removeListener(this);
				animateRevealShow(mRlContainer);
			}

			@Override
			public void onTransitionCancel(Transition transition) {

			}

			@Override
			public void onTransitionPause(Transition transition) {

			}

			@Override
			public void onTransitionResume(Transition transition) {

			}
		});
	}

	private void animateRevealShow(final View viewRoot) {
		int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
		int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
		GUIUtils.animateRevealShow(this, mRlContainer, mFab.getWidth() / 2, R.color.accent_color,
				cx, cy, new OnRevealAnimationListener() {
					@Override
					public void onRevealHide() {

					}

					@Override
					public void onRevealShow() {
						initViews();
					}
				});
	}

	private void initViews() {
		new Handler(Looper.getMainLooper()).post(() -> {
			Animation animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
			animation.setDuration(300);
			mLlContainer.startAnimation(animation);
			mIvClose.startAnimation(animation);
			mLlContainer.setVisibility(View.VISIBLE);
			mIvClose.setVisibility(View.VISIBLE);
		});
	}

	@Override
	public void onBackPressed() {
		GUIUtils.animateRevealHide(this, mRlContainer, R.color.accent_color, mFab.getWidth() / 2,
				new OnRevealAnimationListener() {
					@Override
					public void onRevealHide() {
						backPressed();
					}

					@Override
					public void onRevealShow() {

					}
				});
	}

	@OnClick(R.id.activity_contact_iv_close)
	public void onIvCloseClicked() {
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			onBackPressed();
		} else {
			backPressed();
		}
	}

	private void backPressed() {
		super.onBackPressed();
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	private void setupExitAnimation() {
		Fade fade = new Fade();
		getWindow().setReturnTransition(fade);
		fade.setDuration(getResources().getInteger(R.integer.animation_duration));
	}
}

