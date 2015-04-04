package name.icoder.marytts;

import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.modules.synthesis.Voice;
import marytts.util.data.audio.AudioPlayer;

import javax.sound.sampled.AudioInputStream;
import java.util.Locale;
import java.util.Set;

/**
 * Created by evilgod528 on 15/3/30.
 */
public class demo {
    private static MaryInterface marytts;
    private static AudioPlayer player;
    static {
        try {
            System.setProperty("mary.base","/Users/****/Documents/build/idea/everyIT/maryTTS");
            marytts = new LocalMaryInterface();
            Set<String> voices = marytts.getAvailableVoices();
            marytts.setVoice(voices.iterator().next());

            player = new AudioPlayer();
        } catch (MaryConfigurationException e) {
            e.printStackTrace();
        }
    }
    //不能连续的播放声音
    public static void main(String[] args) {
        //英语
        marytts.setLocale(Locale.US);
        speakText("Good Morning");
        //法语
//        marytts.setLocale(Locale.FRENCH);
//        Voice voice = Voice.getVoice(Locale.FRENCH, new Voice.Gender("male"));
//        marytts.setVoice(voice.getName());
        // marytts.setVoice("upmc-pierre-hsmm");
//        System.out.println(marytts.getVoice());
//        System.out.println(marytts.getVoice());
//
//        speakText("Bonjour");
        //德语
//        marytts.setLocale(Locale.GERMAN);
//        speakText("gutten Morgen");
    }

    public static void speakText(String text){

        if(marytts==null){
            throw new RuntimeException("声音合成器初始化失败，无法合成声音");
        }
        try {
            AudioInputStream audio = marytts.generateAudio(text);
            player.setAudio(audio);
            player.start();
            player.join();
        } catch (SynthesisException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void speakText(AudioInputStream audio){
        if(marytts==null){
            throw new RuntimeException("声音合成器初始化失败，无法合成声音");
        }else if (audio==null){
            throw new RuntimeException("声音文件不存在");
        }
        try {
            player.setAudio(audio);
            player.start();
            player.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static AudioInputStream getTextVoice(String text){
        AudioInputStream audio = null;
        if(marytts==null){
            throw new RuntimeException("声音合成器初始化失败，无法合成声音");
        }
        try {
            audio = marytts.generateAudio(text);
        } catch (SynthesisException e) {
            e.printStackTrace();
        }
        return audio;
    }
}
