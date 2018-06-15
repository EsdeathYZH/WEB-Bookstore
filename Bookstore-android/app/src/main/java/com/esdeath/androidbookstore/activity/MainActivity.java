package com.esdeath.androidbookstore.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.esdeath.androidbookstore.R;
import com.esdeath.androidbookstore.fragment.BookFragment;
import com.esdeath.androidbookstore.fragment.CartFragment;
import com.esdeath.androidbookstore.fragment.CategoryFragment;
import com.esdeath.androidbookstore.fragment.PersonFragment;

public class MainActivity extends BaseActivity {
    private Toolbar mToolbar;
    private String currentTag;//当前tab
    private FragmentManager fragmentManager;
    private String[] titles=new String[]{"首页","分类","购物车","我的"};//各选项名称
    private Fragment[] fragments=new Fragment[]{new BookFragment(),new CategoryFragment(),new CartFragment(),new PersonFragment()};
    private String[] tags=new String[]{"home","category","shoppingcart","person"};//各选项标记
    private int[] icons=new int[]{R.drawable.home,R.drawable.category,R.drawable.shoppingcart,R.drawable.person};
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        initTab();
        updateToobar("home");
    }


    private void initTab(){
        TabHost mTabHost = findViewById(R.id.mTabHost);
        mTabHost.setup();
        for(int i=0;i<titles.length;i++){
            TabHost.TabSpec tabSpec=mTabHost.newTabSpec(tags[i]);
            View view=getLayoutInflater().inflate(R.layout.tab,null);
            TextView tabTitle = view.findViewById(R.id.tabTitle);
            ImageView tabicon = view.findViewById(R.id.tabIcon);
            tabTitle.setText(titles[i]);
            tabicon.setImageResource(icons[i]);
            tabSpec.setIndicator(view);
            tabSpec.setContent(R.id.realContent);
            mTabHost.addTab(tabSpec);
        }
        fragmentManager=getFragmentManager();
        addFragment();
        mTabHost.setOnTabChangedListener(new MyTabChangedListener());
        mTabHost.setCurrentTabByTag("home");
    }
    private void addFragment(){
        for(int i=0;i<4;i++){
            fragmentManager.beginTransaction().add(R.id.realContent,fragments[i],tags[i]).commit();
        }
        fragmentManager.executePendingTransactions();
        for(int i=1;i<4;i++){
            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(tags[i])).commit();
        }
        fragmentManager.executePendingTransactions();
        currentTag="home";

    }

    private void updateToobar(String tag){
        switch (tag){
            case "home":
                mToolbar.setTitle("首页");
                mToolbar.setNavigationIcon(R.drawable.search);
                break;
            case "category":
                mToolbar.setTitle("分类");
                break;
            case "shoppingcart":
                mToolbar.setTitle("购物车");
                break;
            case "person":
                mToolbar.setTitle("我的");
                break;
            default:
                break;
        }
    }

    class MyTabChangedListener implements TabHost.OnTabChangeListener{
        public void onTabChanged(String tag){
            if(!tag.equals(currentTag)){
                fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(currentTag)).commit();
                currentTag = tag;
                fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag(currentTag)).commit();
                fragmentManager.executePendingTransactions();
                updateToobar(tag);
            }
        }
    }
}
