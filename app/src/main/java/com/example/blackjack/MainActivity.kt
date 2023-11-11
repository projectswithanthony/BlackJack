package com.example.blackjack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val game = BlackJack();

        val hit: Button = findViewById(R.id.hitButton);
        hit.setOnClickListener {
            dealPlayerCard(game);
        };





    }
}

fun dealPlayerCard(game: BlackJack) {
    game.dealCardPlayer(game.generateRandom());
}



