package com.tanvi.newsfresh.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Article{
@SerializedName("source")
@Expose
 var source:Source?=null
    @SerializedName("author")
    @Expose
    var author:String?=null
    @SerializedName("content")
    @Expose
    var content:String?=null
    @SerializedName("description")
    @Expose
    var description:String?=null
    @SerializedName("publishedAt")
    @Expose
    var date:String?=null
  @SerializedName("title")

  @Expose
  var title:String?=null
    @SerializedName("url")
    @Expose
    var url:String?=null
//urlToImage
    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String?=null
 }