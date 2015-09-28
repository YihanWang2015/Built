package edu.kvcc.cis298.cis298inclass1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
        // create a class level widget variables so that we will
    //have access to stuff from the view.
    //No value yet. Just declared the variable.
    private Button mTrueButton;
    private Button mFalseButton;

    //variable for the next button.
    private Button mNextButton;
    //variable for the question string.
    private TextView mQuestionTextView;

    //The questions that will be used. It is an array of type
    //Questions, that contains 5 questions. It is a hard coded
    //array. In most apps, you would want your data to come from
    //somewhere else (database, internet). Not be hard coded.
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa,false),
            new Question(R.string.question_america, true),
            new Question(R.string.question_asia, true)
    };

    private int mCurrentIndex = 0;


    //private methods that will be used inside the OnCreate
    //Dave wrote there. Not google

    private void updateQuestion(){




        //Get the Question instance stored at the mCurrentIndex of the
        //QuestionBank array. Then call the getTextResId method(property)
        //to return the integer value that points the string
        //resource in strings.xml that we want to use.
        int question = mQuestionBank[mCurrentIndex].getTextResId();

        //Assign the integer for the string resource to the
        //TextView so that the question text will display.
        mQuestionTextView.setText(question);

    }

    private void checkAnswer(boolean userPressedTrue)
    {
        //create a boolean to represent the actual answer of
        //the current question we are on.
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        //declare an integer that will be a pointer to the string
        //resource that will be used for the toast message
        int messageResId= 0 ;


        //compare the actual answer to the answer that was passed
        //into this method. If they match, the message is correct.
        //else it is incorrect. Assign the R in value to the
        //messageResId.
        if (userPressedTrue == answerIsTrue) {

            messageResId = R.string.correct_toast;

        } else {

            messageResId = R.string.incorrect_toast;
        }

        //make a toast, and use the messageResId for the message
        //to show.
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();

    }




    //Dave didn't write this method. It was given to us by google.
    //It is the 'setup' method for the app.
    //It will be called when the app launches.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        //Begin code Dave writes ********************




        //Get a 'handle' to the TextView in the layout.
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        //this is declared up above. Dave does the work of changing
        //to the next question in the array.
        updateQuestion();



        //Fetch the widget control from the view, and then
        //cast and assign it to the class variable we setup
        mTrueButton = (Button) findViewById(R.id.true_button);

        //Now that I have a 'handle' to the view widget, I can
        //setup an OnClickListener for the widget
        //This OnclickListener uses an an anonymous inner class.
        //We are passing what we want to have happen onClick.
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // use the toast class to print a message to the screen
                // that will fade out after the duration
                // listed as LENGTH_SHORT
                // This method requires 3 parameters.
                // The context, which will usually be Activity.this,
                // The message, which will usually be a string from strings.xml
                // The Length, which will be one of the predefined constants.


                //Toast.makeText(QuizActivity.this,
                               // R.string.correct_toast,
                               // Toast.LENGTH_SHORT).show();

               // @Override
               //         public void onclick(View, view){
                    //call the checkAnswer method that is declared at the top of this class
                    //it will take in the bool value that they selected, and do the work of determining if the answer
                    //is correct. either way it will toast the message to the screen.
                //    checkAnswer(true);
               // }


            }
        });
                // See the notes from TrueButton. It is the same
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(QuizActivity.this,
                        R.string.incorrect_toast,
                        Toast.LENGTH_SHORT).show();
            }
        });


        mNextButton =(Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                mCurrentIndex = (mCurrentIndex + 1 ) % mQuestionBank.length;

               // This method is declared at the top of the class. It handles updating
                //the question text.

                //int question = mQuestionBank[mCurrentIndex].getTextResId();
               // mQuestionTextView.setText(question);
                updateQuestion();

            }

        });


        //End code Dave writes ********************
    }



    // These are  methods that we did not write.
    // If we get to using menus, we will need them. They can be ignored for now.

    //Begin unneeded google methods*********************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //End unneeded google methods*********************
}
