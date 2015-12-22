package com.community.tsinghua;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.community.tsinghua.R;
import com.community.tsinghua.fragment.FloatingButtonFragment;

/**
 * Created by LG on 2015-12-15.
 */
public class Notice1Fragment extends Fragment implements View.OnClickListener {



    public static Notice1Fragment newInstance() {
        Notice1Fragment fragment = new Notice1Fragment();
        return fragment;
    }

    public Notice1Fragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.notice1, container, false);
        ButterKnife.inject(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.fab)
    public void WriteNewNotice() {
        //클릭시 이벤트처리
    }

    @Override
    public void onClick(View v) { }
}
