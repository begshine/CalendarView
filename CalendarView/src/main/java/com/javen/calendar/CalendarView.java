package com.javen.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Calendar;

import static java.util.Calendar.MONDAY;
import static java.util.Calendar.SUNDAY;

/**
 * Created by javen on 13-11-5.
 */
public class CalendarView extends GridView {

    private Calendar mFirst_Month_Day = null;

    private CalendarAdapter mAdapter = null;

    private TextView mLabelView = null;

    public CalendarView(Context context) {
        super(context);
        initWidget(context);
    }

    public Calendar getCurrentDate() {
        return mFirst_Month_Day;
    }


    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWidget(context);

    }

    public CalendarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initWidget(context);
    }

    private void initWidget(Context context) {
        setNumColumns(7);
        mFirst_Month_Day = Calendar.getInstance();
        mFirst_Month_Day.set(Calendar.DATE, 1);
        mAdapter = new CalendarAdapter(context, mFirst_Month_Day);
        setAdapter(mAdapter);
        updateLabel();
    }

    public void setLabelView(TextView label) {
        this.mLabelView = label;
        updateLabel();
    }

    private void updateLabel() {
        if (mLabelView != null) {
            mLabelView.setText(mFirst_Month_Day.get(Calendar.YEAR) + "年" + (mFirst_Month_Day.get(Calendar.MONTH) + 1) + "月");
        }
    }


    public void showNextMonth(int step) {
        mFirst_Month_Day.add(Calendar.MONTH, step);
        mAdapter.changeDate(mFirst_Month_Day);
        updateLabel();
    }

    public void setDate(int year, int month) {
        mFirst_Month_Day = Calendar.getInstance();
        mFirst_Month_Day.set(year, month - 1, 1);
        mAdapter.changeDate(mFirst_Month_Day);
        updateLabel();

    }

    public void setFirstDay(Calendar calendar) {
        mFirst_Month_Day = Calendar.getInstance();
        mFirst_Month_Day.setTime(calendar.getTime());
        mAdapter.changeDate(mFirst_Month_Day);
        updateLabel();
    }


    /**
     * 日历
     */
    static class CalendarAdapter extends BaseAdapter {

        private Context mContext = null;

        private Calendar mDate = null;//calendar's first day in current Month

        public CalendarAdapter(Context context, Calendar date) {
            this.mContext = context;
            changeDate(date);
        }

        public void changeDate(Calendar date) {
            mDate = Calendar.getInstance();
            mDate.setTime(date.getTime());
            mDate.setFirstDayOfWeek(MONDAY);
            mDate.set(Calendar.DATE, 1);
            mDate.set(Calendar.WEEK_OF_MONTH, 1);
            mDate.set(Calendar.DAY_OF_WEEK, mDate.getFirstDayOfWeek());
            notifyDataSetInvalidated();
        }


        @Override
        public int getCount() {
            return 42;
        }

        @Override
        public Object getItem(int position) {
            Calendar itemCalendar = Calendar.getInstance();
            itemCalendar.setTime(mDate.getTime());
            itemCalendar.add(Calendar.DATE, position);
            return itemCalendar;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final Calendar itemCalendar = (Calendar) getItem(position);
            final String date = itemCalendar.get(Calendar.DATE) + "";
            int itemHeight = parent.getHeight() / 6;
            final TextView tv = new TextView(mContext);
            tv.setLayoutParams(new LayoutParams(-1, itemHeight));
            tv.setText(date);
            tv.setGravity(Gravity.CENTER);
            return tv;
        }


    }

}
