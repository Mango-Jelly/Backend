import os
import sys
import time
import ffmpeg
import cv2
from moviepy.editor import VideoFileClip, AudioFileClip

def convert_webm_to_webm(video_path, output_file):
    cap = cv2.VideoCapture(video_path)

    if not cap.isOpened():
        print("Error: Unable to open video file")
        return
    fourcc = cv2.VideoWriter_fourcc(*'VP80') 
    fps = 28
    width = int(cap.get(cv2.CAP_PROP_FRAME_WIDTH))
    height = int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT))
    out = cv2.VideoWriter(output_file, fourcc, fps, (width, height))

    prev_time = 0

    while True:
        ret, frame = cap.read()
        current_time = time.time() - prev_time
        if not ret:
            break
        if current_time > 1./fps:
            prev_time = time.time()
            out.write(frame)

    cap.release()
    out.release()


def convert_to_mp4(video_path,output_file):
    try:
        ffmpeg.input(video_path).output(output_file).run()
        print(f"File converted successfully: {output_file}")
    except ffmpeg.Error as e:
        print(f"Error occurred: {e.stderr}")
        return

def merge_audio_and_video(video_path, audio_path, output_file):
    video_clip = VideoFileClip(video_path)
    audio_clip = AudioFileClip(audio_path,fps=30000)

    video_clip = video_clip.set_audio(audio_clip)

    video_clip.write_videofile(output_file, codec='libx264', audio_codec='aac')

    video_clip.close()
    audio_clip.close()

def convert_audio(audio_path,output_file):
    ffmpeg.input(audio_path).output(output_file, codec='libmp3lame').run()

def delete_videos(file_path):
    try:
        os.remove(file_path)
    except Exception as e:
        print("Error occurred while deleting original files:", e)

if __name__ == "__main__":
    if len(sys.argv) < 3:
        print("Usage: python script.py <video_file> <audio_file> [--delete]")
        sys.exit(1)

    video_path = sys.argv[1]
    audio_path = sys.argv[2]
    
    filename, file_extension = os.path.splitext(video_path)
    converted_webm_file=f"{filename}_converted.webm"
    converted_mp4_file=f"{filename}_converted.mp4"
    converted_mp3_file=f"{filename}_converted.mp3"
    output_file = f"{filename}.mp4"

    convert_webm_to_webm(video_path,converted_webm_file)
    convert_to_mp4(converted_webm_file,converted_mp4_file)
    convert_audio(audio_path,converted_mp3_file)
    merge_audio_and_video(converted_mp4_file, converted_mp3_file, output_file)
    
    if "--delete" in sys.argv:
        delete_videos(converted_webm_file)
        delete_videos(converted_mp4_file)
        delete_videos(converted_mp3_file)
        delete_videos(video_path)
        delete_videos(audio_path)

# python VideoConverter.py ./movie/0004.webm ./movie/0004.mp3 --delete