package dhiraj.projectapp.Listeners;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Dhiraj on 28-02-2018.
 */

public class RecyclerItemTouchListener implements RecyclerView.OnItemTouchListener {

    private RecyclerTouchListener touchListener;
    private GestureDetector gd;
    public interface RecyclerTouchListener {
        public void onClickItem(View v, int position);
        }

    public RecyclerItemTouchListener(Context context, final RecyclerView recyclerView, final RecyclerTouchListener listener){
        this.touchListener=listener;
        gd=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View v = recyclerView.findChildViewUnder(e.getX(), e.getY());
                // Notify the even
                touchListener.onClickItem(v, recyclerView.getChildAdapterPosition(v));
                return true;
            }
        });
    }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        return ( child != null && gd.onTouchEvent(e));
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
