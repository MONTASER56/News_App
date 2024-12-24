package com.example.test23.adapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test23.R
import com.example.test23.reopsitry.ResponseStats
import com.google.android.material.imageview.ShapeableImageView


@BindingAdapter(value = ["imageUrl"])
fun setImage(view: ShapeableImageView,url:String?){
    if (url.isNullOrEmpty()){
view.setImageResource(R.drawable.ic_baseline_home_24)
        Log.d("TAG", "setImage: isNullOrEmpty")
    }
    else
    Glide.with(view).load(url).into(view)
}
@BindingAdapter(value = ["coilUrl"])
fun coil(view: ShapeableImageView,url:String?){
    if (url.isNullOrEmpty()){
        view.setImageResource(R.drawable.ic_baseline_home_24)
        Log.d("TAG", "setImage: isNullOrEmpty")
    }
    else{
    }
        }































//@BindingAdapter(value = ["colors"])
//fun setColors(view: View,colors: MyColors?){
//    when(colors){
//        MyColors.RED -> view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.purple_200))
//        MyColors.YELLOW -> view.setBackgroundColor(ContextCompat.getColor(view.context,
//            R.color.teal_700
//        ))
//        MyColors.BLUE -> view.setBackgroundColor(ContextCompat.getColor(view.context,
//            R.color.teal_200
//        ))
//        else -> {}
//    }


@BindingAdapter(value = ["stats"])
fun setVis(view: View,stats:Boolean?){
    if (stats==true){
        view.visibility=View.VISIBLE
    }
    else{
        view.visibility=View.GONE
    }
}


@BindingAdapter(value = ["showWhenError"])
fun <T>showWhenError(view: View,responseStats: ResponseStats<T>?){
    if (responseStats is ResponseStats.Error){
        view.visibility=View.VISIBLE
        Log.d("TAG", "showWhenError: ")
    }
    else view.visibility=View.GONE
}
@BindingAdapter(value = ["showWhenSuccess"])
fun <T>showWhenSuccess(view: View,responseStats: ResponseStats<T>?) {
    if (responseStats is ResponseStats.Success){
        view.visibility=View.VISIBLE
        Log.d("TAG", "showWhenSuccess:${view.toString()} ")
    }
    else view.visibility=View.GONE
}
@BindingAdapter(value = ["showWhenLoading"])
fun <T>showWhenLoading(view: View,responseStats: ResponseStats<T>?) {
    if (responseStats is ResponseStats.loading){
        view.visibility=View.VISIBLE
        Log.d("TAG", "showWhenLoading: ")
    }
    else view.visibility=View.GONE
}





