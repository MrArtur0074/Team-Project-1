package test_dz_1;

public class VideoPlayer implements Playable{
    @Override
    public void play() {
        System.out.println("Играет видео");
    }

    @Override
    public void pause() {
        System.out.println("Видео на паузе");
    }
}
