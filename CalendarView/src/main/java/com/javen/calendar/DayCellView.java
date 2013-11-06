package com.javen.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by javen on 13-11-6.
 */
public class DayCellView extends FrameLayout {


    public DayCellView(Context context) {
        super(context);

        initWidget(context);
    }

    public DayCellView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initWidget(context);
    }

    public DayCellView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initWidget(context);
    }

    private TextView mSolarText = null;
    private TextView mLunarText = null;
    private TextView mFestivalText = null;

    private void initWidget(Context context) {
        LayoutInflater.from(context).inflate(R.layout.day_cell_layout, this);
        mSolarText = (TextView) findViewById(R.id.cell_solar_tv);
        mLunarText = (TextView) findViewById(R.id.cell_lunar_tv);
        mFestivalText = (TextView) findViewById(R.id.cell_festival);
    }

    public void setValue(String solar, String lunar, String festival) {
        mSolarText.setText(solar);
        mLunarText.setText(lunar);
        mFestivalText.setText(festival);
    }


}
