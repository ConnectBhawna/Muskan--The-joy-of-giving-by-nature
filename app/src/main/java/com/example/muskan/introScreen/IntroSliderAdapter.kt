package com.example.muskan.introScreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.muskan.databinding.IntroSliderBinding

class IntroSliderAdapter(private val context: Context):PagerAdapter() {
    val pages = listOf<IntroClass>(
        IntroClass("Heading 1","Welcome To Muskan an app for the joy of giving by nature. This will be the content to the heading"),
        IntroClass("Heading 2","Welcome To Muskan an app for the joy of giving by nature. This will be the content to the heading"),
        IntroClass("Heading 3","Welcome To Muskan an app for the joy of giving by nature. This will be the content to the heading"),
    )
    override fun getCount(): Int {
        return pages.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==(`object` as View?)
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding= IntroSliderBinding.inflate(layoutInflater,container,false)
        binding.introClass = pages[position]
        container.addView(binding.root)
        return binding.root
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
}