package com.hd.ibrarybase.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class FragmentAdapter extends FragmentStateAdapter {
    private List<Fragment> fragmentList;

    public FragmentAdapter(FragmentManager fragmentManager, Lifecycle lifecycle, List<Fragment> fragmentList) {

        super(fragmentManager, lifecycle);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // 返回指定位置的 Fragment
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        // 返回 Fragment 的数量
        return fragmentList.size();
    }
}
