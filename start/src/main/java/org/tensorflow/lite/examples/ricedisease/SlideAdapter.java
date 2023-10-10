package org.tensorflow.lite.examples.ricedisease;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater inflater;

    public SlideAdapter(Context context) {
        this.context = context;
    }

    //Arrays of images and text
    int images[] = { R.drawable.k11, R.drawable.k22, R.drawable.k33, R.drawable.k44, R.drawable.k55 };

    int head_title[] = { R.string.Section1, R.string.Section2, R.string.Section3, R.string.Section4, R.string.Section5  };

    int descrip[] = { R.string.des1, R.string.des2, R.string.des3, R.string.des4, R.string.des5 };


    @Override
    public int getCount() {
        return head_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View toview = inflater.inflate(R.layout.slide_to_next,container,false);

        ImageView sliderImg = toview.findViewById(R.id.sliderImg);
        TextView title = toview.findViewById(R.id.title);
        TextView description = toview.findViewById(R.id.description);

        sliderImg.setImageResource(images[position]);
        title.setText(head_title[position]);
        description.setText(descrip[position]);

        container.addView(toview);

        return toview;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((ConstraintLayout)object);
    }
}



