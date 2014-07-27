package net.idawn.punch;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by xixi on 2014/7/27.
 */
public class PunchFragmentAdapter extends FragmentPagerAdapter{

    private final String[] titles = {"扫码", "生成","统计", "上传"};


    public PunchFragmentAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){

            case 0:
                if(MainActivity.fScanFrag == null){
                    MainActivity.fScanFrag = new ScanFragment();
                }
                return MainActivity.fScanFrag;
            case 1:
                if(MainActivity.fCreateFrag == null){
                    MainActivity.fCreateFrag = new CreateFragment();
                }
                return MainActivity.fCreateFrag;
            case 2:
                if (MainActivity.fStatisticsFrag == null){
                    MainActivity.fStatisticsFrag = new StatisticsFragment();
                }
                return MainActivity.fStatisticsFrag;
            case 3:
                if (MainActivity.fUploadFrag == null){
                    MainActivity.fUploadFrag = new UploadFragment();
                }
                return MainActivity.fUploadFrag;

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        //return 1;
         return (int)titles.length;
    }
}
