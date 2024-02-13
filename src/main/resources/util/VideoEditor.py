import sys
import os
import argparse
import ffmpeg
from moviepy.editor import VideoFileClip, concatenate_videoclips

def concatenate_videos(output_path,video_paths):
    if not video_paths:
        print("적어도 하나 이상의 비디오 파일 경로를 제공해야 합니다.")
        return

    try:
        video_clips = [(VideoFileClip(video_path).resize(height=720)).set_fps(30) for video_path in video_paths]
        final_clip = concatenate_videoclips(video_clips)
        final_clip.write_videofile(output_path, codec="libx264", ffmpeg_params=['-c:v', 'libx264', '-crf', '18'])

    except Exception as e:
        print(f"오류 발생: {e}")
        delete_videos(video_paths)

def convert_to_mp4(*video_paths):
    converted_files = []
    for video_path in video_paths:
        filename, file_extension = os.path.splitext(video_path)
        output_file = f"{filename}.mp4"
        try:
            ffmpeg.input(video_path).output(output_file).run()
            print(f"File converted successfully: {output_file}")
            converted_files.append(output_file)
        except ffmpeg.Error as e:
            print(f"Error occurred: {e.stderr}")
            return
    return converted_files


def delete_videos(*video_paths):
    for video_path in video_paths:
        os.remove(video_path)
        # print(f"{video_path}를 삭제했습니다.")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Concatenate videos and optionally delete input videos.")
    parser.add_argument("output_video", help="Output video file path")
    parser.add_argument("--delete", action="store_true", help="Delete input videos after concatenation")
    parser.add_argument("video_paths", nargs="+", help="Paths of input videos to be concatenated")
    args = parser.parse_args()

    converted_files = convert_to_mp4(*args.video_paths)
    concatenate_videos(args.output_video, converted_files)

    if args.delete:
        delete_videos(*args.video_paths)

    print(f"영상이 성공적으로 합쳐졌습니다. 결과 파일: {args.output_video}")

# python VideoEditor.py ./movie/final.mp4 --delete ./videos/0001.mp4 ./videos/0002.mp4 ./videos/0003.mp4 ./videos/0004.mp4