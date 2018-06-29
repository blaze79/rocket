package org.slientpom.rocket.opencv;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.slientpom.rocket.domain.geom.Fly;
import org.slientpom.rocket.domain.geom.FlyTrack;
import org.slientpom.rocket.domain.geom.PursitTrack;
import org.slientpom.rocket.domain.geom.Vector;

import java.util.List;

import static org.opencv.core.CvType.CV_8UC3;

/**
 * Created by Vlad on 28.06.2018.
 */
public class ModelRenderer {
    private int imageWith = 900;
    private int imageHeight = 600;
    private Scalar backColor = new Scalar(235, 206, 135);
    private Scalar objectColor = new Scalar(0, 0, 0);
    private Scalar rocketColor = new Scalar(0, 0, 255);

    private org.slientpom.rocket.domain.geom.Point nePoint = org.slientpom.rocket.domain.geom.Point.point(-1500, 1000);
    private org.slientpom.rocket.domain.geom.Point swPoint = org.slientpom.rocket.domain.geom.Point.point(1500, -1000);

    public int getImageWith() {
        return imageWith;
    }

    public void setImageWith(int imageWith) {
        this.imageWith = imageWith;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    private Mat createImage() {
        Mat image = new Mat(imageHeight, imageWith, CV_8UC3);
        image.setTo(backColor);
        return image;
    }

    public Mat render(FlyTrack track) {
        Mat image = createImage();

        List<Fly> path = track.getTrack();
        drawTrack(image, path, objectColor);

        return image;
    }

    private void drawTrack(Mat image,  List<Fly> path, Scalar color) {
        for(int i=0; i<path.size() - 1; i++) {
            Fly current = path.get(i);
            Fly next = path.get(i+1);

            Imgproc.line(
                    image,
                    transformPoint(current.getPoint()),
                    transformPoint(next.getPoint()),
                    color,
                    1
            );
        }
    }

    public Mat renderPursit(PursitTrack track) {
        Mat image = createImage();

        drawTrack(
                image,
                track.getTarget().getTrack(),
                objectColor
        );

        drawTrack(
                image,
                track.getRocket().getTrack(),
                rocketColor
        );

        return image;
    }

    private Point transformPoint(org.slientpom.rocket.domain.geom.Point point) {
        Vector area = nePoint.vectorTo(swPoint);
        Vector moved = nePoint.vectorTo(point);

        return new Point(
                moved.getX()/area.getX()*imageWith,
                moved.getY()/area.getY()*imageHeight
        );
    }
}
