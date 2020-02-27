package com.example.android.songdetail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.android.songdetail.content.SongUtils

class SongDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var selectedSong: Int? = null
    lateinit var mSong : SongUtils.Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if(arguments.containsKey(SongUtils.SONG_ID_KEY)){
                mSong = SongUtils.SONG_ITEMS.get(arguments.getInt(SongUtils.SONG_ID_KEY))
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.song_detail, container, false)

        if(::mSong.isInitialized){
            rootView.findViewById<TextView>(R.id.song_detail).text = mSong.details
        }

        return rootView
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(selectedSong: Int) =
                SongDetailFragment().apply {
                    arguments = Bundle().apply {
                        putInt(SongUtils.SONG_ID_KEY, selectedSong)
                    }
                }
    }
}
