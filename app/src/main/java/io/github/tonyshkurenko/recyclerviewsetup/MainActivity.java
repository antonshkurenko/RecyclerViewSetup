package io.github.tonyshkurenko.recyclerviewsetup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class MainActivity extends AppCompatActivity implements OnStartDragListener {

  private ItemTouchHelper mItemTouchHelper;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    //noinspection ConstantConditions
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    final RecyclerViewAdapter adapter = new RecyclerViewAdapter(this);
    recyclerView.setAdapter(adapter);

    final ItemTouchHelper.Callback callback =
        new SimpleItemTouchHelperCallback(adapter);
    mItemTouchHelper = new ItemTouchHelper(callback);

    mItemTouchHelper.attachToRecyclerView(recyclerView);
  }

  @Override public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
    mItemTouchHelper.startDrag(viewHolder);
  }
}
