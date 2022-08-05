package com.example.marketapp.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.marketapp.R;
import com.example.marketapp.adapter.NewsAdapter;
import com.example.marketapp.model.NewsModel;
import com.example.marketapp.api.APIInterface;
import com.example.marketapp.api.CallRetrofit;
import com.example.marketapp.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements CallRetrofit.OnListReadyCallBack{

    public static final String BASE_URL = "https://newsapi.org/v2/";
    public static final String API_KEY = "f1727553ac7d4a3195050182cf68f5e2";
    public static final String URL = "top-headlines?country=us&category=business&apiKey=" + API_KEY;
    public static final String TECH_URL = "top-headlines?sources=techcrunch&apiKey=" + API_KEY;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public boolean TECH_NEWS = false;
    public boolean US_NEWS = false;

    List<NewsModel> newsModelListOne, newsModelListTwo;
    FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        List<SlideModel> slideImages = new ArrayList<>();
        slideImages.add(new SlideModel(R.drawable.bottles, null));
        slideImages.add(new SlideModel(R.drawable.home_tools, null));
        slideImages.add(new SlideModel(R.drawable.lemonade, null));
        slideImages.add(new SlideModel(R.drawable.techmology, null));
        binding.slider.setImageList(slideImages, ScaleTypes.CENTER_CROP);

        binding.progressBar.setVisibility(View.VISIBLE);
        new CallRetrofit().makeApiCall(URL, TECH_URL, this);

        SnapHelper helper = new PagerSnapHelper();
        helper.attachToRecyclerView(binding.horizontalRecycler);

        return binding.getRoot();
    }

    @Override
    public void getUSCallbackList(List<NewsModel> usNewsModels) {
        US_NEWS = true;
        this.newsModelListOne = usNewsModels;
        NewsAdapter adapter = new NewsAdapter(getActivity().getApplicationContext(), this.newsModelListOne);
        binding.horizontalRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        binding.horizontalRecycler.setItemAnimator(new DefaultItemAnimator());
        binding.horizontalRecycler.setAdapter(adapter);
        if (US_NEWS && TECH_NEWS)
            binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void getTechCallbackList(List<NewsModel> techNewsModels) {
        TECH_NEWS = true;
        this.newsModelListTwo = techNewsModels;
        NewsAdapter adapterTwo = new NewsAdapter(getActivity().getApplicationContext(), this.newsModelListTwo);
        binding.verticalRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        binding.verticalRecycler.setItemAnimator(new DefaultItemAnimator());
        binding.verticalRecycler.setAdapter(adapterTwo);
        if (US_NEWS && TECH_NEWS)
            binding.progressBar.setVisibility(View.GONE);
    }
}