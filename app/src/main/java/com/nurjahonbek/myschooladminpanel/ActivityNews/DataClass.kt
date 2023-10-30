package com.nurjahonbek.myschooladminpanel.ActivityNews

 class DataClass{
     var id :String =""
     var timestamp :Long =0
     var imageUrl: String = ""
     var title: String = ""
     var description: String = ""

     constructor(id: String, timestamp: Long, imageUrl: String, title: String, description: String) {
         this.id = id
         this.timestamp = timestamp
         this.imageUrl = imageUrl
         this.title = title
         this.description = description
     }

     constructor()

 }

