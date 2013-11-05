package com.javen.calendar;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.Calendar;

public class CalendarActivity extends ActionBarActivity implements View.OnClickListener {


    private ViewSwitcher mCalendarView;

    private TextView mTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);
        mCalendarView = (ViewSwitcher) findViewById(R.id.calendar);
        findViewById(R.id.pre).setOnClickListener(this);
        findViewById(R.id.next).setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.label);
        CalendarView calendarV = (CalendarView) mCalendarView.getCurrentView();
        calendarV.setLabelView(mTextView);
//        mCalendarView.setLabelView((TextView) findViewById(R.id.label));
    }


    @Override
    public void onClick(View v) {
        final int id = v.getId();

        if (mCalendarView.getInAnimation() != null && mCalendarView.getInAnimation().hasStarted() && !mCalendarView.getInAnimation().hasEnded()) {
            return;
        }


        CalendarView currentView = (CalendarView) mCalendarView.getCurrentView();
        CalendarView nextView = (CalendarView) mCalendarView.getNextView();
        nextView.setLabelView(mTextView);

        Calendar calendar = currentView.getCurrentDate();
        if (id == R.id.pre) {
            calendar.add(Calendar.MONTH, -1);
            nextView.setFirstDay(calendar);
            mCalendarView.setInAnimation(this, R.anim.slide_in_left);
            mCalendarView.setOutAnimation(this, R.anim.slide_out_right);
            mCalendarView.showNext();
            // mCalendarView.showNextMonth(-1);
        } else {
            mCalendarView.setInAnimation(this, R.anim.slide_in_right);
            mCalendarView.setOutAnimation(this, R.anim.slide_out_left);
            calendar.add(Calendar.MONTH, 1);
            nextView.setFirstDay(calendar);
            mCalendarView.showNext();
//            mCalendarView.showNextMonth(1);
        }
    }
}
