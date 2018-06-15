package com.esdeath.androidbookstore.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import com.esdeath.androidbookstore.R;
import com.esdeath.androidbookstore.model.Book;
import com.esdeath.androidbookstore.network.BookStoreApi;
import com.esdeath.androidbookstore.network.RetrofitClient;
import com.esdeath.androidbookstore.utils.GlideImageLoader;
import com.esdeath.androidbookstore.widget.BookAdapter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

public class BookFragment extends BaseFragment {

    private RecyclerView mBooklist;
    private BookStoreApi mService;
    private BookAdapter mAdapter;
    private ProgressBar mProgressBar;
    private List<String> images = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initService();
        initBannerImages();
    }
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.fragment_home,container,false);
        mBooklist = view.findViewById(R.id.mBooklist);
        mProgressBar = view.findViewById(R.id.progressbar);
        mAdapter = new BookAdapter(getActivity());
        mProgressBar.setVisibility(View.VISIBLE);
        Banner banner = view.findViewById(R.id.banner);
        banner.setImages(images).setImageLoader(new GlideImageLoader()).start();

        mBooklist.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBooklist.setHasFixedSize(true);
        mBooklist.setAdapter(mAdapter);
        loadData();
        return view;
    }

    private void initBannerImages(){
        for(int i=1;i<=4;i++){
            images.add("http://47.106.75.68:8080/static/img/carousel"+i+".jpg");
        }
    }

    private void initService() {
        mService = RetrofitClient.getBookStoreApi();
    }

    private void loadData(){
        mService.getBooks()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<List<Book>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<Book> books) {
                Log.d(TAG, "onNext: ");

                mAdapter.setDataList(books);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");

                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

}
