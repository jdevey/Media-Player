package cs2410.assn7.view;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

/**
 * This class contains a video viewer, which can be used to view several video formats. It features buttons, menu items,
 * event handlers, listeners, etc...
 *
 * @author devey
 * @version 1.0
 */
public class UserInterface extends Application {

    /**
     * Stage that the application uses
     */
    private Stage primaryStage;

    /**
     * Pane that the application takes place in
     */
    private BorderPane borderPane = new BorderPane();

    /**
     * Menu bar at the top
     */
    private MenuBar menuBar = new MenuBar();
    /**
     * Deals with files
     */
    private Menu fileMenu = new Menu("File");
    /**
     * Extra information
     */
    private Menu helpMenu = new Menu("Help");
    /**
     * Opens files
     */
    private MenuItem opnMenuItem = new MenuItem("Open");
    /**
     * Close video
     */
    private MenuItem clsMenuItem = new MenuItem("Close");
    /**
     * Exit app
     */
    private MenuItem extMenuItem = new MenuItem("Exit");
    /**
     * Does nothing
     */
    private MenuItem docMenuItem = new MenuItem("Documentation");
    /**
     * Does nothing
     */
    private MenuItem abtMenuItem = new MenuItem("About");

    /**
     * At the bottom
     */
    private HBox hBox = new HBox();
    /**
     * Plays
     */
    private Button playBtn = new Button("Play");
    /**
     * Stops
     */
    private Button stopBtn = new Button("Stop");
    /**
     * Volume
     */
    private Slider volSlider = new Slider();
    /**
     * Time
     */
    private Slider timeSlider = new Slider();
    /**
     * Volume label
     */
    private Label volLabel = new Label("Volume: ");
    /**
     * Time label
     */
    private Label timeLabel = new Label("Time :");

    /**
     * Chooses files
     */
    private FileChooser fileChooser = new FileChooser();
    /**
     * Text for the current file
     */
    private Text fileText = new Text("");

    /**
     * icon
     */
    private Image aIcon = new Image("http://cdn.mysitemyway.com/etc-mysitemyway/icons/legacy-previews/icons/glossy-black-3d-buttons-icons-alphanumeric/070542-glossy-black-3d-button-icon-alphanumeric-letter-aa.png");
    /**
     * icon
     */
    private Image backIcon = new Image("https://image.flaticon.com/icons/png/512/0/340.png");
    /**
     * icon
     */
    private Image closeIcon = new Image("https://d30y9cdsu7xlg0.cloudfront.net/png/52944-200.png");
    /**
     * icon
     */
    private Image dIcon = new Image("http://cdn.mysitemyway.com/etc-mysitemyway/icons/legacy-previews/icons/glossy-black-3d-buttons-icons-alphanumeric/070548-glossy-black-3d-button-icon-alphanumeric-letter-dd.png");
    /**
     * icon
     */
    private Image pauseIcon = new Image("http://www.myiconfinder.com/uploads/iconsets/256-256-7f94cbb88c4da5c5d4660a475d0d33b6.png");
    /**
     * icon
     */
    private Image playIcon = new Image("http://downloadicons.net/sites/default/files/play-icon-93568.png");
    /**
     * icon
     */
    private Image restartIcon = new Image("https://d30y9cdsu7xlg0.cloudfront.net/png/22762-200.png");
    /**
     * icon
     */
    private Image uploadIcon = new Image("https://image.freepik.com/free-icon/upload-arrow_318-26670.jpg");

    /**
     * Corresponding image view
     */
    private ImageView aIconV = new ImageView(aIcon);
    /**
     * Corresponding image view
     */
    private ImageView backIconV = new ImageView(backIcon);
    /**
     * Corresponding image view
     */
    private ImageView closeIconV = new ImageView(closeIcon);
    /**
     * Corresponding image view
     */
    private ImageView dIconV = new ImageView(dIcon);
    /**
     * Corresponding image view
     */
    private ImageView pauseIconV = new ImageView(pauseIcon);
    /**
     * Corresponding image view
     */
    private ImageView playIconV = new ImageView(playIcon);
    /**
     * Corresponding image view
     */
    private ImageView restartIconV = new ImageView(restartIcon);
    /**
     * Corresponding image view
     */
    private ImageView uploadIconV = new ImageView(uploadIcon);

    /**
     * media
     */
    private Media media;
    /**
     * media player
     */
    private MediaPlayer player;
    /**
     * media player
     */
    private MediaView mediaView = new MediaView();
    /**
     * Where the videos go
     */
    private StackPane videoHolder = new StackPane();
    /**
     * Whether the video is playing
     */
    private boolean isPlaying = false;
    /**
     * Whether the video is at the start
     */
    private boolean isAtStart = true;

    /**
     * Gets the application up and running
     * @param primaryStage typical
     */
    public void start(Stage primaryStage) {

        //Menu bar stuff
        fileMenu.getItems().addAll(opnMenuItem, clsMenuItem, extMenuItem);
        helpMenu.getItems().addAll(docMenuItem, abtMenuItem);
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        borderPane.setTop(menuBar);

        //Icon stuff
        aIconV.setFitWidth(15); aIconV.setFitHeight(15);
        backIconV.setFitWidth(15); backIconV.setFitHeight(15);
        closeIconV.setFitWidth(15); closeIconV.setFitHeight(15);
        dIconV.setFitWidth(15); dIconV.setFitHeight(15);
        pauseIconV.setFitWidth(15); pauseIconV.setFitHeight(15);
        playIconV.setFitWidth(15); playIconV.setFitHeight(15);
        restartIconV.setFitWidth(15); restartIconV.setFitHeight(15);
        uploadIconV.setFitWidth(15); uploadIconV.setFitHeight(15);

        opnMenuItem.setGraphic(uploadIconV);
        clsMenuItem.setGraphic(closeIconV);
        extMenuItem.setGraphic(backIconV);
        docMenuItem.setGraphic(dIconV);
        abtMenuItem.setGraphic(aIconV);
        stopBtn.setGraphic(restartIconV);
        playBtn.setGraphic(playIconV);

        //hBox stuff
        hBox.getChildren().addAll(playBtn, stopBtn, volLabel, volSlider, timeLabel, timeSlider);
        borderPane.setBottom(hBox);
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.BOTTOM_CENTER);

        //Media stuff
        mediaView.setMediaPlayer(player);
        borderPane.setCenter(videoHolder);
        videoHolder.setAlignment(Pos.CENTER);
        videoHolder.setPrefSize(800, 500);
        mediaView.setFitHeight(500);
        mediaView.setFitWidth(800);
        mediaView.setPreserveRatio(true);


        //Slider stuff
        volSlider.setValue(100);

        //Stuff for the whole stage
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        this.primaryStage = primaryStage;
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.setTitle("Video Player");

        //setting all the handlers and listeners
        initHandlers();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "I have added icons to both the MenuItems and the Play/Pause/Stop buttons.");
        alert.setTitle("Bonus Features");
        alert.showAndWait();
    }

    /**
     * Initializes handlers
     */
    private void initHandlers() {
        opnMenuItem.setOnAction(this::initFileChooser);
        clsMenuItem.setOnAction(this::initCloseMedia);
        extMenuItem.setOnAction(this::initExitApp);
        //docMenuItem and abtMenuItem don't really have to do anything.
    }

    /**
     * Initializes the file chooser
     * @param e event
     */
    private void initFileChooser(ActionEvent e) {
        fileChooser.setTitle("Video File Chooser");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("mp4", "*.mp4"),
                new FileChooser.ExtensionFilter("m4v", "*.m4v"),
                new FileChooser.ExtensionFilter("m4a", "*.m4a")
        );
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null) fileText.setText(selectedFile.toString());
        media = new Media(new File(fileText.getText()).toURI().toString());
        player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        videoHolder.getChildren().addAll(mediaView);
        playBtn.setDisable(false);
        stopBtn.setDisable(false);
        player.setVolume(100);
        initBind();
        player.setOnEndOfMedia(() -> {
            isPlaying = false;
            player.seek(player.getStartTime());
            player.pause();
            timeSlider.setValue(0);
            playBtn.setText("Play");
            playBtn.setGraphic(playIconV);
            isAtStart = true;
        });
        playBtn.setOnAction(this::initPlayBtn);
        stopBtn.setOnAction(this::initStopBtn);

        initVolumeListener();
        initTimeListener();
    }

    /**
     * What happens when media closes
     * @param e event
     */
    private void initCloseMedia(ActionEvent e) {
        //idk how to close the video
        videoHolder.getChildren().clear();
        player.dispose();
        playBtn.setText("Play");
        playBtn.setGraphic(playIconV);
        isPlaying = false;
        playBtn.setDisable(true);
        stopBtn.setDisable(true);
    }

    /**
     * What happens when the app closes
     * @param e event
     */
    private void initExitApp(ActionEvent e) {
        primaryStage.close();
    }

    /**
     * What happens when the play button is pressed
     * @param e event
     */
    private void initPlayBtn(ActionEvent e) {
        //Play the video
        isPlaying = !isPlaying;
        isAtStart = false;
        if (isPlaying) {player.play(); playBtn.setText("Pause"); playBtn.setGraphic(pauseIconV);}
        else {player.pause(); playBtn.setText("Play"); playBtn.setGraphic(playIconV);}
    }

    /**
     * What happens when the stop button is pressed
     * @param e event
     */
    private void initStopBtn(ActionEvent e) {
        //Stop the video
        player.pause();
        player.seek(player.getStartTime());
        playBtn.setText("Play");
        playBtn.setGraphic(pauseIconV);
        isPlaying = false;
        isAtStart = true;
    }

    /**
     * What happens when the volume slider is changed
     */
    private void initVolumeListener() {
        volSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                player.setVolume((double)newValue / 100);
                if (volSlider.isValueChanging()) player.setVolume((double)newValue / 100);
            }
        });
    }

    /**
     * How the time slider changes
     */
    private void initTimeListener() {
        timeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (timeSlider.isValueChanging()) {
                    player.seek(player.getMedia().getDuration().multiply(newValue.doubleValue() / 100.0));
                }
                /*
                player.seek(player.getMedia().getDuration().multiply(timeSlider.getValue() / 100.0));
                if (volSlider.isValueChanging()) player.seek(player.getMedia().getDuration().multiply(timeSlider.getValue() / 100.0));
                */
            }
        });
    }

    /**
     * Making the slider move with the video
     */
    private void initBind() {
        player.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!timeSlider.isValueChanging()) {
                timeSlider.setValue((newTime.toSeconds()/player.getMedia().getDuration().toSeconds()) * 100);
                if (isAtStart) timeSlider.setValue(0);
            }
        });
    }
}
