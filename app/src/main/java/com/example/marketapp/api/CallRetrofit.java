package com.example.marketapp.api;

import androidx.annotation.NonNull;

import com.example.marketapp.model.NewsModel;
import com.example.marketapp.home.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallRetrofit {
    APIInterface request;
    List<NewsModel> usNews, techNews;

    public CallRetrofit(){
        if (usNews == null){
            usNews = new ArrayList<>();
        }
        if (techNews == null){
            techNews = new ArrayList<>();
        }
    }

    public void makeApiCall(String url1, String url2, @NonNull OnListReadyCallBack callBack){
        request = APIClient.prepareClient(HomeFragment.BASE_URL).create(APIInterface.class);
        request.getNewsData(url1).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    JSONArray jsonArray = jsonObject.getJSONArray("articles");

                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        NewsModel model = new NewsModel();
                        model.setName(object.getJSONObject("source").getString("name"));
                        model.setAuthor(object.getString("author"));
                        model.setTitle(object.getString("title"));
                        model.setDescription(object.getString("description"));
                        model.setUrl(object.getString("url"));
                        model.setUrlToImage(object.getString("urlToImage"));
                        model.setPublishedAt(object.getString("publishedAt"));
                        usNews.add(model);
                    }
                    callBack.getUSCallbackList(usNews);

                }catch (IOException | JSONException e){
                    e.getStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        request = APIClient.prepareClient(HomeFragment.BASE_URL).create(APIInterface.class);
        request.getNewsData(url2).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    JSONArray jsonArray = jsonObject.getJSONArray("articles");

                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        NewsModel model = new NewsModel();
                        model.setName(object.getJSONObject("source").getString("name"));
                        model.setAuthor(object.getString("author"));
                        model.setTitle(object.getString("title"));
                        model.setDescription(object.getString("description"));
                        model.setUrl(object.getString("url"));
                        model.setUrlToImage(object.getString("urlToImage"));
                        model.setPublishedAt(object.getString("publishedAt"));
                        techNews.add(model);
                    }
                    callBack.getTechCallbackList(techNews);

                }catch (IOException | JSONException e){
                    e.getStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public interface OnListReadyCallBack{
        void getUSCallbackList(List<NewsModel> usNewsModels);
        void getTechCallbackList(List<NewsModel> techNewsModels);
    }
}
