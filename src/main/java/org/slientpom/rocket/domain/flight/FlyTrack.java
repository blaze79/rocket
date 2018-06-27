package org.slientpom.rocket.domain.flight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad on 27.06.2018.
 */
public class FlyTrack {
    private List<Fly> track = new ArrayList<>();

    public List<Fly> getTrack() {
        return track;
    }

    public void push(Fly fly) {
        track.add(fly);
    }

    public int getMaxTime() {
        return track.size();
    }

    public List<Fly> getTrackForTime(int t) {
        return track.subList(0, Math.min(t, getMaxTime()));
    }
}
