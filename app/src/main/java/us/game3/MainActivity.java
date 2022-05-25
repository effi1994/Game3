package us.game3;


        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.ImageView;


public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button buttonPlay = (Button)findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(this);
        final Button buttonQuit = (Button) findViewById(R.id.buttonQuit);
        ImageView imageView=(ImageView) findViewById(R.id.imageView);
        imageView.setBackgroundColor(BIND_WAIVE_PRIORITY);
        imageView.setImageResource(R.drawable.ic_launcher_background);
        imageView.setImageResource(R.drawable.ic_launcher_foreground);

        buttonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent i;
        i = new Intent(this, GameActivity.class);
        startActivity(i);
        finish();

    }

}






