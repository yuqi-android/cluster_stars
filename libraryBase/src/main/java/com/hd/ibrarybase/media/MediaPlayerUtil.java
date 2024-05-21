package com.hd.ibrarybase.media;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;


import java.io.IOException;

public class MediaPlayerUtil {

    private MediaPlayer mPlayer;
    private boolean mStarted;
    private boolean mPaused;
    private boolean mDestroy;
    private ActionListener mActionListener;
    private String bgmName;
    private Context context;
    private final MediaPlayer.OnPreparedListener mOnPreparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            if (mDestroy) {
                destroy();
            } else {
                mPlayer.start();
                mStarted = true;
                if (mActionListener != null) {
                    mActionListener.onPrepared();
                }
            }
        }
    };

    private final MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            mStarted = false;
            if (mActionListener != null) {
                mActionListener.onPlayEnd(bgmName);

            }
        }
    };

    private final MediaPlayer.OnErrorListener mOnErrorListener = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            mStarted = false;
            if (mActionListener != null) {
                mActionListener.onError();
            }
            return false;
        }
    };

    public MediaPlayerUtil(Context context) {
        init(context);
    }


    private void init(Context context) {
        this.context = context;
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
            mPlayer.setOnPreparedListener(mOnPreparedListener);
            mPlayer.setOnErrorListener(mOnErrorListener);
            mPlayer.setOnCompletionListener(mOnCompletionListener);
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        }

    }

    synchronized public void playMusic(Context context, String bgName) {
        if (context == null) {
            return;
        }
//        if (!Constant.canVoiceGuide) {
//            return;
//        }
        try {
            this.bgmName = bgName;
            if (mStarted || mPlayer.isPlaying())
                stopPlay();
            mPlayer.reset();
            AssetFileDescriptor fd = context.getAssets().openFd(bgName);
            mPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            startPlay(bgName.equals("breath_guide.mp3"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            mPlayer=null;
            mPlayer=new MediaPlayer();
            mPlayer.stop();
            mStarted = false;
            if (mActionListener != null) {
                mActionListener.onPlayEnd(bgmName);
            }
        }
    }

    public void playMusic(String bgName) {
        if (context == null) {
            return;
        }
//        if (!Constant.canVoiceGuide) {
//            return;
//        }
        try {
            this.bgmName = bgName;
            if (mStarted || mPlayer.isPlaying())
                stopPlay();
            mPlayer.reset();
            AssetFileDescriptor fd = context.getAssets().openFd(bgName);
            mPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            startPlay(bgName.equals("breath_guide.mp3"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            mPlayer=null;
            mPlayer=new MediaPlayer();
            mPlayer.stop();
            mStarted = false;
            if (mActionListener != null) {
                mActionListener.onPlayEnd(bgmName);
            }
        }
    }

    public void playBGM(String bgName) {
        if (context == null) {
            return;
        }
        try {
            this.bgmName = bgName;
            if (mStarted || mPlayer.isPlaying())
                stopPlay();
            mPlayer.reset();
            AssetFileDescriptor fd = context.getAssets().openFd(bgName);
            mPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            startPlay(true);

        } catch (IllegalStateException e) {
            mPlayer=null;
            mPlayer=new MediaPlayer();
            mPlayer.stop();
            mStarted = false;
            if (mActionListener != null) {
                mActionListener.onPlayEnd(bgmName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playMusic(String bgName, boolean isLoop) {
        if (context == null) {
            return;
        }
//        if (!Constant.canVoiceGuide) {
//            return;
//        }
        try {
            this.bgmName = bgName;
            if (mStarted || mPlayer.isPlaying())
                stopPlay();
            mPlayer.reset();
            AssetFileDescriptor fd = context.getAssets().openFd(bgName);
            mPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            startPlay(isLoop);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            mPlayer=null;
            mPlayer=new MediaPlayer();
            mPlayer.stop();
            mStarted = false;
            if (mActionListener != null) {
                mActionListener.onPlayEnd(bgmName);
            }
        }
    }

    public void startPlay(boolean isLooping) {
        if (!mStarted) {
            try {
                mPlayer.prepare();
                mPlayer.setLooping(isLooping);
                mPlayer.setVolume(1f, 1f);
                mPlayer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mStarted = false;
            try {
                mPlayer.stop();
                mPlayer.reset();
                mPlayer.setLooping(false);
                mPlayer.setVolume(1f, 1f);
                mPlayer.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getCurPosition() {
        if (mPlayer != null) {
            return mPlayer.getCurrentPosition() / 1000;
        }
        return 0;
    }

    public void pausePlay() {
        if (mPlayer != null && mStarted && !mDestroy) {
            mPlayer.pause();
            mPaused = true;
        }
    }

    public void resumePlay() {
        if (mPlayer != null && mStarted && !mDestroy && mPaused) {
            mPaused = false;
            mPlayer.start();
        }
    }

    public boolean isPaused() {
        return mPlayer != null && mStarted && !mDestroy && mPaused;
    }

    // TODO 后期做更改音量减弱
    public void setVolume() {
        mPlayer.setVolume(0.2f, 0.2f);
    }

    public void setVolumeChange() {
        mPlayer.setVolume(0.8f, 0.8f);
    }

    public void setVolumeCandleChange() {
        mPlayer.setVolume(0.6f, 0.6f);
    }

    public void stopPlay() {
        if (mPlayer != null && mStarted) {
            mPlayer.stop();
        }
        mStarted = false;
    }

    public void destroy() {
        stopPlay();
        if (mPlayer != null) {
            mPlayer.release();
        }
        mActionListener = null;
        mDestroy = true;
    }

    public interface ActionListener {

        void onPrepared();

        void onError();

        void onPlayEnd(String name);
    }

    public void setActionListener(ActionListener actionListener) {
        mActionListener = actionListener;
    }

    public boolean isStarted() {
        return mStarted;
    }
}
