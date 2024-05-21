package com.hd.ibrarybase.media

import android.media.MediaPlayer
import android.os.Build
import android.widget.VideoView
import androidx.annotation.RequiresApi
import com.hd.ibrarybase.utils.L
import java.lang.Exception

/**
 * 作者 YuQi
 * 注意代码尽量不要有警告
 * 2023/10/26
 **/
class VideoViewUtils(view: VideoView, listener: OnVideoViewListener) {
    private val videoView = view
    private  val onVideoViewListener=listener

    @RequiresApi(Build.VERSION_CODES.O)
    fun initVideoView(path: String) {
        videoView.setVideoPath(path)
        videoView.setOnPreparedListener {
            it.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING)
            videoView.setOnInfoListener { _, what, _ ->
                onVideoViewListener.onInfo(what)
//                if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
//        //                    simple_video_bg.visibility = View.GONE
//                }
                true

            }
        }
        videoView.setOnCompletionListener {
            onVideoViewListener.onComplete(it)
    //            isPlaying = false

            //            mBinding?.btnGuide?.visibility = View.VISIBLE
    //
    //            mBinding?.btnGuide?.setImageResource(R.drawable.icon_start)
            it?.seekTo(1, MediaPlayer.SEEK_CLOSEST)
        }
    }

    /**
     * 视频播放
     */
    fun startPlay() {
        try {
            if (videoView.isPlaying) return
            videoView.start()
        } catch (e: Exception) {
            L.e("音频播放器  $e")
        }
    }

    fun pausePlay() {
        videoView.pause()
    }


    interface  OnVideoViewListener{
       fun  onComplete(it:MediaPlayer)

       fun onInfo(it: Int)
    }
}