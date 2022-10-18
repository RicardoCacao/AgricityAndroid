package com.example.agricitytest2

import androidx.appcompat.app.AppCompatActivity

enum class GraphType{
    PLOT, PIE, BAR
}
class Graph(val domain: String,val Label: String,val type: GraphType) : AppCompatActivity() {



}