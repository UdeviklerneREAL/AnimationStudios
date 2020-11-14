package com.udeGames.animationStudio.ffmpeg;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;

public class FFmpegUtils {
    private static FFmpeg ffmpeg;
    private static FFprobe ffprobe;

    public static FFmpeg getFfmpeg() {
        return ffmpeg;
    }

    public static void setFfmpeg(FFmpeg ffmpeg) {
        FFmpegUtils.ffmpeg = ffmpeg;
    }

    public static FFprobe getFfprobe() {
        return ffprobe;
    }

    public static void setFfprobe(FFprobe ffprobe) {
        FFmpegUtils.ffprobe = ffprobe;
    }
}
