package com.example.ashishdandriyal.androidtvapptutorial;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v17.leanback.app.GuidedStepSupportFragment;
import android.support.v17.leanback.widget.GuidanceStylist;
import android.support.v17.leanback.widget.GuidedAction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class Help extends FragmentActivity {

    private static final String TAG = Help.class.getSimpleName();

    private static final int ACTION_ACCINFO = 0;
    private static final int ACTION_CONTACTUS = 1;
    private static final int ACTION_BACK = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        if(null == savedInstanceState) {
            GuidedStepSupportFragment.add(getSupportFragmentManager(), new AccountInfo());
        }
    }

    private static void addAction(List actions, Long id, String title, String desc) {
        actions.add(new GuidedAction.Builder().id(id).title(title).description(desc).build());
    }

    public static class AccountInfo extends GuidedStepSupportFragment {

        @NonNull
        @Override
        public GuidanceStylist.Guidance onCreateGuidance(Bundle savedInstanceState){
            String title = "Irdeto-TataSky";
            String breadcrumb = "";
            String description = "The Irdeto 360 Security suite provides advanced premium content protection to the pay media industry. We have comprehensive security solutions for movie studios, sports rights holders, and OTT and broadcast operators across the content value chain. Our solutions help them meet the challenges of delivering high quality, secure and flexible media services to the widest range of consumer devices.";
            Drawable icon = getActivity().getDrawable(R.drawable.irdeto);
            return new GuidanceStylist.Guidance(title, description, breadcrumb, icon);
        }

        @Override
        public void onCreateActions(@NonNull List actions, Bundle savedInstanceState){
            addAction(actions, (long)ACTION_ACCINFO, "Account Info", "Your account information");
            addAction(actions, (long)ACTION_CONTACTUS, "Contact Us", "Our helpline");
            addAction(actions, (long)ACTION_BACK, "Back", "Go back");
        }

        @Override
        public void onGuidedActionClicked(GuidedAction action){
            switch ((int) action.getId()){
                case ACTION_ACCINFO:
                    Toast.makeText(getActivity(), "Account active on courtesy of Irdeto.",  Toast.LENGTH_LONG).show();
                    break;
                case ACTION_CONTACTUS:
                    Toast.makeText(getActivity(), "Sample application! thereby no contact information provided.", Toast.LENGTH_LONG).show();
                    break;
                case ACTION_BACK:
                    getActivity().finish();
                    break;
            }
        }
    }
}
