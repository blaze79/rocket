package org.slientpom.rocket.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.videoio.VideoWriter;
import org.slientpom.rocket.domain.geom.FlyTrack;
import org.slientpom.rocket.domain.geom.PursitTrack;
import org.slientpom.rocket.javafx.utils.FxUtils;
import org.slientpom.rocket.model.PursitFlightModel;
import org.slientpom.rocket.model.SingleFlightModel;
import org.slientpom.rocket.model.impl.*;
import org.slientpom.rocket.opencv.ModelRenderer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vlad on 27.06.2018.
 */
public class XFController {
    // the FXML button
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button buttonProp;

    // the FXML image view
    @FXML
    private ImageView currentFrame;

    private ScheduledExecutorService timer;

    @FXML
    protected void run1Flight(ActionEvent event) {
        PursitFlightModel model = new StupidRocketModel();
        PursitTrack pursitTrack = model.generateFlight();

        renderFilm(pursitTrack);
    }

    @FXML
    protected void run2Flight(ActionEvent event) {
        PursitFlightModel model = new BangBangRocketModel();
        PursitTrack pursitTrack = model.generateFlight();

        renderFilm(pursitTrack);
    }

    @FXML
    protected void runPropFlight(ActionEvent event) {
        PursitFlightModel model = new PropRocketModel();
        PursitTrack pursitTrack = model.generateFlight();

        renderFilm(pursitTrack);
    }

    @FXML
    protected void run3Flight(ActionEvent event) {
        PursitFlightModel model = new HeadToHeadRocketModel();
        PursitTrack pursitTrack = model.generateFlight();

        renderFilm(pursitTrack);
    }

    @FXML
    protected void runPropHeadFlight(ActionEvent event) {
        PursitFlightModel model = new HeadToHeadPropRocketModel();
        PursitTrack pursitTrack = model.generateFlight();

        renderFilm(pursitTrack);
    }



    private void renderOneFrame(PursitTrack pursitTrack) {
        ModelRenderer renderer = new ModelRenderer();
        Mat frame = renderer.renderPursit(pursitTrack);

        Image imageToShow = FxUtils.mat2Image(frame);
        FxUtils.onFXThread(currentFrame.imageProperty(), imageToShow);
    }

    private void renderFilm(PursitTrack pursitTrack) {
        ModelRenderer renderer = new ModelRenderer();

        double fps = 20;
        double msInFrame = (int)Math.floor(1000/fps + 0.5);

        double timeStep = pursitTrack.getTimeStep();
        double stepPerS = 1.0 / timeStep;
        double stepsPerFrame = stepPerS / fps;
        int steps = Math.max( (int)Math.floor(stepsPerFrame + 0.5), 1);

        if(timer == null) {
            timer = Executors.newSingleThreadScheduledExecutor();
        }

        VideoWriter writer = new VideoWriter(
                String.format("out_%d.avi", System.currentTimeMillis() / 1000),
                // -1,
                VideoWriter.fourcc('M', 'J', 'P', 'G'),
                fps,
                new Size(900, 600)
        );

        if (!writer.isOpened()) {
            writer = null;
        }

        timer.scheduleAtFixedRate(new Film(renderer, steps, pursitTrack, writer), 0, (int)msInFrame, TimeUnit.MILLISECONDS );
    }

    public class Film implements Runnable {
        private ModelRenderer renderer;
        private int steps;
        private int currentStep;
        private PursitTrack pursitTrack;
        private VideoWriter writer;

        public Film(ModelRenderer renderer, int steps, PursitTrack pursitTrack, VideoWriter writer) {
            this.renderer = renderer;
            this.steps = steps;
            this.pursitTrack = pursitTrack;
            this.currentStep = 1;
            this.writer = writer;
        }

        @Override
        public void run() {
            Mat frame = renderer.renderPursitFrame(pursitTrack, currentStep);
            Image imageToShow = FxUtils.mat2Image(frame);
            FxUtils.onFXThread(currentFrame.imageProperty(), imageToShow);

            if (writer != null) {
                writer.write(frame);
            }

            if (currentStep > pursitTrack.getRocket().getMaxTime()) {
                if (writer != null) {
                    writer.release();
                }
                throw new RuntimeException("Film should be stopped");
            }
            currentStep += steps;
        }
    }


    private void oldFlight() {
        SingleFlightModel model = new FirstGModel();
        FlyTrack track = model.generateFlight();

        ModelRenderer renderer = new ModelRenderer();
        Mat frame = renderer.render(track);

        Image imageToShow = FxUtils.mat2Image(frame);
        FxUtils.onFXThread(currentFrame.imageProperty(), imageToShow);
    }

    protected void setClosed() {
        if (this.timer != null && !this.timer.isShutdown()) {
            try {
                // stop the timer
                this.timer.shutdown();
                this.timer.awaitTermination(200, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                // log any exception
                System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
            }
        }
    }

}
