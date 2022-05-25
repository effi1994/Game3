package us.game3;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.Random;


public class GameActivity extends Activity implements View.OnClickListener{

    int correctAnswer;
    Button buttonObjectChoice1;
    Button buttonObjectChoice2;
    Button buttonObjectChoice3;
    TextView textObjectPartA;
    TextView textObjectPartB;
    TextView textObjectScore;
    TextView textObjectLevel;

    int currentScore = 0;
    int currentLevel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        textObjectPartA = findViewById(R.id.textPartA);
        textObjectPartB = findViewById(R.id.textPartB);
        textObjectScore = findViewById(R.id.textScore);
        textObjectLevel = findViewById(R.id.textLevel);
        buttonObjectChoice1 = findViewById(R.id.buttonChoice1);
        buttonObjectChoice2 = findViewById(R.id.buttonChoice2);
        buttonObjectChoice3 = findViewById(R.id.buttonChoice3);

        buttonObjectChoice1.setOnClickListener(this);
        buttonObjectChoice2.setOnClickListener(this);
        buttonObjectChoice3.setOnClickListener(this);

        setQuestion();
        final Button buttonBack= (Button)findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(this);

    }//onCreate ends here

    @Override
    public void onClick(View view) {
        int answerGiven=0;
        String back = "";
        switch (view.getId()) {

            case R.id.buttonChoice1:
                //initialize a new int with the value contained in buttonObjectChoice1
                //Remember we put it there ourselves previously
                answerGiven = Integer.parseInt("" + buttonObjectChoice1.getText());
                break;

            case R.id.buttonChoice2:
                answerGiven = Integer.parseInt("" + buttonObjectChoice2.getText());
                break;

            case R.id.buttonChoice3:
                answerGiven = Integer.parseInt("" + buttonObjectChoice3.getText());
                break;
            case R.id.buttonBack:
                back="yes";
                Intent i;
                i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
                break;

        }
        if (back!="yes"){
            updateScoreAndLevel(answerGiven);
            setQuestion();
        }


    }

    void setQuestion(){
        int numberRange = currentLevel * 3;
        Random randInt = new Random();

        int partA = randInt.nextInt(numberRange);
        partA++;

        int partB = randInt.nextInt(numberRange);
        partB++;

        correctAnswer = partA * partB;
        int wrongAnswer1 = correctAnswer-2;
        int wrongAnswer2 = correctAnswer+2;

        textObjectPartA.setText(""+partA);
        textObjectPartB.setText(""+partB);

        //set the multi choice buttons
        //A number between 0 and 2
        int buttonLayout = randInt.nextInt(3);
        switch (buttonLayout){
            case 0:
                buttonObjectChoice1.setText(""+correctAnswer);
                buttonObjectChoice2.setText(""+wrongAnswer1);
                buttonObjectChoice3.setText(""+wrongAnswer2);
                break;

            case 1:
                buttonObjectChoice2.setText(""+correctAnswer);
                buttonObjectChoice3.setText(""+wrongAnswer1);
                buttonObjectChoice1.setText(""+wrongAnswer2);
                break;

            case 2:
                buttonObjectChoice3.setText(""+correctAnswer);
                buttonObjectChoice1.setText(""+wrongAnswer1);
                buttonObjectChoice2.setText(""+wrongAnswer2);
                break;
        }
    }


    void updateScoreAndLevel(int answerGiven){

        if(isCorrect(answerGiven)){
            currentScore++;
            currentLevel++;
        }else{
            currentScore = 0;
            currentLevel = 1;
        }

        textObjectScore.setText("Score: " + currentScore);
        textObjectLevel.setText("Level: " + currentLevel);


    }

    boolean isCorrect(int answerGiven){
        boolean correctTrueOrFalse;
        if(answerGiven == correctAnswer){
            Toast.makeText(getApplicationContext(), "Well done!", Toast.LENGTH_LONG).show();
            correctTrueOrFalse=true;
        }else{//Uh-oh!
            Toast.makeText(getApplicationContext(), "Sorry", Toast.LENGTH_LONG).show();
            correctTrueOrFalse=false;
        }

        return correctTrueOrFalse;
    }

}
