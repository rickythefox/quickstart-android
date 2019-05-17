package com.google.firebase.quickstart.analytics.kotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.quickstart.analytics.R

/**
 * This fragment displays a featured, specified image.
 */
class ImageFragment : Fragment() {

    private var resId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            resId = it.getInt(ARG_PATTERN)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, null)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(resId)
        imageView.setOnClickListener(View.OnClickListener {
            logFirebaseEvents()
        })

        return view
    }

    private fun logFirebaseEvents() {
        val addToCartBundle = Bundle()

        addToCartBundle.putString(FirebaseAnalytics.Param.ITEM_ID, "123456")
        addToCartBundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Test product")
        addToCartBundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "Test category")
        addToCartBundle.putLong(FirebaseAnalytics.Param.QUANTITY, 1L)
        addToCartBundle.putDouble(FirebaseAnalytics.Param.PRICE, 123.0)
        addToCartBundle.putDouble(FirebaseAnalytics.Param.VALUE, 123.0)
        addToCartBundle.putString(FirebaseAnalytics.Param.CURRENCY, "SEK")

        context?.let {
            FirebaseAnalytics.getInstance(it).logEvent(FirebaseAnalytics.Event.ADD_TO_CART, addToCartBundle)
            Toast.makeText(it, "Sent ${FirebaseAnalytics.Event.ADD_TO_CART}", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val ARG_PATTERN = "pattern"

        /**
         * Create a [ImageFragment] displaying the given image.
         *
         * @param resId to display as the featured image
         * @return a new instance of [ImageFragment]
         */
        fun newInstance(resId: Int): ImageFragment {
            val fragment = ImageFragment()
            val args = Bundle()
            args.putInt(ARG_PATTERN, resId)
            fragment.arguments = args
            return fragment
        }
    }
}