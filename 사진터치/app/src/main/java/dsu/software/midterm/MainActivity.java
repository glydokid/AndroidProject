package dsu.software.midterm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
@SuppressWarnings("deprecation") // 경고 방지
public class MainActivity extends AppCompatActivity implements
        ActionBar.TabListener{
    ActionBar.Tab tabDog, tabCat, tabRabbit, tabHorse;
    MyTabFragment myFrags[] = new MyTabFragment[4];
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // dog 탭
        tabDog = bar.newTab();
        tabDog.setIcon(R.drawable.dog_icon);
        tabDog.setText("dog");
        tabDog.setTabListener(this);
        bar.addTab(tabDog);
        // cat 탭
        tabCat = bar.newTab();
        tabCat.setIcon(R.drawable.cat_icon);
        tabCat.setText("cat");
        tabCat.setTabListener(this);
        bar.addTab(tabCat);
        // rabbit 탭
        tabRabbit = bar.newTab();
        tabRabbit.setIcon(R.drawable.rabbit_icon);
        tabRabbit.setText("rabbit");
        tabRabbit.setTabListener(this);
        bar.addTab(tabRabbit);
        // horse 탭
        tabHorse = bar.newTab();
        tabHorse.setIcon(R.drawable.horse_icon);
        tabHorse.setText("horse");
        tabHorse.setTabListener(this);
        bar.addTab(tabHorse);
    }
    // 탭을 선택하면 동작
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        MyTabFragment myTabFrag = null;
        if (myFrags[tab.getPosition()] == null) {
            myTabFrag = new MyTabFragment();
            Bundle data = new Bundle();
            data.putString("tabName", tab.getText().toString());
            myTabFrag.setArguments(data);
            myFrags[tab.getPosition()] = myTabFrag;
        } else
            myTabFrag = myFrags[tab.getPosition()];
        ft.replace(android.R.id.content, myTabFrag);
    }
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }
    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }
    public static class MyTabFragment extends Fragment {
        String tabName;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle data = getArguments();
            tabName = data.getString("tabName");
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View myView = null;
            if (tabName == "dog")
                myView = inflater.inflate(R.layout.dog, null);
            if (tabName == "cat")
                myView = inflater.inflate(R.layout.cat, null);
            if (tabName == "rabbit")
                myView = inflater.inflate(R.layout.rabbit, null);
            if (tabName == "horse")
                myView = inflater.inflate(R.layout.horse, null);
            return myView;
        }
    }
}