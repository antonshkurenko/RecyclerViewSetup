package io.github.tonyshkurenko.recyclerviewsetup;

import android.support.v7.widget.RecyclerView;

/**
 * Created by: Anton Shkurenko (tonyshkurenko)
 * Project: RecyclerViewSetup
 * Date: 6/2/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public interface OnStartDragListener {

  /**
   * Called when a view is requesting a start of a drag.
   *
   * @param viewHolder The holder of the view to drag.
   */
  void onStartDrag(RecyclerView.ViewHolder viewHolder);
}