package com.example.jeepitybasic



import com.example.jeepitybasic.models.BlogPost

class DataSource{

    companion object{

        fun createDataSet(): ArrayList<BlogPost>{
            val list = ArrayList<BlogPost>()
            list.add(
                BlogPost(
                    "Stop",

                    "Parra / Pa-ruh"
                )
            )
            list.add(
                BlogPost(
                    "Where is the jeep station for ...?",

                    "Saan ang Istasyon ng jeep ...?"
                )
            )

            list.add(
                BlogPost(
                    "How can I get there?",

                    "Paano ako makakarating doon?"
                )
            )
            list.add(
                BlogPost(
                    "Where are you? ",

                    "Nasaan ka?"
                )
            )
            list.add(
                BlogPost(
                    "I'm at...",

                    "Ako ay nasa…"
                )
            )
            list.add(
                BlogPost(
                    "The last jeep has already gone.",

                    "Ang huling jeep ay umalis"
                )
            )
            list.add(
                BlogPost(
                    "How much?",

                    "Magkano"
                )
            )
            list.add(
                BlogPost(
                    "Please take me to...",

                    "Pakiusap, Paki hatid ako sa..."
                )
            )
            list.add(
                BlogPost(
                    "I need change for..",

                    "Sukli yung..."
                )
            )
            list.add(
                BlogPost(
                    "Can you pass this fare?",

                    "Bayad"
                )
            )
            return list
        }
    }
}