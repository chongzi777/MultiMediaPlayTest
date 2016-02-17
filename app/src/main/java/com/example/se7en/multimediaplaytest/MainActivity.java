package com.example.se7en.multimediaplaytest;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //音频
    private Button audioPlayBtn;
    private Button audioPauseBtn;
    private Button audioStopBtn;
    private MediaPlayer mediaPlayer = new MediaPlayer();

    //视频
    private Button videoPlayBtn;
    private Button videoPauseBtn;
    private Button videoStopBtn;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioPlayBtn = (Button)findViewById(R.id.audioPlayBtn);
        audioPauseBtn = (Button)findViewById(R.id.audioPauseBtn);
        audioStopBtn = (Button)findViewById(R.id.audioStopBtn);
        audioPlayBtn.setOnClickListener(this);
        audioPauseBtn.setOnClickListener(this);
        audioStopBtn.setOnClickListener(this);
        initMediaPlayer();  //初始化音频播放组件以及音频文件

        videoView = (VideoView)findViewById(R.id.videoView);
        videoPlayBtn = (Button)findViewById(R.id.videoPlayBtn);
        videoPauseBtn = (Button)findViewById(R.id.videoPauseBtn);
        videoStopBtn = (Button)findViewById(R.id.videoStopBtn);
        videoPlayBtn.setOnClickListener(this);
        videoPauseBtn.setOnClickListener(this);
        videoStopBtn.setOnClickListener(this);
        initVideoPath();  //设置视频文件路径

    }

    /**
     * 初始化视频信息
     */
    private void initVideoPath(){
        File file = new File(Environment.getExternalStorageDirectory(),"movie.mp4");
        videoView.setVideoPath(file.getPath());
    }

    /**
     * 准备音频文件和启动mediaPlay
     */
    private void initMediaPlayer(){
        File file = new File(Environment.getExternalStorageDirectory(),"music.mp3");
        try {
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.audioPlayBtn:
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }
                break;
            case R.id.audioPauseBtn:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
                break;
            case R.id.audioStopBtn:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
                break;
            case R.id.videoPlayBtn:
                if(!videoView.isPlaying()){
                    videoView.start();
                }
                break;
            case R.id.videoPauseBtn:
                if(videoView.isPlaying()){
                    videoView.pause();
                }
                break;
            case R.id.videoStopBtn:
                if(videoView.isPlaying()){
                    videoView.resume();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        if(videoView!=null){
            videoView.suspend();
        }
    }
}
