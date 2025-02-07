package test_dz_1;

public class MusicPlayer implements Playable{
    @Override
    public void play() {
        System.out.println("Играет музяка");
    }

    @Override
    public void pause() {
        System.out.println("Музяка на паузе");
    }
}
