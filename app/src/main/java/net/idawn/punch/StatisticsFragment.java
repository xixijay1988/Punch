package net.idawn.punch;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ryg.expandable.ui.PinnedHeaderExpandableListView;
import com.ryg.expandable.ui.StickyLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StatisticsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StatisticsFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class StatisticsFragment extends android.support.v4.app.Fragment implements ExpandableListView.OnChildClickListener,
        ExpandableListView.OnGroupClickListener,
        PinnedHeaderExpandableListView.OnHeaderUpdateListener, StickyLayout.OnGiveUpTouchEventListener {

    private PinnedHeaderExpandableListView expandableListView;
    private StickyLayout stickyLayout;
    private ArrayList<GroupTime> groupList;
    private ArrayList<List<Passenger>> childList;

    private MyexpandableListAdapter adapter;


    /***
     * InitData
     */
    void initData() {
        groupList = new ArrayList<GroupTime>();
        GroupTime group = null;
        for (int i = 0; i < 3; i++) {
            group = new GroupTime();
            group.setTitle("group-" + i);
            groupList.add(group);
        }

        childList = new ArrayList<List<Passenger>>();
        for (int i = 0; i < groupList.size(); i++) {
            ArrayList<Passenger> childTemp;
            if (i == 0) {
                childTemp = new ArrayList<Passenger>();
                for (int j = 0; j < 13; j++) {
                    Passenger people = new Passenger();
                    people.setId(1000 + j);
                    people.setName("zhang" + j);
                    people.setTel("133" + j);

                    childTemp.add(people);
                }
            } else if (i == 1) {
                childTemp = new ArrayList<Passenger>();
                for (int j = 0; j < 8; j++) {
                    Passenger people = new Passenger();
                    people.setId(2000 + j);
                    people.setName("li" + j);
                    people.setTel("134" + j);

                    childTemp.add(people);
                }
            } else {
                childTemp = new ArrayList<Passenger>();
                for (int j = 0; j < 23; j++) {
                    Passenger people = new Passenger();
                    people.setId(3000 + j);
                    people.setName("qian" + j);
                    people.setTel("135" + j);

                    childTemp.add(people);
                }
            }
            childList.add(childTemp);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        expandableListView = (PinnedHeaderExpandableListView) getActivity().findViewById(R.id.expandablelist);
        stickyLayout = (StickyLayout)getActivity().findViewById(R.id.sticky_layout);
        initData();

        adapter = new MyexpandableListAdapter(getActivity());
        expandableListView.setAdapter(adapter);

        // 展开所有group
        for (int i = 0, count = expandableListView.getCount(); i < count; i++) {
            expandableListView.expandGroup(i);
        }

        expandableListView.setOnHeaderUpdateListener(this);
        expandableListView.setOnChildClickListener(this);
        expandableListView.setOnGroupClickListener(this);
        stickyLayout.setOnGiveUpTouchEventListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long l) {
        return false;
    }

    @Override
    public boolean giveUpTouchEvent(MotionEvent event) {
        if (expandableListView.getFirstVisiblePosition() == 0) {
            View view = expandableListView.getChildAt(0);
            if (view != null && view.getTop() >= 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
        return false;
    }

    @Override
    public View getPinnedHeader() {
        View headerView = (ViewGroup) getActivity().getLayoutInflater().inflate(R.layout.group, null);
        headerView.setLayoutParams(new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));

        return headerView;
    }

    @Override
    public void updatePinnedHeader(View headerView, int firstVisibleGroupPos) {
        GroupTime firstVisibleGroup = (GroupTime) adapter.getGroup(firstVisibleGroupPos);
        TextView textView = (TextView) headerView.findViewById(R.id.group);
        textView.setText(firstVisibleGroup.getTitle());

    }


    class GroupHolder {
        TextView textView;
        ImageView imageView;
    }

    class ChildHolder {
        TextView textId;
        TextView textName;
        TextView textTel;
        TextView textTime;
    }

    /***
     * 数据源
     *
     * @author Administrator
     *
     */
    class MyexpandableListAdapter extends BaseExpandableListAdapter {
        private Context context;
        private LayoutInflater inflater;

        public MyexpandableListAdapter(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        // 返回父列表个数
        @Override
        public int getGroupCount() {
            return groupList.size();
        }

        // 返回子列表个数
        @Override
        public int getChildrenCount(int groupPosition) {
            return childList.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {

            return groupList.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childList.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {

            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            GroupHolder groupHolder = null;
            if (convertView == null) {
                groupHolder = new GroupHolder();
                convertView = inflater.inflate(R.layout.group, null);
                groupHolder.textView = (TextView) convertView
                        .findViewById(R.id.group);
                groupHolder.imageView = (ImageView) convertView
                        .findViewById(R.id.groupimage);
                convertView.setTag(groupHolder);
            } else {
                groupHolder = (GroupHolder) convertView.getTag();
            }

            groupHolder.textView.setText(((GroupTime) getGroup(groupPosition))
                    .getTitle());
            if (isExpanded)// ture is Expanded or false is not isExpanded
                groupHolder.imageView.setImageResource(R.drawable.expanded);
            else
                groupHolder.imageView.setImageResource(R.drawable.collapse);
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            ChildHolder childHolder = null;
            if (convertView == null) {
                childHolder = new ChildHolder();
                convertView = inflater.inflate(R.layout.child, null);

                childHolder.textId = (TextView) convertView
                        .findViewById(R.id.child_id);
                childHolder.textName = (TextView) convertView
                        .findViewById(R.id.child_name);
                childHolder.textTel = (TextView) convertView
                        .findViewById(R.id.child_tel);
                childHolder.textTime = (TextView) convertView
                        .findViewById(R.id.child_time);


                convertView.setTag(childHolder);
            } else {
                childHolder = (ChildHolder) convertView.getTag();
            }

            childHolder.textId.setText(((Passenger) getChild(groupPosition,
                    childPosition)).getId() + "");

            childHolder.textName.setText(String.valueOf(((Passenger) getChild(
                    groupPosition, childPosition)).getName()));
            childHolder.textTel.setText(((Passenger) getChild(groupPosition,
                    childPosition)).getTel());
            DateFormat df = new SimpleDateFormat("HH:mm:ss");

            childHolder.textTime.setText(df.format(new Date()));

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
