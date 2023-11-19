package com.example.astrocore.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.astrocore.Answer
import com.example.astrocore.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class QuizActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var optionButton1: Button
    private lateinit var optionButton2: Button
    private lateinit var optionButton3: Button
    private lateinit var optionButton4: Button
    private lateinit var actionButton: Button
    private lateinit var mercuryQuestionsArrayList: ArrayList<String>
    private lateinit var venusQuestionsArrayList: ArrayList<String>
    private lateinit var mercuryAnswersArrayList: ArrayList<Answer>
    private lateinit var venusAnswersArrayList: ArrayList<Answer>
    private var progress = 0
    private  var db : FirebaseFirestore = FirebaseFirestore.getInstance()
    private  var auth : FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the status bar and navigation bar to transparent
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        // Set the status bar and navigation bar background to transparent
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT

        mercuryQuestionsArrayList = ArrayList()
        mercuryAnswersArrayList = ArrayList()
        venusQuestionsArrayList = ArrayList()
        venusAnswersArrayList = ArrayList()

        // mercury
        mercuryQuestionsArrayList.add("What is one distinctive feature of Mercury's surface?")
        mercuryAnswersArrayList.add(Answer("Extensive volcanic plains.",false))
        mercuryAnswersArrayList.add(Answer("Thick atmosphere.",false))
        mercuryAnswersArrayList.add(Answer("Smooth, liquid surface.",false))
        mercuryAnswersArrayList.add(Answer("Heavily cratered terrain.",true))

        mercuryQuestionsArrayList.add("What type of geological feature covers much of Mercury's surface, indicating past volcanic activity?")
        mercuryAnswersArrayList.add(Answer("Deserts.",false))
        mercuryAnswersArrayList.add(Answer("Oceans.",false))
        mercuryAnswersArrayList.add(Answer("Volcanic plains.",true))
        mercuryAnswersArrayList.add(Answer("Mountain ranges.",false))

        mercuryQuestionsArrayList.add("What are \"catenae\" on Mercury?")
        mercuryAnswersArrayList.add(Answer("Chains of craters formed by a single body that broke apart before impact.",true))
        mercuryAnswersArrayList.add(Answer("Vast expanses of volcanic plains.",false))
        mercuryAnswersArrayList.add(Answer("Deep valleys carved by ancient rivers.",false))
        mercuryAnswersArrayList.add(Answer("Large, circular impact basins.",false))

        mercuryQuestionsArrayList.add("What do the long channels carved into Mercury's surface suggest?")
        mercuryAnswersArrayList.add(Answer("Movement of water.",false))
        mercuryAnswersArrayList.add(Answer("Movement of molten rock.",true))
        mercuryAnswersArrayList.add(Answer("Formation by wind erosion.",false))
        mercuryAnswersArrayList.add(Answer("Presence of underground caves.",false))

        // venus
        mercuryQuestionsArrayList.add("What is one of the key factors that makes Venus the hottest planet in our solar system?")
        mercuryAnswersArrayList.add(Answer("Its proximity to the Sun",false))
        mercuryAnswersArrayList.add(Answer("Its lack of mountains and valleys",false))
        mercuryAnswersArrayList.add(Answer("Its thick, toxic atmosphere",true))
        mercuryAnswersArrayList.add(Answer("Its small size compared to Earth",false))

        mercuryQuestionsArrayList.add("Which distinguishing feature does Venus lack in comparison to Earth?")
        mercuryAnswersArrayList.add(Answer("A magnetic field",true))
        mercuryAnswersArrayList.add(Answer("A recognizable plate tectonic system",false))
        mercuryAnswersArrayList.add(Answer("Similar mass",false))
        mercuryAnswersArrayList.add(Answer("Proximity to the Sun",false))

        mercuryQuestionsArrayList.add("What are the three main topographic units on Venus?")
        mercuryAnswersArrayList.add(Answer("Mountains, Valleys, and Plains",false))
        mercuryAnswersArrayList.add(Answer("Lowlands, Highlands, and Plains",true))
        mercuryAnswersArrayList.add(Answer("Craters, Basins, and Ridges",false))
        mercuryAnswersArrayList.add(Answer("Oceans, Deserts, and Forests",false))

        mercuryQuestionsArrayList.add("What do some studies suggest about Venus's past regarding water?")
        mercuryAnswersArrayList.add(Answer("It had a deep ocean for 2 billion years",false))
        mercuryAnswersArrayList.add(Answer("It never had any water",false))
        mercuryAnswersArrayList.add(Answer("It had a shallow ocean for up to 2 billion years",true))
        mercuryAnswersArrayList.add(Answer("It currently has liquid water on its surface",false))

        // Set the status bar and navigation bar to transparent
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        // Set the status bar and navigation bar background to transparent
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        setContentView(R.layout.activity_quiz)

        initializeUI()
    }

    private fun initializeUI() {
        questionTextView = findViewById(R.id.questionTextView)
        optionButton1 = findViewById(R.id.buttonOption1)
        optionButton2 = findViewById(R.id.buttonOption2)
        optionButton3 = findViewById(R.id.buttonOption3)
        optionButton4 = findViewById(R.id.buttonOption4)
        actionButton = findViewById(R.id.actionButton)
        if (this.intent.getStringExtra("PLANET_NAME").equals("mercury")) {
            resetInterfaceToFirst(mercuryQuestionsArrayList, mercuryAnswersArrayList)
        }
        if (this.intent.getStringExtra("PLANET_NAME").equals("venus")) {
            resetInterfaceToFirst(venusQuestionsArrayList, venusAnswersArrayList)
        }
    }

    private fun resetInterfaceToFirst(questionsArrayList: ArrayList<String>, answersArrayList: ArrayList<Answer>) {
        questionTextView.text = questionsArrayList[0]
        optionButton1.text = answersArrayList[0].answer
        optionButton2.text = answersArrayList[1].answer
        optionButton3.text = answersArrayList[2].answer
        optionButton4.text = answersArrayList[3].answer
        optionButton1.setBackgroundResource(R.drawable.answer_button_background)
        optionButton2.setBackgroundResource(R.drawable.answer_button_background)
        optionButton3.setBackgroundResource(R.drawable.answer_button_background)
        optionButton4.setBackgroundResource(R.drawable.answer_button_background)
        optionButton1.setOnClickListener {
            chooseOneAnswer(1)
            if (answersArrayList[0].correct) {
                actionButton.setOnClickListener {
                    optionButton1.setBackgroundResource(R.drawable.correct_answer_button)
                    optionButton2.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToSecond(questionsArrayList, answersArrayList)
                        optionButton2.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton4.isEnabled = true
                        progress += 25
                    }
                }
            }
            else {
                actionButton.setOnClickListener {
                    optionButton1.setBackgroundResource(R.drawable.wrong_answer_button_background)
                    optionButton2.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToSecond(questionsArrayList, answersArrayList)
                        optionButton2.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton4.isEnabled = true
                    }
                }
            }
        }
        optionButton2.setOnClickListener {
            chooseOneAnswer(2)
            if (answersArrayList[1].correct) {
                actionButton.setOnClickListener {
                    optionButton2.setBackgroundResource(R.drawable.correct_answer_button)
                    optionButton1.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToSecond(questionsArrayList, answersArrayList)
                        optionButton1.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton4.isEnabled = true
                        progress += 25
                    }
                }
            }
            else {
                actionButton.setOnClickListener {
                    optionButton1.setBackgroundResource(R.drawable.wrong_answer_button_background)
                    optionButton1.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToSecond(questionsArrayList, answersArrayList)
                        optionButton1.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton4.isEnabled = true
                    }
                }
            }
        }
        optionButton3.setOnClickListener {
            chooseOneAnswer(3)
            if (answersArrayList[2].correct) {
                actionButton.setOnClickListener {
                    optionButton3.setBackgroundResource(R.drawable.correct_answer_button)
                    optionButton2.isEnabled = false
                    optionButton1.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToSecond(questionsArrayList, answersArrayList)
                        optionButton2.isEnabled = true
                        optionButton1.isEnabled = true
                        optionButton4.isEnabled = true
                        progress += 25
                    }
                }
            }
            else {
                actionButton.setOnClickListener {
                    optionButton3.setBackgroundResource(R.drawable.wrong_answer_button_background)
                    optionButton2.isEnabled = false
                    optionButton1.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToSecond(questionsArrayList, answersArrayList)
                        optionButton2.isEnabled = true
                        optionButton1.isEnabled = true
                        optionButton4.isEnabled = true
                    }
                }
            }
        }
        optionButton4.setOnClickListener {
            chooseOneAnswer(4)
            if (answersArrayList[3].correct) {
                actionButton.setOnClickListener {
                    optionButton4.setBackgroundResource(R.drawable.correct_answer_button)
                    optionButton1.isEnabled = false
                    optionButton2.isEnabled = false
                    optionButton3.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToSecond(questionsArrayList, answersArrayList)
                        optionButton1.isEnabled = true
                        optionButton2.isEnabled = true
                        optionButton3.isEnabled = true
                        progress += 25
                    }
                }
            }
            else {
                actionButton.setOnClickListener {
                    optionButton4.setBackgroundResource(R.drawable.wrong_answer_button_background)
                    optionButton1.isEnabled = false
                    optionButton2.isEnabled = false
                    optionButton3.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToSecond(questionsArrayList, answersArrayList)
                        optionButton1.isEnabled = true
                        optionButton2.isEnabled = true
                        optionButton3.isEnabled = true
                    }
                }
            }
        }
        actionButton.text = "Next"
    }

    private fun resetInterfaceToSecond(questionsArrayList: ArrayList<String>, answersArrayList: ArrayList<Answer>) {
        questionTextView.text = questionsArrayList[1]
        optionButton1.text = answersArrayList[4].answer
        optionButton2.text = answersArrayList[5].answer
        optionButton3.text = answersArrayList[6].answer
        optionButton4.text = answersArrayList[7].answer
        optionButton1.setBackgroundResource(R.drawable.answer_button_background)
        optionButton2.setBackgroundResource(R.drawable.answer_button_background)
        optionButton3.setBackgroundResource(R.drawable.answer_button_background)
        optionButton4.setBackgroundResource(R.drawable.answer_button_background)
        optionButton1.setOnClickListener {
            chooseOneAnswer(1)
            if (answersArrayList[4].correct) {
                actionButton.setOnClickListener {
                    optionButton1.setBackgroundResource(R.drawable.correct_answer_button)
                    optionButton2.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToThird(questionsArrayList, answersArrayList)
                        optionButton2.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton4.isEnabled = true
                        progress += 25
                    }
                }
            }
            else {
                actionButton.setOnClickListener {
                    optionButton1.setBackgroundResource(R.drawable.wrong_answer_button_background)
                    optionButton2.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToThird(questionsArrayList, answersArrayList)
                        optionButton2.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton4.isEnabled = true
                    }
                }
            }
        }
        optionButton2.setOnClickListener {
            chooseOneAnswer(2)
            if (answersArrayList[5].correct) {
                actionButton.setOnClickListener {
                    optionButton2.setBackgroundResource(R.drawable.correct_answer_button)
                    optionButton1.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToThird(questionsArrayList, answersArrayList)
                        optionButton1.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton4.isEnabled = true
                        progress += 25
                    }
                }
            }
            else {
                actionButton.setOnClickListener {
                    optionButton2.setBackgroundResource(R.drawable.wrong_answer_button_background)
                    optionButton1.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToThird(questionsArrayList, answersArrayList)
                        optionButton1.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton4.isEnabled = true
                    }
                }
            }
        }
        optionButton3.setOnClickListener {
            chooseOneAnswer(3)
            if (answersArrayList[6].correct) {
                actionButton.setOnClickListener {
                    optionButton3.setBackgroundResource(R.drawable.correct_answer_button)
                    optionButton2.isEnabled = false
                    optionButton1.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToThird(questionsArrayList, answersArrayList)
                        optionButton2.isEnabled = true
                        optionButton1.isEnabled = true
                        optionButton4.isEnabled = true
                        progress += 25
                    }
                }
            }
            else {
                actionButton.setOnClickListener {
                    optionButton3.setBackgroundResource(R.drawable.wrong_answer_button_background)
                    optionButton2.isEnabled = false
                    optionButton1.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToThird(questionsArrayList, answersArrayList)
                        optionButton2.isEnabled = true
                        optionButton1.isEnabled = true
                        optionButton4.isEnabled = true
                    }
                }
            }
        }
        optionButton4.setOnClickListener {
            chooseOneAnswer(4)
            if (answersArrayList[7].correct) {
                actionButton.setOnClickListener {
                    optionButton4.setBackgroundResource(R.drawable.correct_answer_button)
                    optionButton2.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton1.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToThird(questionsArrayList, answersArrayList)
                        optionButton2.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton1.isEnabled = true
                        progress += 25
                    }
                }
            }
            else {
                actionButton.setOnClickListener {
                    optionButton4.setBackgroundResource(R.drawable.wrong_answer_button_background)
                    optionButton2.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton1.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToThird(questionsArrayList, answersArrayList)
                        optionButton2.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton1.isEnabled = true
                    }
                }
            }
        }
        actionButton.text = "Next"
    }

    private fun resetInterfaceToThird(questionsArrayList: ArrayList<String>, answersArrayList: ArrayList<Answer>) {
        questionTextView.text = questionsArrayList[2]
        optionButton1.text = answersArrayList[8].answer
        optionButton2.text = answersArrayList[9].answer
        optionButton3.text = answersArrayList[10].answer
        optionButton4.text = answersArrayList[11].answer
        optionButton1.setBackgroundResource(R.drawable.answer_button_background)
        optionButton2.setBackgroundResource(R.drawable.answer_button_background)
        optionButton3.setBackgroundResource(R.drawable.answer_button_background)
        optionButton4.setBackgroundResource(R.drawable.answer_button_background)
        optionButton1.setOnClickListener {
            chooseOneAnswer(1)
            if (answersArrayList[8].correct) {
                actionButton.setOnClickListener {
                    optionButton1.setBackgroundResource(R.drawable.correct_answer_button)
                    optionButton2.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToFourth(questionsArrayList, answersArrayList)
                        optionButton2.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton4.isEnabled = true
                        progress += 25
                    }
                }
            }
            else {
                actionButton.setOnClickListener {
                    optionButton1.setBackgroundResource(R.drawable.wrong_answer_button_background)
                    optionButton2.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToFourth(questionsArrayList, answersArrayList)
                        optionButton2.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton4.isEnabled = true
                    }
                }
            }
        }
        optionButton2.setOnClickListener {
            chooseOneAnswer(2)
            if (answersArrayList[9].correct) {
                actionButton.setOnClickListener {
                    optionButton2.setBackgroundResource(R.drawable.correct_answer_button)
                    optionButton1.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToFourth(questionsArrayList, answersArrayList)
                        optionButton1.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton4.isEnabled = true
                        progress += 25
                    }
                }
            }
            else {
                actionButton.setOnClickListener {
                    optionButton2.setBackgroundResource(R.drawable.wrong_answer_button_background)
                    optionButton1.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToFourth(questionsArrayList, answersArrayList)
                        optionButton1.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton4.isEnabled = true
                    }
                }
            }
        }
        optionButton3.setOnClickListener {
            chooseOneAnswer(3)
            if (answersArrayList[10].correct) {
                actionButton.setOnClickListener {
                    optionButton3.setBackgroundResource(R.drawable.correct_answer_button)
                    optionButton2.isEnabled = false
                    optionButton1.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToFourth(questionsArrayList, answersArrayList)
                        optionButton2.isEnabled = true
                        optionButton1.isEnabled = true
                        optionButton4.isEnabled = true
                        progress += 25
                    }
                }
            }
            else {
                actionButton.setOnClickListener {
                    optionButton3.setBackgroundResource(R.drawable.wrong_answer_button_background)
                    optionButton2.isEnabled = false
                    optionButton1.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToFourth(questionsArrayList, answersArrayList)
                        optionButton2.isEnabled = true
                        optionButton1.isEnabled = true
                        optionButton4.isEnabled = true
                    }
                }
            }
        }
        optionButton4.setOnClickListener {
            chooseOneAnswer(4)
            if (answersArrayList[7].correct) {
                actionButton.setOnClickListener {
                    optionButton4.setBackgroundResource(R.drawable.correct_answer_button)
                    optionButton2.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton1.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToFourth(questionsArrayList, answersArrayList)
                        optionButton2.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton1.isEnabled = true
                        progress += 25
                    }
                }
            }
            else {
                actionButton.setOnClickListener {
                    optionButton4.setBackgroundResource(R.drawable.wrong_answer_button_background)
                    optionButton2.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton1.isEnabled = false
                    actionButton.setOnClickListener {
                        resetInterfaceToFourth(questionsArrayList, answersArrayList)
                        optionButton2.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton1.isEnabled = true
                    }
                }
            }
        }
        actionButton.text = "Next"
    }

    private fun resetInterfaceToFourth(questionsArrayList: ArrayList<String>, answersArrayList: ArrayList<Answer>) {
        questionTextView.text = questionsArrayList[3]
        optionButton1.text = answersArrayList[12].answer
        optionButton2.text = answersArrayList[13].answer
        optionButton3.text = answersArrayList[14].answer
        optionButton4.text = answersArrayList[15].answer
        optionButton1.setBackgroundResource(R.drawable.answer_button_background)
        optionButton2.setBackgroundResource(R.drawable.answer_button_background)
        optionButton3.setBackgroundResource(R.drawable.answer_button_background)
        optionButton4.setBackgroundResource(R.drawable.answer_button_background)
        optionButton1.setOnClickListener {
            chooseOneAnswer(1)
            if (answersArrayList[12].correct) {
                actionButton.setOnClickListener {
                    optionButton1.setBackgroundResource(R.drawable.correct_answer_button)
                    optionButton2.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        optionButton2.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton4.isEnabled = true
                        progress += 25
                        if (progress == 100) {
                            intent =  Intent(this,ResultActivity::class.java)
                            startActivity(intent)
                        } else {
                            // here you send progress to the database
                            val updates = hashMapOf<String,Any>(
                                "mercury" to progress.toString()
                            )
                            db.collection("users").document(auth.currentUser!!.uid).update(updates)
                            intent =  Intent(this,HomeActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }
            else {
                actionButton.setOnClickListener {
                    optionButton1.setBackgroundResource(R.drawable.wrong_answer_button_background)
                    optionButton2.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        if (progress == 100) {
                            intent =  Intent(this,ResultActivity::class.java)
                            startActivity(intent)
                        } else {
                            // here you send progress to the database
                            val updates = hashMapOf<String,Any>(
                                "mercury" to progress.toString()
                            )
                            db.collection("users").document(auth.currentUser!!.uid).update(updates)
                            intent =  Intent(this,HomeActivity::class.java)
                            startActivity(intent)
                        }
                        optionButton2.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton4.isEnabled = true
                    }
                }
            }
        }
        optionButton2.setOnClickListener {
            chooseOneAnswer(2)
            if (answersArrayList[13].correct) {
                actionButton.setOnClickListener {
                    optionButton2.setBackgroundResource(R.drawable.correct_answer_button)
                    optionButton1.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        optionButton1.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton4.isEnabled = true
                        progress += 25
                        if (progress == 100) {
                            intent =  Intent(this,ResultActivity::class.java)
                            startActivity(intent)
                        } else {
                            // here you send progress to the database
                            val updates = hashMapOf<String,Any>(
                                "mercury" to progress.toString()
                            )
                            db.collection("users").document(auth.currentUser!!.uid).update(updates)
                            intent =  Intent(this,HomeActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }
            else {
                actionButton.setOnClickListener {
                    optionButton2.setBackgroundResource(R.drawable.wrong_answer_button_background)
                    optionButton1.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        if (progress == 100) {
                            intent =  Intent(this,ResultActivity::class.java)
                            startActivity(intent)
                        } else {
                            // here you send progress to the database
                            val updates = hashMapOf<String,Any>(
                                "mercury" to progress.toString()
                            )
                            db.collection("users").document(auth.currentUser!!.uid).update(updates)
                            intent =  Intent(this,HomeActivity::class.java)
                            startActivity(intent)
                        }
                        optionButton1.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton4.isEnabled = true
                    }
                }
            }
        }
        optionButton3.setOnClickListener {
            chooseOneAnswer(3)
            if (answersArrayList[14].correct) {
                actionButton.setOnClickListener {
                    optionButton3.setBackgroundResource(R.drawable.correct_answer_button)
                    optionButton2.isEnabled = false
                    optionButton1.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        optionButton2.isEnabled = true
                        optionButton1.isEnabled = true
                        optionButton4.isEnabled = true
                        progress += 25
                        if (progress == 100) {
                            intent =  Intent(this,ResultActivity::class.java)
                            startActivity(intent)
                        } else {
                            // here you send progress to the database
                            val updates = hashMapOf<String,Any>(
                                "mercury" to progress.toString()
                            )
                            db.collection("users").document(auth.currentUser!!.uid).update(updates).addOnSuccessListener {
                                intent =  Intent(this,HomeActivity::class.java)
                                startActivity(intent)
                            }

                        }
                    }
                }
            }
            else {
                actionButton.setOnClickListener {
                    optionButton3.setBackgroundResource(R.drawable.wrong_answer_button_background)
                    optionButton2.isEnabled = false
                    optionButton1.isEnabled = false
                    optionButton4.isEnabled = false
                    actionButton.setOnClickListener {
                        if (progress == 100) {
                            intent =  Intent(this,ResultActivity::class.java)
                            startActivity(intent)
                        } else {
                            // here you send progress to the database
                            val updates = hashMapOf<String,Any>(
                                "mercury" to progress.toString()
                            )
                            db.collection("users").document(auth.currentUser!!.uid).update(updates).addOnSuccessListener {
                                intent =  Intent(this,HomeActivity::class.java)
                                startActivity(intent)
                            }
                        }
                        optionButton2.isEnabled = true
                        optionButton1.isEnabled = true
                        optionButton4.isEnabled = true
                    }
                }
            }
        }
        optionButton4.setOnClickListener {
            chooseOneAnswer(4)
            if (answersArrayList[15].correct) {
                actionButton.setOnClickListener {
                    optionButton4.setBackgroundResource(R.drawable.correct_answer_button)
                    optionButton2.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton1.isEnabled = false
                    actionButton.setOnClickListener {
                        optionButton2.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton1.isEnabled = true
                        progress += 25
                        if (progress == 100) {
                            intent =  Intent(this,ResultActivity::class.java)
                            startActivity(intent)
                        } else {
                            // here you send progress to the database
                            val updates = hashMapOf<String,Any>(
                                "mercury" to progress.toString()
                            )
                            db.collection("users").document(auth.currentUser!!.uid).update(updates).addOnSuccessListener {
                                intent =  Intent(this,HomeActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                }
            }
            else {
                actionButton.setOnClickListener {
                    optionButton4.setBackgroundResource(R.drawable.wrong_answer_button_background)
                    optionButton2.isEnabled = false
                    optionButton3.isEnabled = false
                    optionButton1.isEnabled = false
                    actionButton.setOnClickListener {
                        optionButton2.isEnabled = true
                        optionButton3.isEnabled = true
                        optionButton1.isEnabled = true
                        if (progress == 100) {
                            intent =  Intent(this,ResultActivity::class.java)
                            startActivity(intent)
                        } else {
                            // here you send progress to the database
                            val updates = hashMapOf<String,Any>(
                                "mercury" to progress.toString()
                            )
                            db.collection("users").document(auth.currentUser!!.uid).update(updates).addOnSuccessListener {
                                intent =  Intent(this,HomeActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
        actionButton.text = "Next"
    }
    private fun chooseOneAnswer(i: Int) {
        when (i) {
            1 -> {
                optionButton1.setBackgroundResource(R.drawable.selected_answer_button_background)
                optionButton2.setBackgroundResource(R.drawable.answer_button_background)
                optionButton3.setBackgroundResource(R.drawable.answer_button_background)
                optionButton4.setBackgroundResource(R.drawable.answer_button_background)
            }
            2 -> {
                optionButton1.setBackgroundResource(R.drawable.answer_button_background)
                optionButton2.setBackgroundResource(R.drawable.selected_answer_button_background)
                optionButton3.setBackgroundResource(R.drawable.answer_button_background)
                optionButton4.setBackgroundResource(R.drawable.answer_button_background)
            }
            3 -> {
                optionButton1.setBackgroundResource(R.drawable.answer_button_background)
                optionButton2.setBackgroundResource(R.drawable.answer_button_background)
                optionButton3.setBackgroundResource(R.drawable.selected_answer_button_background)
                optionButton4.setBackgroundResource(R.drawable.answer_button_background)
            }
            4 -> {
                optionButton1.setBackgroundResource(R.drawable.answer_button_background)
                optionButton2.setBackgroundResource(R.drawable.answer_button_background)
                optionButton3.setBackgroundResource(R.drawable.answer_button_background)
                optionButton4.setBackgroundResource(R.drawable.selected_answer_button_background)
            }
        }
    }
}