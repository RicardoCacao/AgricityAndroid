package com.example.agricitytest2

enum class GraphType{
    PLOT, PIE, BAR
}
class Graph(val domain: String,val Label: String,val type: GraphType) {
}