package io.github.tonyshkurenko.recyclerviewsetup;

import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by: Anton Shkurenko (tonyshkurenko)
 * Project: RecyclerViewSetup
 * Date: 6/2/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>
    implements ItemTouchHelperAdapter {

  private static final String[] STRINGS = new String[] {
      "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"
  };

  private final List<String> mItems = new ArrayList<>();

  private final OnStartDragListener mDragStartListener;

  public RecyclerViewAdapter(OnStartDragListener dragStartListener) {
    mDragStartListener = dragStartListener;

    mItems.addAll(Arrays.asList(STRINGS));
  }

  @Override public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_default, parent, false);
    return new ItemViewHolder(view);
  }

  @Override public void onBindViewHolder(final ItemViewHolder holder, int position) {
    holder.mTextView.setText(mItems.get(position));

    holder.mImageView.setOnTouchListener(new View.OnTouchListener() {
      @Override public boolean onTouch(View v, MotionEvent event) {
        if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
          mDragStartListener.onStartDrag(holder);
        }
        return false;
      }
    });
  }

  @Override public int getItemCount() {
    return mItems.size();
  }

  @Override public boolean onItemMove(int fromPosition, int toPosition) {
    if (fromPosition < toPosition) {
      for (int i = fromPosition; i < toPosition; i++) {
        Collections.swap(mItems, i, i + 1);
      }
    } else {
      for (int i = fromPosition; i > toPosition; i--) {
        Collections.swap(mItems, i, i - 1);
      }
    }
    notifyItemMoved(fromPosition, toPosition);
    return true;
  }

  @Override public void onItemDismiss(int position) {
    mItems.remove(position);
    notifyItemRemoved(position);
  }

  protected static class ItemViewHolder extends RecyclerView.ViewHolder
      implements ItemTouchHelperViewHolder {

    final TextView mTextView;
    final ImageView mImageView;

    public ItemViewHolder(View itemView) {
      super(itemView);
      mTextView = (TextView) itemView.findViewById(R.id.text);
      mImageView = (ImageView) itemView.findViewById(R.id.handle);
    }

    @Override public void onItemSelected() {
      //itemView.setBackgroundColor(Color.LTGRAY);
    }

    @Override public void onItemClear() {
      //itemView.setBackgroundColor(0);
    }
  }
}