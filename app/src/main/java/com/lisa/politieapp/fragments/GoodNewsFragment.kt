package com.lisa.politieapp.fragments

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.VideoView
import androidx.fragment.app.FragmentManager
import com.lisa.politieapp.HomeActivity
import com.lisa.politieapp.R

class GoodNewsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_good_news, container, false)

        val backButton = view.findViewById<Button>(R.id.goodNewsBtnHome)
        // Stel een OnClickListener in voor de terugknop
        backButton.setOnClickListener {
            // Maak een nieuwe intent om de HomeActivity te starten
            val intent = Intent(requireContext(), HomeActivity::class.java)
            // Start de activiteit
            startActivity(intent)
            // Sluit de huidige activiteit
            requireActivity().finish()
        }

        val categoryButton = view.findViewById<ImageButton>(R.id.goodNewsBtnCategory)
        // Stel een OnClickListener in voor de categorieknop
        categoryButton.setOnClickListener {
            OpenCategory()
        }

        val imageViewPeperbus = view.findViewById<ImageView>(R.id.goodNewsImgPeperbus)
        // scale animatie maken
        val scaleAnimation = ObjectAnimator.ofPropertyValuesHolder(
            imageViewPeperbus,
            PropertyValuesHolder.ofFloat("scaleX", 0f, 1.1f),
            PropertyValuesHolder.ofFloat("scaleY", 0f, 1.1f)
        )        // Lengte van de animatie in milliseconden
        scaleAnimation.duration = 10000
        scaleAnimation.start()

        val videoView = view.findViewById<VideoView>(R.id.goodNewsVideoView)
        // De locatie van de video instellen
        val videoPath = "android.resource://" + requireActivity().packageName + "/" + R.raw.bergenopzoom
        // URI voor de video maken
        val uri = Uri.parse(videoPath)
        // De URI instellen voor de videoView
        videoView.setVideoURI(uri)
        // Start de video
        videoView.start()

        val imageViewLogo1: ImageView = view.findViewById(R.id.goodNewsImgLogo1)
        rotateImageRight(imageViewLogo1)
        val imageViewLogo2: ImageView = view.findViewById(R.id.goodNewsImgLogo2)
        rotateImageLeft(imageViewLogo2)

        return view
    }

    private fun OpenCategory() {
        var categoryFragment = CategoryFragment()
        var fm : FragmentManager = parentFragmentManager
        var ft = fm.beginTransaction()
        ft.add(R.id.fragmentGoodNewsView, categoryFragment)
        ft.commit()
    }

    private fun rotateImageLeft(imageView: ImageView) {
        // Rotatie animatie maken
        val rotateAnimation = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f)
        // Lengte van de animatie in milliseconden
        rotateAnimation.duration = 4000
        // Aantal keer dat de animatie herhaald moet worden
        rotateAnimation.repeatCount = ObjectAnimator.INFINITE
        rotateAnimation.start()
    }

    private fun rotateImageRight(imageView: ImageView) {
        // Rotatie animatie maken
        val rotateAnimation = ObjectAnimator.ofFloat(imageView, "rotation", 360f, 0f)
        // Lengte van de animatie in milliseconden
        rotateAnimation.duration = 4000
        // Aantal keer dat de animatie herhaald moet worden
        rotateAnimation.repeatCount = ObjectAnimator.INFINITE
        rotateAnimation.start()
    }
}