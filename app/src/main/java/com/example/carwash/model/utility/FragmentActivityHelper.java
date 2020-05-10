package com.example.carwash.model.utility;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carwash.R;
import com.example.carwash.view.activity.BaseActivity;
import com.example.carwash.view.activity.MainActivity;
import com.example.carwash.view.fragment.BaseFragment;


public class FragmentActivityHelper {
    private IActivityContract iContract;
    private BaseActivity mHostingActivity;

    private TextView tvHeader;


    public FragmentActivityHelper(BaseActivity fragmentHostActivity) {

        if (!(fragmentHostActivity instanceof IActivityContract))
            throw new IllegalArgumentException("must implement contract ");
        this.iContract = (IActivityContract) fragmentHostActivity;
        this.mHostingActivity = fragmentHostActivity;
        //tvHeader = fragmentHostActivity.findViewById(iContract.getHeaderTitleId());


    }


    public void fragmentUpdated(BaseFragment fragment) {
        //  if (fragment.getTitle() != null) {
        //   tvHeader.setText(fragment.getTitle());
        // }
    }

    public interface IActivityContract {
        int getHeaderTitleId();

        int getRightIconId();

        int getRightTwoIconId();

        int getLeftIcon();
    }


}
