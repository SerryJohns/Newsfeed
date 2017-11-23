package com.serionz.newsfeed.main.global_news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;
import com.serionz.newsfeed.R;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GlobalNewsFragment extends Fragment implements SendNews {
	private static final String TAG = GlobalNewsFragment.class.getSimpleName();
	private OnFragmentInteractionListener mListener;
	private RecyclerView recyclerView;
	private Controller mController;
	private GlobalNewsViewAdapter mGlobalNewsViewAdapter;
	private List<Article> mArticleList = new ArrayList<>();

	public GlobalNewsFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_global_news, container, false);
		recyclerView = (RecyclerView) view.findViewById(R.id.news_list);

		mGlobalNewsViewAdapter = new GlobalNewsViewAdapter(mArticleList);
		recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setAdapter(mGlobalNewsViewAdapter);

		mController = new Controller(getContext());
		mController.fetchGlobalNews(this);
		mGlobalNewsViewAdapter.notifyDataSetChanged();

		return view;
	}

	@Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof OnFragmentInteractionListener) {
			mListener = (OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	@Override public void receivedNews(NewsList newsList) {
		mArticleList.addAll(newsList.getArticles());
		this.mGlobalNewsViewAdapter.notifyDataSetChanged();
	}

	public interface OnFragmentInteractionListener {
		void onFragmentInteraction(Uri uri);
	}
}
