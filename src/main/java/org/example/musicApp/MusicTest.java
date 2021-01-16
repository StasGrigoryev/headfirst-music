package org.example.musicApp;

import javax.sound.midi.*;

public class MusicTest {

    public void play() {
        try {
            Sequencer player = MidiSystem.getSequencer();
            System.out.println("Now we've got a sequencer");
            player.open();
            System.out.println("Sequencer is opened");
            Sequence seq = new Sequence(Sequence.PPQ,4);
            System.out.println("Sequence object created");
            Track t = seq.createTrack();

            ShortMessage b = new ShortMessage();
            b.setMessage(192, 1, 50, 0); // change instrument
            MidiEvent changeInstrument = new MidiEvent(b, 1);
            t.add(changeInstrument);

            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, 75, 100);
            MidiEvent noteOn = new MidiEvent(a, 1);
            t.add(noteOn);

            ShortMessage c = new ShortMessage();
            c.setMessage(128, 1, 75, 100);
            MidiEvent noteOff = new MidiEvent(c, 16);
            t.add(noteOff);

            player.setSequence(seq);
            player.start();
        }
        catch (MidiUnavailableException | InvalidMidiDataException e) {
            System.out.println(e + "");
        }
    }

    public static void main(String[] args) {
        MusicTest mt = new MusicTest();
        mt.play();
    }
}
