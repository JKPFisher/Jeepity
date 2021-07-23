package com.example.jeepitybasic.models


data class BlogPost(

    var title: String,



    var username: String // Author of blog post


) {

    override fun toString(): String {
        return "BlogPost(title='$title', username='$username')"
    }


}
























