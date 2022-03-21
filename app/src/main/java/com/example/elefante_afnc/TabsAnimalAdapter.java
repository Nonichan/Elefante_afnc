package com.example.elefante_afnc;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabsAnimalAdapter extends FragmentStateAdapter {
    private int numberOfTabs;

    public TabsAnimalAdapter(Fragment fragment, int numberOfTabs){
        super(fragment);
        this.numberOfTabs = numberOfTabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                TabDatosAnimalFragment tab1 = new TabDatosAnimalFragment();
                return tab1;
            case 1:
                TabCuriosidadesAnimalFragment tab2 = new TabCuriosidadesAnimalFragment();
                return tab2;
            case 2:
                return new TabComprarAnimalFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return numberOfTabs;
    }
}
