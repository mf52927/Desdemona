package com.othello.desdemona.app;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.Object;



public class PlayActivity extends Activity{
    static GridView myGrid;
    Context context = this;
    static ViewAdapter adapter;
    static TextView textVieww;
    static TextView textViewb;
    static TextView winText;
    static Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.game = new Game();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        myGrid= (GridView) findViewById(R.id.myGrid);
        adapter = new ViewAdapter(this.context);
        myGrid.setAdapter(adapter);
        textVieww = (TextView) findViewById(R.id.P2score);
        textVieww.setText(Integer.toString(2));
        winText = (TextView) findViewById(R.id.winbool);
        winText.setText("No Winner Yet!");
        textViewb = (TextView) findViewById(R.id.P1score);
        textViewb.setText(Integer.toString(2));
    }

    public static void resetAdapter() {
        game = adapter.game;
        adapter.notifyDataSetChanged();
        myGrid.setAdapter(adapter);
        String wscore = Integer.toString(game.whiteScore());
        String bscore = Integer.toString(game.blackScore());
        textVieww.setText(wscore);
        textViewb.setText(bscore);
        if(game.isWinner()){
            if (game.whiteScore()>game.blackScore())
                winText.setText("White Wins!");
            else if (game.whiteScore()<game.blackScore())
                winText.setText("Black Wins!");
            else winText.setText("Tie Game.");
        }
        }
}




class ViewAdapter extends BaseAdapter{

    Game game;
    Context context;
    int[] images = {R.drawable.green_box, R.drawable.black,R.drawable.white, R.drawable.possible};

    ViewAdapter(Context context){
        this.game=new Game();
        this.context = context;

    }

    boolean reAdapter(int i){
        int x = i%8;
        int y = 7-(i/8);
        if(this.game.do_move(x,y)){
            PlayActivity.resetAdapter();
            return true;
        }else{
            return false;}
    }

    @Override
    public int getCount() {return 64;}


    public int getImage(int i) {
        switch(this.game.grid[i]) {
            case 'n': return images[0];
            case 'b': return images[1];
            case 'w': return images[2];
            case 'p': return images[3];
        }
        return images[0];
    }
    @Override
    public Object getItem(int i){
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        final ImageView myImage = getImageInGetView(view, i);
        myImage.setImageResource(getImage(i));

        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reAdapter(i);
                myImage.setImageResource(getImage(i));
            }
        });

        return myImage;
    }

    public ImageView getImageInGetView(View convertView, int position) {
        ImageView imageview;
        if (convertView == null) {

            imageview = new ImageView(this.context);
            imageview.setLayoutParams(new GridView.LayoutParams(40, 40));
            imageview.setScaleType(ImageView.ScaleType.CENTER);
            imageview.setPadding(1,1,1,1);
        } else {
            imageview = (ImageView) convertView;
        }
        return imageview;
    }
}