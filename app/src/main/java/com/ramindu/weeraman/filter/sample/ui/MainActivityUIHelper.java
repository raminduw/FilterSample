package com.ramindu.weeraman.filter.sample.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ramindu.weeraman.filter.sample.R;


public class MainActivityUIHelper {

    private FilterDialogActionListener filterDialogActionListener;

    public MainActivityUIHelper(FilterDialogActionListener filterDialogActionListener) {
        this.filterDialogActionListener = filterDialogActionListener ;
    }

    public void revealShow(View dialogView, FloatingActionButton filterFabButton, boolean isShow,
                           boolean filterApply, final Dialog dialog) {
        final View view = dialogView.findViewById(R.id.dialog);
        int w = view.getWidth();
        int h = view.getHeight();
        int endRadius = (int) Math.hypot(w, h);

        int cx = (int) (filterFabButton.getX() + (filterFabButton.getWidth() / 2));
        int cy = (int) (filterFabButton.getY()) + filterFabButton.getHeight() + 56;
        if (isShow) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, endRadius);
            view.setVisibility(View.VISIBLE);
            revealAnimator.setDuration(300);
            revealAnimator.start();

        } else {
            Animator anim =
                    ViewAnimationUtils.createCircularReveal(view, cx, cy, endRadius, 0);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    dialog.dismiss();
                    view.setVisibility(View.INVISIBLE);
                    if (filterApply) {
                        filterDialogActionListener.onFilterApply();
                    }
                }
            });
            anim.setDuration(300);
            anim.start();
        }
    }

    public interface FilterDialogActionListener{
        void onFilterApply();
    }
}
