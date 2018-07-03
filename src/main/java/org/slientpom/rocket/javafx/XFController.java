package org.slientpom.rocket.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.opencv.core.Mat;
import org.slientpom.rocket.domain.geom.FlyTrack;
import org.slientpom.rocket.domain.geom.PursitTrack;
import org.slientpom.rocket.javafx.utils.FxUtils;
import org.slientpom.rocket.model.PursitFlightModel;
import org.slientpom.rocket.model.SingleFlightModel;
import org.slientpom.rocket.model.impl.BangBangRocketModel;
import org.slientpom.rocket.model.impl.FirstGModel;
import org.slientpom.rocket.model.impl.HeadToHeadRocketModel;
import org.slientpom.rocket.model.impl.StupidRocketModel;
import org.slientpom.rocket.opencv.ModelRenderer;

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
    // the FXML image view
    @FXML
    private ImageView currentFrame;

    @FXML
    protected void run1Flight(ActionEvent event) {
        PursitFlightModel model = new StupidRocketModel();
        PursitTrack pursitTrack = model.generateFlight();

        ModelRenderer renderer = new ModelRenderer();
        Mat frame = renderer.renderPursit(pursitTrack);

        Image imageToShow = FxUtils.mat2Image(frame);
        FxUtils.onFXThread(currentFrame.imageProperty(), imageToShow);
    }

    @FXML
    protected void run2Flight(ActionEvent event) {
        PursitFlightModel model = new BangBangRocketModel();
        PursitTrack pursitTrack = model.generateFlight();

        ModelRenderer renderer = new ModelRenderer();
        Mat frame = renderer.renderPursit(pursitTrack);

        Image imageToShow = FxUtils.mat2Image(frame);
        FxUtils.onFXThread(currentFrame.imageProperty(), imageToShow);
    }

    @FXML
    protected void run3Flight(ActionEvent event) {
        PursitFlightModel model = new HeadToHeadRocketModel();
        PursitTrack pursitTrack = model.generateFlight();

        ModelRenderer renderer = new ModelRenderer();
        Mat frame = renderer.renderPursit(pursitTrack);

        Image imageToShow = FxUtils.mat2Image(frame);
        FxUtils.onFXThread(currentFrame.imageProperty(), imageToShow);
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

    }

}
