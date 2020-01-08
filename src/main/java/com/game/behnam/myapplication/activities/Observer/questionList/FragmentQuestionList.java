
package com.game.behnam.myapplication.activities.Observer.questionList;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.game.behnam.myapplication.R;
import com.game.behnam.myapplication.activities.Observer.checkAnswer.DialogCheckAnswer;
import com.game.behnam.myapplication.utils.Question;
import com.game.behnam.myapplication.base.BaseFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;


import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.game.behnam.myapplication.activities.Main.user_id;


public class FragmentQuestionList extends BaseFragment implements IQuestioView {
    ProgressDialog mProgressDialog;
    FragmentActivity c;
    @BindView(R.id.event_list_rcle)
    RecyclerView rcle;
    @BindView(R.id.bt_filter)
    ImageView iv_filter;
    PaginationAdapter adapter;
    @BindView(R.id.empty_lay)
    LinearLayout empty_lay;
    private final int PAGE_START = 0;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private int TOTAL_PAGES = 5;
    private int currentPage = PAGE_START;
  //  FragmentQuestionsPresenter fragmentQuestionsPresenter;

    ObsQuestionListPresenter obsQuestionListPresenter;
    private BottomSheetBehavior mBehavior;
    private BottomSheetDialog mBottomSheetDialog;
    private View bottom_sheet;
    static ArrayList<Question> eventsList;
    ProgressBar progressBar;
    LinearLayout errorLayout;
    Button btnRetry;
    TextView txtError;
    ImageView btn_ok_alert;
    EditText et_price_alert;
    AlertDialog ad;
    public static String e_id;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_questions, container, false);
        ButterKnife.bind(this, rootView);

        setRetainInstance(true);
        obsQuestionListPresenter=new ObsQuestionListPresenter(this);
        c = getActivity();
        // setToolbar(rootView, "سوالات حل نشده");
        currentPage = 0;
        progressBar = rootView.findViewById(R.id.main_progress);
     //   errorLayout = rootView.findViewById(R.id.error_layout);
      ////  btnRetry = rootView.findViewById(R.id.error_btn_retry);
      //  txtError = rootView.findViewById(R.id.error_txt_cause);
        adapter = new PaginationAdapter(getActivity());
        NpaGridLayoutManager layoutManager = new NpaGridLayoutManager(getContext(),1);
        rcle.setLayoutManager(layoutManager);
        //rcle.setItemAnimator(new DefaultItemAnimator());
        rcle.setAdapter(adapter);

        adapter.setOnCardClickListner(new PaginationAdapter.OnCardClickListner() {
            @Override
            public void OnCardClicked(View view, int position, Question q) {
                dialog_event_end(q,position);

            }
        });



        rcle.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                // loadNextPage();
                obsQuestionListPresenter.getEventList(user_id,e_id,currentPage);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });


        mProgressDialog = new ProgressDialog(c);
        setFragmentActivity(getActivity());

     //   fragmentQuestionsPresenter = new FragmentQuestionsPresenter(this);

        bottom_sheet = rootView.findViewById(R.id.bottom_sheet);
        mBehavior = BottomSheetBehavior.from(bottom_sheet);
        iv_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   showBottomSheetDialog();

            }
        });


        obsQuestionListPresenter.getEventList(user_id,e_id,PAGE_START);




        return rootView;

    }



    public void setItems(ArrayList<Question> eventsList) {
//eventsList=new ArrayList<>();


        this.eventsList = eventsList;
        int i = currentPage;
        if (eventsList.size() != 0) {
            if (currentPage < 1) {
              //  hideErrorView();
                progressBar.setVisibility(View.GONE);
                // if(this.eventsList==null)
                //  this.eventsList = eventsList;
                adapter.addAll(eventsList);
                // madapter = new adapter_game_questions(eventsList);
                // SlideInBottomAnimationAdapter alphaAdapter = new SlideInBottomAnimationAdapter(madapter);
                // alphaAdapter.setFirstOnly(true);

                if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            } else {
                adapter.removeLoadingFooter();
                isLoading = false;
                adapter.addAll(eventsList);

                if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;

            }
        } else {
            adapter.removeLoadingFooter();

            // isLoading = false;

        }
        if(adapter.getItemCount()==0){
            empty_lay.setVisibility(View.VISIBLE);
        }
        else {
            empty_lay.setVisibility(View.GONE);
        }
    }

    @Override
    public void removeitem(int position) {
        // eventsList.remove(position);
       // rcle.getRecycledViewPool().clear();
        adapter.removeitem(position);
    }

    public void showLoading() {

     //   showLoading_base();
    }

    public void hideLoading() {
       // hideLoading_base();
    }

    @Override
    public void dimissSellAlert() {
        ad.dismiss();
    }



    private void showErrorView(Throwable throwable) {

        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

            txtError.setText(fetchErrorMessage(throwable));
        }
    }

    /**
     * @param throwable to identify the type of error
     * @return appropriate error message
     */

    private String fetchErrorMessage(Throwable throwable) {
        String errorMsg = "";
        //errorMsg = getResources().getString(R.string.error_msg_unknown);
        if (!isNetworkConnected()) {
       //     errorMsg = getResources().getString(R.string.error_msg_no_internet);
        } else if (throwable instanceof TimeoutException) {
        //    errorMsg = getResources().getString(R.string.error_msg_timeout);
        }

        return errorMsg;
    }

    // Helpers -------------------------------------------------------------------------------------



    /**
     * Remember to add android.permission.ACCESS_NETWORK_STATE permission.
     *
     * @return
     */
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    public void onStart() {


        super.onStart();
        //update your fragment

    }

    public static FragmentQuestionList newInstance() {
        FragmentQuestionList fragment = new FragmentQuestionList();

        return fragment;
    }

    public void dialog_event_end(Question question,int position) {
        Dialog ad;
        DialogCheckAnswer dt = new DialogCheckAnswer(getActivity());

        ad=dt.CheckAnswer(getActivity(),question,position,"",obsQuestionListPresenter);

        ad.show();
    }

}