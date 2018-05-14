package com.udacity.zeban.baking.presentation.recipe_detail;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;
import com.udacity.zeban.baking.R;
import com.udacity.zeban.baking.data.models.Step;
import com.udacity.zeban.baking.databinding.FragmentStepDetailBinding;

public class StepDetailFragment extends Fragment implements Player.EventListener {

    public static final String ARG_STEP = "step_key";

    private Step step;

    private FragmentStepDetailBinding binding;

    private SimpleExoPlayer exoPlayer;
    private MediaSessionCompat mediaSession;
    private PlaybackStateCompat.Builder stateBuilder;


    private StepDetailViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_step_detail, container, false);

        initArguments();

        StepDetailViewModelFactory factory = new StepDetailViewModelFactory(step);
        viewModel = ViewModelProviders.of(this, factory).get(StepDetailViewModel.class);

        binding.setVm(viewModel);
        return binding.getRoot();
    }

    private void initArguments() {
        if (getArguments().containsKey(ARG_STEP)) {
            step = getArguments().getParcelable(ARG_STEP);
        } else {
            throw new IllegalStateException("step == null");
        }
    }

    @BindingAdapter({"imageUrl"})
    public static void setImageUrl(ImageView view, String imageUrl) {
        if (imageUrl == null || imageUrl.isEmpty()) {
            view.setVisibility(View.GONE);
            return;
        }
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(view.getContext().getResources().getDrawable(R.drawable.ic_image_24dp))
                .error(view.getContext().getResources().getDrawable(R.drawable.ic_broken_image_24dp))
                .into(view);
    }

    @BindingAdapter({"videoUrl"})
    public static void setVideoUrl(SimpleExoPlayerView view, String videoUrl) {
        if (videoUrl == null || videoUrl.isEmpty()) {
            view.setVisibility(View.GONE);
        }
    }

    @BindingAdapter("layout_height")
    public static void setLayoutHeight(View view, boolean orientationLandscape) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = orientationLandscape ? RelativeLayout.LayoutParams.MATCH_PARENT : 600;
        view.setLayoutParams(layoutParams);
    }

    private void initMediaSession() {
        mediaSession = new MediaSessionCompat(getContext(), StepDetailFragment.class.getSimpleName());
        mediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
        mediaSession.setMediaButtonReceiver(null);
        stateBuilder = new PlaybackStateCompat.Builder().setActions(
                PlaybackStateCompat.ACTION_PLAY |
                        PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                        PlaybackStateCompat.ACTION_PLAY_PAUSE);

        mediaSession.setPlaybackState(stateBuilder.build());
        mediaSession.setCallback(new MediaSessionCompat.Callback() {
            @Override
            public void onPlay() {
                exoPlayer.setPlayWhenReady(true);
            }

            @Override
            public void onPause() {
                exoPlayer.setPlayWhenReady(false);
            }

            @Override
            public void onSkipToPrevious() {
                exoPlayer.seekTo(0);
            }
        });
        mediaSession.setActive(true);
    }


    private void initExoPlayer(Uri mediaUri) {
        if (exoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);
            binding.stepVideo.setPlayer(exoPlayer);
            exoPlayer.addListener(this);
            String userAgent = Util.getUserAgent(getContext(), getString(R.string.app_name));
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
            exoPlayer.seekTo(viewModel.playerPosition.get());
        }
    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            viewModel.playerPosition.set(exoPlayer.getCurrentPosition());
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }

        if (mediaSession != null) {
            mediaSession.setActive(false);
        }
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if ((playbackState == Player.STATE_READY) && playWhenReady) {
            stateBuilder.setState(PlaybackStateCompat.STATE_PLAYING,
                    exoPlayer.getCurrentPosition(), 1f);
        } else if ((playbackState == Player.STATE_READY)) {
            stateBuilder.setState(PlaybackStateCompat.STATE_PAUSED,
                    exoPlayer.getCurrentPosition(), 1f);
        }
        mediaSession.setPlaybackState(stateBuilder.build());
    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }

    @Override
    public void onResume() {
        super.onResume();
        String url = viewModel.step.get().getVideoURL();
        if (url != null && !url.isEmpty()) {
            viewModel.orientationLandscape.set(
                    getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE
                            && !getResources().getBoolean(R.bool.isTablet)
            );
            initMediaSession();
            initExoPlayer(Uri.parse(viewModel.step.get().getVideoURL()));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }
}
